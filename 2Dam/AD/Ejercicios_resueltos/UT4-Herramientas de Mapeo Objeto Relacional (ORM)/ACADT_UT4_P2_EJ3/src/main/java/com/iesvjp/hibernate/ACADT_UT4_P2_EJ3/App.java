package com.iesvjp.hibernate.ACADT_UT4_P2_EJ3;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// Quito comentarios feos
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		// EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ACADT_UT4_P2_EJ3");
		EntityManager em = emf.createEntityManager();

		// CONSULTAS====================================
		System.out.println("\n**************************************************************************");
		System.out.println("Visualiza los datos del departamento 20 y los apellidos de sus empleados.");
		System.out.println("**************************************************************************");
		String hql = "select d from Departamento d where d.deptNo = 20";
		Query query = em.createQuery(hql);
		Departamento resultado = (Departamento) query.getSingleResult();
		resultado.mostrar();

		System.out.println("\n******************************************************************************");
		System.out.println("Visualiza los datos del empleado con mayor salario del departamento de VENTAS.");
		System.out.println("******************************************************************************");
		hql = "select e from Empleado e where e.salario >= ALL (select emp.salario from Empleado emp where emp.departamento.dnombre = 'VENTAS')";
		query = em.createQuery(hql).setMaxResults(1);
		Empleado resultado2 = (Empleado) query.getSingleResult();
		System.out.println(resultado2.toString());

		System.out.println("\n******************************************************************************");
		System.out.println("Visualiza el salario medio de los empleados del departamento de Barcelona.");
		System.out.println("******************************************************************************");
		hql = "select avg(e.salario) from Empleado e where e.departamento.loc = 'BARCELONA' group by e.departamento.deptNo";
		query = em.createQuery(hql);
		double resultado3 = (double) query.getSingleResult();
		System.out.println("El sueldo medio del departamento de Barcelona es " + resultado3);

		System.out.println("\n******************************************************************************");
		System.out.println("Visualiza aquellos empleados que empezaron a trabajar en el año 2005.");
		System.out.println("******************************************************************************");
		hql = "select e from Empleado e where year(e.fechaAlt)=2005";
		query = em.createQuery(hql);
		List<Empleado> resultado4 = query.getResultList();
		for (Empleado empleado : resultado4) {
			System.out.println(empleado.toString());
		}

		System.out.println("\n******************************************************************************");
		System.out.println("Visualiza el número de empleados del departamento de VENTAS");
		System.out.println("******************************************************************************");
		hql = "select size(d.empleados) from Departamento d where d.dnombre='VENTAS'";
		query = em.createQuery(hql);
		int resultado5 = (int) query.getSingleResult();
		System.out.println("El número de empleados del departamento 'VENTAS' es " + resultado5);

		// =============================================

		// Libero recursos
		em.close();
		emf.close();
	}
}
