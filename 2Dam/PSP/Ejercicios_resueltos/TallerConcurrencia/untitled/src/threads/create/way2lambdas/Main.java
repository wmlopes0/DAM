package threads.create.way2lambdas;

public class Main {
    public static void main(String[] args) {
        Thread hilo = new Thread(() -> System.out.println("My name is " + Thread.currentThread().getName() + " , state: " + Thread.currentThread().getState()));

        hilo.start();
    }
}
