package com.example.demo.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modelo.Ingrediente;
import com.example.demo.persistencia.IngredienteRepository;
import com.example.demo.servicio.interfaces.IngredienteService;

public class IngredienteServiceImpl implements IngredienteService{

	@Autowired
	public IngredienteRepository ingRepo;
	
	@Override
	public Ingrediente insertarIngrediente(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return ingRepo.save(ingrediente);
	}

	@Override
	public List<Ingrediente> listarIngredientes() {
		// TODO Auto-generated method stub
		return ingRepo.findAll();
	}

	@Override
	public Ingrediente obtenerIngredientePorId(Integer id) {
		// TODO Auto-generated method stub
		return ingRepo.findById(id).get();
	}

	@Override
	public void eliminarIngrediente(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		ingRepo.delete(ingrediente);
	}

	@Override
	public void eliminarIngredientePorId(Integer id) {
		// TODO Auto-generated method stub
		ingRepo.delete(ingRepo.findById(id).get());
	}

}
