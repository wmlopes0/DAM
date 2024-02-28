package Ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {

    //Variables globales
    private static int puerto = 8000;
    private ServerSocket serverSocket;
    private Socket socket;

    //Main
    public static void main(String[] args) {
        //Instancio clase
        Servidor1 servidor = new Servidor1();
        //Inicio el servidor
        servidor.iniciarServidor();
    }

    //Método para iniciar el servidor
    public void iniciarServidor() {
        try {
            //Inicio servidor
            serverSocket = new ServerSocket(puerto);
//          serverSocket.setSoTimeout(10000); //Espero un máximo de 10 segundos, si en ese tiempo no se conecta ningún cliente el servidor se desconecta
            //Mensaje Check
            System.out.println("Servidor iniciado...");
            while (true) {
                socket = serverSocket.accept();//Acepto cliente
                HiloServidor1 hiloServidor = new HiloServidor1(socket);
                hiloServidor.start();//Atiendo al cliente
            }
        } catch (IOException e) {
//            System.out.println("-- SERVIDOR DESCONECTADO POR INACTIVIDAD, NO SE ATENDERÁN MAS CLIENTES.--");
            liberarRecursos();
        }
    }

    public void liberarRecursos() {
        try {
            serverSocket.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }


}
