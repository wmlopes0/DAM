package com.vjp.psp;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int puerto = 8000;
	private static ServerSocket servidor;

	public static void main(String[] args) {
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor iniciado...");
			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Cliente conectado:" + cliente.toString());
				HiloServidor hilo = new HiloServidor(cliente);
				hilo.start();
				

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
