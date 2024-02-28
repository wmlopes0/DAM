package psp_ut1_ejercicio13;

public class Hilo {
	private boolean turno = false;
	
	
	public synchronized void put(String pintar) throws InterruptedException {
		while (turno) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Thread.sleep(500);
		System.out.println(pintar);
		turno = true;
		notifyAll();
		
	}
	
	public synchronized void get(String pintar) throws InterruptedException {
		while (!turno) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Thread.sleep(500);
		System.out.println(pintar);
		turno = false;
		notifyAll();
	}
}
