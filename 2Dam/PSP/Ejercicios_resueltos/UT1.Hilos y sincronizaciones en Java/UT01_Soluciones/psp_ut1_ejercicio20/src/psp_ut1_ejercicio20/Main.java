package psp_ut1_ejercicio20;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Peluqueria peluqueria = new Peluqueria();
		Barbero barbero = new Barbero("Barbero", peluqueria);
		ArrayList<Cliente> lClientes = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			Cliente cliente = new Cliente("Cliente " + i, peluqueria);
			lClientes.add(cliente);
		}
		
		barbero.start();
		for (int i = 0; i < 10; i++) {
			lClientes.get(i).start();
		}
	}

}
