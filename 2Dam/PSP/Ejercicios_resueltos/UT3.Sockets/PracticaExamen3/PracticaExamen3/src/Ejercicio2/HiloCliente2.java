package Ejercicio2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloCliente2 extends Thread {

    //Variables globales
    private Socket cliente;
    private ObjectOutputStream flujoSalida;
    private Habitacion habitacion;
    private int numHabitacion;


    public HiloCliente2(Socket cliente, int numHabitacion) {
        try {
            this.cliente = cliente;
            this.numHabitacion = numHabitacion;
            this.flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                habitacion = new Habitacion(numHabitacion);
                System.out.println(habitacion);
                flujoSalida.writeObject(habitacion);
                flujoSalida.flush();
                flujoSalida.reset();
                Thread.sleep(3000);
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
