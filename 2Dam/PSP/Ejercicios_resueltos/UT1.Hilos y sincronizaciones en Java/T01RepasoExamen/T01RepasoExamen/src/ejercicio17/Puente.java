package ejercicio17;

public class Puente {

    private int numPersonas;
    private boolean barco;

    public Puente() {
        this.numPersonas = 0;
        this.barco = false;
    }

    public void pasaPeaton(Peaton peaton) {
        //Entrar en el puente si no hay un barco pasando o esperando a pasar
        synchronized (this) {
            while (barco) {
                try {
                    wait();
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }
            numPersonas++;
            System.out.println("Entra " + peaton.getNombre() + " por el puente. Hay " + numPersonas);
        }

        System.out.println(peaton.getNombre() + " está en el puente.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

        //BLOQUE PARA SALIR DEL PUENTE
        synchronized (this) {
            numPersonas--;
            System.out.println("\t=> Sale " + peaton.getNombre() + " del puente.Quedan " + numPersonas);
            notifyAll();
        }
    }

    public synchronized void pasaBarco(Barco barquito) {
        while (numPersonas != 0) {
            barco = true;
            System.out.println("******************** BARCO QUIERE PASAR ********************");
            try {
                wait();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        System.out.println(barquito.getNombre() + " está cruzando ....<__P__>");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
        System.out.println("El barco salió del puente.");
        barco = false;
        notifyAll();
    }

}
