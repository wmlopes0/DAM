import java.util.Random;

public class Archivo {

    //Atributos
    private String nombre;
    private float tamanio;

    //Constructor
    public Archivo(String nombre) {
        this.nombre = nombre;
        this.tamanio = generarAleatorio(300f, 5120f);//Introduzco un tamaño aleatorio entre 300MB y 5120MB
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getTamanio() {
        return tamanio;
    }

    public void setTamanio(float tamanio) {
        this.tamanio = tamanio;
    }

    //Métodos propios
    private float generarAleatorio(float inicio, float fin) {
        //Este método genera un float aleatorio entre el inicio y el fin
        return new Random().nextFloat(inicio, fin);
    }
}
