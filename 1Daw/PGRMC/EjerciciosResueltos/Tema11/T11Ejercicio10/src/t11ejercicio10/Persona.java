package t11ejercicio10;

/**
 *
 * @author Walter
 */
public class Persona implements Comparable<Persona> {

    private String nombre;
    private int edad;

    public Persona() {
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    //MOSTRAR
    public void mostrar() {
        System.out.println("#NOMBRE: " + nombre + " #EDAD:" + edad);
    }

   @Override
    public int compareTo(Persona t) {
        if (this.edad > t.getEdad()) {
            return 1;
        } else if (this.edad < t.getEdad()) {
            return -1;
        } else {
            return 0;
        }
    }
}
