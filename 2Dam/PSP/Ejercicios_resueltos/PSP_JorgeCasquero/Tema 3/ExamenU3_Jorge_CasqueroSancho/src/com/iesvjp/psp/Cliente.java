package com.iesvjp.psp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	private static int puerto = 8000;
	private static Socket cliente;

	public static void main(String[] args) {
		// declaramos las variables
		// Socket cliente;
		Habitacion habitacion = new Habitacion();
		ObjectOutputStream flujoSalida;
		HiloRecibir recibir;
		try {
			// creamos la conexion
			// cliente = new Socket("localhost", puerto);
			setConexion();
			recibir = new HiloRecibir(cliente);

			recibir.start();

			flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
			while (true) {
				habitacion.generarTemperatura();
				flujoSalida.writeObject(habitacion);
				System.out.println(habitacion.toString());
				flujoSalida.flush();
				flujoSalida.reset();
				// genero la medida de los datos cada 10 segundos
				Thread.sleep(1000);
			}
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		// cerramos los flujos
		
		

	}

//metodo por el cual se establece la conexion en localhost con el puerto indicado en esta caso el 8000
	public static void setConexion() {
		try {
			cliente = new Socket("localhost", puerto);
			System.out.println("Conexion establecida  con el servidor");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	
}
