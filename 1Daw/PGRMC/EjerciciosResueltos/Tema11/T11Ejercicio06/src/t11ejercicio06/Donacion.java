package t11ejercicio06;

/**
 *
 * @author Walter Martín Lopes
 */
public class Donacion implements Comparable<Donacion> {

    private String nombre;
    private float cantidad;

    //CONSTRUCTORES
    public Donacion() {
        nombre = "";
        cantidad = 0;
    }

    public Donacion(String nombre, float cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public float getCantidad() {
        return cantidad;
    }

    //Implemento el metodo compareTo para especificar el criterio de ordenacion natural
    @Override
    public int compareTo(Donacion t) {
        if (this.cantidad < t.getCantidad()) {
            return 1;
        } else if (this.cantidad > t.getCantidad()) {
            return -1;
        } else {
            return 0;
        }
    }

    //===========METODOS PROPIOS===========
    public void mostrar() {
        System.out.println("-----Donacion-----");
        System.out.println("-Nombre: " + nombre);
        System.out.println("-Cantidad: " + cantidad);
    }
}
