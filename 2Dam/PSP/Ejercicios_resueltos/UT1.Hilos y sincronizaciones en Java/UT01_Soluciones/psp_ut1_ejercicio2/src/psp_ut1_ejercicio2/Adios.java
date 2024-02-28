package psp_ut1_ejercicio2;

public class Adios implements Runnable {
	
	@Override
	public void run() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < 100; i++) {
			System.out.println("Adios");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage()); 
			}
		}
	}
}
