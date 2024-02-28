package com.iesvjp.psp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;

public class Servidor {

	private static int puerto = 8000;

	public static void main(String[] args) {
		try {

			ServerSocket servidor = new ServerSocket(puerto);
			System.out.println("Server iniciado...");
			while (true) {
				Socket cliente = new Socket();
				cliente = servidor.accept();
				HiloServidor hilo = new HiloServidor(cliente);
				hilo.start();// se atiende al cliente

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
