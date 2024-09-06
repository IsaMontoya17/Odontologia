package com.digitalhouse.odontologia.service;

import com.digitalhouse.odontologia.entity.Domicilio;
import com.digitalhouse.odontologia.entity.Odontologo;
import com.digitalhouse.odontologia.exception.ResourceNotFoundException;

import java.util.List;

public interface IDomicilioService {
    Domicilio guardar(Domicilio domicilio);
    Domicilio buscarPorId(Long id)throws ResourceNotFoundException;
    void eliminar(Long id);
    Domicilio actualizar(Domicilio domicilio);
    List<Domicilio> listar();
}
