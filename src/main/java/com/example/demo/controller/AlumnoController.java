package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Alumno;
import com.example.demo.persistencia.AlumnoDAO;

@RequestMapping("/alumnos")
@Controller
public class AlumnoController {
	
	AlumnoDAO alumnoDAO = new AlumnoDAO();
	
	//controlador para listar los alumnos y pasar parametros
	@GetMapping(value= {"", "/"})
	String homealumnos(Model model) {
		
		//salir a buscar a la bd
		ArrayList<Alumno> misAlumnos = alumnoDAO.listarAlumnosJPA();
		model.addAttribute("listaAlumnos", misAlumnos);
		//le pasamos un nuevo alumno para poder editar un alumno
		model.addAttribute("alumnoaEditar", new Alumno());
		model.addAttribute("alumnoNuevo", new Alumno());
		
		return "alumnos";
	}	
	
	//controlador para editar un alumno
	@PostMapping("/edit/{id}")
	public String editarAlumno(@PathVariable Integer id, @ModelAttribute("alumnoaEditar") Alumno alumnoEditado, BindingResult bindingresult) {
		
		Alumno alumnoaEditar = alumnoDAO.buscarPorIdJPA(id);
		alumnoaEditar.setNombre(alumnoEditado.getNombre());
		alumnoDAO.modificarAlumnoJPA(alumnoaEditar);
		
		
		return "redirect:/alumnos";
	}
	
	//controlador para eliminar un alumno
	@GetMapping("/delete/{id}")
	public String eliminarAlumno(Model model, @PathVariable Integer id) {
		
		Alumno alumnoaEliminar = alumnoDAO.buscarPorIdJPA(id);
		alumnoDAO.deleteAlumnoJPA(alumnoaEliminar);
		
		return "redirect:/alumnos";
	}
	
	//controlador para añadir un alumno
	@PostMapping("/add")
	public String addAlumno(@ModelAttribute("alumnoNuevo") Alumno alumnoNew, BindingResult bindingresult) {

		alumnoDAO.insertarAlumnoJPA(alumnoNew);
		
		return "redirect:/alumnos";
	}
	
	//controlador para la ficha del pedido
	@GetMapping(value= {"/{id}"})
	String idAlumno(Model model, @PathVariable Integer id) {
		
		Alumno alumnoMostrar = alumnoDAO.buscarPorIdJPA(id);
		model.addAttribute("alumnoMostrar", alumnoMostrar);
		
		return "alumno";
	}	
}
