package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.modelo.Ingrediente;
import com.example.demo.persistencia.IngredienteDAO;
import com.example.demo.servicio.impl.IngredienteServiceImpl;

@RequestMapping("/ingredientes")
@Controller
public class IngredienteController {
	@Autowired
	public IngredienteServiceImpl ingService;
	
	@GetMapping(value= {"","/"})
	String homeIngredientes(Model model) {
		ArrayList<Ingrediente> misIngredientes = (ArrayList<Ingrediente>) ingService.listarIngredientes();
		model.addAttribute("listaIngredientes", misIngredientes);
		model.addAttribute("ingredienteaEditar", new Ingrediente());
		model.addAttribute("ingredienteNuevo", new Ingrediente());
		
		
		return "ingredientes";
	}
}
