package com.digitalhouse.odontologia.service.impl;

import com.digitalhouse.odontologia.entity.Odontologo;
import com.digitalhouse.odontologia.exception.HandleConflictException;
import com.digitalhouse.odontologia.exception.ResourceNotFoundException;
import com.digitalhouse.odontologia.repository.IOdontologoRepository;
import com.digitalhouse.odontologia.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IOdontologoService {

    @Autowired
    IOdontologoRepository odontologoRepository;

    @Override
    public Odontologo guardar(Odontologo odontologo) throws HandleConflictException {
        Optional<Odontologo> odontologoExistente = odontologoRepository.findByMatricula(odontologo.getMatricula());
        if (odontologoExistente.isPresent()) {
            throw new HandleConflictException("Ya existe un odontólogo con la matrícula: " + odontologo.getMatricula());
        }
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(id);
        if(odontologoEncontrado.isPresent()) {
            return odontologoEncontrado.get();
        }else{
            throw new ResourceNotFoundException("No se encontro el odontologo con ID: " + id);
        }
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se puede borrar el odontologo porque no se encontró el odontólogo con ID: " + id);
        }
    }


    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listar() {
        List<Odontologo> todosLosOdontologos = odontologoRepository.findAll();
        return todosLosOdontologos;
    }

}
