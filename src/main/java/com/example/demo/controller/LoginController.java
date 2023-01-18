package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Alumno;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.dto.UsuarioDTO;
import com.example.demo.servicio.impl.UsuarioServiceImpl;

@RequestMapping("/login")
@Controller
public class LoginController {
	
	//controlador para listar los usuarios y pasar parametros
	@GetMapping(value= {"", "/"})
	String homeUsuarios(Model model) {
		
		
		
		return "login";
	}	
	
	//controlador para editar un usuario
	@GetMapping("/logout")
	public String editarUsuario() {
		
		
		
		return "login";
	}
	
	
	

	
}
