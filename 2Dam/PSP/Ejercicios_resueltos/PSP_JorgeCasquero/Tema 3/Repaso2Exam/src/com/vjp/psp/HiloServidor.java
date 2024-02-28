package com.vjp.psp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.LocalDate;

public class HiloServidor extends Thread {
	ObjectInputStream flujoEntrada;
	DataOutputStream flujoSalida;
	Socket cliente;

	public HiloServidor(Socket cliente) {
		this.cliente = cliente;
		try {
			flujoEntrada = new ObjectInputStream(cliente.getInputStream());
			flujoSalida = new DataOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			Coche coche = (Coche) flujoEntrada.readObject();
			System.out.println("[Servidor]Consultar Coche;" + coche.toString());
			flujoSalida.writeUTF(comprobarFechaITV(coche));
			flujoEntrada.close();
			flujoSalida.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String comprobarFechaITV(Coche coche) {

		String mensaje = "";
		LocalDate hoy = LocalDate.now();
		int anioMatricula = coche.getAnioMatricula();
		int fechaUltimaITV = coche.getFechaUltimaITV();

		int anios = hoy.getYear() - anioMatricula;
		System.out.println("Años: " + anios);

		if (anios < 4) {
			mensaje = "=> El coche " + coche.getMatricula() + " esta exento de pagar la itv";

		}
		if (fechaUltimaITV != -1) {
			if (anios >= 4 && anios <= 10) {
				mensaje = "=> El coche " + coche.getMatricula() + "tiene que pasar la itv en el "
						+ (fechaUltimaITV + 2);
			} else {
				mensaje = "=> El coche " + coche.getMatricula() + "tiene que pasar la itv en el "
						+ (fechaUltimaITV + 1);
			}
		} else if (anios >= 4) {
			mensaje = "=> El coche tiene más de 4 años y no hay datos sobre la ultima itv";
		}
		return mensaje;
	}

}
