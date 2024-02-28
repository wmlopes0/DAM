package com.iesvjp.psp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author jorca
 *
 */
/**
 * @author jorca
 *
 */
public class HiloEnviar extends Thread {
	private String nombre;
	private Socket socket;
	private DataOutputStream flujoSalida;

	public HiloEnviar(String nombre, Socket socket) {
		this.nombre = nombre;
		this.socket = socket;
		this.flujoSalida = null;
	}

	/**
	 * este bucle se ejecuta hasta que se cancela nuestra ejecucion
	 *
	 */
	@Override
	public void run() {
		while (setMensaje()) {

		}
	}

	/**
	 * Metodo que envia el mensaje que se introduzca tanto en el servidor como en el
	 * cliente
	 * 
	 * @return true si se envia el mensaje o false si este falla
	 */
	public boolean setMensaje() {
		Scanner teclado = new Scanner(System.in);
		String mensaje = teclado.nextLine();
		try {
			flujoSalida = new DataOutputStream(socket.getOutputStream());
			flujoSalida.writeUTF("[" + nombre + "]: " + mensaje);
			return true;
		} catch (IOException e) {
			System.out.println("setFlujoSalida" + e.getMessage());
			return false;
		}

	}

}
