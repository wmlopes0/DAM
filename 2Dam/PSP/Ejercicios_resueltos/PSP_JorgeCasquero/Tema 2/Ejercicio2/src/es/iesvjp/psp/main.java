package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class main {
	/*
	 * Los 30 HiloInicial pintan por pantalla su identificador (los identificadores
	 * van del 1 al 30). Estos hilos no están sincronizados por lo que el 27 puede
	 * que pinte su identificador antes que el 30. • El hiloFinal pinta en su
	 * ejecución: “Hola soy el hilo final”
	 * 
	 * HiloFinal no podrá escribir su nombre hasta que todos los otros hilos
	 * anteriores no hayan pintado su identificador por pantalla.
	 */

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(0);
		int numHilos = 30;
		HiloInical[] vhi = new HiloInical[numHilos];
		HiloFinal hf = new HiloFinal(semaforo, numHilos);
		hf.start();
		
		//hasta que no estan los 30 permisos cedidos por el hilo inicial, el hilo final no puede hacer nada.
		for (int i = 0; i < numHilos; i++) {
			vhi[i] = new HiloInical(semaforo, i);
			vhi[i].start();

		}
	}
}