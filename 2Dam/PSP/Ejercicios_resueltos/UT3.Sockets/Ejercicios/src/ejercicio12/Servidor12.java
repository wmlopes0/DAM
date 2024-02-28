package ejercicio12;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor12 {

    //Variables globales
    private static int puerto = 8000;
    private ServerSocket servidor;
    private Socket socket;

    public static void main(String[] args) {
        //Instancio clase
        Servidor12 claseServidor = new Servidor12();
        //Inicio servidor
        claseServidor.iniciarServidor();
    }

    public void iniciarServidor() {
        try {
            servidor = new ServerSocket(8000);
            servidor.setSoTimeout(10000);
            System.out.println("SERVIDOR INICIADO.... ESPERANDO INFORMACIÓN DE PACIENTES...");
            //Recibo clientes
            while (true) {
                socket = servidor.accept();//Recibo cliente
                HiloServidor12 hiloServidor = new HiloServidor12(socket);
                hiloServidor.start();//Atiendo al cliente
            }
        } catch (IOException e) {
//            System.out.println("ERROR: No se pudo iniciar el servidor.");
        }
    }
}
