package Ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloCliente extends Thread {

    //Variables globales
    private Socket socket;
    private ObjectOutputStream flujoSalida;
    private BufferedReader flujoEntrada;

    //Constructor
    public HiloCliente(Socket socket) {
        try {
            //Recupero el Socket
            this.socket = socket;
            //Recupero flujos
            flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            flujoEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    //Tarea

    @Override
    public void run() {
        try {
            //Creo objeto habitacion
            Habitacion habitacion = new Habitacion();
            while (true) {
                //Genero temperaturas aleatoriamente
                habitacion.generarTemperatura();
                //Mando la habitacion al servidor para obtener la respuesta
                flujoSalida.writeObject(habitacion);
                flujoSalida.flush();
                flujoSalida.reset();
                //Recupero respuesta del servidor y la muestro por pantalla
                System.out.println(flujoEntrada.readLine());
                try {
                    //Espera de 10 s
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

}
