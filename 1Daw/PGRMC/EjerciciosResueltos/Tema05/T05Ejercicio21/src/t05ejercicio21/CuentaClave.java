package t05ejercicio21;

/**
 *
 * @author Walter
 */
public class CuentaClave extends Cuenta {

    private int clave;

    public CuentaClave(int clave) {
        super();
        this.clave = clave;
    }

    public CuentaClave(float saldo, int clave) {
        super(saldo);
        this.clave = clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getClave() {
        return clave;
    }

    @Override
    public void extraer(float cantidad) {
        if (this.saldo >= cantidad) {
            super.extraer(cantidad);
        }
    }
}
