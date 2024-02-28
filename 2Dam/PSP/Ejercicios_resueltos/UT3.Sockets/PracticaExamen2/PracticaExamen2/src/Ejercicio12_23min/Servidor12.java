package Ejercicio12_23min;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor12 {
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
            servidor.setSoTimeout(5000);
            System.out.println("SERVIDOR INICIADO... ESPERANDO CLIENTES...");
            while (true) {
                cliente = servidor.accept();//Acepto cliente
                HiloServidor12 hiloServidor = new HiloServidor12(cliente);
                hiloServidor.start();
            }

        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
