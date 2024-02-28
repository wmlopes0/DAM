package com.iesvjp.hibernate.PruebaValoresEntidades;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaValoresEntidades");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Direccion direccion1 = new Direccion("Calle Níscalo", "10600", "Plasencia", "Cáceres");
		Direccion direccion2 = new Direccion("Calle Badajoz", "10400", "Jaraiz de la Vera", "Cáceres");

		Estudiante estudiante1 = new Estudiante("45134320V", "Walter", "Martin Lopes",
				Date.valueOf(LocalDate.of(1998, 8, 27)), 722748406, "wmlopes0@gmail.com", direccion1);
		Estudiante estudiante2 = new Estudiante("45132337N", "Raquel", "Barbero Sánchez",
				Date.valueOf(LocalDate.of(1996, 11, 14)), 676615106, "raquelbarberosanchez90@gmail.com", direccion2);

		em.persist(estudiante1);
		em.persist(estudiante2);
		em.getTransaction().commit();

		em.close();
		emf.close();

	}
}
