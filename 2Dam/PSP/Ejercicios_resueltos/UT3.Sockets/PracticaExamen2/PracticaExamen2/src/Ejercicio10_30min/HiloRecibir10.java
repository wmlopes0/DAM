package Ejercicio10_30min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloRecibir10 extends Thread {

    //Variables globales
    private Socket cliente;
    private BufferedReader flujoEntrada;

    //Constructor
    public HiloRecibir10(Socket cliente) {
        try {
            this.cliente = cliente;
            flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (IOException e) {
            System.out.println("ERROR AL RECUPERAR EL FLUJO DE LECTURA");
        }
    }

    @Override
    public void run() {
        try {
            String mensaje;
            do {
                mensaje = flujoEntrada.readLine();
                if (!mensaje.equalsIgnoreCase("*")) {
                    System.out.println("--->" + mensaje);
                }else{
                    System.out.println("La otra parte se ha desconectado, pulse cualquier tecla para salir.");
                }
            } while (!mensaje.equalsIgnoreCase("*"));
            //Libero recursos
            cliente.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
