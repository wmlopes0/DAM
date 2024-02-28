package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class HiloC implements Runnable {

	private Semaphore esperaPorC;

	public HiloC(Semaphore esperaPorC) {
		this.esperaPorC = esperaPorC;
	}

	@Override
	public void run() {
		System.out.println("C ");
		esperaPorC.release();
		Thread.yield();

		for (int i = 0; i < 9; i++) {
			System.out.println("C ");
			Thread.yield();
		}
	}

}
