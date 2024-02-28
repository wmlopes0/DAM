package sincronizacion_comunicacion.Ejemplo4_Wait_NotifyAll;

public class Pastelero extends Thread {
    private Pasteleria pasteleria;

    public Pastelero(Pasteleria pasteleria) {
        this.pasteleria = pasteleria;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            pasteleria.producir(i);
            System.out.println("El pastelero ha producido " + i + " bizcochos.");
        }
    }
}