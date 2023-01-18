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
@Table(name="bocadillos")
public class Bocadillo {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name="precio")
	private double precio;
	
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
		name = "pedidos_bocadillos",
		joinColumns = {@JoinColumn(name= "id_bocadillo")},
		inverseJoinColumns = {@JoinColumn(name="id_pedido")}
	)
	@JsonIgnore
	private Set<Pedido> pedidos;
	
	@ManyToMany(mappedBy = "bocadillos", fetch = FetchType.EAGER)
	private Set<Ingrediente> ingredientes;
	
	public Bocadillo() {
		
	}
	

	public Bocadillo(String nombre, double precio) {
		this.nombre = nombre;
		this.pedidos = new HashSet<Pedido>();
		this.ingredientes = new HashSet<Ingrediente>();
		this.precio = precio;
	}
		
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public Set<Ingrediente> getIngredientes() {
		return ingredientes;
	}


	public void setIngredientes(Set<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}


	@Override
	public String toString() {
		
		String resultado = "";
		for (Ingrediente b : ingredientes) {
			resultado = resultado + b.toString();
		}

		
		return "  Bocadillo [id=" + id + ", nombre=" + nombre+" precio del bocadillo = "+precio+"]"+"\n"+resultado;
	}

	
	
}
