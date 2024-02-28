package p1_ejercicio2;

public class Contacto {
    private String nombre;
    private String apellidos;
    private String email;
    private int telefono;

    public Contacto() {
    }

    public Contacto(String nombre, String apellidos, String email, int telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void mostrar(){
        System.out.println("====CONTACTO====");
        System.out.println("nombre = " + nombre);
        System.out.println("apellidos = " + apellidos);
        System.out.println("email = " + email);
        System.out.println("telefono = " + telefono);
    }
}
