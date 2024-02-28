package Ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {

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
            //Inicio servidor
            servidor = new ServerSocket(puerto);
            System.out.println("SERVIDOR INICIADO.... ESPERANDO CLIENTES...");
            //Establezo un tiempo de espera de 5 segundos (Espera explícita, esto no lo ha dado Nacho pero soy un friki)
            //Lo que hace basicamente es esperar x segundos, cuando en x segundos no se conecte nadie al servidor se va a salir del while true
            //lanzando una excepción IOException, por eso libero los recursos ahí.
            servidor.setSoTimeout(5000);

            while (true) {
                socket = servidor.accept();//Acepto cliente
                System.out.println("\nCliente conectado, port: " + socket.getPort());//Mensaje Check
                HiloServidor hiloServidor = new HiloServidor(socket);//Inicio hilo Servidor para que haga sus cosas
                hiloServidor.start();//Atiendo cliente
            }

        } catch (IOException e) {
//            System.out.println("SERVIDOR DESCONECTADO, NO SE ATENDERÁN MAS CLIENTES");
            //Libero recursos
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
