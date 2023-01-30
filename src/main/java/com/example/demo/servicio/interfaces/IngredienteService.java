package com.example.demo.servicio.interfaces;

import java.util.List;

import com.example.demo.modelo.Ingrediente;

public interface IngredienteService {
	public Ingrediente insertarIngrediente (Ingrediente ingrediente);
	public List<Ingrediente> listarIngredientes();
	public Ingrediente obtenerIngredientePorId (Integer id);
	public void eliminarIngrediente(Ingrediente ingrediente);
	public void eliminarIngredientePorId(Integer id);
}
