package psp_ut1_ejercicio1;

import java.time.LocalDateTime;

public class Hora implements Runnable {
	
	@Override
	public void run() { /* código concurrente */
		for (int i = 0; i < 10; i++) {
			System.out.println(LocalDateTime.now().toString());
			try {
					Thread.sleep(1000);/* esperar 1000 ms */
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
	}
}
