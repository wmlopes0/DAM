package Ejercicio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor2 extends Thread {

    //Variables globales
    private Socket socket;
    private ObjectInputStream flujoEntrada;
    private PrintWriter flujoSalida;

    //Constructor
    public HiloServidor2(Socket socket) {
        try {
            //Recupero socket
            this.socket = socket;
            //Recupero flujos
            flujoEntrada = new ObjectInputStream(socket.getInputStream());
            flujoSalida = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    //Tarea
    @Override
    public void run() {
        try {
            //Mientras el cliente no se haya desconectado
            while (!socket.isClosed()) {
                //Recibo la habitación del cliente
                Habitacion habitacion = (Habitacion) flujoEntrada.readObject();
                //Mensajes Check
                System.out.println("Recibo: " + socket);
                System.out.println(habitacion);
                //Mando respuesta al cliente
                flujoSalida.println(comprobarTemperatura(habitacion));
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
        }
    }

    public String comprobarTemperatura(Habitacion habitacion) {
        String respuesta = "[Habitacion " + habitacion.getNumHabitacion() + "], ";
        //Si la temperatura es menor o igual a 15º
        if (habitacion.getTemperatura() <= 15) {
            respuesta += "temperatura muy BAJA (" + habitacion.getTemperatura() + ") activamos la calefacción.";
        } else {
            //Si la temperatura es mayor o igual a 30º
            if (habitacion.getTemperatura() >= 30) {
                respuesta += "temperatura muy ALTA (" + habitacion.getTemperatura() + ") activamos el aire acondicionado.";
            } else {
                //En todos los demás caso la temperatura será ideal
                respuesta += "temperatura IDEAL, desactivamos los sistemas de climatización.";
            }
        }
        //Retorno respuesta
        return respuesta;
    }
}
