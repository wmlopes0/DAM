package Ejercicio12_23min;

import java.io.IOException;
import java.net.Socket;

public class Cliente12 {

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
            HiloCliente12 hiloCliente = new HiloCliente12(cliente);
            hiloCliente.start();
            //Cliente 2
            cliente = new Socket("localhost", puerto);
            HiloCliente12 hiloCliente2 = new HiloCliente12(cliente);
            hiloCliente2.start();

            //Espero y libero recursos
            hiloCliente.join();
            cliente.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
