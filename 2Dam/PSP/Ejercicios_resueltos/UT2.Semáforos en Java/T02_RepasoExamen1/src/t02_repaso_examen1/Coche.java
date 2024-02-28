package t02_repaso_examen1;

public class Coche extends Thread {

    //Atributos
    private String nombre;
    private boolean carrilDerecho;
    private Carretera carretera;

    //Constructor
    public Coche(String nombre, boolean carrilDerecho, Carretera carretera) {
        this.nombre = nombre;
        this.carrilDerecho = carrilDerecho;
        this.carretera = carretera;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    public boolean isCarrilDerecho() {
        return carrilDerecho;
    }

    @Override
    public void run() {
        try {
            if (carrilDerecho) {
                carretera.pasarDerecha(this);
            } else {
                carretera.pasarIzquierda(this);
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }
    }
}
