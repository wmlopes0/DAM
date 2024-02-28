package com.vjp.psp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utiles {

	public static int pedirNumero(String msg) {
		int numero = 0;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println(msg);
			try {
				numero = teclado.nextInt();
				teclado.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
			}
		} while (numero <= 0);
		return numero;
	}

	public static String pedirCadena(String msg) {
		String mensaje = "";
		Scanner teclado = new Scanner(System.in);
		mensaje = teclado.nextLine();
		return mensaje;
	}

	public static Coche pedirDatosCoche() {
		String matricula, marca, modelo, resp;
		int anio_matric, fecha_ul_ITV;
		Scanner teclado = new Scanner(System.in);

		matricula = pedirCadena("introduce la matricula del coche");
		marca = pedirCadena("introduce la marca del coche");
		modelo = pedirCadena("introduce el modelo del coche");
		anio_matric = pedirNumero("introudce el año de matriculacion");
		Coche coche = new Coche(matricula, marca, modelo, anio_matric);
		do {
			System.out.println("¿Desea introducir el año de la última ITV (y/n)");
			resp = teclado.nextLine();

		} while (!resp.equalsIgnoreCase("y") && !resp.equalsIgnoreCase("n"));

		if (resp.equalsIgnoreCase("y")) {
			fecha_ul_ITV = pedirNumero("introduce el año de la ultima ITV del cochee:");
			coche.setFechaUltimaITV(fecha_ul_ITV);
		}
		teclado.close();
		return coche;
	}
}