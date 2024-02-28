package acadt_ut1_p1;

import java.io.File;

public class Ejercicio4 {
	public static void main(String[] args) {
		File ruta = new File("src/2DAM/");
		String [] archivos = ruta.list();
		
		for (int i = 0; i < archivos.length; i++) {
			System.out.println(archivos[i]);
		}
	}
}
