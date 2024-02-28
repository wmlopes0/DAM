package Ejercicio2;

import Ejercicio1.Coche;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor2 extends Thread {
    //Variables globales
    private Socket socket;
    private ObjectInputStream flujoEntrada;

    //Constructor
    public HiloServidor2(Socket socket) {
        try {
            this.socket = socket;
            flujoEntrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR AL RECUPERAR LOS FLUJOS DEL SOCKET EN EL SERVIDOR");
        }
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                //Recibo objeto Habitacion
                Habitacion habitacion = (Habitacion) flujoEntrada.readObject();
                //Muestro acción
                System.out.println(comprobarTemperatura(habitacion));
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
        }
    }

    private static String comprobarTemperatura(Habitacion habitacion) {
        String respuesta = "";
        if (habitacion.getTemperatura() > 30) {
            respuesta += "[Habitación " + habitacion.getNumHabitacion() + "] - Temperatura muy alta (" + habitacion.getTemperatura() + "º). Activamos aire acondicionado.";
        } else {
            if (habitacion.getTemperatura() < 15) {
                respuesta += "[Habitación " + habitacion.getNumHabitacion() + "] - Temperatura muy baja (" + habitacion.getTemperatura() + "º). Activamos la calefacción.";
            } else {
                if (habitacion.getTemperatura() == 24) {
                    respuesta += "[Habitación " + habitacion.getNumHabitacion() + "] - Temperatura ideal (" + habitacion.getTemperatura() + "º).";
                }
            }
        }
        return respuesta;
    }
}
