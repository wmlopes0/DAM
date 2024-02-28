package Ejercicio2;

import com.sun.security.jgss.GSSUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class HiloServidor2 extends Thread {

    //Variables globales
    private Socket cliente;
    private ObjectInputStream flujoEntrada;

    public HiloServidor2(Socket cliente) {
        try {
            this.cliente = cliente;
            this.flujoEntrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (!cliente.isClosed()) {
                //Recibo habitacion
                Habitacion habitacion = (Habitacion) flujoEntrada.readObject();
                //Muestro accion por pantalla
                System.out.println(Utileria.comprobarTemperatura(habitacion));
            }
        } catch (IOException e) {
//                throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
        }
    }
}
