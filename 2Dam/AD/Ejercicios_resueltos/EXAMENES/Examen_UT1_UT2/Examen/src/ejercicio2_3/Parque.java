package ejercicio2_3;

public class Parque {
    private String nombre;
    private String ciudad;

//    Constructores

    public Parque() {

    }

    public Parque(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

//    Getter y Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    //    Mostrar
    public void mostrar() {
        System.out.println("----- PARQUE -----");
        System.out.println("nombre = " + nombre);
        System.out.println("ciudad = " + ciudad);
    }
}
