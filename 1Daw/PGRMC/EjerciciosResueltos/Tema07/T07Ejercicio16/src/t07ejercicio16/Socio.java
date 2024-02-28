package t07ejercicio16;

/**
 *
 * @author Walter
 */
public class Socio {

    private String nombre;
    private float precioAbonado;

    public Socio() {
        nombre = "";
        precioAbonado = 0;
    }

    public Socio(String nombre, float precioAbonado) {
        this.nombre = nombre;
        this.precioAbonado = precioAbonado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioAbonado(float precioAbonado) {
        this.precioAbonado = precioAbonado;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecioAbonado() {
        return precioAbonado;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nPrecio abonado: " + precioAbonado;
    }

}