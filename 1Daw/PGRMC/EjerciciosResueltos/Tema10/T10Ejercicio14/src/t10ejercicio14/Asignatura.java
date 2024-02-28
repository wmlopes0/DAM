package t10ejercicio14;

/**
 *
 * @author Walter
 */
public class Asignatura {

    private String nombre;
    private float nota;

    public Asignatura() {
        nombre = "";
        nota = 0;
    }

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura(String nombre, float nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public float getNota() {
        return nota;
    }

}
