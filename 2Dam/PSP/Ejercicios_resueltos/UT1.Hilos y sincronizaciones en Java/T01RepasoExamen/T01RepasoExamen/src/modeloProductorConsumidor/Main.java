package modeloProductorConsumidor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pasteleria pasteleria = new Pasteleria(0);
        Productor pastelero = new Productor(pasteleria);
        Consumidor cliente1 = new Consumidor("Walter", pasteleria);
        Consumidor cliente2 = new Consumidor("Raquel", pasteleria);

        pastelero.start();
        cliente1.start();
        cliente2.start();

        pastelero.join();
        cliente1.join();
        cliente2.join();

        System.out.println("PROGRAMA FINALIZADO.");
    }
}
