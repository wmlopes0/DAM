package modeloProductorConsumidor;

public class Consumidor extends Thread {
    private String nombre;
    private Pasteleria pasteleria;

    public Consumidor(String nombre, Pasteleria pasteleria) {
        this.nombre = nombre;
        this.pasteleria = pasteleria;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            pasteleria.consumir(this);
            Thread.yield();
        }
    }
}
