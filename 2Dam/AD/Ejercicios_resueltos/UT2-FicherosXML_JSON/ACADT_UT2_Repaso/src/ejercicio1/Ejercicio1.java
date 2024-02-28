package ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

	private static File fichero;
	private static String ruta = "src/ejercicio1";

	public static void main(String[] args) throws IOException {
		// Controlo que el fichero exista
		do {
			fichero = pedirFichero();
			// Leer fichero
			leerFichero();
		} while (seguir());
	}

	public static File pedirFichero() {
		// Declaraciones
		Scanner s = new Scanner(System.in);
		File f;
		String nombreFichero;
		// Pido por pantalla
		System.out.println("Escribe el nombre del fichero de texto que  deseas leer: ");
		nombreFichero = s.nextLine() + ".txt";
		f = new File(ruta, nombreFichero);
		// Controlo si existe
		if (!f.exists()) {
			System.out.println("ERROR: Fichero no encontrado.");
			f = pedirFichero();
		}
		// Retorno el File
		return f;
	}

	public static boolean seguir() {
		// Declaraciones
		Scanner s = new Scanner(System.in);
		System.out.println("=====================================");
		System.out.println("¿Desea seguir leyendo ficheros?");
		return s.nextLine().equalsIgnoreCase("si");
	}

	public static void leerFichero() throws IOException {
		// Abro flujos
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		// Leo
		System.out.println("***********************************");
		System.out.println("********** L E C T U R A **********");
		System.out.println("***********************************");
		String linea = br.readLine();
		while (linea != null) {
			System.out.println(linea);
			linea = br.readLine();
		}
		// Cierro Flujos
		br.close();
	}
}
