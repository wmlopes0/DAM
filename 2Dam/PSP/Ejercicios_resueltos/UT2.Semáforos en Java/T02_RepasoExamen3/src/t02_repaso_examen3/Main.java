package t02_repaso_examen3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables globales
    final static int NUM_PLAZAS = 4;
    final static int NUM_AMBULANCIAS = 6;
    static List<Ambulancia> ambulancias = new ArrayList<>();

    public static void main(String[] args) {
        //Instancio Parking
        Parking parking = new Parking(NUM_PLAZAS);

        //Instancio ambulancias
        for (int i = 0; i < NUM_AMBULANCIAS; i++) {
            ambulancias.add(new Ambulancia("Ambulancia" + i, parking));
        }

        //Inicio las ambulancias
        for (Ambulancia ambulancia : ambulancias) {
            ambulancia.start();
        }

        //Espero a que terminen todos los hilos
        try {
            for (Ambulancia ambulancia : ambulancias) {
                ambulancia.join();
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }

        //CHECK
        System.out.println("PROGRAMA FINALIZADO.");
    }
}
