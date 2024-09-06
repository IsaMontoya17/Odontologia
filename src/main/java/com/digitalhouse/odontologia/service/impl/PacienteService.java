package com.digitalhouse.odontologia.service.impl;

import com.digitalhouse.odontologia.entity.Paciente;
import com.digitalhouse.odontologia.exception.ResourceNotFoundException;
import com.digitalhouse.odontologia.repository.IPacienteRepository;
import com.digitalhouse.odontologia.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    IPacienteRepository pacienteRepository;

    @Override
    public Paciente guardar(Paciente paciente) {
        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        logger.info("Paciente guardado con ID: " + pacienteGuardado.getId());
        if (pacienteGuardado.getDomicilio() != null) {
            logger.info("Domicilio guardado con ID: " + pacienteGuardado.getDomicilio().getId());
        } else {
            logger.warn("El domicilio no fue guardado.");
        }

        return pacienteGuardado;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if(pacienteEncontrado.isPresent()) {
            return pacienteEncontrado.get();
        }else{
            throw new ResourceNotFoundException("No se encontro el paciente con ID: " + id);
        }
    }

    @Override
    public void eliminar(Long id) {

        logger.info("Intentando eliminar el paciente con ID: " + id);

        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isPresent()) {
            pacienteRepository.deleteById(id);
            logger.info("Paciente con ID: " + id + " eliminado exitosamente.");
        } else {
            logger.warn("No se encontró ningún paciente con ID: " + id + " para eliminar.");
        }
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        logger.info("Actualizando paciente con ID: " + paciente.getId());

        String domicilioInfo = (paciente.getDomicilio() != null)
                ? "Domicilio ID: " + paciente.getDomicilio().getId()
                : "Domicilio no asignado";

        logger.info("Información del paciente antes de actualizar: " +
                "Paciente ID: " + paciente.getId() + ", " +
                domicilioInfo);

        Paciente pacienteActualizado = pacienteRepository.save(paciente);

        logger.info("Paciente actualizado con ID: " + pacienteActualizado.getId());
        if (pacienteActualizado.getDomicilio() != null) {
            logger.info("Domicilio del paciente actualizado con ID: " + pacienteActualizado.getDomicilio().getId());
        } else {
            logger.warn("El paciente actualizado no tiene domicilio asignado.");
        }

        return pacienteActualizado;
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> todosLosPacientes = pacienteRepository.findAll();

        if (todosLosPacientes.isEmpty()) {
            logger.info("No se encontraron pacientes en la base de datos.");
        } else {
            logger.info("Pacientes: ");
            for (Paciente paciente : todosLosPacientes) {
                String domicilioInfo = (paciente.getDomicilio() != null)
                        ? "Domicilio ID: " + paciente.getDomicilio().getId()
                        : "Domicilio no asignado";

                logger.info("Paciente ID: " + paciente.getId() + ", " + domicilioInfo);
            }
        }

        return todosLosPacientes;
    }

}