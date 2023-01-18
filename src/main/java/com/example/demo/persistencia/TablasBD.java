package com.example.demo.persistencia;

import com.example.demo.modelo.Alumno;
import com.example.demo.modelo.Bocadillo;
import com.example.demo.modelo.Ingrediente;
import com.example.demo.modelo.Pedido;

public class TablasBD {
	public void crearTablas() {
		Alumno a = new Alumno("Juan1");
		Alumno a1 = new Alumno("Juan2");
		Alumno a2 = new Alumno("Juan3");

		
		GenericDAO<Alumno> genericoAlumnos = new GenericDAO<Alumno>(Alumno.class);
		
		
		Pedido p = new Pedido();
		GenericDAO<Pedido> genericoPedidos = new GenericDAO<Pedido>(Pedido.class);
		
		Bocadillo b1 = new Bocadillo("Bocadillo de pollo", 2.50);
		Bocadillo b2 = new Bocadillo("Bocadillo vegetal", 2.50);
		GenericDAO<Bocadillo> genericoBocadillo = new GenericDAO<Bocadillo>(Bocadillo.class);
		
		Ingrediente i1 = new Ingrediente("Pollo", false);
		Ingrediente i2 = new Ingrediente("Lechuga", true);
		Ingrediente i3 = new Ingrediente("Tomate", true);
		GenericDAO<Ingrediente> genericoIngredientes = new GenericDAO<Ingrediente>(Ingrediente.class);
		
		
		
		//añadimos la relacion entre los bocadillos y los ingredientes
		b1.getIngredientes().add(i1);
		b2.getIngredientes().add(i2);
		b2.getIngredientes().add(i3);
		
		i1.getBocadillos().add(b1);
		i2.getBocadillos().add(b2);
		i3.getBocadillos().add(b2);
		
		//añadimos la relacion entre los objetos pedido p y bocadillo b1 y alumno a2
		
		//añadimos a la clase pedidos al atrib array de bocadillos el bocadillo b1 y el bocadillo b2
		p.getBocadillos().add(b1);
		p.getBocadillos().add(b2);
		//añadimos a la clase bocadillos al atrib array de pedidos el pedido p
		b1.getPedidos().add(p);
		b2.getPedidos().add(p);
		
		//calculamos el precio del pedido
		p.calcularPrecio();
		
		//añadimos a la clase alumno al atrib array de pedidos el pedido p
		a2.getPedidos().add(p);
		//añadimos a la clase pedido al atrib alumno el alumno a2
		p.setAlumno(a2);
		
		
		genericoAlumnos.insertarRegistroJPA(a);
		genericoAlumnos.insertarRegistroJPA(a1);
		genericoAlumnos.insertarRegistroJPA(a2);
		
		//insertamos el pedido y el bocadillo

		genericoPedidos.insertarRegistroJPA(p);
		genericoBocadillo.insertarRegistroJPA(b1);
		genericoBocadillo.insertarRegistroJPA(b2);
		
		//insertamos lo ingredientes
		genericoIngredientes.insertarRegistroJPA(i1);
		genericoIngredientes.insertarRegistroJPA(i2);
		genericoIngredientes.insertarRegistroJPA(i3);
		
		//imprimos los alumnos
		genericoAlumnos.imprimirRegistros(genericoAlumnos.listarRegistrosJPA("Alumno"));
		
	}
}
