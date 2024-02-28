package modeloProductorConsumidor;

public class Productor extends Thread {
    private Pasteleria pasteleria;

    public Productor(Pasteleria pasteleria) {
        this.pasteleria = pasteleria;
    }

    @Override
    public void run() {
        //Va a producir 2 veces bizcochos
        for (int i = 0; i <2; i++) {
            pasteleria.producir(5);
        }
    }
}
