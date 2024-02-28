package Ejercicio1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor1 extends Thread {

    //Variables globales
    private Socket socket;
    private PrintWriter flujoSalida;
    private ObjectInputStream flujoEntrada;

    public HiloServidor1(Socket socket) {
        try {
            this.socket = socket;
            this.flujoSalida = new PrintWriter(socket.getOutputStream(), true);
            this.flujoEntrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            //Recupero del cliente el objeto coche
            Coche coche = (Coche) flujoEntrada.readObject();
            //Mando respuesta al servidor
            flujoSalida.println(Utileria.comprobarItv(coche));
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
        }
    }
}
