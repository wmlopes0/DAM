package psp_ut1_ejercicio1;

public class Tiempo implements Runnable {
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Se te está acabando el tiempo");
			try {
					Thread.sleep(5000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
	}
}
