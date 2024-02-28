package ejercicio15;


public class Print {
    private int letras = 20;
    boolean printA;

    public synchronized void pintar()  {
        if (letras != 0) {
            if (Thread.currentThread().getName().equalsIgnoreCase("A")) {
                System.out.print("A");
                printA = true;
                letras--;//Decrementamos contador de letras
                notifyAll();
            } else {
                if (printA && Thread.currentThread().getName().equalsIgnoreCase("B")) {
                    System.out.print("B");
                    printA = false;
                    letras--;//Decrementamos contador de letras
                    notifyAll();
                }
                else{
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
//                NO HAGO NADA
                    }
                }
            }

        } else {
            Thread.currentThread().interrupt();
//            System.out.println("\n"+Thread.currentThread().getName()+": He sido interrumpido.");
        }
    }

}
