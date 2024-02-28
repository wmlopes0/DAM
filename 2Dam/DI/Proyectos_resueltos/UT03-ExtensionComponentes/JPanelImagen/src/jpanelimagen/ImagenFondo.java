package jpanelimagen;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Walter
 */
public class ImagenFondo implements Serializable {

    //Atributos
    private File rutaImagen;
    private Float opacidad;

    //Constructores
    public ImagenFondo() {
    }

    public ImagenFondo(File rutaImagen, Float opacidad) {
        this.rutaImagen = rutaImagen;
        this.opacidad = opacidad;
    }

    //Getter y Setter
    public File getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(File rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Float getOpacidad() {
        return opacidad;
    }

    public void setOpacidad(Float opacidad) {
        this.opacidad = opacidad;
    }

}
