package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class HiloFinal extends Thread {
	private Semaphore semaforo;
	private int num;

	public HiloFinal(Semaphore semaforo, int num) {
		super();
		this.semaforo = semaforo;
		this.num = num;
	}

	@Override
	public void run() {

		try {
			//este hiulo recoje elnumero de permisos que le pasamos en el constructor 
			semaforo.acquire(this.num);
			
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		System.out.println("HILO FINAL");
	}
}
