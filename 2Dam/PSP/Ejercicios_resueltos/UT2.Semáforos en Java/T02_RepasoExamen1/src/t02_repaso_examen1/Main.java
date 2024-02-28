package t02_repaso_examen1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables globales
    private static List<Coche> coches = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        //INstancio carretera
        Carretera carretera = new Carretera(8);

        //Instancio coches
        for (int i = 0; i < 10; i++) {
            coches.add(new Coche("coche " + i, true, carretera));
        }
        for (int i = 10; i < 20; i++) {
            coches.add(new Coche("coche " + i, false, carretera));
        }

        //Inicio coches
        for (Coche coche : coches) {
            coche.start();
        }

        //Espero a que terminen los hilos
        for (Coche coche : coches) {
            coche.join();
        }

        //CHECK
        System.out.println("PROGRAMA FINALIZADO");
    }
}
