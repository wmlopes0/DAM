package acadt_ut1_p1;

import java.io.File;

public class Ejercicio3 {

	public static void main(String[] args) {
		String ruta = "src/2DAM/AD/";
		String nombre = "P1.txt";
		String nombreNuevo = "practica1.txt";
		File fichero = new File(ruta + nombre);
		File ficheroNuevo = new File(ruta + nombreNuevo);

		if (fichero.exists()) {
			fichero.renameTo(ficheroNuevo);
			System.out.println("Fichero renombrado exitosamente!");
		} else {
			System.out.println("LO SIENTO NO EXISTE JIJI.");
		}
	}
}
