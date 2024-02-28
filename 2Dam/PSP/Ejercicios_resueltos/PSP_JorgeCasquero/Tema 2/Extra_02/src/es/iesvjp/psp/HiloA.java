package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class HiloA implements Runnable {
	private Semaphore esperaB;

	public HiloA(Semaphore esperaB) {
		this.esperaB = esperaB;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				esperaB.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A ");
			Thread.yield();
		}
	}
}
