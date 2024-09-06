package com.digitalhouse.odontologia.service.impl;

import com.digitalhouse.odontologia.entity.Domicilio;
import com.digitalhouse.odontologia.exception.ResourceNotFoundException;
import com.digitalhouse.odontologia.repository.IDomicilioRepository;
import com.digitalhouse.odontologia.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IDomicilioService {

    @Autowired
    IDomicilioRepository domicilioRepository;

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    @Override
    public Domicilio buscarPorId(Long id) {
        Optional<Domicilio> domicilioEncontrado = domicilioRepository.findById(id);
        if(domicilioEncontrado.isPresent()) {
            return domicilioEncontrado.get();
        }else{
            throw new ResourceNotFoundException("No se encontro el domicilio con ID: " + id);
        }
    }

    @Override
    public void eliminar(Long id) {
        domicilioRepository.deleteById(id);
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    @Override
    public List<Domicilio> listar() {
        List<Domicilio> todosLosDomicilios = domicilioRepository.findAll();
        return todosLosDomicilios;
    }

}
