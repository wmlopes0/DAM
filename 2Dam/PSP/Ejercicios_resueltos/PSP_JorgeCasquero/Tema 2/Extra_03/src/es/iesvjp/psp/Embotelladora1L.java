package es.iesvjp.psp;

public class Embotelladora1L extends Thread {
	Caja caja;

	public Embotelladora1L(Caja caja) {
		this.caja = caja;
	}

	@Override
	public void run() {
		for (int i = 0; i < 14; i++) {
			if (i % 2 != 0) {
				caja.llenarBotellaLitro(i);
			}

		}
	}

}
