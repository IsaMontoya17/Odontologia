package com.digitalhouse.odontologia.service;

import com.digitalhouse.odontologia.entity.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ITurnoService {
    Turno registrarTurno (Long odontologoId, Long pacienteId, LocalDate fecha, LocalTime hora);
    public List<Turno> obtenerTodosLosTurnos();
    void eliminarTurno(Long turnoId);
}
