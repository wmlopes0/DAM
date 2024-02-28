package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Jaula {

	private Semaphore comedero;
	private Semaphore columpio;

	public Jaula() {
		this.comedero = new Semaphore(3);
		this.columpio = new Semaphore(1);
	}

	public void comer(String nombre) {
		try {
			this.comedero.acquire();// quitamos un permiso al semaforo del comedero
			System.out.println(nombre + " está comiendo alpiste.<0");
			Thread.sleep(5000);
			System.out.println("\t" + nombre + "terminó de comer");
			this.comedero.release();// DEVOLVEMOS EL PERMISO CUANDO ESTE HA TERMINADO
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void columpiarse(String nombre) {
		try {
			this.columpio.acquire();// cuando se columpian quitamos el unico permiso que existe en este semaforo
			System.out.println("\t\t" + nombre + "está columpiandose. ||__||");
			Thread.sleep(6000);
			System.out.println("\t\t\t" + nombre + "terminó de columpiarse//__//");
			this.columpio.release();// devolvemos el permiso
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
