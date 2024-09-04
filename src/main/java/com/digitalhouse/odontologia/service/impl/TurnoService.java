package com.digitalhouse.odontologia.service.impl;

import com.digitalhouse.odontologia.entity.Odontologo;
import com.digitalhouse.odontologia.entity.Paciente;
import com.digitalhouse.odontologia.entity.Turno;
import com.digitalhouse.odontologia.repository.IOdontologoRepository;
import com.digitalhouse.odontologia.repository.IPacienteRepository;
import com.digitalhouse.odontologia.repository.ITurnoRepository;
import com.digitalhouse.odontologia.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoService implements ITurnoService {
    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    public Turno registrarTurno(Long odontologoId, Long pacienteId, LocalDate fecha, LocalTime hora) {
        // Verificar si el odontólogo existe
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(odontologoId);
        if (odontologoOptional.isEmpty()) {
            throw new IllegalArgumentException("Odontólogo no encontrado con ID: " + odontologoId);
        }

        // Verificar si el paciente existe
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(pacienteId);
        if (pacienteOptional.isEmpty()) {
            throw new IllegalArgumentException("Paciente no encontrado con ID: " + pacienteId);
        }

        // Verificar si ya existe un turno para el odontólogo en la misma fecha y hora
        List<Turno> todosLosTurnos = turnoRepository.findAll();  // Recuperar todos los turnos
        List<Turno> turnosDelOdontologo = todosLosTurnos.stream()
                .filter(turno -> turno.getOdontologo().getId().equals(odontologoId) && turno.getFecha().equals(fecha))
                .collect(Collectors.toList());

        boolean existeTurno = turnosDelOdontologo.stream()
                .anyMatch(turno -> turno.getHora().equals(hora));

        if (existeTurno) {
            throw new IllegalArgumentException("Turno no disponible. El odontólogo ya tiene un turno asignado en esa fecha y hora.");
        }

        // Crear el nuevo turno
        Turno nuevoTurno = new Turno();
        nuevoTurno.setOdontologo(odontologoOptional.get());
        nuevoTurno.setPaciente(pacienteOptional.get());
        nuevoTurno.setFecha(fecha);
        nuevoTurno.setHora(hora);

        // Guardar el turno en la base de datos
        return turnoRepository.save(nuevoTurno);
    }

    public List<Turno> obtenerTodosLosTurnos() {
        return turnoRepository.findAll();
    }

    @Override
    public void eliminarTurno(Long turnoId) {
        if (turnoRepository.existsById(turnoId)) {
            turnoRepository.deleteById(turnoId);
        } else {
            throw new IllegalArgumentException("Turno no encontrado con ID: " + turnoId);
        }
    }
}
