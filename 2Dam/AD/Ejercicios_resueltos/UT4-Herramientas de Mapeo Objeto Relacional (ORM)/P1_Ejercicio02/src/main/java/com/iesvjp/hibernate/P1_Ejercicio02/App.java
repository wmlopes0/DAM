package com.iesvjp.hibernate.P1_Ejercicio02;

import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// Quito comentarios feos
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("P1_Ejercicio02");

		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();

		// Iniciamos una transacción
		em.getTransaction().begin();

		// Instanciamos un objeto tipo Estudiante
		Estudiante estudiante1 = new Estudiante(1, "Walter", "Martin Lopes", 722748406, "wmartinl01@iesvjp.es");
		Estudiante estudiante2 = new Estudiante(2, "Manolo", "Martin Pereira", 722348406, "mmartinp01@iesvjp.es");

		// Persistimos los objetos
		em.persist(estudiante1);
		em.persist(estudiante2);

		// Commiteamos la transacción
		em.getTransaction().commit();

		// Cerramos el EntityManager
		em.close();
		emf.close();

	}
}
