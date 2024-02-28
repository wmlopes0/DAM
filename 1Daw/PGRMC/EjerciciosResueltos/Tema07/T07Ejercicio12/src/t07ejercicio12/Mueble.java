package t07ejercicio12;

/**
 *
 * @author Walter
 */
public class Mueble {

    private float precio;
    private String descripcion;

    public Mueble() {
        precio = 0;
        descripcion = "";
    }

    public Mueble(float precio, String descripcion) {
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "\n#Mueble: \n" + "Precio: " + precio +" euros."+ "\nDescripcion: " + descripcion+".";
    }
    
    

}