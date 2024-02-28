package com.iesvjp.psp;

import java.io.DataInputStream;
import java.net.Socket;

public class Cliente {
	public static int puerto = 8000;

	public static void main(String[] args) {
		Socket cliente;

		try {
			cliente = new Socket("localhost", puerto);

			DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
			String fechaServer = flujoEntrada.readUTF();
			System.out.println("-->FECHA del sistema:{ " + fechaServer + " }");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
