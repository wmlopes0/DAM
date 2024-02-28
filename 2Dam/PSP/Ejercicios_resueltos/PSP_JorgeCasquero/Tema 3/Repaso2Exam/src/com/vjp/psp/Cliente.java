package com.vjp.psp;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	private static int puerto = 8000;
	private Socket clienteS;
	private DataInputStream flujoEntrada = null;
	private ObjectOutputStream flujoSalida = null;

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setConexion();

		System.out.println("\n***** sistema de comprobacion de itv****");
		cliente.inicializarFlujos();
		cliente.enviarCocheServidor(Utiles.pedirDatosCoche());
		cliente.recibirMensajeServidor();

		cliente.closeStreamSockets();
	}

	public void setConexion() {
		try {
			clienteS = new Socket("localhost", puerto);
			System.out.println("Socket cliente iniciado...");
		} catch (IOException e) {
			System.out.println("Error en establecerConexion " + e.getMessage());
		}
	}

	public void inicializarFlujos() {
		try {
			flujoEntrada = new DataInputStream(clienteS.getInputStream());
			flujoSalida = new ObjectOutputStream(clienteS.getOutputStream());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public void recibirMensajeServidor() {

		try {
			System.out.println(flujoEntrada.readUTF());
			System.out.println("mensaje recibido");

		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("recibirMensajeServidor " + e.getMessage());
		}
	}

	public void enviarCocheServidor(Coche coche) {

		try {
			flujoSalida.writeObject(coche);
			flujoSalida.flush();
			flujoSalida.reset();
		} catch (IOException e) {
			System.out.println("setFlujoSalida " + e.getMessage());
		}
	}

	public void closeStreamSockets() {
		try {
			flujoEntrada.close();
			flujoSalida.close();
			clienteS.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
