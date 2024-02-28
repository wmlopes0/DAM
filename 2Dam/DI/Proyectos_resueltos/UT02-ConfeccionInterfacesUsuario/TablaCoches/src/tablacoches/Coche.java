package tablacoches;

/**
 *
 * @author Walter
 */
public class Coche {

    private String modelo;
    private String color;
    private float precio;
    private String fechaMatriculacion;

    //Constructores
    public Coche() {
    }

    public Coche(String modelo, String color, float precio, String fechaMatriculacion) {
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    //Getter y Setter
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(String fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

}
