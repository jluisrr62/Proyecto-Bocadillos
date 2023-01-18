package com.example.demo.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer>{

}
