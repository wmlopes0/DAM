package es.iesvjp.psp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LeerEscribir {
	public void leer() {
		File fichero = new File("fichero");
		
		FileReader fr = null;
		BufferedReader br = null;
		
		
		
	}

	public void escribir() throws IOException {
		File fichero = new File("fichero");

		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(fichero);
			pw = new PrintWriter(fw);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		fw.close();
		pw.close();
	}

}
