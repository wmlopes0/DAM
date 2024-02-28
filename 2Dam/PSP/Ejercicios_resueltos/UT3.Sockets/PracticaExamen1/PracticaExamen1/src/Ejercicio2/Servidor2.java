package Ejercicio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {
    //Variables globales
    private static int puerto = 8000;
    private static ServerSocket servidor;
    private static Socket socket;

    public static void main(String[] args) {
        //Arranco servidor
        iniciarServidor();
    }

    public static void iniciarServidor() {
        try {
            //Inicio el servidor
            servidor = new ServerSocket(puerto);
            //Mensaje check
            System.out.println("SERVIDOR INICIADO.... ESPERANDO CLIENTES...");
            servidor.setSoTimeout(5000);//Establezo un tiempo de espera de 5 segundos
            while (true) {
                socket = servidor.accept();//Acepto cliente
                System.out.println("\nCliente conectado, port: " + socket.getLocalPort());//Mensaje Check
                HiloServidor2 hiloServidor = new HiloServidor2(socket);//Inicio hilo
                hiloServidor.start();//Atiendo cliente
            }

        } catch (IOException e) {
//            System.out.println("SERVIDOR DESCONECTADO, NO SE ATENDERÁN MAS CLIENTES");
            //Libero recursos
            liberarRecursos();
        }
    }

    private static void liberarRecursos(){
        try {
            servidor.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
