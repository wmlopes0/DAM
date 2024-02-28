package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Principal {
	public static void main(String[] args) {

		Semaphore esperaporB = new Semaphore(0);
		Semaphore esperaporC = new Semaphore(0);

		new Thread(new HiloA(esperaporB)).start();
		new Thread(new HiloB(esperaporB, esperaporC)).start();
		new Thread(new HiloC(esperaporC)).start();
	}

}
