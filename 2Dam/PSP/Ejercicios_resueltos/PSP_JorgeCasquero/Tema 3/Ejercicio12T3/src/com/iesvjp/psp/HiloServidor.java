package com.iesvjp.psp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class HiloServidor extends Thread {

	private final static int TMIN = 37;
	private final static int TMAX = 42;
	private final static int FCMIN = 60;
	private final static int FCMAX = 100;
	private final static int PSISMIN = 120;
	private final static int PSISMAX = 140;
	private final static int PDISMIN = 70;
	private final static int PDISMAX = 90;
	private Socket socket;
	private DataOutputStream flujoSalida = null;
	private ObjectInputStream flujoEntrada = null;
	private Paciente paciente;

	public HiloServidor(Socket cliente) {
		this.socket = cliente;
		try {
			this.flujoEntrada = new ObjectInputStream(this.socket.getInputStream());
			this.flujoSalida = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				this.paciente = (Paciente) this.flujoEntrada.readObject();
				System.out.println("Recibido de cliente:" + this.socket.toString());
				System.out.println(this.paciente.toString());
				comprobarConstantes();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	private void comprobarConstantes() {
		boolean mandar = false;
		String mensaje = "**PELIGRO, los siguientes valores no son normales :";

		int frecuencia = this.paciente.getFrecuencia();
		int temperatura = this.paciente.getTemperatura();
		int pDiastolica = this.paciente.getPresionDiastolica();
		int pSistolica = this.paciente.getPresionSistolica();

		if (frecuencia < FCMIN || frecuencia > FCMAX) {
			mensaje += "[FRECUENCIA " + frecuencia + " ppm]";
			mandar = true;
		}
		if (temperatura < TMIN || temperatura > TMAX) {
			mensaje += "[TEMPERATURA " + temperatura + "ºC]";
			mandar = true;
		}
		if (pDiastolica < PDISMIN || pDiastolica > PDISMAX) {
			mensaje += "[DIASTOLICA " + pDiastolica + " mm Hg]";
			mandar = true;
		}
		if (pSistolica < PSISMIN || pSistolica > PDISMAX) {
			mensaje += "[SISTOLICA " + pSistolica + " mm hg]";
			mandar = true;
		}
		if (mandar) {
			try {
				this.flujoSalida.writeUTF(mensaje);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

}
