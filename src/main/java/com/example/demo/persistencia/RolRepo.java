package com.example.demo.persistencia;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Rol;

public interface RolRepo extends JpaRepository<Rol, Integer>{
	public Rol findByNombre(String nombre);
}
