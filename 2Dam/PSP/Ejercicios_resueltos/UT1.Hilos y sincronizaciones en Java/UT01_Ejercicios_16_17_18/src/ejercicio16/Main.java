package ejercicio16;

public class Main {
    public static void main(String[] args) {
        //Creo Wc, objeto compartido entre todos los hilos donde se sincronizarán
        Wc wc = new Wc();
        //Creo personas(Hilos) cada una de ellas entrará 2 veces al baño
        Persona persona1 = new Persona(wc, "Juan");
        Persona persona2 = new Persona(wc, "Marcos");
        Persona persona3 = new Persona(wc, "Ana");
        Persona persona4 = new Persona(wc, "Pedro");
        Persona persona5 = new Persona(wc, "María");
        //Inicio los hilos
        persona1.start();
        persona2.start();
        persona3.start();
        persona4.start();
        persona5.start();
        //Hago esperar al hilo main, para que no termine hasta que todos los demas hilos hayan terminado.
        try {
            persona1.join();
            persona2.join();
            persona3.join();
            persona4.join();
            persona5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Muestro mensaje final
        System.out.println("=====================================");
        System.out.println("¡TODAS LAS PERSONAS HAN MEADO AGUSTO!");
    }
}
