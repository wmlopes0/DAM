package psp_ut1_ejercicio7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PintaTabla extends Thread {
	private int num;

	public PintaTabla(int num) {
		super();
		this.num = num;
	}
	
	@Override
	public void run() {		
		File fichero = new File("tablas/Tabla" + this.num + ".txt");
		
			
		System.out.println("Generando la tabla del " + this.num);
			
		try {
			FileWriter fw = new FileWriter(fichero);
			PrintWriter pw = new PrintWriter(fw);				
			
			pw.println("La tabla del " + this.num + " es: ");
			for (int i = 0; i < 10; i++) {
				pw.println(this.num + " x " + (i+1) + " = " + (this.num*(i+1)));
			}
			
			if (pw != null) {
				pw.close();
			}
			if (fw != null) {
				fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
