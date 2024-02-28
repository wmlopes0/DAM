package es.iesvjp.psp;

import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class HiloB implements Runnable {

	private Semaphore esperaB;
	private Semaphore esperaC;

	public HiloB(Semaphore esperaB, Semaphore esperaC) {
		this.esperaB = esperaB;
		this.esperaC = esperaC;
	}

	@Override
	public void run() {

		try {
			esperaC.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("B ");
			esperaB.release();
			Thread.yield();
		}

	}

}
