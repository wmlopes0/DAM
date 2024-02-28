package com.iesvjp.psp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int puerto = 8000;

	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(puerto);
			System.out.println("Servidor iniciado ...");
			while (true) {
				Socket cliente = new Socket();
				cliente = servidor.accept();// esperamos cliente
				HiloServidor hilo = new HiloServidor(cliente);
				hilo.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
