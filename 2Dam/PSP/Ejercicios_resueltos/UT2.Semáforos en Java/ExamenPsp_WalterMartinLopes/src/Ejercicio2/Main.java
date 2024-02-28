package Ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables globales
    final static int CAPACIDAD_FABRICA = 40;
    final static int DUENDES_PRODUCTORES = 5;
    final static int TOTAL_DUENDES = 11;
    final static int NUM_JUGUETES_DUENDES_PRODUCTORES = 12;
    final static int NUM_JUGUETES_DUENDES_CONSUMIDORES = 10;
    public static List<Thread> duendes = new ArrayList<>();


    public static void main(String[] args) {
        //Instancio FabricaJuguetes
        FabricaJuguetes fabricaJuguetes = new FabricaJuguetes(CAPACIDAD_FABRICA);

        //Instancio DuendesProductores
        for (int i = 0; i < DUENDES_PRODUCTORES; i++) {
            duendes.add(new DuendeProductor("Duende " + i, NUM_JUGUETES_DUENDES_PRODUCTORES, fabricaJuguetes));
        }
        //Instancio DuendesConsumidores
        for (int i = DUENDES_PRODUCTORES; i < TOTAL_DUENDES; i++) {
            duendes.add(new DuendeConsumidor("Duende " + i, NUM_JUGUETES_DUENDES_CONSUMIDORES, fabricaJuguetes));
        }

        //Inicio hilos
        for (Thread duende : duendes) {
            duende.start();
        }

        //Espero a que terminen los hilos
        try {
            for (Thread duende : duendes) {
                duende.join();
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }

        //CHECK
        System.out.println("PROGRAMA FINALIZADO");
    }
}
