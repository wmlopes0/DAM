package com.iesvjp.psp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
	private static int puerto = 8000;
	private Socket cliente;
	private ObjectInputStream flujoEntrada = null;
	private ObjectOutputStream flujoSalida = null;

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setConexion();
		Persona person = cliente.recibiendoMensajeServidor();
		System.out.println("[Cliente recibiendo] " + person.getNombre());
		person.setEdad(60);
		person.setNombre("Pepe");
		System.out.println("[Cliente enviado]:" + person.getNombre());
		cliente.enviarMensajeServidor(person);
		cliente.closeStreamsSockets();

	}

	public void setConexion() {
		try {
			cliente = new Socket("localhost", puerto);
			System.out.println("Conexion con Servidor en el puerto " + puerto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Persona recibiendoMensajeServidor() {
		try {
			Persona person;
			flujoEntrada = new ObjectInputStream(cliente.getInputStream());
			person = (Persona) flujoEntrada.readObject();
			return person;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void enviarMensajeServidor(Persona person) {
		try {
			flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
			flujoSalida.writeObject(person);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeStreamsSockets() {
		try {
			flujoSalida.close();
			flujoEntrada.close();
			cliente.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
