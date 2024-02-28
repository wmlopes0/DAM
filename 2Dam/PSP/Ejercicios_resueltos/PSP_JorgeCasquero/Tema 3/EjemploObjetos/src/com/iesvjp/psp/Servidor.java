package com.iesvjp.psp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int puerto = 8000;
	private static Socket cliente;
	private static ServerSocket servidor;
	private static ObjectInputStream flujoEntrada = null;
	private static ObjectOutputStream flujoSalida = null;

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.setConexion();
		Persona persona = new Persona("pepe", 25);
		servidor.enviarMensajeCliente(persona);
		Persona person = servidor.recibirMensajeCliente();
		System.out.println("[Servidor recibiendo]" + person.getNombre());
		servidor.closeStreamsSockets();
	}

	public void setConexion() {
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexion entrante en el puerto " + puerto);
			cliente = servidor.accept();
			System.out.println("Conexion establecida con " + cliente.getInetAddress().getHostName());

		} catch (IOException e) {
			System.out.println("Error en establecer Conexion " + e.getMessage());
		}

	}

	public Persona recibirMensajeCliente() {
		Persona person = null;
		try {
			flujoEntrada = new ObjectInputStream(cliente.getInputStream());
			person = (Persona) flujoEntrada.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return person;

	}

	public void enviarMensajeCliente(Persona persona) {
		try {
			flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
			flujoSalida.writeObject(persona);
			flujoSalida.flush();
			flujoSalida.reset();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void closeStreamsSockets() {
		try {
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
