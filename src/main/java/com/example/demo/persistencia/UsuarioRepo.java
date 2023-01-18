package com.example.demo.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{
	public Optional<Usuario> findByUsername(String username);
}
