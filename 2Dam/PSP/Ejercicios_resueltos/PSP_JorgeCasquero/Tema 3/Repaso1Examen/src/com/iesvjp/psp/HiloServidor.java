package com.iesvjp.psp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidor extends Thread {
	private Habitacion habitacion;
	private ObjectInputStream flujoEntrada;
	private DataOutputStream flujoSalida;
	private Socket cliente;

	public HiloServidor(Socket cliente) throws IOException {
		this.habitacion = habitacion;
		this.cliente = cliente;
		this.flujoSalida = new DataOutputStream(cliente.getOutputStream());
		this.flujoEntrada = new ObjectInputStream(cliente.getInputStream());
	
	}

	@Override
	public void run() {
		// genero la medida de los datos cada 10 segundos
		try {
			while (true) {
				
				
				// leemos el objeto que nos manda el cliente
				habitacion = (Habitacion) flujoEntrada.readObject();
				// llamamos al metodo comprobaciones para saber que mensaje hay que enviarle al
				// cliente de nuevo
				String mensaje = comprobaciones(habitacion);
				flujoSalida.writeUTF(mensaje);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String comprobaciones(Habitacion habitacion) {

		String sms = "[Habitacion:" + habitacion.getNumHabitacion() + "]";
		int temp = habitacion.getTemp();
		if (temp >= 30) {
			return sms += "la temperatura es muy alta,(" + temp + ") se enciende el aire";

		}
		if (temp <= 15) {
			return sms += "la temperatura es muy baja,(" + temp + ") se enciende la calefaccion";

		}
		if (temp <= 30 && temp >= 15) {
			return sms += "la temperatura es  Perfecta,(" + temp + ")";
		}

		return "";

	}
}
