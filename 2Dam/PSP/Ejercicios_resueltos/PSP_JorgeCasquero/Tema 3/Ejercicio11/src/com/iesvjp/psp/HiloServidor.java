package com.iesvjp.psp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HiloServidor extends Thread {

	private Socket socket;

	public HiloServidor(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		Date fecha = new Date();
		try {
			DataOutputStream flujoSalida = new DataOutputStream(this.socket.getOutputStream());
			flujoSalida.writeUTF(fecha.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}