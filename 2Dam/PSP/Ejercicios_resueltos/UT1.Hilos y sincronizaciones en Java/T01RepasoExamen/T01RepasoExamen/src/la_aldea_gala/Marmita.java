package la_aldea_gala;

public class Marmita {
    private int numRaciones;
    private boolean rellenar;

    public Marmita(int numRaciones) {
        this.numRaciones = numRaciones;
        this.rellenar = false;
    }

    public synchronized void tomarRacion(Galo galo) {
        if (numRaciones == 0 && rellenar == false) {
            System.out.println("000-La marmita está vacía," + galo.getNombre() + " despierta al Druida");
            rellenar = true;
            notifyAll();
        }
        while (numRaciones == 0) {
            try {
                wait();//Espero a que la rellene
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        numRaciones--;
        System.out.println("---" + galo.getNombre() + " está tomando su ración de brebaje mágico. Raciones restantes: " + numRaciones);
    }

    public synchronized void rellenarMarmita(Druida druida) {
        while (!rellenar) {
            try {
                wait();//Espera
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        numRaciones = 4;
        rellenar = false;
        numRaciones--;
        System.out.println("Druida ha rellanado la marmita, toma una ración.Raciones restantes: " + numRaciones);
        notifyAll();
    }
}
