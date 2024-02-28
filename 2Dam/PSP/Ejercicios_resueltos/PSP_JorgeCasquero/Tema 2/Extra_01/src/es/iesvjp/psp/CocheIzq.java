package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class CocheIzq extends Thread {
	private int id;
	public Semaphore mutexGlobal;
	private Semaphore mutexIzq;

	public CocheIzq(int id, Semaphore mutexGlobal, Semaphore mutexIzq) {
		this.id = id;
		this.mutexGlobal = mutexGlobal;
		this.mutexIzq = mutexIzq;
	}

	@Override
	public void run() {
		try {
			mutexIzq.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		Principal.numCochesIzq++;
		if (Principal.numCochesIzq == 1) {
			try {
				mutexGlobal.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}
		mutexIzq.release();
		System.out.println("Pasa el coche " + id + " a la izquierda");
		Thread.yield();

		try {
			mutexIzq.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		Principal.numCochesIzq--;
		if (Principal.numCochesIzq == 0) {
			System.out.println("Salió el ultimo coche de la izquierda");
			mutexGlobal.release();
		}

		mutexIzq.release();
	}

}
