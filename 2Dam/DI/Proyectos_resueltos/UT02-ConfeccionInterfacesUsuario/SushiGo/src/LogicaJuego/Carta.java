package LogicaJuego;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author wmartinl01
 */
//Esta clase representa una carta individual en el juego
public class Carta {

    //Atributos
    private String nombre;
    private int valor;
    private String rutaImg;
    private ImageIcon representacion;

    //Constructores
    public Carta(String nombre, int valor, String rutaImg) {
        this.nombre = nombre;
        this.valor = valor;
        this.rutaImg = rutaImg;
        representacion = generarRepresentacion();
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public ImageIcon getRepresentacion() {
        return representacion;
    }

    //MÉTODOS PROPIOS
    //Este método genera el objeto ImageIcon redimensionado que establecemos al botón a partir de la ruta de la imagen.
    private ImageIcon generarRepresentacion() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(rutaImg));
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(Constantes.ANCHO_CARTA, Constantes.ALTO_CARTA, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }
}
