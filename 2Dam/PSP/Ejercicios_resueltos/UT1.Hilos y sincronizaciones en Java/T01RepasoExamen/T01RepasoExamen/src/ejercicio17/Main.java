package ejercicio17;

public class Main {
    public static void main(String[] args) {
        Puente puente = new Puente();
        Peaton peaton1 = new Peaton("María", puente);
        Peaton peaton2 = new Peaton("Ana", puente);
        Peaton peaton3 = new Peaton("Marcos", puente);
        Peaton peaton4 = new Peaton("Juan", puente);
        Peaton peaton5 = new Peaton("Pedro", puente);

        Barco barquito = new Barco("Titanic", puente);

        peaton1.start();
        peaton2.start();
        peaton3.start();
        peaton4.start();
        peaton5.start();

        barquito.start();

        try {
            peaton1.join();
            peaton2.join();
            peaton3.join();
            peaton4.join();
            peaton5.join();
            barquito.join();
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        System.out.println("PROGRAMA FINALIZADO");


    }
}
