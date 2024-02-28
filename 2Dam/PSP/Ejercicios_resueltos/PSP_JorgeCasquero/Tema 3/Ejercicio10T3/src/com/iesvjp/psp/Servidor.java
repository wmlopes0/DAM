package com.iesvjp.psp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int puerto = 8000;
	private static Socket cliente;
	private ServerSocket servidor;

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.setConexion();
		HiloRecibir recibir = new HiloRecibir(cliente);
		recibir.start();
		HiloEnviar enviar = new HiloEnviar("Servidor", cliente);
		enviar.start();

	}

	public void setConexion() {
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexion entrante en el puerto " + puerto + " ... ");
			cliente = servidor.accept();
			System.out.println("Conexion establecida con " + cliente.getInetAddress().getHostName() + "\n\n\n");
			System.out.println("Ya puede empezxar la conexion.");

		} catch (IOException e) {

			System.out.println("Error en establecer Conexion" + e.getMessage());
		}
	}

}