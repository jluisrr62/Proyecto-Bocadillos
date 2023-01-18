package com.example.demo.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.demo.modelo.Bocadillo;
import com.example.demo.util.HibernateUtil;
import com.example.demo.util.JPAUtil;



public class BocadilloDAO {
	// Hibernate
		public void insertarBocadilloHibernate(Bocadillo b) {
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
		
		public void modificarBocadilloHibernate(Bocadillo b) {
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
		
		public void deleteBocadilloHibernate(Bocadillo b) {
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
		
		public ArrayList<Bocadillo> listarBocadillosHibernate() {
			ArrayList<Bocadillo> resultado = null;
			
			Transaction tr = null;
			Session sesion = null;
			
			try {

				sesion = HibernateUtil.getSessionFactory().openSession();
				tr = sesion.beginTransaction();
				resultado = (ArrayList<Bocadillo> ) sesion.createQuery("from Bocadillo").getResultList();
				tr.commit();
				
				return resultado;

			} catch (PersistenceException e) {
				tr.rollback();

			} finally {
				sesion.close();
			}
			
			return null;
		}
		
		public Bocadillo buscarPorIdHibernate(int i) {
			
			Transaction tr = null;
			Session sesion = null;
			
			try {

				sesion = HibernateUtil.getSessionFactory().openSession();
				tr = sesion.beginTransaction();
				Bocadillo miBocadillo = (Bocadillo) sesion.find(Bocadillo.class, i);
				System.out.println("El bocadillo buscado se llama " + miBocadillo.getNombre());
				tr.commit();
				return miBocadillo;

			} catch (PersistenceException e) {
				tr.rollback();

			} finally {
				sesion.close();
			}
			
			return null;
		}

		// JPA
		public void insertarBocadilloJPA(Bocadillo b) {

			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(b);
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			} finally {
				em.close();
			}

		}

		

		public void modificarBocadilloJPA(Bocadillo b) {
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				em.merge(b);
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			} finally {
				em.close();
			}
		}

		public void deleteBocadilloJPA(Bocadillo b) {
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				em.remove(em.contains(b) ? b : em.merge(b));
				em.getTransaction().commit();
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			} finally {
				em.close();
			}
		}

		public ArrayList<Bocadillo> listarBocadillosJPA() {

			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

			try {
				em.getTransaction().begin();

				ArrayList<Bocadillo> misBocadillos = (ArrayList<Bocadillo>) em.createQuery("from Bocadillo").getResultList();

				em.getTransaction().commit();
				return misBocadillos;
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			} finally {
				em.close();
			}

			return null;
		}

		public Bocadillo buscarPorIdJPA(int i) {

			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				Bocadillo bocadillo = (Bocadillo) em.find(Bocadillo.class, i);
				System.out.println("El bocadillo buscado se llama " + bocadillo.getNombre());
				return bocadillo;
			} catch (PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			} finally {
				em.close();
			}

			return null;
		}
		
		//generico

		public void imprimirBocadillos(ArrayList<Bocadillo> misBocadillos) {
			System.out.println("listado de bocadillos");
			for (Bocadillo b : misBocadillos) {
				System.out.println(b.toString());
			}
		}

	}


