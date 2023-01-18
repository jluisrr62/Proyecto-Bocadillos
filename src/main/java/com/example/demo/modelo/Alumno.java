package com.example.demo.modelo;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alumnos")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	
	@OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER)
	private Set<Pedido> pedidos;
	
	public Alumno() {
		
	}

	public Alumno(String n) {
		this.nombre = n;
		this.pedidos = new HashSet<Pedido>();
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {

		String resultado = "";
		for (Pedido p : pedidos) {
			resultado = resultado + p.toString();
		}

		return  "Alumno [id=" + id + ", nombre=" + nombre + "]"+"\n"+resultado;

	}

}
