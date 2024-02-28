package threads.create.way2;

public class Main {
    public static void main(String[] args) {
        Runnable task = new CustomRunnable();
        Thread hilo = new Thread(task);
        hilo.start();
    }
}
