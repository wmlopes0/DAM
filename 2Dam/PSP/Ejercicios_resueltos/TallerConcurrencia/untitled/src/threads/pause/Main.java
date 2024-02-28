package threads.pause;

public class Main {
    public static void main(String[] args) {

//        Esto es lo mismo que hacer lo que pongo abajo
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
//        Thread hilo1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 5; i++) {
//                    System.out.println("Iteracion " + i);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        });

        hilo.start();

    }
}
