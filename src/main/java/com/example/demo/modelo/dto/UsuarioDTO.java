package com.example.demo.modelo.dto;

import javax.persistence.Column;

public class UsuarioDTO {
	
	private String nombre;

	private String username;
	
	private String password;

	
	public UsuarioDTO(String nombre, String username, String password) {
		this.nombre = nombre;
		this.username = username;
		this.password = password;
	}

	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
