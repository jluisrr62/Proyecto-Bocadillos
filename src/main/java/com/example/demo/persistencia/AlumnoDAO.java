package com.example.demo.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.demo.modelo.Alumno;
import com.example.demo.util.HibernateUtil;
import com.example.demo.util.JPAUtil;



public class AlumnoDAO {

	// Hibernate
	public void insertarAlumnoHibernate(Alumno a) {
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
	
	public void modificarAlumnoHibernate(Alumno a) {
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
	
	public void deleteAlumnoHibernate(Alumno a) {
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
	
	public ArrayList<Alumno> listarAlumnosHibernate() {
		ArrayList<Alumno> resultado = null;
		
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			resultado = (ArrayList<Alumno> ) sesion.createQuery("from Alumno").getResultList();
			tr.commit();
			
			return resultado;

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
		
		return null;
	}
	
	public Alumno buscarPorIdHibernate(int i) {
		
		Transaction tr = null;
		Session sesion = null;
		
		try {

			sesion = HibernateUtil.getSessionFactory().openSession();
			tr = sesion.beginTransaction();
			Alumno miAlumno = (Alumno) sesion.find(Alumno.class, i);
			System.out.println("El alumno buscado se llama " + miAlumno.getNombre());
			tr.commit();
			return miAlumno;

		} catch (PersistenceException e) {
			tr.rollback();

		} finally {
			sesion.close();
		}
		
		return null;
	}

	// JPA
	public void insertarAlumnoJPA(Alumno a) {

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

	

	public void modificarAlumnoJPA(Alumno a) {
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

	public void deleteAlumnoJPA(Alumno a) {
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

	public ArrayList<Alumno> listarAlumnosJPA() {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

		try {
			em.getTransaction().begin();

			ArrayList<Alumno> misAlumnos = (ArrayList<Alumno>) em.createQuery("from Alumno").getResultList();

			em.getTransaction().commit();
			return misAlumnos;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}

	public Alumno buscarPorIdJPA(int i) {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Alumno alumno = (Alumno) em.find(Alumno.class, i);
			System.out.println("El alumno buscado se llama " + alumno.getNombre());
			return alumno;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}

		return null;
	}
	
	//generico

	public void imprimirAlumnos(ArrayList<Alumno> misAlumnos) {
		System.out.println("LISTADO DE ALUMNOS"+"\n");
		for (Alumno a : misAlumnos) {
			System.out.println(a.toString());
		}
	}

}
