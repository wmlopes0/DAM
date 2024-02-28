package sincronizacion_comunicacion.Ejemplo3_Wait_NotifyAll;

public class Main {
    public static void main(String[] args) {
        Buffer pasteleria = new Buffer();
        Productor pastelero = new Productor(pasteleria);
        Consumidor cliente = new Consumidor(pasteleria);

        pastelero.start();
        cliente.start();
    }
}
