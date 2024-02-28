package acadt_ut1_p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) throws IOException {
		// Creamos variables necesarias
		File ruta = new File("./usuarios.txt");
		String dni, nombre, apellido, fechaNac;
		int telefono;

		// Recopilamos datos
		System.out.println("BIENVENIDO, INTRODUCE LOS DATOS.");
		dni = pedirCadena("Introduce tu DNI:");
		nombre = pedirCadena("Introduce tu nombre:");
		apellido = pedirCadena("Introduce tu apellido:");
		fechaNac = pedirCadena("Introduce tu fecha de nacimiento:");
		telefono = pedirEntero("Introduce tu telefono:");

		// Abrimos flujos y escribimos
		try {
			FileWriter fw = new FileWriter(ruta);
			fw.write(dni);
			fw.write(nombre);
			fw.write(apellido);
			fw.write(fechaNac);
			fw.write(telefono);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Leemos del archivo y mostramos por pantalla
		try {
			mostrarDatos(ruta);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String pedirCadena(String texto) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(texto);
		return entrada.nextLine();
	}

	public static int pedirEntero(String texto) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(texto);
		try {
			return entrada.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ERROR, INTRODUCE UNA LETRA.");
			return pedirEntero(texto);
		}
	}

	public static void mostrarDatos(File ruta) throws IOException {
		FileReader fr = new FileReader(ruta);
	 while(fr.read() != -1) {
		 System.out.println();
	 }
	}



}
