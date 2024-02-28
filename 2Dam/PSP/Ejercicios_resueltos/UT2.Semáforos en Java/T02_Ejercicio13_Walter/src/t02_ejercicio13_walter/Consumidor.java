package t02_ejercicio13_walter;

public class Consumidor extends Thread {

    //Atributos
    private String nombre;
    private Platanera platanera;
    private String calidad;

    //Constructor
    public Consumidor(String nombre, String calidad, Platanera platanera) {
        this.nombre = nombre;
        this.calidad = calidad;
        this.platanera = platanera;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    public String getCalidad() {
        return calidad;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //Si la cinta esta vacia no consume
                if (!platanera.getCintaProduccion().isEmpty()) {
                    platanera.consumir(this);
                }
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }
    }
}
