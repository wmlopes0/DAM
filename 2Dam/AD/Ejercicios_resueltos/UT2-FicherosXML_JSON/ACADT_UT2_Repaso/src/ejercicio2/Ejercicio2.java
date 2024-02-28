package ejercicio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Ejercicio2 {

	public static void main(String[] args) throws IOException {
		String rutaOrigen = "src/ejercicio2/origen.txt";
		String rutaDestino = "src/ejercicio2/destino.txt";
		// Realizo una copia
		realizarCopia(rutaOrigen, rutaDestino);
		System.out.println("COPIA REALIZADA CON ÉXITO.");
	}

	public static void realizarCopia(String rutaOrigen, String rutaDestino) throws IOException {
		// Creo File
		File ficheroOrigen = new File(rutaOrigen);
		File ficheroDestino = new File(rutaDestino);
		// Hago comprobaciones necesarias
		if (!ficheroDestino.exists()) {
			ficheroDestino.createNewFile();
		}
		// Abro flujos lectura
		BufferedReader br = new BufferedReader(new FileReader(new File(rutaOrigen)));
		// Abro flujos escritura
		PrintWriter pw = new PrintWriter(new FileWriter(new File(rutaDestino)));
		// Leo y escribo
		String linea = br.readLine();
		while (linea != null) {
			pw.println(linea);
			linea = br.readLine();
		}

		// Cierro flujos
		pw.close();
		br.close();
	}

}
