package com.digitalhouse.odontologia.service.impl;

import com.digitalhouse.odontologia.entity.Odontologo;
import com.digitalhouse.odontologia.exception.ResourceNotFoundException;
import com.digitalhouse.odontologia.repository.IOdontologoRepository;
import com.digitalhouse.odontologia.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    private IOdontologoService odontologoService;


    @Test
    void testGuardarOdontologo() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Pérez");
        odontologo.setMatricula("12345");

        Odontologo guardado = odontologoService.guardar(odontologo);

        assertNotNull(guardado.getId());
        assertEquals("Juan", guardado.getNombre());
        assertEquals("Pérez", guardado.getApellido());
        assertEquals("12345", guardado.getMatricula());
    }

    @Test
    void testBuscarPorId() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Ana");
        odontologo.setApellido("Gómez");
        odontologo.setMatricula("67890");

        Odontologo guardado = odontologoService.guardar(odontologo);
        Odontologo encontrado = odontologoService.buscarPorId(guardado.getId());

        assertNotNull(encontrado);
        assertEquals(guardado.getId(), encontrado.getId());
        assertEquals("Ana", encontrado.getNombre());
        assertEquals("Gómez", encontrado.getApellido());
        assertEquals("67890", encontrado.getMatricula());
    }

    @Test
    void testEliminarOdontologo() {

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Luis");
        odontologo.setApellido("Martínez");
        odontologo.setMatricula("54321");

        Odontologo guardado = odontologoService.guardar(odontologo);

        odontologoService.eliminar(guardado.getId());

        assertThrows(ResourceNotFoundException.class, () -> {
            odontologoService.buscarPorId(guardado.getId());
        });
    }

    @Test
    void testActualizarOdontologo() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Carlos");
        odontologo.setApellido("Hernández");
        odontologo.setMatricula("11223");

        Odontologo guardado = odontologoService.guardar(odontologo);
        guardado.setNombre("Carlos Updated");
        Odontologo actualizado = odontologoService.actualizar(guardado);

        assertNotNull(actualizado);
        assertEquals("Carlos Updated", actualizado.getNombre());
    }

}