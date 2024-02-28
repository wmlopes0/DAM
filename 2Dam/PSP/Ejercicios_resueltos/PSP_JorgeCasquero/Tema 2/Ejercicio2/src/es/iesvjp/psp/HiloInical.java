package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class HiloInical extends Thread{
	private Semaphore semaforo;
	private int id;
	
	public HiloInical(Semaphore semaforo, int id) {
		
		this.semaforo = semaforo;
		this.id = id;
	}
	
	@Override
	public void run() {
	System.out.println("Hilo "+this.id);
	
	semaforo.release();
	
	}

}
