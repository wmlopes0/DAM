package com.iesvjp.hibernate.ACADT_UT4_P2_EJ5;

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
		// Quitar comentarios feos
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ACADT_UT4_P2_EJ5");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Equipo equipo1 = new Equipo();
		equipo1.setNombre("Equipo1");
		equipo1.setCategoria("Categoria1");
		equipo1.setPais("Pais1");
		equipo1.setEstadio("Estadio1");
		
		Equipo equipo2 = new Equipo();
		equipo2.setNombre("Equipo2");
		equipo2.setCategoria("Categoria2");
		equipo2.setPais("Pais2");
		equipo2.setEstadio("Estadio2");
		
		List<Jugador> jugadores1 = new ArrayList<>();
		List<Jugador> jugadores2 = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			Jugador jugador = new Jugador(i, "Nombre" + i, "Apellidos" + i, "Nacionalidad" + i, "Posicion" + i,
					equipo1);
			em.persist(jugador);
			jugadores1.add(jugador);
		}

		for (int i = 6; i < 10; i++) {
			Jugador jugador = new Jugador(i, "Nombre" + i, "Apellidos" + i, "Nacionalidad" + i, "Posicion" + i,
					equipo2);
			em.persist(jugador);
			jugadores2.add(jugador);
		}
		
		equipo1.setJugadores(jugadores1);
		em.persist(equipo1);
		equipo2.setJugadores(jugadores2);
		em.persist(equipo2);
		

		em.getTransaction().commit();

		// Liberamos recursos;
		em.close();
		emf.close();
	}
}
