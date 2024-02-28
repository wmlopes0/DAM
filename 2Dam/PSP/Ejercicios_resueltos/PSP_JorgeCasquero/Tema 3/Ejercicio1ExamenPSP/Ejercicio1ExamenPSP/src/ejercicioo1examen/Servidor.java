package ejercicioo1examen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Servidor {
	private static int puerto = 8000;
	private static ServerSocket servidor;

	public static void main(String[] args) {
		try {
			servidor = new ServerSocket(puerto);

			System.out.println(" Servidor iniciado.... ");
			while (true) {
				
				Socket cliente = servidor.accept();// esperando cliente
				System.out.println("cliennte conectado: "+cliente.toString());
				HiloServidor hilo = new HiloServidor(cliente);
				hilo.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
