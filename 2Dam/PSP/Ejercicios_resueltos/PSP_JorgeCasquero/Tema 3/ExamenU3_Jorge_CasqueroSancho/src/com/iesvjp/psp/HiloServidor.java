package com.iesvjp.psp;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class HiloServidor extends Thread {
	private Socket socket;
	private static ObjectInputStream flujoEntrada = null;
	//private static DataOutputStream flujoSalida = null;
	private Habitacion habitacion;

	public HiloServidor(Socket socket) {
		this.socket = socket;
		try {
			flujoEntrada = new ObjectInputStream(socket.getInputStream());
		//	flujoSalida = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		String mensaje = "";
		try {
			while (true) {
				habitacion = (Habitacion) flujoEntrada.readObject();
				mensaje = comprobarTemperatura(habitacion);
				System.out.println(mensaje);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//metodo que comprueba cual es la temperatura de la habitacion y devuelve un mensaje en funcion de ello
	/**
	 * @param hab recibe una habitacion y trabaja con su temperatura
	 * @return devuleve el mesaje correspondiente
	 */
	public static String comprobarTemperatura(Habitacion hab) {
		String mensaje = "";
		int temp = hab.getTemp();
		if (temp > 30) {//si es mayor que 30 sube enciende la temperatura
			mensaje += "temperatura MUY ALTA (" + temp + ")activamos aire acondicionado ";

		}
		if (temp == 24) {//si es idonea, 24ºC se apagan los sistemas
			mensaje += "temperatura IDEAl(" + temp + "),desactivamos los sistemas de climatizacion";

		}
		
		if (temp < 15) {//si es menor de 15 se activa la calefaccion
			mensaje += "temperatura MUY BAJA(" + temp + ")activamos la calefaccion ";

		}
		return mensaje;
	}

}
