package com.iesvjp.psp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static final int PUERTO = 8000;

	public static void main(String[] args) {
		ServerSocket servidor = null;
		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexión en el puerto " + PUERTO);
			Socket cliente = null;
			while (true) {
				cliente = servidor.accept();
				System.out.println("Conexión establecida con " + cliente.toString());
				HiloServidor hilo = new HiloServidor(cliente);
				hilo.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (servidor != null) {
					servidor.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
