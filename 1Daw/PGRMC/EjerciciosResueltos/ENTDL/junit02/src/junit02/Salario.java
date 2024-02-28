package junit02;

/**
 *
 * @author Walter
 */
public class Salario {

    public static void main(String[] args) {
    }

    public static float calculaSalarioBruto(String tipo, float ventasMes, float horasExtra) {
        int salarioBase = 0;
        int prima = 0;
        int precioHExtra = 20;

        //Controlo el tipo
        if (tipo.equalsIgnoreCase("vendedor")) {
            salarioBase = 1000;
        } else {
            if (tipo.equalsIgnoreCase("encargado")) {
                salarioBase = 1500;
            }
        }

        //Controlo la prima aplicable
        if (ventasMes >= 1000 && ventasMes < 1500) {
            prima = 100;
        } else {
            if (ventasMes >= 1500) {
                prima = 200;
            }
        }
        return salarioBase + prima + (horasExtra * precioHExtra);
    }

    public static float calculaSalarioNeto(float salarioBruto) {
        float retencion = 1;
        if (salarioBruto >= 1000 && salarioBruto < 1500) {
            retencion = 0.84f;
        } else {
            if (salarioBruto >= 1500) {
                retencion = 0.82f;
            }
        }
        return salarioBruto * retencion;
    }
}
