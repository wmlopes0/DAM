package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Limpiador extends Thread {

	private Banio banio;

	public Limpiador(Banio banio) {

		this.banio = banio;
	}

	@Override
	public void run() {
		try {
			this.banio.limpiar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
