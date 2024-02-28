package ejercicio10mulithilo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor10 {

    //Variables globales
    private static int puerto = 8000;
    private static Socket cliente;

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado ... ");

            while (true) {
                cliente = servidor.accept();//Esperando cliente
                HiloServidor10 hilo = new HiloServidor10(cliente);
                hilo.start(); //Se atiende al cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


