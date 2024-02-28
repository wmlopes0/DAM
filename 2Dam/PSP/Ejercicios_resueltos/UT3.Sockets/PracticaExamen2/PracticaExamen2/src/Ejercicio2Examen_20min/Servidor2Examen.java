package Ejercicio2Examen_20min;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2Examen {

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
                System.out.println("Conexión establecida con: " + cliente);
                HiloServidor2Examen hiloServidor = new HiloServidor2Examen(cliente);
                hiloServidor.start();
            }

        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
