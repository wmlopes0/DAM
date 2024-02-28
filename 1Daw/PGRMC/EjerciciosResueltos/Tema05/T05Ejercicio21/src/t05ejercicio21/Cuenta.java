package t05ejercicio21;

/**
 *
 * @author Walter
 */
public class Cuenta {

    protected float saldo;

    public Cuenta() {
        saldo = 0;
    }

    public Cuenta(float saldo) {
        this.saldo = saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void ingresar(float cantidad) {
        saldo += cantidad;
    }

    public void extraer(float cantidad) {
        saldo -= cantidad;
    }

}
