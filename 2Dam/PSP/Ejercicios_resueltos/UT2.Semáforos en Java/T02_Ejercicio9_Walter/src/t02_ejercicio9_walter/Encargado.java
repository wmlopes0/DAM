package t02_ejercicio9_walter;

public class Encargado extends Thread {

    //Atributos
    private String nombre;
    private Jaula jaula;

    //Constructor
    public Encargado(String nombre, Jaula jaula) {
        this.nombre = nombre;
        this.jaula = jaula;
    }

    //Getter nombre
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        //Repone el alpiste 3 veces
        try {
            for (int i = 0; i < 3; i++) {
                jaula.reponerAlpiste(this);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }
    }
}
