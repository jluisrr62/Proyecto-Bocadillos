package com.example.demo.servicio.interfaces;

import java.util.List;

import com.example.demo.modelo.Alumno;


public interface AlumnoService {
	public Alumno insertarAlumno (Alumno alumno);
	public List<Alumno> listarAlumnos();
	public Alumno obtenerAlumnoPorId (Integer id);
	public void eliminarAlumno(Alumno alumno);
	public void eliminarAlumnoPorId(Integer id);
}
