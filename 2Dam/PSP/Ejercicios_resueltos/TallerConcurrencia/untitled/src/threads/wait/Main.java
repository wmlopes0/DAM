package threads.wait;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread hilo = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Iteracion " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        hilo.start();
        hilo.join();//Espera a que este hilo termine antes de continuar con el hilo principal

        System.out.println("My name is " + Thread.currentThread().getName());
        System.out.println("My state is " + Thread.currentThread().getState());

    }
}
