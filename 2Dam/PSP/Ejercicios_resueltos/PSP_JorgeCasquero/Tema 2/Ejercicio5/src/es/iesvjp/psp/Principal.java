package es.iesvjp.psp;

import java.text.spi.NumberFormatProvider;
import java.util.concurrent.Semaphore;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Principal {
	public static void main(String[] args) {

		String[] vNombres = { "Encarni", "Aarón", "Eusebio", "Marcos", "Kike", "Puerto", "Julio" };
		Semaphore semaforo = new Semaphore(3);
		Profesor[] profesor = new Profesor[7];
		SalaProfesores salaProfes = new SalaProfesores();
		for (int i = 0; i < vNombres.length; i++) {
			profesor[i] = new Profesor(vNombres[i], salaProfes);
			profesor[i].start();
		}
	}
}
