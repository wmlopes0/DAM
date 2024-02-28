package com.iesvjp.psp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int puerto = 8000;
	private static Socket cliente;
	private static ServerSocket servidor = null;
	private static DataInputStream flujoEntrada = null;
	private static DataOutputStream flujoSalida = null;

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.setConexion();
		int enviado;
		int recibido;

		// while (true) {
		recibido = getMensajeCliente();
		System.out.println("[Recibido]" + recibido);
		enviado = (int) Math.pow(recibido, 2);
		setMesajeCliente(enviado);
		closeStreamSocket();
		// }

	}

	private static int getMensajeCliente() {
		int recibido;
		try {
			// el cliente envia el mensaje
			flujoEntrada = new DataInputStream(cliente.getInputStream());
			recibido = flujoEntrada.readInt();
		} catch (Exception e) {
			System.out.println("setFlujoEntrada" + e.getMessage());
			return 0;
		}
		return recibido;
	}

	private static void setMesajeCliente(int enviado) {
		try {
			flujoSalida=new DataOutputStream(cliente.getOutputStream());
			flujoSalida.writeInt(enviado);
			flujoSalida.flush();
			System.out.println("[Enviado]" + enviado);
		} catch (IOException e) {
			System.out.println("Set Flujo salida" + e.getMessage());
		}
	}

	private void setConexion() {
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexion entrante en el puerto" + puerto);
			cliente = servidor.accept();
			System.out.println("Conexion establecida con " + cliente.getInetAddress().getHostName() + "\n\n\n");
		} catch (IOException e) {
			System.out.println("Error en establecer conexion" + e.getMessage());
		}

	}

	private static void closeStreamSocket() {
		try {
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
			servidor.close();
		} catch (IOException e) {
			System.out.println("closeStreamSockets()" + e.getMessage());
		}

	}

}
