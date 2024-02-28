package t07ejercicio13;

/**
 *
 * @author Walter
 */
public class Alumno {

    private String nombre;
    private int edad;
    private float notaMedia;

    public Alumno() {
        nombre = "";
        edad = 0;
        notaMedia = 0;
    }

    public Alumno(String nombre, int edad, float notaMedia) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaMedia = notaMedia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public float getNotaMedia() {
        return notaMedia;
    }
    

}
