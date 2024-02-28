package T02_Ejercicio5_Walter;

public class Main {

    //Variables globales
    public static Canario[] canarios = new Canario[10];

    public static void main(String[] args) throws InterruptedException {
        //Instancio Jaula
        Jaula jaula = new Jaula(3, 1);

        //Instancio e inicio Canarios
        for (int i = 0; i < canarios.length; i++) {
            canarios[i] = new Canario("Canario-" + i, jaula);
            canarios[i].start();
        }

        //Espero a que terminen todos los canarios
        for (int i = 0; i < canarios.length; i++) {
            canarios[i].join();
        }

        //Mensaje Check
        System.out.println("PROGRAMA FINALIZADO");
    }
}
