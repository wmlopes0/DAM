package Ejercicio1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;

public class HiloServidor extends Thread {

    //Variables globales
    private Socket socket;
    private ObjectInputStream flujoEntrada;
    private PrintWriter flujoSalida;

    //Constructor
    public HiloServidor(Socket socket) {
        try {
            //Recupero el socket y los flujos que necesito
            this.socket = socket;
            flujoEntrada = new ObjectInputStream(socket.getInputStream());
            flujoSalida = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("ERROR AL RECUPERAR LOS FLUJOS DEL SOCKET EN EL SERVIDOR");
        }
    }

    @Override
    public void run() {
        try {
            //Recibo objeto Coche
            Coche coche = (Coche) flujoEntrada.readObject();
            //Envio respuesta al cliente
            flujoSalida.println(comprobarItv(coche));
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
        }
    }

    //Mensaje que implementa la lógica necesaria y te devuelve un String con el mensaje que le tienes que mandar al cliente
    private String comprobarItv(Coche coche) {
        String respuesta = "#" + coche.getMarca() + " " + coche.getModelo() + " con matrícula " + coche.getMatricula() + ".";
        LocalDate hoy = LocalDate.now();
        int anosCoche = hoy.getYear() - coche.getAnoMatriculacion();
        switch (anosCoche) {
            case 1:
            case 2:
            case 3:
            case 4:
                respuesta += "EXENTO DE ITV.";
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                respuesta += "DEBE PASAR LA ITV CADA 2 AÑOS.";
                break;
            default:
                respuesta += "DEBE PASAR LA ITV CADA AÑO.";
        }
        return respuesta;
    }
}
