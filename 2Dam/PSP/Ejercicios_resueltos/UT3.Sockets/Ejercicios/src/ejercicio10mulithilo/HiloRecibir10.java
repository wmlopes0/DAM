package ejercicio10mulithilo;

import java.io.BufferedReader;
import java.io.IOException;

public class HiloRecibir10 extends Thread {

    //Variables globales
    private BufferedReader flujoEntrada;

    public HiloRecibir10(BufferedReader entrada) {
        this.flujoEntrada = entrada;
    }

    @Override
    public void run() {
        String mensaje;
        try {
            do {
                //Leo
                mensaje = flujoEntrada.readLine();

                //Si esto recibe un null es porque el otro lado ha puesto exit
                if (mensaje != null) {
                    //Muestro
                    System.out.println("--> " + mensaje);
                } else {
                    mensaje = "EXIT";
                }
            } while (!mensaje.trim().equalsIgnoreCase("EXIT"));

            //Cierro flujo
            flujoEntrada.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}