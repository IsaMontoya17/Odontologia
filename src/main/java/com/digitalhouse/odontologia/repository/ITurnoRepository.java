package com.digitalhouse.odontologia.repository;

import com.digitalhouse.odontologia.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

}
