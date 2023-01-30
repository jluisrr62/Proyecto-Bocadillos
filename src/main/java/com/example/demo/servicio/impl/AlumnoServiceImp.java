package com.example.demo.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.example.demo.modelo.Alumno;
import com.example.demo.servicio.interfaces.AlumnoService;
import com.example.demo.persistencia.AlumnoRepository;

public class AlumnoServiceImp implements AlumnoService{

	@Autowired
	public AlumnoRepository alumnoRepo;
	
	@Override
	public Alumno insertarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		return alumnoRepo.save(alumno);
	}

	@Override
	public List<Alumno> listarAlumnos() {
		// TODO Auto-generated method stub
		return alumnoRepo.findAll();
	}

	@Override
	public Alumno obtenerAlumnoPorId(Integer id) {
		// TODO Auto-generated method stub
		return alumnoRepo.findById(id).get();
	}


	@Override
	public void eliminarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		alumnoRepo.delete(alumno);
	}

	@Override
	public void eliminarAlumnoPorId(Integer id) {
		
		alumnoRepo.delete(alumnoRepo.findById(id).get());
	}
	
	
}
