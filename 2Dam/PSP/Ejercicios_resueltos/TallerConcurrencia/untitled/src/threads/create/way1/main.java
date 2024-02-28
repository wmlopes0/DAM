package threads.create.way1;

public class main {
    public static void main(String[] args) {
        CustomThread hilo1 = new CustomThread();
        hilo1.start();
        System.out.println("================================");
        System.out.println("My name is " + Thread.currentThread().getName());
        System.out.println("My state is " + Thread.currentThread().getState());
    }
}
