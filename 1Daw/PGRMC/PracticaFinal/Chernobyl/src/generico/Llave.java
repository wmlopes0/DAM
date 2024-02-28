package generico;

/**
 *
 * @author Walter
 */
public class Llave {

    private int idLlave;
    private int celdaAct;

    //CONSTRUCTORES
    public Llave() {
        idLlave = 0;
        celdaAct = 0;
    }

    public Llave(int idLlave, int celdaAct) {
        this.idLlave = idLlave;
        this.celdaAct = celdaAct;
    }

    //SETTER Y GETTER
    public void setIdLlave(int idLlave) {
        this.idLlave = idLlave;
    }

    public void setCeldaAct(int celdaAct) {
        this.celdaAct = celdaAct;
    }

    public int getIdLlave() {
        return idLlave;
    }

    public int getCeldaAct() {
        return celdaAct;
    }
}
