package pRepaso_ejercicio7;

public class Ciudad {
    private String nombre;
    private String faccion;

    // Constructor

    public Ciudad() {
    }

    public Ciudad(String nombre, String faccion) {
        super();
        this.nombre = nombre;
        this.faccion = faccion;
    }

    // Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFaccion() {
        return faccion;
    }

    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }

    public void mostrar() {
        System.out.println("CIUDAD: nombre - " + nombre + ", faccion - " + faccion);
    }
}
