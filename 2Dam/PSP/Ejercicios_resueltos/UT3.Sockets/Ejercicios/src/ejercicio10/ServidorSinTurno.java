package ejercicio10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSinTurno {

    //Variables globales
    private static int puerto = 8000;
    private ServerSocket servidor;
    private Socket cliente;
    private PrintWriter flujoSalida;
    private BufferedReader flujoEntrada;

    public static void main(String[] args) {
        //Instancio clase
        ServidorSinTurno claseServidor = new ServidorSinTurno();
        //Inicio servidor
        claseServidor.iniciarServidor();
    }

    public void iniciarServidor() {
        try {
            //Arranco servidor
            servidor = new ServerSocket(puerto);
            System.out.println("Esperando conexion");
            cliente = servidor.accept();
            System.out.println("Conexion establecida");
            flujoSalida = new PrintWriter(cliente.getOutputStream(), true);
            HiloEscritura hiloEscritura = new HiloEscritura(flujoSalida);
            flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            HiloLectura hiloLectura = new HiloLectura(flujoEntrada, this);
            hiloEscritura.start();
            hiloLectura.start();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    public void cerrarTodo() {
        System.out.println("CHAT TERMINADO");
        try {
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            servidor.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
