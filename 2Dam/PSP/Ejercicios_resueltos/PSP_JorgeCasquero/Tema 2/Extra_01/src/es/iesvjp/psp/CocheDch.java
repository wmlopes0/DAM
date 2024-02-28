package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class CocheDch extends Thread {
	private int id;
	public Semaphore mutexGlobal;
	private Semaphore mutexDerecho;

	public CocheDch(int id, Semaphore mutexGlobal, Semaphore mutexDerecho) {
		this.id = id;
		this.mutexGlobal = mutexGlobal;
		this.mutexDerecho = mutexDerecho;
	}

	@Override
	public void run() {
		try {
			mutexDerecho.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		Principal.numCochesDer++;
		if (Principal.numCochesDer == 1) {
			try {
				mutexGlobal.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}
		mutexDerecho.release();
		System.out.println("Pasa el coche " + id + " a la derecha");
		Thread.yield();

		try {
			mutexDerecho.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		Principal.numCochesDer--;
		if (Principal.numCochesDer == 0) {
			System.out.println("Salió el ultimo coche de la derecha");
			mutexGlobal.release();
		}

		mutexDerecho.release();
	}

}
