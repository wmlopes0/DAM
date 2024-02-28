package metodosPrincipalesThread;

public class Ejemplo1_sleep_isAlive {
    public static void main(String[] args) throws InterruptedException {
//        Creo un hilo que se duerme 5 s y finalmente antes de morir saca el mensaje de que ya no está vivo
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("SOY UN HILO QUE YA NO ESTÁ VIVO");
        });
        //Inicio el hilo
        hilo.start();
//        Muestro que el hilo esta vivo
        System.out.println("SOY UN HILO VIVO");
//        Muestro información principal del hilo
        System.out.println(hilo.toString());
        //Mientras el hilo esta vivo...
        while (hilo.isAlive()) {
            System.out.println("Sigo vivo...");
            Thread.sleep(1000);
        }
    }
}
