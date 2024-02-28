package threads6_synchronized;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean sincronizado = true;

        Counter c = new Counter(sincronizado);

        HiloContador h1 = new HiloContador(1, c, 5);
        HiloContador h2 = new HiloContador(2, c, 5);

        h1.start();
        h2.start();

        h1.join();
        h2.join();

        System.out.println("PROGRAMA FINALIZADO");
    }
}
