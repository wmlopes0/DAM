package t02_ejercicio8_walter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables globales
    private static List<Persona> personas = new ArrayList<>();

    public static void main(String[] args) {
        //Creo sala de baile
        SalaBaile salaBaile = new SalaBaile();
        //Creamos 3 hombres y 3 mujeres
        for (int i = 0; i < 3; i++) {
            personas.add(new Persona("Hombre " + i, true, salaBaile));
        }
        for (int i = 0; i < 3; i++) {
            personas.add(new Persona("Mujer " + i, false, salaBaile));
        }
        //Iniciamos los hilos
        personas.forEach(persona -> {
            persona.start();
        });

        //Esperamos a que terminen todos los hilos
        personas.forEach(persona -> {
            try {
                persona.join();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        });

        //CHECK
        System.out.println("PROGRAMA FINALIZADO");
    }
}
