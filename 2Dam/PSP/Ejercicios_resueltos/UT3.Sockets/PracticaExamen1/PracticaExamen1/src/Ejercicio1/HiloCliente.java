package Ejercicio1;

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
            //Inicializo el socket y recupero los flujos que necesito
            this.socket = socket;
            this.flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            this.flujoEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("ERROR AL RECUPERAR LOS FLUJOS DEL SOCKET");
        }
    }

    @Override
    public void run() {
        try {
            //Creo coche y relleno datos
            Coche coche = new Coche();
            //Mando el coche al servidor
            flujoSalida.writeObject(coche);
            flujoSalida.reset();
            flujoSalida.flush();
            //Recupero la respuesta del servidor y la muestro por pantalla
            System.out.println(flujoEntrada.readLine());
        } catch (IOException e) {
            System.out.println("ERROR DE COMUNICACIÓN CON EL SERVIDOR");
        }
    }
}
