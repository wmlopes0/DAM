package t02_ejercicio9_walter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables globales
    public static List<Canario> canarios = new ArrayList<>();

    public static void main(String[] args) {
        //Instancio Jaula
        Jaula jaula = new Jaula();

        //Instancio pajaros
        for (int i = 0; i < 10; i++) {
            canarios.add(new Canario("Canario " + i, jaula));
        }

        //Instancio Encargado
        Encargado encargado = new Encargado("El encargado", jaula);

        //Inicio hilos
        canarios.forEach(canario -> {
            canario.start();
        });
        encargado.start();

        //Espero a que terminen todos los hilos
        try {
            for (Canario canario : canarios) {
                canario.join();
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }

        //CHECK
        System.out.println("PROGRAMA FINALIZADO");
    }
}
