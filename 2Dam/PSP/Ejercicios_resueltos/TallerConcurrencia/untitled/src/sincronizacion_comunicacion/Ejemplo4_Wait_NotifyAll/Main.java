package sincronizacion_comunicacion.Ejemplo4_Wait_NotifyAll;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pasteleria pasteleria = new Pasteleria();
        Pastelero pastelero = new Pastelero(pasteleria);
        Cliente cliente = new Cliente(pasteleria);
        pastelero.start();
        cliente.start();
        Thread.sleep(1000);
    }
}
