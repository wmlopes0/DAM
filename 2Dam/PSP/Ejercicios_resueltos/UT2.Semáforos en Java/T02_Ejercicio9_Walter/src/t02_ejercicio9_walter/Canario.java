package t02_ejercicio9_walter;

public class Canario extends Thread {

    //Atributos
    private String nombre;
    private Jaula jaula;

    //Constructor
    public Canario(String nombre, Jaula jaula) {
        this.nombre = nombre;
        this.jaula = jaula;
    }

    //Getter nombre
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        //Cada canario come alpiste 3 veces
        try {
            for (int i = 0; i < 3; i++) {
                jaula.comerAlpiste(this);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }
    }
}
