package com.iesvjp.psp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	private static int puerto = 8000;
	private static Socket cliente;

	public static void setConexion() {
		try {
			cliente = new Socket("localhost", puerto);
			System.out.println("Socket cliente inciado.Ya puede enviar mensajes.");
		} catch (IOException e) {
			System.out.println("Error de Conexion" + e.getMessage());
		}

	}

	public static void main(String[] args) {

		setConexion();
		HiloRecibir recibir = new HiloRecibir(cliente);
		recibir.start();
		HiloEnviar enviar = new HiloEnviar("Cliente", cliente);
		enviar.start();

	}
}
