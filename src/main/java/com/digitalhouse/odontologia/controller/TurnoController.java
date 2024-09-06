package com.digitalhouse.odontologia.controller;

import com.digitalhouse.odontologia.entity.Turno;
import com.digitalhouse.odontologia.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<?> registrarTurno(@RequestBody Map<String, Object> request) {
        try {
            Long odontologoId = Long.valueOf(request.get("odontologoId").toString());
            Long pacienteId = Long.valueOf(request.get("pacienteId").toString());
            LocalDate fecha = LocalDate.parse(request.get("fecha").toString());
            LocalTime hora = LocalTime.parse(request.get("hora").toString());

            Turno nuevoTurno = turnoService.registrarTurno(odontologoId, pacienteId, fecha, hora);
            return ResponseEntity.ok(nuevoTurno);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Formato de fecha u hora inválido.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el turno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> obtenerTurnos() {
        List<Turno> turnos = turnoService.obtenerTodosLosTurnos();
        return ResponseEntity.ok(turnos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        try {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado con éxito.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
