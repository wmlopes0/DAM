package es.iesvjp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author jorca
 *
 */
public class Servidor {
	private static int puerto = 8000;
	private static Socket cliente;
	private static ServerSocket servidor;
	private static DataInputStream flujoEntrada = null;
	private static DataOutputStream flujoSalida = null;

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.setConexion();
		String enviado;
		String recibido;
		Scanner teclado = new Scanner(System.in);
		do {
			recibido = getMensajeCliente();
			System.out.println("[RECIBIDO]" + recibido);
			enviado = new StringBuilder(recibido).reverse().toString().toUpperCase();
			setMensajeCliente(enviado);
		} while (!recibido.equals("salir"));
		teclado.close();
		closeStreamsSockets();

	}

	private void setConexion() {
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexion entrante en el puerto " + puerto + "...");
			cliente = servidor.accept();
			System.out.println("Conexion establecida con: " + cliente.getInetAddress().getHostName() + "\n\n\n");
		} catch (IOException e) {
			System.out.println("Error en establecerConexion " + e.getMessage());
		}
	}

	// establecemos un flujo de entrada para recibir mensajes desde el cliente
	private static String getMensajeCliente() {
		String recibido;
		try {
			flujoEntrada = new DataInputStream(cliente.getInputStream());// el cliente me envia un mensaje
			recibido = flujoEntrada.readUTF();
		} catch (IOException e) {
			System.out.println("setFLujoEntrada" + e.getMessage());
			recibido = "error";
		}
		return recibido;
	}

//
	/**
	 * creamos un flujo de salida hacia el cliente
	 * 
	 * @param sms
	 */
	private static void setMensajeCliente(String sms) {
		try {
			flujoSalida = new DataOutputStream(cliente.getOutputStream());
			flujoSalida.writeUTF(sms);
			flujoSalida.flush();
			System.out.println("[ENVIADO]" + sms);
		} catch (IOException e) {
			System.out.println("setFlujoSalida" + e.getMessage());
		}
	}

	/**
	 * Cerramos los flujos de entrada/salida y los Sockets Cliente y servidor
	 */
	private static void closeStreamsSockets() {
		try {
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
			servidor.close();
		} catch (IOException e) {
			System.out.println("closeStreamsSockets()" + e.getMessage());
		}

	}

}