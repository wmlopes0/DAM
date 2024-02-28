package com.iesvjp.psp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	private static int puerto = 8000;
	// private Socket cliente;

	public static void main(String[] args) {
		Socket cliente;
		DataInputStream flujoSalida;
		ObjectOutputStream flujoEntrada;
		try {
			cliente = new Socket("localhost", puerto);
			System.out.println("Conexion establecida con el servidor");
			Habitacion habitacion = new Habitacion();
			flujoEntrada = new ObjectOutputStream(cliente.getOutputStream());
			// hasta que no se corte la conexion no se deja de mostrar el mensaje
			// correspondiente
			while (true) {

				habitacion.generarTemperatura();
				flujoEntrada.writeObject(habitacion);
				flujoEntrada.flush();
				flujoEntrada.reset();
				flujoSalida = new DataInputStream(cliente.getInputStream());
				String mensaje = flujoSalida.readUTF();

				System.out.println(mensaje);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
