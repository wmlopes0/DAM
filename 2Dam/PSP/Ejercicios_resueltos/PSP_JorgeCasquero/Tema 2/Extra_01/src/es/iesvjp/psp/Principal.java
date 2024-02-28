package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Principal {
	static int numCochesIzq = 0;
	static int numCochesDer = 0;

	public static void main(String[] args) {
		Semaphore mutexGlobal = new Semaphore(1);
		Semaphore mutexIzquierdo = new Semaphore(1);
		Semaphore mutexDerecho = new Semaphore(1);
		CocheIzq[] cochesIzq = new CocheIzq[10];
		CocheDch[] cochesDer = new CocheDch[10];
		for (int i = 0; i < 10; i++) {
			cochesIzq[i] = new CocheIzq(i, mutexGlobal, mutexIzquierdo);
			cochesDer[i] = new CocheDch(i + 10, mutexGlobal, mutexDerecho);
			cochesIzq[i].start();
			cochesDer[i].start();
		

		}
	}
}
