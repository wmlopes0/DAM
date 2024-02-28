package t07ejercicio06;

/**
 *
 * @author Walter
 */
public class Empleado {

    private String nombre;
    private int horasTrabajadas;
    private float tarifaHora;

    public Empleado() {
        nombre = "";
        horasTrabajadas = 0;
        tarifaHora = 0;
    }

    public Empleado(String nombre, int horasTrabajadas, float tarifaHora) {
        this.nombre = nombre;
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaHora = tarifaHora;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public void setTarifaHora(float tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public float getTarifaHora() {
        return tarifaHora;
    }

}
