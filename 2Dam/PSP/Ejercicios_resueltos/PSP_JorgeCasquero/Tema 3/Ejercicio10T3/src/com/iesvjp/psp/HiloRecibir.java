package com.iesvjp.psp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HiloRecibir extends Thread {

	private Socket socket;
	private DataInputStream flujoEntrada;

	public HiloRecibir(Socket socket) {
		this.socket = socket;
		this.flujoEntrada = null;

	}

	/**
	 * este bucle se ejecuta hasta que se cancela nuestra ejecucion
	 *
	 */
	@Override
	public void run() {
		while (getMensaje()) {

		}
	}

	/**
	 * Método que recibe un mensaje y lo pinta por consola.
	 */
	public boolean getMensaje() {
		String mensaje;
		try {
			flujoEntrada = new DataInputStream(socket.getInputStream());
			mensaje = flujoEntrada.readUTF();
			System.out.println(mensaje);
			return true;
		} catch (IOException e) {
			System.out.println("getFlujoSalida" + e.getMessage());
			return false;
		}

	}

}
