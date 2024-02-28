package sistemaSeguridadPuente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    final static int COCHES = 10;
    final static int AMBULANCIAS = 5;

    public static void main(String[] args) {
        //Lista de vehículos
        List<Vehiculo> vehiculos = new ArrayList<>();
        //CREO PUENTE
        Puente puente = new Puente(10, 15000);
        //CREO COCHES
        for (int i = 0; i < COCHES; i++) {
            vehiculos.add(new Vehiculo(i, generarAleatorio(1000, 6000), "Coche", puente));
        }
        //CREO AMBULANCIAS
        for (int i = 0; i < AMBULANCIAS; i++) {
            Vehiculo ambulancia = new Vehiculo(i, generarAleatorio(4000, 8000), "Ambulancia", puente);
            ambulancia.setPriority(Thread.MAX_PRIORITY);
            vehiculos.add(ambulancia);
        }
        //ARRANCO TODOS LOS VEHICULOS, COCHES PRIMERO
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.start();
        }
        //ESPERO A QUE TERMINEN
        try {
            for (Vehiculo vehiculo : vehiculos) {
                vehiculo.join();
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }
        //FIN DEL PROGRAMA
        System.out.println("FIN DEL PROGRAMA");

    }

    public static int generarAleatorio(int inicio, int fin) {
        return new Random().nextInt((fin + 1) - inicio) + inicio;
    }
}
