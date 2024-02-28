package profesorBacterio;

public class Controlador {

    private int moleculasHidrogeno = 0;
    private int moleculasOxigeno = 0;

    public Controlador() {
    }

    public synchronized void meterOxigeno() {
        while (moleculasOxigeno == 1) {
            try {
                System.out.println("No se pueden meter más oxigenos.");
                wait();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        moleculasOxigeno++;
        System.out.println("Se ha metido un oxígeno.");
        producirAgua();
//        notifyAll();
    }

    public synchronized void meterHidrogeno() {
        while (moleculasHidrogeno == 2) {
            try {
                System.out.println("No se pueden meter más hidrógenos.");
                wait();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        moleculasHidrogeno++;
        System.out.println("Se ha metido un hidrógeno.");
        producirAgua();
//        notifyAll();
    }

    public void producirAgua() {
        if (moleculasOxigeno == 1 && moleculasHidrogeno == 2) {
            System.out.println("Se hizo agua\n");
            moleculasHidrogeno = 0;
            moleculasOxigeno = 0;
            try {
                Thread.sleep(300);//Espero para no recalentamiento
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyAll();
        }
    }
}
