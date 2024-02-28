package p5_Ejercicio2;

public class Producto {
    private String referencia;
    private String nombre;
    private float precio;
    private int existencias;

    //Constructores

    public Producto() {
    }

    public Producto(String referencia, String nombre, float precio, int existencias) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
    }

    //  Getter y Setter
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    //    Mostrar
    public void mostrar() {
        System.out.println("----PRODUCTO----");
        System.out.println("referencia = " + referencia);
        System.out.println("nombre = " + nombre);
        System.out.println("precio = " + precio);
        System.out.println("existencias = " + existencias);
    }

}
