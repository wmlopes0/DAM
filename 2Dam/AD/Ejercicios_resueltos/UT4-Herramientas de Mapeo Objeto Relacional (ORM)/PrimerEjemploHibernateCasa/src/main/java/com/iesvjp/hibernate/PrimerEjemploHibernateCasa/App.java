package com.iesvjp.hibernate.PrimerEjemploHibernateCasa;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		// Inicialización del SessionFactory
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		/*
		 * //Codigo "legacy" SessionFactory sf = new Configuration() .configure()
		 * .buildSessionFactory();
		 */
		// Apertura de una sesión (e inicio de una transacción)
		Session session = sf.openSession();

		User user1 = new User();
		user1.setId(1);
		user1.setUserName("Pepe");
		user1.setUserMessage("Hello world from Pepe");

		User user2 = new User();
		user2.setId(2);
		user2.setUserName("Juan");
		user2.setUserMessage("Hello world from Juan");
		session.beginTransaction();

		// Almacenamos los objetos
		session.save(user1);
		session.save(user2);

		// Commit de la transacción
		session.getTransaction().commit();

		// Cierre de la sesión
		session.close();
		sf.close();
	}
}
