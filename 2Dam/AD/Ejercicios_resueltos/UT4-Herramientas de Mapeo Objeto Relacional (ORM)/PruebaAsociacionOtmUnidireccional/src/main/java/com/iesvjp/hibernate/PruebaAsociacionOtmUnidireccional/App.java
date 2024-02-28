package com.iesvjp.hibernate.PruebaAsociacionOtmUnidireccional;

import java.util.ArrayList;
import java.util.List;
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
		// Para que no salgan comentarios feos
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaAsociacionOtmUnidireccional");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		// Creo empleados
		Empleado empleado1 = new Empleado("Juan", "Pérez", 123456789);
		Empleado empleado2 = new Empleado("Ana", "García", 987654321);
		Empleado empleado3 = new Empleado("Luis", "Martínez", 123123123);
		Empleado empleado4 = new Empleado("Sara", "Jiménez", 456456456);
		Empleado empleado5 = new Empleado("Carlos", "Hernández", 789789789);
		Empleado empleado6 = new Empleado("Lucía", "López", 321321321);
		Empleado empleado7 = new Empleado("Mario", "González", 654654654);
		Empleado empleado8 = new Empleado("Paula", "Ruiz", 987987987);
		Empleado empleado9 = new Empleado("David", "Moreno", 147147147);
		Empleado empleado10 = new Empleado("Elena", "Alvarez", 258258258);

		List<Empleado> lista1 = new ArrayList<>();
		lista1.add(empleado1);
		lista1.add(empleado2);
		lista1.add(empleado3);
		lista1.add(empleado4);
		lista1.add(empleado5);

		List<Empleado> lista2 = new ArrayList<>();
		lista2.add(empleado6);
		lista2.add(empleado7);
		lista2.add(empleado8);
		lista2.add(empleado9);
		lista2.add(empleado10);

		// Creo departamentos
		Departamento informatica = new Departamento("Informática", lista1);
		Departamento recursosHumanos = new Departamento("RecursosHumanos", lista2);

		// Persistimos los objetos
		lista1.forEach(empleado -> {
			em.persist(empleado);
		});

		lista2.forEach(empleado -> {
			em.persist(empleado);
		});

		em.persist(informatica);
		em.persist(recursosHumanos);

		// Commit
		em.getTransaction().commit();

		// Liberamos recursos
		em.close();
		emf.close();

	}
}
