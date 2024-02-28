package pokemon;

/**
 *
 * @author wmartinl01
 */
public class Ataque {

    private String nombre;
    private String tipo;
    private int puntosDeDanoNormal;
    private int puntosDeDanoVulnerable;
    private int puntosDeDanoInofensivo;

    //CONSTRUCTORES
    public Ataque(String nombre, String tipo, int puntosDeDanoNormal, int puntosDeDanoVulnerable, int puntosDeDanoInofensivo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.puntosDeDanoNormal = puntosDeDanoNormal;
        this.puntosDeDanoVulnerable = puntosDeDanoVulnerable;
        this.puntosDeDanoInofensivo = puntosDeDanoInofensivo;
    }

    //SETTER Y GETTER
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPuntosDeDanoNormal() {
        return puntosDeDanoNormal;
    }

    public void setPuntosDeDanoNormal(int puntosDeDanoNormal) {
        this.puntosDeDanoNormal = puntosDeDanoNormal;
    }

    public int getPuntosDeDanoVulnerable() {
        return puntosDeDanoVulnerable;
    }

    public void setPuntosDeDanoVulnerable(int puntosDeDanoVulnerable) {
        this.puntosDeDanoVulnerable = puntosDeDanoVulnerable;
    }

    public int getPuntosDeDanoInofensivo() {
        return puntosDeDanoInofensivo;
    }

    public void setPuntosDeDanoInofensivo(int puntosDeDanoInofensivo) {
        this.puntosDeDanoInofensivo = puntosDeDanoInofensivo;
    }

    //MÉTODOS PROPIOS
    public void mostrarAtaque() {
        System.out.println("   -" + nombre);
        System.out.println("   -TIPO: " + tipo);
        System.out.println("   -Daño normal: " + puntosDeDanoNormal);
        System.out.println("   -Daño vulnerable: " + puntosDeDanoVulnerable);
        System.out.println("   -Daño inofensivo: " + puntosDeDanoInofensivo);
    }
}
