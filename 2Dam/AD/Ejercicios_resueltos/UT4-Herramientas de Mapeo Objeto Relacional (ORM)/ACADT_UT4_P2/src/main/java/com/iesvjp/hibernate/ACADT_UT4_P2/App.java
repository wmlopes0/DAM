package com.iesvjp.hibernate.ACADT_UT4_P2;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class App {
	public static void main(String[] args) {
		// Quitar comentarios feos
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		// EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ACADT_UT4_P2");
		EntityManager em = emf.createEntityManager();

		// Menus
		menu1(em);
		// menu2(em);

		// Libero recursos
		em.close();
		emf.close();
	}

	// PRIMER MENÚ
	// ====================================================================
	public static void menu1(EntityManager em) {
		char opcion;
		do {
			opcion = mostrarMenu1();
			switch (opcion) {
			case 'A':
				menu1OpcionA(em);
				break;
			case 'B':
				menu1OpcionB(em);
				break;
			case 'C':
				menu1OpcionC(em);
				break;
			case 'D':
				menu1OpcionD(em);
				break;
			case 'E':
				menu1OpcionE(em);
				break;
			case 'F':
				menu1OpcionF(em);
				break;
			case 'G':
				menu1OpcionG(em);
				break;
			case 'H':
				menu1OpcionH(em);
				break;
			case 'I':
				menu1OpcionI(em);
				break;
			case 'J':
				menu1OpcionJ(em);
				break;
			case 'K':
				menu1OpcionK(em);
				break;
			case 'L':
				menu1OpcionL(em);
				break;
			case 'M':
				System.out.println("Hasta pronto!");
				break;

			default:
				System.out.println("ERROR. Introduce una opción válida.");
				break;
			}
		} while (opcion != 'M');

	}

	public static void menu2(EntityManager em) {
		char opcion;
		do {
			opcion = mostrarMenu2();
			switch (opcion) {
			case 'a':
				menu2OpcionA(em);
				break;
			case 'b':
				menu2OpcionB(em);
				break;
			case 'c':
				menu2OpcionC(em);
				break;
			case 'd':
				menu2OpcionD(em);
				break;
			case 'e':
				System.out.println("Hasta pronto!");
				break;
			default:
				System.out.println("ERROR. Introduce una opción válida.");
				break;
			}
		} while (opcion != 'e');

	}

	// Mostrar menú
	private static char mostrarMenu1() {
		System.out.println("\n***********************************************");
		System.out.println("***************** M E N U   1 *****************");
		System.out.println("***********************************************");
		System.out.println("A. Muestra la cantidad de productos que hay en el catálogo.\r\n"
				+ "B. Muestra todos aquellos pedidos (orders) cuyo estado esté en “disputed”\r\n"
				+ "C. Muestra el pago de mayor cuantía realizado\r\n"
				+ "D. Muestra aquello empleados de la oficina de “San Francisco”\r\n"
				+ "E. Muestra el número de empleados que hay por oficina. Muestra el código de \r\n"
				+ "oficina, la ciudad y el número total de empleados. Ordena las oficinas por orden \r\n"
				+ "creciente de número de empleados.\r\n\n"
				+ "F. Muestra aquellos clientes (customer) que hayan sido atendidos por el \r\n"
				+ "empleado Gerard Hernandez\r\n\n" + "G. Muestra al empleado/s que haya/n atendido a más clientes.\r\n"
				+ "H. Aumenta un 5% el precio de venta (campo MSRP) de todos los productos cuya \r\n"
				+ "línea de productos sea Motorcycles. Redondea el precio de venta resultante a \r\n"
				+ "2 decimales\r\n\n" + "I. Muestra el total recaudado en ventas del año 2005\r\n"
				+ "J. Muestra el nombre de los clientes que se hayan gastado más de 120.000€\r\n"
				+ "K. Muestra el mejor cliente (aquel que más se haya gastado comprando). Utiliza \r\n"
				+ "setMaxResults(1)\r\n\n"
				+ "L. Muestra el mejor cliente (aquel que más se haya gastado comprando). Utiliza \r\n" + "SQL nativo");
		System.out.println("M. Salir.");
		System.out.println("*********************************************");
		return pedirChar("Introduzca una opción: ");
	}

	private static char mostrarMenu2() {
		System.out.println("\n***********************************************");
		System.out.println("***************** M E N U   2 *****************");
		System.out.println("***********************************************");
		System.out.println("a. Muestra todos aquellos productos cuyo vendedor se pase como parámetro.\r\n"
				+ "b. Muestra todos aquellos productos cuyo stock sea inferior a 100 unidades.\r\n"
				+ "c. Muestra el producto cuyo precio recomendado de venta (MSRP) sea el más " + "caro.\r\n"
				+ "d. Muestra el producto más vendido.\r\n" + "e. Salir.\n");
		return pedirChar("Introduzca una opción: ");
	}

	// OPCIONES MENÚ 1
	// ===========================================================================
	public static void menu1OpcionA(EntityManager em) {
		String hql = "select count(p) from Product p";
		Query query = em.createQuery(hql);
		long resultado = (long) query.getSingleResult();
		System.out.println("El total de productos es " + resultado);
	}

	public static void menu1OpcionB(EntityManager em) {
		String hql = "select o from Order o where o.status='disputed'";
		Query query = em.createQuery(hql);
		List<Order> resultado = query.getResultList();
		for (Order order : resultado) {
			System.out.println(order.toString());
		}
	}

	public static void menu1OpcionC(EntityManager em) {
		String hql = "select p from Payment p where p.amount = (select max(pa.amount) from Payment pa)";
		Query query = em.createQuery(hql);
		List<Payment> resultado = query.getResultList();
		for (Payment payment : resultado) {
			System.out.println(payment.toString());
		}
	}

	public static void menu1OpcionD(EntityManager em) {
		String hql = "select e from Employee e where e.office.city = 'San Francisco'";
		Query query = em.createQuery(hql);
		List<Employee> resultado = query.getResultList();
		for (Employee employee : resultado) {
			System.out.println(employee.toString());
		}
	}

	public static void menu1OpcionE(EntityManager em) {
		String hql = "select o.officeCode, o.city, size(o.employees) from Office o group by o.officeCode order by size(o.employees)";
		Query query = em.createQuery(hql);
		List<Object[]> resultado = query.getResultList();
		for (Object[] o : resultado) {
			System.out.println("- CodOficina:" + o[0] + ", Ciudad:" + o[1] + ", NumEmpleados:" + o[2]);
		}
	}

	public static void menu1OpcionF(EntityManager em) {
		String hql = "select c from Customer c where c.employee.firstName='Gerard' and c.employee.lastName='Hernandez'";
		Query query = em.createQuery(hql);
		List<Customer> resultado = query.getResultList();
		for (Customer customer : resultado) {
			System.out.println(customer.toString());
		}
	}

	public static void menu1OpcionG(EntityManager em) {
		String hql = "select e from Employee e where size(e.customers) >= all (select size(emp.customers) from Employee emp group by emp.employeeNumber)";
		Query query = em.createQuery(hql);
		List<Employee> resultado = query.getResultList();
		for (Employee employee : resultado) {
			System.out.println(employee.toString());
		}
	}

	public static void menu1OpcionH(EntityManager em) {
		em.getTransaction().begin();
		String hql = "update Product p set p.msrp = round(p.msrp * 1.05,2) where p.productline.productLine ='Motorcycles'";
		Query query = em.createQuery(hql);
		int resultado = query.executeUpdate();
		em.getTransaction().commit();

		System.out.println("Actualización realizada correctamente, el número de filas afectadas ha sido " + resultado);
	}

	public static void menu1OpcionI(EntityManager em) {
		String hql = "select sum(p.amount) from Payment p where year(p.paymentDate)=2005";
		Query query = em.createQuery(hql);
		BigDecimal resultado = (BigDecimal) query.getSingleResult();
		System.out.println("La suma total de ventas del año 2005 ha sido " + resultado);
	}

	public static void menu1OpcionJ(EntityManager em) {
		String hql = "select p.customer from Payment p group by p.customer.customerNumber having sum(p.amount)>=120000";
		Query query = em.createQuery(hql);
		List<Customer> resultado = query.getResultList();
		for (Customer customer : resultado) {
			System.out.println("-" + customer.getCustomerName());
		}
	}

	public static void menu1OpcionK(EntityManager em) {
		String hql = "select p.customer from Payment p group by p.customer.customerNumber order by sum(p.amount) desc";
		Query query = em.createQuery(hql).setMaxResults(1);
		Customer resultado = (Customer) query.getSingleResult();
		System.out.println(resultado.toString());
	}

	public static void menu1OpcionL(EntityManager em) {
		Object[] cliente = (Object[]) em.createNativeQuery(
				"select sum(p.amount) as sumaTotal, c.contactFirstName, c.contactLastName from payments p "
						+ "inner join customers c ON p.customerNumber=c.customerNumber " + "group by c.customerNumber "
						+ "order by sumaTotal desc limit 1")
				.getSingleResult();

		System.out.println("\n====================================================\n");
		System.out.println("l) El mejor cliente es: -----\n");
		System.out.println("Nombre : " + cliente[1] + " Apellidos: " + cliente[2] + " Total gastado: " + cliente[0]);
	}

	// OPCIONES MENÚ 2
	// ===========================================================================
	public static void menu2OpcionA(EntityManager em) {
		String nombreVendedor = pedirString("Indroduzca el nombre del vendedor: ");

		Query query = em.createNamedQuery("Product.consultaA");
		query.setParameter("vendedor", nombreVendedor);

		List<Product> resultado = query.getResultList();

		for (Product product : resultado) {
			System.out.println(product.toString());
		}
	}

	public static void menu2OpcionB(EntityManager em) {
		Query query = em.createNamedQuery("Product.consultaB");
		List<Product> resultado = query.getResultList();

		for (Product product : resultado) {
			System.out.println(product.toString());
		}
	}

	public static void menu2OpcionC(EntityManager em) {
		Query query = em.createNamedQuery("Product.consultaC").setMaxResults(1);
		Product resultado = (Product) query.getSingleResult();
		System.out.println(resultado.toString());
	}

	public static void menu2OpcionD(EntityManager em) {
		Query query = em.createNamedQuery("Product.consultaD").setMaxResults(1);
		Object[] resultado = (Object[]) query.getSingleResult();
		System.out.println("El producto más vendido es " + resultado[0] + " con " + resultado[1] + " ventas.");
	}

	// UTILERIA
	// =======================================================================
	// PEDIR ENTERO
	public static int pedirEntero(String texto) {
		Scanner s = new Scanner(System.in);
		int entero;
		System.out.println(texto);
		try {
			entero = s.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR, vuelva a intentarlo.");
			entero = pedirEntero(texto);
		}
		return entero;
	}

	// PEDIR CHAR
	public static char pedirChar(String texto) {
		Scanner s = new Scanner(System.in);
		System.out.println(texto);
		return s.nextLine().charAt(0);
	}

	// PEDIR FLOAT
	public static float pedirFloat(String texto) {
		Scanner s = new Scanner(System.in);
		float flotante;
		System.out.println(texto);
		try {
			flotante = s.nextFloat();
		} catch (InputMismatchException e) {
			System.out.println("ERROR, vuelva a intentarlo.");
			flotante = pedirFloat(texto);
		}
		return flotante;
	}

	// PEDIR STRING
	public static String pedirString(String texto) {
		Scanner s = new Scanner(System.in);
		System.out.println(texto);
		return s.nextLine();
	}

	// PEDIR DATE
	public static Date pedirDate(String texto) {
		LocalDate fecha;
		String fechaString = pedirString(texto);
		String[] fechaArray = fechaString.split("-");
		try {
			fecha = LocalDate.of(Integer.valueOf(fechaArray[0]), Integer.valueOf(fechaArray[1]),
					Integer.valueOf(fechaArray[2]));
		} catch (DateTimeException e) {
			System.out.println("ERROR, vuelva a intentarlo.");
			return pedirDate(texto);
		}
		return Date.valueOf(fecha);
	}

	// SI O NO
	public static boolean seguir(String texto) {
		Scanner s = new Scanner(System.in);
		System.out.println(texto);
		return s.nextLine().equalsIgnoreCase("si");
	}
}
