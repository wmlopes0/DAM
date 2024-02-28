package psp_ut1_ejercicio2;

public class Hola implements Runnable {
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Hola");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage()); 
			}
		}
	}
}
