package ejercicio17;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Puente
        Puente puente = new Puente();
//        Peatones
        Peaton peaton1 = new Peaton("María", puente);
        Peaton peaton2 = new Peaton("Ana", puente);
        Peaton peaton3 = new Peaton("Marcos", puente);
        Peaton peaton4 = new Peaton("Juan", puente);
        Peaton peaton5 = new Peaton("Pedro", puente);
//        Barco
        Barco barco = new Barco(puente);
//        Inicio los hilos
        peaton1.start();
        peaton2.start();
        peaton3.start();
        peaton4.start();
        peaton5.start();
        barco.start();
//        Espero a que todos terminen para mostrar el mensaje final
        peaton1.join();
        peaton2.join();
        peaton3.join();
        peaton4.join();
        peaton5.join();
        barco.join();
//        Mensaje final
        System.out.println("******************************************");
        System.out.println("********** FIN DE LA SIMULACIÓN **********");
        System.out.println("******************************************");
    }
}
