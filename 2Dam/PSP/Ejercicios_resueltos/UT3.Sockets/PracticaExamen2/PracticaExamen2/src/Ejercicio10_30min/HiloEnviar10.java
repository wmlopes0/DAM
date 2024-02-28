package Ejercicio10_30min;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HiloEnviar10 extends Thread {

    //Variables globales
    private Socket cliente;
    private PrintWriter flujoSalida;

    //Constructor
    public HiloEnviar10(Socket cliente) {
        try {
            this.cliente = cliente;
            flujoSalida = new PrintWriter(cliente.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("ERROR AL RECUPERAR EL FLUJO DE ESCRITURA");
        }
    }

    @Override
    public void run() {
        try {
            String mensaje = "";
            while (!mensaje.equalsIgnoreCase("*") && !cliente.isClosed()) {
                mensaje = pedirCadena();
                flujoSalida.println(mensaje);
            }
            //Libero recursos
            cliente.close();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    private static String pedirCadena() {
        Scanner entrada = new Scanner(System.in);
        return entrada.nextLine();
    }
}
