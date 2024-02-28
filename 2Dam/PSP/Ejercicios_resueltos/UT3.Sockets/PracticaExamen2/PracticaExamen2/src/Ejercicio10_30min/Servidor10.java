package Ejercicio10_30min;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor10 {

    //Variables globales
    private static int puerto = 8000;
    private static ServerSocket servidor;
    private static Socket cliente;

    //Main
    public static void main(String[] args) {
        //Inicio servidor
        iniciarServidor();
    }

    private static void iniciarServidor() {
        try {
            servidor = new ServerSocket(puerto);//Inicio servidor
            System.out.println("SERVIDOR INICIADO... ESPERANDO CLIENTES...");
            cliente = servidor.accept();//Acepto cliente
            System.out.println("Conexión establecida con el cliente " + cliente.getPort());
            //Inicio hilos
            HiloEnviar10 hiloEnviar = new HiloEnviar10(cliente);
            HiloRecibir10 hiloRecibir = new HiloRecibir10(cliente);
            hiloEnviar.start();
            hiloRecibir.start();
            //Espero a que terminen
            hiloEnviar.join();
            hiloRecibir.join();
            //Libero recursos
            servidor.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
