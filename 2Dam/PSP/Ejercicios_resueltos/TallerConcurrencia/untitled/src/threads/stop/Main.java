package threads.stop;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread hilo = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Iteracion " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Me han interrumpido.");
                }
            }
        });
        hilo.start();

        Thread.sleep(5000);//Pauso el hilo principal 5 segundos, si el otro hilo no ha terminado lo interrumpo

        if (hilo.isAlive()){
            hilo.interrupt();
        }
    }
}
