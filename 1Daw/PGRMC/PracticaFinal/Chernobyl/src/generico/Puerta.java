package generico;

/**
 *
 * @author Walter
 */
public class Puerta {

    private int idCelda;
    private int idLlave;
    private boolean abierta;

    //CONSTRUCTORES
    public Puerta() {
        idCelda = 0;
        idLlave = 0;
        abierta = false;
    }

    public Puerta(int idCelda) {
        this.idCelda = idCelda;
        idLlave = 0;
        abierta = false;
    }

    public Puerta(int idCelda, int idLlave) {
        this.idCelda = idCelda;
        this.idLlave = idLlave;
        abierta = false;
    }

    //SETTER Y GETTER
    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    public void setIdLlave(int idLlave) {
        this.idLlave = idLlave;
    }

    public void setAbierta(boolean puertaAbierta) {
        this.abierta = puertaAbierta;
    }

    public int getIdCelda() {
        return idCelda;
    }

    public int getIdLlave() {
        return idLlave;
    }

    public boolean isAbierta() {
        return abierta;
    }

}
