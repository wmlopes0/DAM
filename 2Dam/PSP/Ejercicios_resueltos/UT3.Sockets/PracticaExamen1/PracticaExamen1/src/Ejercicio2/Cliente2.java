package Ejercicio2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente2 {

    //Variables globales
    private static int puerto = 8000;
    private static Socket cliente;

    //Main
    public static void main(String[] args) {
        //Establezco conexión
        establecerConexion();
    }

    private static void establecerConexion() {
        try {
            //Cliente 1
            cliente = new Socket("localhost", puerto);
            System.out.println("Habitacion 1 ha establecido conexión con el servidor correctamente.");
            HiloCliente2 hiloCliente = new HiloCliente2(cliente, 1);
            hiloCliente.start();
            //Cliente 2
            cliente = new Socket("localhost", puerto);
            System.out.println("Habitacion 2 ha establecido conexión con el servidor correctamente.");
            HiloCliente2 hiloCliente2 = new HiloCliente2(cliente, 2);
            hiloCliente2.start();

            //Espero a que terminen los clientes
            hiloCliente.join();
            hiloCliente2.join();
            //Libero recursos
            cliente.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
