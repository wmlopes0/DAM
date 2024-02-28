package t02_ejercicio13_walter;

public class Main {
    public static void main(String[] args) {
        //Instancio platanera
        Platanera platanera = new Platanera();

        //Instancio productores
        Productor productor1 = new Productor("Productor", platanera);
        Productor productor2 = new Productor("Productor", platanera);

        //Instancio consumidores
        Consumidor consumidorA = new Consumidor("Señor A", "A", platanera);
        Consumidor consumidorB = new Consumidor("Señor B", "B", platanera);

        //Inicio hilos
        productor1.start();
        productor2.start();
        consumidorA.start();
        consumidorB.start();
    }
}
