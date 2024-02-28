package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Parking {
	static final int CAPACIDAD = 2;
	boolean[] vPlazas = { false, false};

	private Semaphore semaforo = new Semaphore(CAPACIDAD, true);
	private Semaphore comprobador = new Semaphore(1);

	public void entrarParking(Coche coche) {
		try {
			semaforo.acquire();
			comprobador.acquire();
			if (!vPlazas[0]) {
				vPlazas[0] = true;
				coche.setPlaza(0);

			} else {
				vPlazas[1] = true;
				coche.setPlaza(1);
			}
			comprobador.release();
			System.out.println("\t\t=>El coche número " + coche.getNumcoche() + " entra al Parking. Plazas libres:"
					+ semaforo.availablePermits());
			System.out.println("----- El coche nº " + coche.getNumcoche() + " ha aparcado en la plaza " + coche.getPlaza() + ". Coches esperando " + semaforo.getQueueLength());

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void salirParking(Coche coche) {

		try {
			System.out.println("\t<=El coche número " + coche.getNumcoche() + "sale del Parking.Plazas libres:"
					+ semaforo.availablePermits());
			comprobador.acquire();
			vPlazas[coche.getPlaza()] = false;
			coche.setPlaza(0);
			comprobador.release();
			semaforo.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
