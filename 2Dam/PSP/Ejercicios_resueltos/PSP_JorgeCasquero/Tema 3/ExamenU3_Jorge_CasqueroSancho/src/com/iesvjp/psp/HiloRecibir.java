package com.iesvjp.psp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloRecibir extends Thread {

	private Socket cliente;
	private DataInputStream flujoEntrada;

	public HiloRecibir(Socket cliente) {
		this.cliente = cliente;
		try {
			this.flujoEntrada = new DataInputStream(cliente.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String mensaje;
		while (true) {
			try {
				mensaje = this.flujoEntrada.readUTF();
				System.out.println(mensaje);
			} catch (IOException e) {
				try {
					this.cliente.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		}
	}
}