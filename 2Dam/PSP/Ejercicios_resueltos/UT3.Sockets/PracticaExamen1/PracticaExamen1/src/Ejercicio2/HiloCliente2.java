package Ejercicio2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloCliente2 extends Thread {

    //Variables globales
    private Socket cliente;
    private ObjectOutputStream flujoSalida;
    private int numHabitacion;

    //Constructor
    public HiloCliente2(Socket cliente, int numHabitacion) {
        try {
            //Recupero el socket y los flujos que necesito
            this.cliente = cliente;
            this.numHabitacion = numHabitacion;
            flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            //Manda el log de la temperatura 5 veces cada cliente
            for (int i = 0; i < 5; i++) {
                //Envio habitacion al servidor
                Habitacion habitacion = new Habitacion(numHabitacion);
                flujoSalida.writeObject(habitacion);
                flujoSalida.flush();
                flujoSalida.reset();
                //Muestro info habitación
                System.out.println(habitacion);
                Thread.sleep(3000);//Espera explítica de 3s
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
