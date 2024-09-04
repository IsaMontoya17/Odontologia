package com.digitalhouse.odontologia.service.impl;

import com.digitalhouse.odontologia.entity.Odontologo;
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

    @Autowired
    ObjectMapper mapper;

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(id);
        if (odontologoEncontrado.isPresent()) {
            return mapper.convertValue(odontologoEncontrado, Odontologo.class);
        }
        return odontologoEncontrado.get();
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
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
