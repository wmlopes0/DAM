package pruebatablemodel;

/**
 *
 * @author wmartinl01
 */
public class Alumno {
    private String nombre, curso;

    //Constructor
    public Alumno(String nombre, String curso) {
        this.nombre = nombre;
        this.curso = curso;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }   
}
