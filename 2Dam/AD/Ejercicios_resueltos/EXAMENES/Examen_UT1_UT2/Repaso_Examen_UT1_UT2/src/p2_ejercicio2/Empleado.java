package p2_ejercicio2;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private int sueldo;

    //Constructores
    public Empleado() {
    }

    public Empleado(int id, String nombre, String apellido, int sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sueldo = sueldo;
    }

//    Getter y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    //    Mostrar
    public void mostrar() {
        System.out.println(nombre + " " + apellido + ", sueldo: " + sueldo + "€");
    }
}
