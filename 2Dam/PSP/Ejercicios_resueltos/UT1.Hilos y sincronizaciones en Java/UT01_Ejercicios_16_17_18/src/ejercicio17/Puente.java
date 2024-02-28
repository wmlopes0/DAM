package ejercicio17;

public class Puente {

    //    Atributos
    private int contadorPersonas = 0;
    private boolean barcoEsperando;

    //    Constructor
    public Puente() {
    }

    //Getter del contador
    public int getContadorPersonas() {
        return contadorPersonas;
    }

    //    Métodos sincronizados de entrar y salir del puente.
    public synchronized void entrar(Peaton peaton, Barco barco) throws InterruptedException {
        if (barco != null) {
            while (contadorPersonas != 0) {
                barcoEsperando = true;
                System.out.println("*************** BARCO QUIERE PASAR ***************");
                wait();
            }
            System.out.println("        Titanic está cruzando....<__P__>");
            Thread.sleep(Utileria.tiempoAleatorio(100, 500));
        } else {
            while (barcoEsperando == true) {
                wait();
            }
            contadorPersonas++;
            System.out.println("Entra " + peaton.getNombre() + " por el puente. Hay " + contadorPersonas);
            System.out.println("        " + peaton.getNombre() + " está en el puente.");
            Thread.sleep(Utileria.tiempoAleatorio(150, 300));
        }
        notifyAll();
    }

    public synchronized void salir(Peaton peaton, Barco barco) {
        if (barco != null) {
            System.out.println("            El barco Titanic salió del puente.");
            barcoEsperando = false;
        } else {
            contadorPersonas--;
            System.out.println("            =>Sale " + peaton.getNombre() + " del puente. Quedan " + contadorPersonas);
            if (barcoEsperando && contadorPersonas == 0) {
                notifyAll();  // Notificar al barco esperando que el puente está vacío.
            }
        }
    }
}
