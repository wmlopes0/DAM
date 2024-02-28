package threads.allThreads;

public class Main {
    public static void main(String[] args) {
        Thread.getAllStackTraces().keySet().forEach(thread ->{
            System.out.println("---------------------------");
            System.out.println(thread.getId());
            System.out.println(thread.getName());
            System.out.println(thread.getState().name());
            System.out.println(thread.isAlive());
            System.out.println(thread.isInterrupted());
        });
    }
}
