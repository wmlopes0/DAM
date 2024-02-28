package com.iesvjp.psp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int puerto = 8000;
	private static ServerSocket servidor;
	private static Socket cliente;
	private ObjectOutputStream flujoSalida = null;
	private ObjectInputStream flujoEntrada = null;

	public static void main(String[] args) {
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexion en el puerto " + puerto);
			while (true) {
				cliente = servidor.accept();
				System.out.println("conexion establecida con " + cliente.getInetAddress().getHostName());
				HiloServidor recibir = new HiloServidor(cliente);
				recibir.start();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
