package t02_ejercicio11_walter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables globales
    final static int AFORO_MAXIMO = 6;
    final static int PESO_MAXIMO = 450;
    public static List<String> nombresPersona = retornarNombresPersona();
    public static List<Persona> personas = new ArrayList<>();


    public static void main(String[] args) {
        //Instancio ascensor
        Ascensor ascensor = new Ascensor(AFORO_MAXIMO, PESO_MAXIMO);

        //Instancio personas
        nombresPersona.forEach(nombre -> {
            personas.add(new Persona(nombre, ascensor));
        });

        //Inicio hilos
        personas.forEach(persona -> {
            persona.start();
        });

        //Espero a que terminen
        try {
            for (Persona persona : personas) {
                persona.join();
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }

        //CHECK
        System.out.println("PROGRAMA FINALIZADO");
    }

    //Método que rellena la lista de nombres de persona, obviamente me he ayudado de ChatGpt no estoy tan zumbado
    public static List<String> retornarNombresPersona() {
        List<String> nombres = new ArrayList<>();
        nombres.add("María");
        nombres.add("Juan");
        nombres.add("Ana");
        nombres.add("Carlos");
        nombres.add("Laura");
        nombres.add("José");
        nombres.add("Carmen");
        nombres.add("Luis");
        nombres.add("Sofía");
        nombres.add("David");
        nombres.add("Patricia");
        nombres.add("Daniel");
        nombres.add("Lucía");
        nombres.add("Miguel");
        nombres.add("Andrea");
        nombres.add("Fernando");
        nombres.add("Sara");
        nombres.add("Pablo");
        nombres.add("Elena");
        nombres.add("Antonio");
        nombres.add("Isabel");
        nombres.add("Jorge");
        nombres.add("Cristina");
        return nombres;
    }
}
