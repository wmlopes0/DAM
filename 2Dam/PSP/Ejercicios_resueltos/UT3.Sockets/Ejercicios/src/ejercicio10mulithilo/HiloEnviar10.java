package ejercicio10mulithilo;

import java.io.PrintWriter;
import java.util.Scanner;

public class HiloEnviar10 extends Thread {

    //Variables globales
    private PrintWriter flujoSalida;

    //Constructor
    public HiloEnviar10(PrintWriter flujoSalida) {
        this.flujoSalida = flujoSalida;
    }

    @Override
    public void run() {
        //Pido cadena
        String cadena;
        do {
            //Pido mensaje
            cadena = pedirMsj();
            //Envio
            flujoSalida.println(cadena);
        } while (!cadena.trim().equalsIgnoreCase("EXIT"));

        //Cierro flujo
        flujoSalida.close();
    }

    public String pedirMsj() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un mensaje: ");
        return entrada.nextLine();
    }

}