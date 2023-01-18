package com.example.demo.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.demo.util.HibernateUtil;
import com.example.demo.util.JPAUtil;


public class GenericDAO <T>{
	
	private Class<T> claseDelRegistro;
	
	public GenericDAO(Class<T> miClase) {
		claseDelRegistro = miClase;
	}
	
	
	//Hibernate
	public void insertarRegistroHibernate(T b) {
		Transaction tr = null;
		Session sesion = null;

		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			sesion.persist(b);
			tr.commit();

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
	}
	
	public void modificarRegsitroHibernate(T b) {
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			sesion.merge(b);
			tr.commit();

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
	}
	
	public void deleteRegistroHibernate(T b) {
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			sesion.remove(b);
			tr.commit();

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
	}
	
	public ArrayList<T> listarRegistrosHibernate(String clase) {
		ArrayList<T> resultado = null;
		
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			resultado = (ArrayList<T> ) sesion.createQuery("from "+clase).getResultList();
			tr.commit();
			
			return resultado;

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
		
		return null;
	}
	
	public T buscarPorIdHibernate(int i) {
		
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			T miRegistro = sesion.find(claseDelRegistro, i);
			System.out.println("El registro buscado se llama " + miRegistro);
			tr.commit();
			return miRegistro;

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
		
		return null;
	}
	
	
	//JPA
	public void insertarRegistroJPA(T a) {

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

	

	public void modificarRegistroJPA(T a) {
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

	public void deleteRegistroJPA(T a) {
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

	public ArrayList<T> listarRegistrosJPA(String tabla) {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

		try {
			em.getTransaction().begin();

			ArrayList<T> misRegistros = (ArrayList<T>) em.createQuery("from "+tabla).getResultList();

			em.getTransaction().commit();
			return misRegistros;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}

	public T buscarPorIdJPA(int i) {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			T registro = em.find(claseDelRegistro, i);
			System.out.println("El registro buscado se llama " + registro);
			return registro;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}
	
	//generico
	public void imprimirRegistros(ArrayList<T> misRegistros) {
		System.out.println("LISTADO DE REGISTROS"+"\n");
		for (T a : misRegistros) {
			System.out.println(a.toString());
		}
	}
	
}
