package es.iesvjp.psp;

public class Embotelladora2L extends Thread {
	Caja caja;

	public Embotelladora2L(Caja caja) {
		this.caja = caja;
	}

	@Override
	public void run() {
		for (int i = 0; i < 9; i++) {
			if (i % 2 == 0) {
				caja.llenarBotella2Litros(i);
			}
		}
	}

}
