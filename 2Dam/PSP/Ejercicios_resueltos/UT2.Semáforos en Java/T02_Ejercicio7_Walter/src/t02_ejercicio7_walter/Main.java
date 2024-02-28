package t02_ejercicio7_walter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables globales
    private static String[] nombrePersonas = {"Ana", "Natalia", "Víctor", "Jesús", "Sergio", "Pepe", "Luis", "Claudia","Walter","Rosa"};
    private static List<Persona> personas = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        //Instancio Baño
        Banio banio = new Banio();

        //Instancio e inicio 5 personas
        for (int i = 0; i < 5; i++) {
            personas.add(new Persona(nombrePersonas[i], banio));
            personas.get(i).start();
        }

        //Instancio e inicio el empleado
        Empleado empleado = new Empleado("El empleado", banio);
        empleado.start();

        for (int i = 5; i < 10; i++) {
            personas.add(new Persona(nombrePersonas[i], banio));
            personas.get(i).start();
        }

        //Espero a que terminen todos los hilos
        for (Persona persona : personas) {
            persona.join();
        }
        empleado.join();

        //Check
        System.out.println("PROGRAMA FINALIZADO");
    }
}
