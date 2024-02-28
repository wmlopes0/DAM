package psp_ut1_ejercicio1;

public class Sonido implements Runnable {
	
	@Override
	public void run() { /* código concurrente */
		for (int i = 0; i < 20; i++) {
			System.out.println("Beep!!");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
