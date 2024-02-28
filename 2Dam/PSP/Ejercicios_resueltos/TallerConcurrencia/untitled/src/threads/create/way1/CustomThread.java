package threads.create.way1;

public class CustomThread extends Thread{
    @Override
    public void run() {
        System.out.println("My name is "+this.getName());
        System.out.println("My state is "+this.getState());
    }
}
