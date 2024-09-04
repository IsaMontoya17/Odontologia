package com.digitalhouse.odontologia.service;

import com.digitalhouse.odontologia.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardar(Odontologo odontologo);
    Odontologo buscarPorId(Long id);
    void eliminar(Long id);
    Odontologo actualizar(Odontologo odontologo);
    List<Odontologo> listar();
}
