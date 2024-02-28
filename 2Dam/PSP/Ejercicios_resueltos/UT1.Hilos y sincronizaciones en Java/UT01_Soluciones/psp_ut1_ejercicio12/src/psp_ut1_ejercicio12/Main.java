package psp_ut1_ejercicio12;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Cola cola = new Cola();
		Productor prod = new Productor("David",cola);
		ArrayList<Consumidor> lConsumidores = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Consumidor cons = new Consumidor("Ladron "+i,cola);
			lConsumidores.add(cons);
		}
		
		prod.start();
		for (int i = 0; i < lConsumidores.size(); i++) {
			lConsumidores.get(i).start();
		}
	}

}
