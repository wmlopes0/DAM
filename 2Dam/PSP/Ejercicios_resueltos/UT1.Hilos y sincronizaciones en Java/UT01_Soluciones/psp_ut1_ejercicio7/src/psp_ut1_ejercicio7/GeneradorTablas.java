package psp_ut1_ejercicio7;

import java.util.Scanner;

public class GeneradorTablas {
	
	public static int pedirNum() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introduce el número para crear la tabla: ");
		return teclado.nextInt();
	}
	
	public static void crearTabla() {
		int num = pedirNum();
		PintaTabla tabla = new PintaTabla(num);
		tabla.start();
	}
	
	public static void opcionesMenu(int num) {
		switch (num) {
			case 1:
				crearTabla();
				break;
			case 2:
				System.out.println("Hasta la proxima.");
				break;
			default:
				System.out.println("Número introducido no válido.");
				break;
		}
	}
	
	public static void mostrarMenu() {
		System.out.println("	-- OPCIONES --");
		System.out.println("1. Generar una tabla.");
		System.out.println("2. Salir.");
	}
}
