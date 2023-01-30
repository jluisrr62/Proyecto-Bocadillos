package com.example.demo.servicio.interfaces;

import java.util.List;

import com.example.demo.modelo.Bocadillo;

public interface BocadilloService {
	public Bocadillo insertarBocadillo (Bocadillo bocadillo);
	public List<Bocadillo> listarBocadillos();
	public Bocadillo obtenerBocadilloPorId (Integer id);
	public void eliminarBocadillo(Bocadillo bocadillo);
	public void eliminarBocadilloPorId(Integer id);
}
