package ejercicio10;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HiloEscritura extends Thread {

    //Variables globales
    private PrintWriter flujoSalida;
    private Socket cliente;


    //Constructor
    public HiloEscritura(PrintWriter flujoSalida) {
        this.flujoSalida = flujoSalida;
    }

    @Override
    public void run() {
        String mensaje;
        do {
            //Pido cadena
            mensaje = pedirCadena();
            //La mando
            flujoSalida.println(mensaje);
        } while (!mensaje.equalsIgnoreCase("EXIT"));
    }

    public static String pedirCadena() {
        Scanner entrada = new Scanner(System.in);
        return entrada.nextLine();
    }

}
