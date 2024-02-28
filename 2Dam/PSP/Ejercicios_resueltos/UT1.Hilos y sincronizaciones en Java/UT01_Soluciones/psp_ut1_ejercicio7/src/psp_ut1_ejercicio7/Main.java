package psp_ut1_ejercicio7;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int num = 0;
		do {
			GeneradorTablas.mostrarMenu();
			System.out.print("--> ");
			num = teclado.nextInt();
			GeneradorTablas.opcionesMenu(num);
		} while (num != 2);
	}

}
