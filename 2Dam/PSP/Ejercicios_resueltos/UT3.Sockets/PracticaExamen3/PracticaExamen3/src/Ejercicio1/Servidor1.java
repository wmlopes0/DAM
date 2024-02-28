package Ejercicio1;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {

    //Variables globales
    private static int puerto = 8000;
    private static ServerSocket servidor;
    private static Socket cliente;

    public static void main(String[] args) {
        //Inicio servidor
        iniciarServidor();
    }

    private static void iniciarServidor() {
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("SERVIDOR INICIADO, ESPERANDO CLIENTES...");
            servidor.setSoTimeout(5000);
            while (true) {
                cliente = servidor.accept();
                HiloServidor1 hiloServidor = new HiloServidor1(cliente);
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
