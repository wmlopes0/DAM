package es.iesvjp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
	private static int puerto = 8000;
	private Socket cliente;
	private ServerSocket servidor;
	private DataInputStream flujoEntrada = null;
	private DataOutputStream flujoSalida = null;

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.setConexion();
		servidor.setMensajeCliente("Saludos al Cliente de parte del Servidor");
		servidor.getMensajeCliente();
		servidor.closeStreamsSockets();
	}

	public void setConexion() {
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexión entrante en el puerto " + puerto + "...");
			cliente = servidor.accept();
			System.out.println("Conexión establecida con: " + cliente.getInetAddress().getHostName() + "\n\n\n");
		} catch (IOException e) {
			System.out.println("Error en establecerConexion " + e.getMessage());
		}
	}

	/**
	 * Establecemos un flujo de entrada para recibir mensajes desde el Cliente
	 */
	public void getMensajeCliente() {
		try {
			flujoEntrada = new DataInputStream(cliente.getInputStream());
			//El cliente me envía un mensaje
			System.out.println("Recibiendo del cliente: " + flujoEntrada.readUTF());
		} catch (IOException e) {
			System.out.println("setFlujoEntrada " + e.getMessage());
		}
	}

	/**
	 * Creamos un flujo de salida hacia el Cliente
	 * 
	 * @param msj
	 */
	public void setMensajeCliente(String msj) {
		try {
			flujoSalida = new DataOutputStream(cliente.getOutputStream());
			flujoSalida.writeUTF(msj);
		} catch (IOException e) {
			System.out.println("setFlujoSalida " + e.getMessage());
		}
	}

	/**
	 * UT3. Sockets. Comunicación entre procesos Programación de Servicios y
	 * Procesos Marcos Fernández Sellers 24 Cerramos los flujos de entrada/salida y
	 * los Sockets Cliente y Servidor
	 */
	public void closeStreamsSockets() {
		try {
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
			servidor.close();
		} catch (IOException e) {
			System.out.println("closeStreamSockets " + e.getMessage());
		}
	}
}
