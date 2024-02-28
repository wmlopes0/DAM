package threads2_contadores_join;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Contador c1 = new Contador("Contador 1",40);
        Contador c2 = new Contador("Contador 2",20);
        Contador c3 = new Contador("Contador 3",30);
        Contador c4 = new Contador("Contador 4",10);

        Thread th1 = new Thread(c1);
        Thread th2 = new Thread(c2);
        Thread th3 = new Thread(c3);
        Thread th4 = new Thread(c4);

        th1.start();
        th2.start();
        th3.start();
        th4.start();

        th1.join();
        th2.join();
        th3.join();
        th4.join();

        System.out.println("¡PROGRAMA FINALIZADO!");
    }
}
