package threads.create.way2;

public class CustomRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("My name is "+Thread.currentThread().getName());
        System.out.println("My state is "+Thread.currentThread().getState());
    }
}
