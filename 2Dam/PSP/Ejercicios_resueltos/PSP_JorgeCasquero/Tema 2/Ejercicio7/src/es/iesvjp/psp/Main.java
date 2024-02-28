package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {

		Semaphore vacio = new Semaphore(0);
		/*
		 * semáforo sin permisos, el primero que llame a acquire() se bloqueará
		 */
		Semaphore lleno = new Semaphore(5); // semáforo que se bloqueará cuando el buffer esté lleno
		Semaphore mutex = new Semaphore(1); // semáforo para la sección crítica.
		Consumidor[] consumidores = new Consumidor[2];
		Productor[] productores = new Productor[2];
		for (int i = 0; i < consumidores.length; i++) {
			consumidores[i] = new Consumidor("C" + (i + 1), vacio, lleno, mutex);
			consumidores[i].start();
		}
		for (int i = 0; i < productores.length; i++) {
			productores[i] = new Productor("P" + (i + 1), vacio, lleno, mutex);
			productores[i].start();
		}
	}
}
