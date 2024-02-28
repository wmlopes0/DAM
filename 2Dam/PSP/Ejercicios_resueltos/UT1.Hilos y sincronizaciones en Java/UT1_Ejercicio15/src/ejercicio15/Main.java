package ejercicio15;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Print print = new Print();
        ThreadA threadA = new ThreadA(print);
        ThreadB threadB = new ThreadB(print);
        threadA.start();
        threadB.start();
        //Espero a que terminen
        threadA.join();
        threadB.join();
        //Sigo con el hilo principal
        System.out.println("\n--------------------");
        System.out.println("A's: "+threadA.getContador());
        System.out.println("B's: "+(20-threadA.getContador()));
    }
}
