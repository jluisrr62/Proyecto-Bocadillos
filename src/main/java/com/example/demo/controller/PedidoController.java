package com.example.demo.controller;

import java.util.ArrayList;

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
import com.example.demo.modelo.Bocadillo;
import com.example.demo.modelo.Pedido;
import com.example.demo.persistencia.AlumnoDAO;
import com.example.demo.persistencia.AlumnoRepository;
import com.example.demo.persistencia.BocadilloDAO;
import com.example.demo.persistencia.BocadillosRepository;
import com.example.demo.persistencia.PedidoDAO;
import com.example.demo.persistencia.PedidoRepository;
import com.example.demo.servicio.impl.AlumnoServiceImpl;
import com.example.demo.servicio.impl.BocadilloServiceImpl;
import com.example.demo.servicio.impl.PedidoServiceImpl;

@RequestMapping("/pedidos")
@Controller
public class PedidoController {

	
	@Autowired
	PedidoServiceImpl pedidoService;
	
	@Autowired
	AlumnoServiceImpl alumnoService;
	
	@Autowired
	BocadilloServiceImpl bocadilloService;
	
	//controlador para listar los pedidos y pasar parametros
	@GetMapping(value= {"", "/"})
	String homepedidos(Model model) {
		
		//salir a buscar a la bd
//		ArrayList<Pedido> misPedidos = pedidoDAO.listarPedidosJPA();
		ArrayList<Pedido> misPedidos = (ArrayList<Pedido>) pedidoService.listarPedidos();
//		ArrayList<Alumno> misAlumnos = alumnoDAO.listarAlumnosJPA();
		ArrayList<Alumno> misAlumnos = (ArrayList<Alumno>) alumnoService.listarAlumnos();
//		ArrayList<Bocadillo> misBocadillos = bocadilloDAO.listarBocadillosJPA();
		ArrayList<Bocadillo> misBocadillos = (ArrayList<Bocadillo>) bocadilloService.listarBocadillos();
		
		
		model.addAttribute("listaPedidos", misPedidos);
		model.addAttribute("listaAlumnos", misAlumnos);
		model.addAttribute("listaBocadillos", misBocadillos);
		//le pasamos un nuevo pedido para poder editar un pedido
		model.addAttribute("pedidoaEditar", new Pedido());
		model.addAttribute("pedidoNuevo", new Pedido());
		
		return "pedidos";
	}	
	
	//controlador para editar un pedido
	@PostMapping("/edit/{id}")
	public String editarPedido(@PathVariable Integer id, @ModelAttribute("pedidoaEditar") Pedido pedidoEditado, BindingResult bindingresult) {
		
		Alumno a = alumnoService.obtenerAlumnoPorId(pedidoEditado.getAlumno().getId());
		pedidoEditado.setAlumno(a);
		
		Pedido pedidoaEditar = pedidoService.obtenerPedidoPorId(id);
		
		for(Bocadillo b:pedidoaEditar.getBocadillos()) {
			if(!pedidoEditado.getBocadillos().contains(b)) {
				b.getPedidos().remove(pedidoaEditar);
			}
			
			
		}
	
		
		return "redirect:/pedidos";
	}
	
	//controlador para eliminar un pedido
	@GetMapping("/delete/{id}")
	public String eliminarPedido(Model model, @PathVariable Integer id) {
		
//		Pedido pedidoaEliminar = pedidoDAO.buscarPedidoPorIdJPA(id);
//		pedidoDAO.deletePedidoJPA(pedidoaEliminar);
		
		pedidoService.eliminarPedidoPorId(id);
		
		return "redirect:/pedidos";
	}
	
	//controlador para añadir un pedido
	@PostMapping("/add")
	public String addPedido(@ModelAttribute("pedidoNuevo") Pedido pedidoNew, BindingResult bindingresult) {

		pedidoNew.calcularPrecio();
		Alumno alumnoNuevo = alumnoService.obtenerAlumnoPorId(pedidoNew.getAlumno().getId()); //obtener alumno 
		
		alumnoNuevo.getPedidos().add(pedidoNew);
		pedidoNew.setAlumno(alumnoNuevo);
		

		pedidoService.insertarPedido(pedidoNew);
		
		for(Bocadillo b: pedidoNew.getBocadillos()) {
			b.getPedidos().add(pedidoNew);
			bocadilloService.insertarBocadillo(b);
		}
		
		return "redirect:/pedidos";
	}

	//controlador para la ficha del pedido
	@GetMapping(value= {"/{id}"})
	String idPedido(Model model, @PathVariable Integer id) {
		
		Pedido pedidoMostrar = pedidoService.obtenerPedidoPorId(id);
		model.addAttribute("pedidoMostrar", pedidoMostrar);
		
		return "pedido";
	}	
}
