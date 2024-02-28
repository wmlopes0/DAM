package es.iesvjp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private static int puerto = 8000;
	private static Socket cliente;
	private static DataInputStream flujoEntrada = null;
	private static DataOutputStream flujoSalida = null;

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setConexion();
		String mensaje;
		String recibido;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Introduce una cadena, por favor: ");
			mensaje = teclado.next();
			setMensajeServidor(mensaje);
			recibido = getMensajeServidor().toUpperCase();
			System.out.println("[Recibido]"+recibido);
		} while (!mensaje.equalsIgnoreCase("salir"));
		teclado.close();
		closeStreamsSockets();
	}

	private void setConexion() {
		try {
			cliente = new Socket("localHost", puerto);
			System.out.println("Socket cliente iniciado...");

		} catch (IOException e) {
			System.out.println("Error en establecerConecion" + e.getMessage());

		}

	}

	private static String getMensajeServidor() {
		String mensaje;
		try {
			flujoEntrada = new DataInputStream(cliente.getInputStream());// el servidor me envia un mensaje
			mensaje = flujoEntrada.readUTF();
		} catch (IOException e) {
			System.out.println("setFlujoEntrada" + e.getMessage());
			mensaje = "error";
		}
		return mensaje;
	}

	private static void setMensajeServidor(String mensaje) {

		try {
			flujoSalida = new DataOutputStream(cliente.getOutputStream());
			flujoSalida.writeUTF(mensaje);
			System.out.println("[Enviado]" + mensaje);
		} catch (IOException e) {
			System.out.println("setFlujoSalida " + e.getMessage());
		}

	}

	//cerramos los flujos de entrada/salida y los socket cliente y servidor
	private static void closeStreamsSockets() {
		try {
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
		} catch (IOException e) {
			System.out.println("closeStreamSockets " + e.getMessage());
		}

	}

}
