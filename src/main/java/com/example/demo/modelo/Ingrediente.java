package com.example.demo.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="ingredientes")

public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="vegano")
	private boolean vegano;
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "bocadillos_ingredientes",
			joinColumns = {@JoinColumn(name= "id_bocadillo")},
			inverseJoinColumns = {@JoinColumn(name="id_ingrediente")}
		)
	@JsonIgnore
	private Set<Bocadillo> bocadillos;
	
	public Ingrediente() {
	
	}
	
	public Ingrediente(String nombre, boolean vegano) {
		this.nombre = nombre;
		this.vegano = vegano;
		this.bocadillos = new HashSet<Bocadillo>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean isVegano() {
		return vegano;
	}

	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Bocadillo> getBocadillos() {
		return bocadillos;
	}

	public void setBocadillos(Set<Bocadillo> bocadillos) {
		this.bocadillos = bocadillos;
	}

	@Override
	public String toString() {
		return "   Ingrediente [id=" + id + ", nombre=" + nombre + " es vegano = "+vegano+"]"+"\n";
	}
	
	
	
}
