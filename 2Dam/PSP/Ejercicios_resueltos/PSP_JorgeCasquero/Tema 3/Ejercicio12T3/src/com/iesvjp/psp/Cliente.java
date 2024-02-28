package com.iesvjp.psp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	private static int puerto = 8000;

	public static void main(String[] args) {
		Socket cliente;
		Paciente paciente = new Paciente();
		ObjectOutputStream flujoSalida;
		HiloRecibir recibir;
		try {
			cliente = new Socket("localhost", puerto);
			recibir = new HiloRecibir(cliente);
			recibir.start();
			flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
			while (true) {
				paciente.actualizarConstantesVitales();
				flujoSalida.writeObject(paciente);
				flujoSalida.flush();
				flujoSalida.reset();
				Thread.sleep(10000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}
}
