package Ejercicio2Examen_20min;

import java.io.IOException;
import java.net.Socket;

public class Cliente2Examen {

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
            HiloCliente2Examen hiloCliente = new HiloCliente2Examen(cliente, 1);
            hiloCliente.start();
            //Cliente 2
            cliente = new Socket("localhost", puerto);
            System.out.println("Habitacion 2 ha establecido conexión con el servidor correctamente.");
            HiloCliente2Examen hiloCliente2 = new HiloCliente2Examen(cliente, 2);
            hiloCliente2.start();

            //Espero y libero recursos
            hiloCliente.join();
            hiloCliente2.join();
            cliente.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
