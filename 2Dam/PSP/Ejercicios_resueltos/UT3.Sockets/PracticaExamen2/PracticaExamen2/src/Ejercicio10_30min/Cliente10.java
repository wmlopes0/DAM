package Ejercicio10_30min;

import java.io.IOException;
import java.net.Socket;

public class Cliente10 {

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
            cliente = new Socket("localhost", puerto);//Establezco conexión
            System.out.println("Conexión establecida con el servidor");
            //Inicio hilos
            HiloEnviar10 hiloEnviar = new HiloEnviar10(cliente);
            HiloRecibir10 hiloRecibir = new HiloRecibir10(cliente);
            hiloEnviar.start();
            hiloRecibir.start();
            //Espero a que terminen
            hiloEnviar.join();
            hiloRecibir.join();
            //Libero recursos
            cliente.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
