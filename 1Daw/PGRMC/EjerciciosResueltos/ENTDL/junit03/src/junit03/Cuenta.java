package junit03;

/**
 *
 * @author Walter
 */
public class Cuenta {

    private String titular;
    private double cantidad;

    public Cuenta() {
        titular = "";
        cantidad = 0;
    }

    public Cuenta(String titular, double cantidad) {
        this.titular = titular;
        this.cantidad = cantidad;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTitular() {
        return titular;
    }

    public double getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "titular=" + titular + ", cantidad=" + cantidad + '}';
    }

    //METODOS ESPECIALES
    public void ingresar(double cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > this.cantidad) {
            this.cantidad = 0;
        } else {
            this.cantidad -= cantidad;
        }
    }

}
