package Ejercicio1;

import java.io.IOException;
import java.net.Socket;

public class Cliente1 {

    //Variables globales
    private static int puerto = 8000;

    public static void main(String[] args) {
        //Establezco la conexión con el servidor
        establecerConexion();
    }

    public static void establecerConexion() {
        try {
            //Establezco conexión con el servidor
            Socket socket1 = new Socket("localhost", puerto);
            //Mensaje Check
            System.out.println("\nConexión establecida correctamente.Port:" + socket1.getPort());
            //Inicio hiloCliente para que haga sus cosas
            HiloCliente hiloCliente1 = new HiloCliente(socket1);
            hiloCliente1.start();
            //Espero a que termine de hacer sus cosas
            hiloCliente1.join();
            //Libero recursos
            socket1.close();
        } catch (IOException e) {
            System.out.println("ERROR AL ESTABLECER LA CONEXIÓN CON EL SERVIDOR");
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
