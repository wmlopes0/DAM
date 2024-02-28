import java.util.Random;

import javax.swing.plaf.SliderUI;

public class Banyo {
	final int MAX_PERSONAS = 3;
	int numPersonas;

	public Banyo() {
		numPersonas = 0; // Inicialmente no hay nadie
	}

	// El m�todo no puede ser completamente sincronizado, porque quiero que entren
	// varias personas por el ba�o a la vez.
	public void entrabanio(String nombre) {
		// Compruebo que puedo entrar
		synchronized (this) {
			while (numPersonas == MAX_PERSONAS) {
				System.out.println("�" + nombre + " espera porque el ba�o est� lleno!");
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			numPersonas++; // modifico numPersonas todav�a en exclusi�n m�tua
			System.out.println("Entra " + nombre + "---------- Hay " + numPersonas);
		}

		// Hace algo en el ba�o un tiempo aleatorio:
		Random rd = new Random();
		int tiempo = rd.nextInt(20) + 1;
		System.out.println("\t" + nombre + " permanece en el ba�o " + tiempo + " milisegundos.");
		try {
			Thread.sleep(tiempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// A la salida, tiene que volver a estar sincronizado, porque toco la variable
		// com�n numPersonas y hay que hacer un notifyAll
		synchronized (this) {
			numPersonas--;
			System.out.println("\t\tSale " + nombre + "---------- Hay " + numPersonas);
			notifyAll();
		}

	}
}
