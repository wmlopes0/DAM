package Ejercicio1;

import java.io.IOException;
import java.net.Socket;

public class Cliente1 {

    //Variables globales
    private static int puerto = 8000;
    private static Socket socket;

    //Main
    public static void main(String[] args) {
        //Establezco conexión
        establecerConexion();
    }

    //Establecer conexión
    private static void establecerConexion() {
        try {
            //Establezco conexión con el servidor
            socket = new Socket("localhost", puerto);
            HiloCliente1 hiloCliente1 = new HiloCliente1(socket);
            hiloCliente1.start();
            //Espero a que termine
            hiloCliente1.join();
            //Libero recursos
            socket.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
