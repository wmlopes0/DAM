package Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables globales
    final static int NUMERO_PLAZAS = 4;
    final static int NUMERO_AMBULANCIAS = 6;
    public static List<Ambulancia> ambulancias = new ArrayList<>();


    public static void main(String[] args) {
        //Instancio Parking
        Parking parking = new Parking(NUMERO_PLAZAS);

        //Instancio Ambulancias
        for (int i = 0; i < NUMERO_AMBULANCIAS; i++) {
            ambulancias.add(new Ambulancia("ambulancia número " + i, parking));
        }

        //Inicio hilos
        for (Ambulancia ambulancia : ambulancias) {
            ambulancia.start();
        }

        //Espero a que terminen los hilos
        try {
            for (Ambulancia ambulancia : ambulancias) {
                ambulancia.join();
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }

        //CHECK
        System.out.println("PROGRAMA FINALIZADO");
    }
}
