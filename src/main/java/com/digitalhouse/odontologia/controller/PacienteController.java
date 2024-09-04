package com.digitalhouse.odontologia.controller;

import com.digitalhouse.odontologia.entity.Paciente;
import com.digitalhouse.odontologia.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        pacienteService.eliminar(id);
        response = ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente pacienteExistente = pacienteService.buscarPorId(id);
        if (pacienteExistente != null) {
            // el id del paciente se mantiene igual
            paciente.setId(id);

            // el id del domicilio se mantiene igual
            if (pacienteExistente.getDomicilio() != null) {
                paciente.getDomicilio().setId(pacienteExistente.getDomicilio().getId());
            }

            Paciente pacienteActualizado = pacienteService.actualizar(paciente);
            return ResponseEntity.ok(pacienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}//CIERRE DE LA CLASE