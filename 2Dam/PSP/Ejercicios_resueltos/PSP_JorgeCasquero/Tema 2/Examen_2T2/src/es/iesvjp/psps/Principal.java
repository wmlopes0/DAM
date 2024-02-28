package es.iesvjp.psps;

public class Principal {
	final static int DUENDESPRODUCTORES = 5; // Número de duendes que introduciran juguetes
	final static int DUENDESCONSUMIDORES = 6; // Número de uendes que sacarán juguetes

	public static void main(String[] args) {

		Maquina maquina = new Maquina();
		DuendeProductor[] vProd = new DuendeProductor[DUENDESPRODUCTORES];
		DuendeConsumidor[] vCons = new DuendeConsumidor[DUENDESCONSUMIDORES];

		int j = 0; // Esta variable sirve para asignar un nombre diferente a los duendes.

		/* Nos creamos los duendes productores y "los ponemos a trabajar" */
		for (int i = 0; i < vProd.length; i++) {
			vProd[i] = new DuendeProductor("Duende-" + j, maquina);
			vProd[i].start();
			j++;
		}

		/* Ahora con los duendes consumidores */
		for (int i = 0; i < vCons.length; i++) {
			vCons[i] = new DuendeConsumidor("Duende-" + j, maquina);
			vCons[i].start();
			j++;
		}

	}

}
