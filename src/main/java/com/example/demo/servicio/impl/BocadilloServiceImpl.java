package com.example.demo.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modelo.Bocadillo;
import com.example.demo.persistencia.BocadillosRepository;
import com.example.demo.servicio.interfaces.BocadilloService;

public class BocadilloServiceImpl implements BocadilloService{

	@Autowired
	public BocadillosRepository bocataRepo;
	
	@Override
	public Bocadillo insertarBocadillo(Bocadillo bocadillo) {
		// TODO Auto-generated method stub
		return bocataRepo.save(bocadillo);
	}

	@Override
	public List<Bocadillo> listarBocadillos() {
		// TODO Auto-generated method stub
		return bocataRepo.findAll();
	}

	@Override
	public Bocadillo obtenerBocadilloPorId(Integer id) {
		// TODO Auto-generated method stub
		return bocataRepo.findById(id).get();
	}

	@Override
	public void eliminarBocadillo(Bocadillo bocadillo) {
		// TODO Auto-generated method stub
		bocataRepo.delete(bocadillo);
	}

	@Override
	public void eliminarBocadilloPorId(Integer id) {
		// TODO Auto-generated method stub
		bocataRepo.delete(bocataRepo.findById(id).get());
	}

}
