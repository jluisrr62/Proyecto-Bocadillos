package com.example.demo.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{

}
