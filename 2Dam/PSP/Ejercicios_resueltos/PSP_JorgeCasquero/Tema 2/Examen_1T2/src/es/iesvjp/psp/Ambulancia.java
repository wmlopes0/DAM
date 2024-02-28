package es.iesvjp.psp;

import java.util.Iterator;

public class Ambulancia extends Thread {
	private int num;
	Parking parking;
	private boolean aparcado = false;

	public Ambulancia(int id, Parking parking) {
		this.num = id;
		this.parking = parking;

	}

	public void run() {
		for (int i = 0; i < 6; i++) {

			try {
				System.out.println("Arranca la ambulancia número " + num);
				parking.aparcar(num);
				sleep(4000);
				Thread.yield();
				parking.salir(num);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
