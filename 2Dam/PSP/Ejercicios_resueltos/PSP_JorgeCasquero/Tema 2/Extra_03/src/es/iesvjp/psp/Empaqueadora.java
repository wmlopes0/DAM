package es.iesvjp.psp;

public class Empaqueadora extends Thread {
	Caja caja;

	public Empaqueadora(Caja caja) {
		this.caja = caja;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {

			caja.empaquetar();

		}
	}

}
