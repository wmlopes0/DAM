package ejercicio10mulithilo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor10 extends Thread {

    //Variables globales
    private BufferedReader fentrada;
    private PrintWriter fsalida;
    private Socket socket;

    //Constructor
    public HiloServidor10(Socket socket) {
        this.socket = socket;
        try {
            // Inicializamos los flujos de entrada y de salida
            fsalida = new PrintWriter(socket.getOutputStream(), true);
            fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        //Tarea para cada cliente
        try {
            System.out.println("COMUNICO CON: " + socket.toString());

            // Iniciar hilo para enviar mensajes
            HiloEnviar10 enviarMensaje = new HiloEnviar10(fsalida);
            enviarMensaje.start();

            // Iniciar hilo para recibir mensajes
            HiloRecibir10 recibirMensaje = new HiloRecibir10(fentrada);
            recibirMensaje.start();

            enviarMensaje.join();
            recibirMensaje.join();

            System.out.println("FIN CON: " + socket.toString());

            //Cierro flujos
            fsalida.close();
            fentrada.close();
            socket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}