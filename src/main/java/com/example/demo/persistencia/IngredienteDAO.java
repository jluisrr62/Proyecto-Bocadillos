package com.example.demo.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.demo.modelo.Ingrediente;
import com.example.demo.util.HibernateUtil;
import com.example.demo.util.JPAUtil;


public class IngredienteDAO {

	// Hibernate
	public void insertarIngredienteHibernate(Ingrediente a) {
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
	
	public void modificarIngredienteHibernate(Ingrediente a) {
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
	
	public void deleteIngredienteHibernate(Ingrediente a) {
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
	
	public ArrayList<Ingrediente> listarIngredientesHibernate() {
		ArrayList<Ingrediente> resultado = null;
		
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			resultado = (ArrayList<Ingrediente> ) sesion.createQuery("from Ingrediente").getResultList();
			tr.commit();
			
			return resultado;

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
		
		return null;
	}
	
	public Ingrediente buscarPorIdHibernate(int i) {
		
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			Ingrediente miIngrediente = (Ingrediente) sesion.find(Ingrediente.class, i);
			System.out.println("El ingrediente se llama" + miIngrediente.getNombre());
			tr.commit();
			return miIngrediente;

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
		
		return null;
	}

	// JPA
	public void insertarIngredienteJPA(Ingrediente a) {

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

	

	public void modificarIngredienteJPA(Ingrediente a) {
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

	public void deleteIngredienteJPA(Ingrediente a) {
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

	public ArrayList<Ingrediente> listarIngredientesJPA() {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

		try {
			em.getTransaction().begin();

			ArrayList<Ingrediente> misIngredientes = (ArrayList<Ingrediente>) em.createQuery("from Ingrediente").getResultList();

			em.getTransaction().commit();
			return misIngredientes;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}

	public Ingrediente buscarIngredientePorIdJPA(int i) {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Ingrediente ingrediente = (Ingrediente) em.find(Ingrediente.class, i);
			System.out.println("el id ingrediente es " + ingrediente.getId());
			return ingrediente;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}
	
	//generico

	public void imprimirIngredientes(ArrayList<Ingrediente> misIngredientes) {
		System.out.println("listado de Ingredientes");
		for (Ingrediente a : misIngredientes) {
			System.out.println(a.toString());
		}
	}

	
}
