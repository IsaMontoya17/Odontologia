package com.digitalhouse.odontologia.service;

import com.digitalhouse.odontologia.entity.Odontologo;
import com.digitalhouse.odontologia.entity.Paciente;

import java.util.Collection;
import java.util.List;

public interface IPacienteService {
    Paciente guardar(Paciente paciente);

    Paciente buscarPorId(Long id);

    void eliminar(Long id);

    Paciente actualizar(Paciente paciente);

    List<Paciente> listar();
}
