package Ejercicio1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.time.LocalDate;

public class HiloServidor1 extends Thread {

    //Variables globales
    private Socket socket;
    private ObjectInputStream flujoEntrada;
    private PrintWriter flujoSalida;

    //Constructor
    public HiloServidor1(Socket socket) {
        try {
            //Recupero socket
            this.socket = socket;
            //Muestro Check
            System.out.println("Cliente conectado: " + socket);
            //Recupero flujos
            flujoEntrada = new ObjectInputStream(socket.getInputStream());
            flujoSalida = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    //Tarea
    @Override
    public void run() {
        try {
            //Recibo el coche del cliente
            Coche coche = (Coche) flujoEntrada.readObject();
            //Mensaje Check
            System.out.println("[Servidor] Consultar coche: " + coche);
            //Mando respuesta al cliente
            flujoSalida.println(comprobarItv(coche));
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
        }
    }

    public String comprobarItv(Coche coche) {
        String respuesta = "=>El coche MATRÍCULA " + coche.getMatricula();
        //Recupero antiguedad del coche
        int antiguedad = LocalDate.now().getYear() - coche.getAnioMatriculacion();
        //Mensaje Check
        System.out.println("Años: " + antiguedad);
        //Si el coche tiene menos de 4 años
        if (antiguedad < 4) {
            respuesta += " está exento de pasar la ITV.";
        } else {
            //Si no se introduce año de última ITV
            if (coche.getAnioUltimaItv() == 0) {
                respuesta = "=>El coche tiene más de 4 años y no existe información del año de la última ITV.";
            } else {
                //Si tiene entre 4 y 10 años
                if (antiguedad >= 4 && antiguedad <= 10) {
                    respuesta += " tiene que pasar la ITV en el año " + (coche.getAnioUltimaItv() + 2) + ".";
                } else {
                    //Si tiene más de 10 años
                    respuesta += " tiene que pasar la ITV en el año " + (coche.getAnioUltimaItv() + 1) + ".";
                }
            }
        }
        //Retorno respuesta
        return respuesta;
    }
}
