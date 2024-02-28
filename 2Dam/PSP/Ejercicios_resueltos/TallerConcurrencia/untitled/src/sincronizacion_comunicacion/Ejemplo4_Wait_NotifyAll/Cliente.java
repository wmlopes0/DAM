package sincronizacion_comunicacion.Ejemplo4_Wait_NotifyAll;

public class Cliente extends Thread {
    private Pasteleria pasteleria;

    public Cliente(Pasteleria pasteleria) {
        this.pasteleria = pasteleria;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            int consumed = pasteleria.consumir();
            System.out.println("El cliente se ha comido " + consumed + " bizcochos.");
        }
    }
}