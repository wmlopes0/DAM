package Ejercicio2Examen_20min;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class HiloServidor2Examen extends Thread {

    //Variables globales
    private Socket cliente;
    private ObjectInputStream flujoEntrada;


    //Constructor
    public HiloServidor2Examen(Socket cliente) {
        try {
            this.cliente = cliente;
            flujoEntrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (!cliente.isClosed()) {
                //Recibo habitación
                Habitacion habitacion = (Habitacion) flujoEntrada.readObject();
                //Muestro mensaje
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
