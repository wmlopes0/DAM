package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloCliente1 extends Thread {

    //Variables globales
    private Socket socket;
    private ObjectOutputStream flujoSalida;
    private BufferedReader flujoEntrada;

    //Constructor
    public HiloCliente1(Socket socket) {
        try {
            this.socket = socket;
            this.flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            this.flujoEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        try {
            //Creo,relleno datos y envio al servidor el coche
            Coche coche = new Coche();
            flujoSalida.writeObject(coche);
            flujoSalida.flush();
            flujoSalida.reset();
            //Recibo respuesta del servidor y la muestro por pantalla
            System.out.println(flujoEntrada.readLine());
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
