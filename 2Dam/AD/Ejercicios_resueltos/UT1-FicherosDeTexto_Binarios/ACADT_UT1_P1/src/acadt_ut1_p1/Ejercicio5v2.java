package acadt_ut1_p1;

import java.io.File;

public class Ejercicio5v2 {
	public static void main(String[] args) {
		File ruta = new File("src/2DAM/");
		File[] files = ruta.listFiles();
		String[] archivos;

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				archivos = files[i].list();
				mostrar(archivos);
			} else {
				if (comprobarTxt(files[i].getName())) {
					System.out.println(files[i].getName());
				}
			}
		}
	}

	public static void mostrar(String[] archivos) {
		for (int i = 0; i < archivos.length; i++) {
			if (comprobarTxt(archivos[i])) {
				System.out.println(archivos[i]);
			}
		}
	}

	public static boolean comprobarTxt(String archivo) {
		if (archivo.endsWith(".txt")) {
			return true;
		} else {
			return false;
		}
	}

}
