package ejercicio10mulithilo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente10 {
    private static int puerto = 8000;

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", puerto);
            BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter flujoSalida = new PrintWriter(cliente.getOutputStream(), true);

            // Iniciar hilo para recibir mensajes del servidor
            HiloRecibir10 recibirMensaje = new HiloRecibir10(flujoEntrada);
            recibirMensaje.start();

            // Iniciar hilo para enviar mensajes al servidor
            HiloEnviar10 enviarMensaje = new HiloEnviar10(flujoSalida);
            enviarMensaje.start();

            //Espero a que terminen los hilos
            recibirMensaje.join();
            enviarMensaje.join();

            //Cierro el cliente
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

