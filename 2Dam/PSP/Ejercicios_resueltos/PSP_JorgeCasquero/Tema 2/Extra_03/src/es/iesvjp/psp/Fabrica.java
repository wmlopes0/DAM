package es.iesvjp.psp;

import java.util.Iterator;

public class Fabrica {

	public static void main(String[] args) {
		Caja caja = new Caja();
		Embotelladora1L[] embotelladora1l = new Embotelladora1L[1];
		Embotelladora2L[] embotelladora2l = new Embotelladora2L[1];
		Empaqueadora[] empaquetadora = new Empaqueadora[1];

		for (int i = 0; i < embotelladora1l.length; i++) {
			embotelladora1l[i] = new Embotelladora1L(caja);
			embotelladora1l[i].start();

		}
		for (int i = 0; i < embotelladora2l.length; i++) {
			embotelladora2l[i] = new Embotelladora2L(caja);
			embotelladora2l[i].start();

		}
		for (int i = 0; i < empaquetadora.length; i++) {
			empaquetadora[i] = new Empaqueadora(caja);
			empaquetadora[i].start();

		}
	}

}
