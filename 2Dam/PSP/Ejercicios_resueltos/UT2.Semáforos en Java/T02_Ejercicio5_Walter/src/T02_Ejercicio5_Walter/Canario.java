package T02_Ejercicio5_Walter;

public class Canario extends Thread {
    //Atributos
    private String nombre;
    private Jaula jaula;

    //Constructor
    public Canario(String nombre, Jaula jaula) {
        this.nombre = nombre;
        this.jaula = jaula;
    }

    //GetNombre
    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try {
            jaula.comer(this);
            jaula.columpiarse(this);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
