package com.example.demo.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pedidos")

public class Pedido {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToMany(mappedBy = "pedidos", fetch = FetchType.EAGER)
	private Set<Bocadillo> bocadillos;
	
	@ManyToOne
	@JoinColumn(name = "id_alumno", nullable = false)
	@JsonIgnore
	private Alumno alumno;
	
	@Column(name = "precio")
	private double precio;
	
	public Pedido() {
		this.bocadillos = new HashSet<Bocadillo>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	public Set<Bocadillo> getBocadillos() {
		return bocadillos;
	}

	public void setBocadillos(Set<Bocadillo> bocadillos) {
		this.bocadillos = bocadillos;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void calcularPrecio() {
		
		double precioFinal = 0;
		
		for(Bocadillo b : bocadillos) {
			precioFinal += b.getPrecio();
		}
		
		this.precio = precioFinal;
	}
	
	
	@Override
	public String toString() {
		
		String resultado = "";
		for (Bocadillo b : bocadillos) {
			resultado = resultado + b.toString();
		}

		
		return " Pedido [id=" + id+" precio del pedido= "+precio+"]"+"\n"+resultado;
	}
	
	
	
}
