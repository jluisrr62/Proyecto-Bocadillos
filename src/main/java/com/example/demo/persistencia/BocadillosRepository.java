package com.example.demo.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Bocadillo;

public interface BocadillosRepository extends JpaRepository<Bocadillo, Integer>{

	public Optional<Bocadillo> findByNombre(String nombre);
	
}
