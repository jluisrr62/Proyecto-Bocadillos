package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Alumno;
import com.example.demo.modelo.Rol;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.dto.UsuarioDTO;
import com.example.demo.servicio.impl.UsuarioServiceImpl;

@RequestMapping("/usuarios")
@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioServiceImpl userDetailsService;
	
	//controlador para listar los usuarios y pasar parametros
	@GetMapping(value= {"", "/"})
	String homeUsuarios(Model model) {
		
		//salir a buscar a la bd
		List<Usuario> misUsuarios = userDetailsService.listarUsuarios();
		model.addAttribute("listaAlumnos", misUsuarios);
		//le pasamos un nuevo alumno para poder editar un alumno
		model.addAttribute("alumnoaEditar", new Usuario());
		model.addAttribute("alumnoNuevo", new Usuario());
		
		return "usuarios";
	}	
	
	//controlador para editar un usuario
	@PostMapping("/edit/{id}")
	public String editarUsuario(@PathVariable Integer id, @ModelAttribute("usuarioaEditar") Usuario usuarioEditado, BindingResult bindingresult) {
		
		Usuario usuarioaEditar = userDetailsService.obtenerUsuarioPorId(id);
		usuarioaEditar.setNombre(usuarioEditado.getNombre());
		userDetailsService.insertarUsuario(usuarioaEditar);
		
		
		return "redirect:/usuarios";
	}
	
	//controlador para eliminar un usuario
	@GetMapping("/delete/{id}")
	public String eliminarUsuario(Model model, @PathVariable Integer id) {
		if(elUsuarioLogueadoEsAdmin()) {
			userDetailsService.eliminarUsuarioPorId(id);
		}
		
		
		return "redirect:/";
	}
	
	//controlador para añadir un usuario
	@PostMapping("/add")
	public String addUsuario(@ModelAttribute("usuarioNuevo") Usuario usuarioNew, BindingResult bindingresult) {

		userDetailsService.insertarUsuario(usuarioNew);
		
		return "redirect:/usuarios";
	}
	
	//controlador para el formulario de registro
	@PostMapping("/addRegistro")
	public String addRegistro(@ModelAttribute("newUserDTO") UsuarioDTO usuarioNew, BindingResult bindingresult) {

		userDetailsService.insertarUsuarioDTO(usuarioNew);
		
		return "redirect:/login";
	}
	
	//controlador para el login
	@GetMapping("/register")
	public String register(Model model) {
		
		model.addAttribute("newUserDTO", new UsuarioDTO());
		
		return "register";
		
	}
	
	private boolean elUsuarioLogueadoEsAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		
		if(principal instanceof UserDetails) {
			
			userDetails = (UserDetails) principal;
			
			Usuario u = userDetailsService.obtenerUsuarioPorNombre(userDetails.getUsername());
			
			for(Rol r : u.getRoles()) {
				if(r.getNombre().compareTo("ROL_ADMIN")==0) {
					return true;
				}
			}
		}
				
		return false;
	}
}
