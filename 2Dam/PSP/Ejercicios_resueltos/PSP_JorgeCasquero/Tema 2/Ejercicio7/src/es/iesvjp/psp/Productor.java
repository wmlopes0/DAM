package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Productor extends Thread {
	String name;
	Semaphore vacio;
	Semaphore lleno;
	Semaphore mutex;

	public Productor(String name, Semaphore vacio, Semaphore lleno, Semaphore mutex) {
		this.name = name;
		this.vacio = vacio;
		this.lleno = lleno;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {

			try {
				lleno.acquire();// tomamos un permiso del semáforo (inicialmente son 5)
				mutex.acquire();// para acceder en exclusion mutua a la cola
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String produce = name + "-" + i;
			// insertamos un elemento en la cola
			Buffer.cola.offer(produce);
			System.out.println("Productor " + name + " produce " + produce);
			Buffer.mostrarCola();
			mutex.release();// salimos de la exclusión mutua
			vacio.release();/*
							 liberamos un permiso de vacío, avisamos que hay un elemento,inicialmente está
							 a 0
							 */
		}
	}

}
