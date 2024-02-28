package ejercicio1;

/**
 *
 * @author Walter
 */
public class Actividad {

    private String nombre;
    private String tipo;
    private boolean paraTodaLaFamilia;

    //Constructores
    public Actividad() {
        nombre = "";
        tipo = "";
        paraTodaLaFamilia = false;
    }

    public Actividad(String nombre, String tipo, boolean paraTodaLaFamilia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.paraTodaLaFamilia = paraTodaLaFamilia;
    }

    //Setter y getter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setParaTodaLaFamilia(boolean paraTodaLaFamilia) {
        this.paraTodaLaFamilia = paraTodaLaFamilia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isParaTodaLaFamilia() {
        return paraTodaLaFamilia;
    }

    //Metodos propios
}
