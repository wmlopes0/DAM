package Ejercicio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {

    //Variables globales
    private static int puerto = 8000;
    private static ServerSocket servidor;
    private static Socket cliente;

    public static void main(String[] args) {
        //Inicio el servidor
        iniciarServidor();
    }

    private static void iniciarServidor() {
        try {
            servidor = new ServerSocket(puerto);
            servidor.setSoTimeout(5000);
            System.out.println("SERVIDOR INICIADO, ESPERANDO CLIENTES....");
            while (true) {
                cliente = servidor.accept();
                HiloServidor2 hiloServidor = new HiloServidor2(cliente);
                hiloServidor.start();
            }
        } catch (IOException e) {
            System.out.println("SERVIDOR CERRADO, NO SE ACEPTARAN MAS CLIENTES");
            liberarRecursos();
        }
    }

    private static void liberarRecursos() {
        try {
            servidor.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
