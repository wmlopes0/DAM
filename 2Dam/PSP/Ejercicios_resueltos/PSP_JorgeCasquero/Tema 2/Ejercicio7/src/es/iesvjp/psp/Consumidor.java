package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {
	String name;
	Semaphore vacio;
	Semaphore lleno;
	Semaphore mutex;

	public Consumidor(String name, Semaphore vacio, Semaphore lleno, Semaphore mutex) {
		this.name = name;
		this.vacio = vacio;
		this.lleno = lleno;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		// cada consumidor consume 5 productos
		for (int i = 0; i < 5; i++) {
			try {
				vacio.acquire();// tomamos un permiso del semáforo, como no tiene se bloquea hasta que el
								// productor produzca
				mutex.acquire();// para acceder en exclusión mutua a la cola
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String consumido = Buffer.cola.poll();// tomamos un valor de la cola y se borra
			System.out.println("Consumidor " + name + "==>" + consumido);
			Buffer.mostrarCola();

			mutex.release();// salimos de la exclusión mutua
			lleno.release();// liberamos un permiso del semáforo lleno
			Thread.yield();
		}
	}

}
