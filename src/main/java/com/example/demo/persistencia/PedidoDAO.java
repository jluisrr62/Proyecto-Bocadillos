package com.example.demo.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.demo.modelo.Pedido;
import com.example.demo.util.HibernateUtil;
import com.example.demo.util.JPAUtil;



public class PedidoDAO {

	// Hibernate
	public void insertarPedidoHibernate(Pedido a) {
		Transaction tr = null;
		Session sesion = null;

		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			sesion.persist(a);
			tr.commit();

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
	}
	
	public void modificarPedidoHibernate(Pedido a) {
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			sesion.merge(a);
			tr.commit();

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
	}
	
	public void deletePedidoHibernate(Pedido a) {
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			sesion.remove(a);
			tr.commit();

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
	}
	
	public ArrayList<Pedido> listarPedidosHibernate() {
		ArrayList<Pedido> resultado = null;
		
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			resultado = (ArrayList<Pedido> ) sesion.createQuery("from Pedido").getResultList();
			tr.commit();
			
			return resultado;

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
		
		return null;
	}
	
	public Pedido buscarPorIdHibernate(int i) {
		
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			Pedido miPedido = (Pedido) sesion.find(Pedido.class, i);
			System.out.println("El id Pedido es " + miPedido.getId());
			tr.commit();
			return miPedido;

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
		
		return null;
	}

	// JPA
	public void insertarPedidoJPA(Pedido a) {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

	}

	

	public void modificarPedidoJPA(Pedido a) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(a);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void deletePedidoJPA(Pedido a) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.contains(a) ? a : em.merge(a));
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public ArrayList<Pedido> listarPedidosJPA() {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

		try {
			em.getTransaction().begin();

			ArrayList<Pedido> misPedidos = (ArrayList<Pedido>) em.createQuery("from Pedido").getResultList();

			em.getTransaction().commit();
			return misPedidos;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}

	public Pedido buscarPedidoPorIdJPA(int i) {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Pedido pedido = (Pedido) em.find(Pedido.class, i);
			System.out.println("el id pedido es " + pedido.getId());
			return pedido;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}
	
	//generico

	public void imprimirPedidos(ArrayList<Pedido> misPedidos) {
		System.out.println("listado de Pedidos");
		for (Pedido a : misPedidos) {
			System.out.println(a.toString());
		}
	}

}
