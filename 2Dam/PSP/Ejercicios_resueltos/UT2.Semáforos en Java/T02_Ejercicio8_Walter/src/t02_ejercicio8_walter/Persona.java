package t02_ejercicio8_walter;

public class Persona extends Thread {

    //Atributos
    private String nombre;
    private boolean esHombre;
    private SalaBaile salaBaile;

    //Constructor
    public Persona(String nombre, boolean esHombre, SalaBaile salaBaile) {
        this.nombre = nombre;
        this.esHombre = esHombre;
        this.salaBaile = salaBaile;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }
    public boolean esHombre() {
        return esHombre;
    }

    @Override
    public void run() {
        //Cada persona baila 3 veces
//        for (int i = 0; i < 3; i++) {
            try {
                salaBaile.bailar(this);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
//        }
    }
}
