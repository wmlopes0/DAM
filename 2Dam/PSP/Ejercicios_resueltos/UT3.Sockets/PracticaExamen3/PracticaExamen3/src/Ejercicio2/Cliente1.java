package Ejercicio2;

import java.io.IOException;
import java.net.Socket;

public class Cliente1 {

    //Variables globales
    private static int puerto = 8000;
    private static Socket cliente;
    private static HiloCliente2 hiloCliente;

    public static void main(String[] args) {
        //Establecer conexión
        establecerConexion();
    }

    private static void establecerConexion() {
        try {
            //Cliente 1
            cliente = new Socket("localhost", puerto);
            hiloCliente = new HiloCliente2(cliente, 1);
            hiloCliente.start();
            Thread.sleep(2000);
            //Cliente 2
            cliente = new Socket("localhost", puerto);
            hiloCliente = new HiloCliente2(cliente, 2);
            hiloCliente.start();
            Thread.sleep(2000);
            //Cliente 3
            cliente = new Socket("localhost", puerto);
            hiloCliente = new HiloCliente2(cliente, 3);
            hiloCliente.start();
            Thread.sleep(2000);

            //Espero a que terminen
            hiloCliente.join();
            //Libero recursos
            cliente.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
