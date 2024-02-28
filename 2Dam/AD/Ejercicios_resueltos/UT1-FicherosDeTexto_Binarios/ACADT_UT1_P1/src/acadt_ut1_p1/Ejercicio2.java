package acadt_ut1_p1;

import java.io.File;
import java.io.IOException;

public class Ejercicio2 {
	public static void main(String[] args) {

		boolean correcto = true;
		String ruta1 = "src/2DAM/";
		String ruta2 = "src/2DAM/AD/";
		String ruta3 = "src/2DAM/PSP/";
		String nombre1 = "P1.txt";
		String nombre2 = "UD1.txt";
		String nombre3 = "UD2.txt";

		File ad = new File(ruta2);
		File psp = new File(ruta3);
		File fichero1 = new File(ruta2 + nombre1);
		File fichero2 = new File(ruta2 + nombre2);
		File fichero3 = new File(ruta2 + nombre3);
		File fichero4 = new File(ruta3 + nombre2);
		File fichero5 = new File(ruta3 + nombre3);
		File fichero6 = new File(ruta1 + "misNotas.txt");

		ad.mkdirs();
		psp.mkdirs();

		try {
			fichero1.createNewFile();
			fichero2.createNewFile();
			fichero3.createNewFile();
			fichero4.createNewFile();
			fichero5.createNewFile();
			fichero6.createNewFile();
		} catch (IOException e) {
			System.out.println("ERROR DE ENTRADA/SALIDA.");
			correcto = false;
		}

		if (correcto) {
			System.out.println("¡Ficheros creados correctamente!");
		}
	}
}
