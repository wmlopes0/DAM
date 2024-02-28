package com.iesvjp.psp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int puerto = 8000;
	private Socket cliente;
	private ServerSocket servido;
	private ObjectInputStream flujoEntrada = null;
	private ObjectOutputStream flujoSalida = null;

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.setConexion();

		Persona persona = new Persona("Jorge", 25);
		System.out.println("[Servidor enviado]" + persona);
		servidor.enviarMensaje(persona);
		Persona persona2 = servidor.recibirMensajeCliente();
		System.out.println("[Servidor recibiendo]" + persona2);
		servidor.closeStreamSockets();
	}
}
