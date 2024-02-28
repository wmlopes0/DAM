package t07ejercicio07;

/**
 *
 * @author Walter
 */
public class Dia {

    private String nombre;
    private int temperatura;

    public Dia() {
        nombre = "";
        temperatura = 0;
    }

    public Dia(String nombre, int temperatura) {
        this.nombre = nombre;
        this.temperatura = temperatura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTemperatura() {
        return temperatura;
    }

}
