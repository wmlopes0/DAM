package t12ejercicio19;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
class SupuestoBecario {

    private String nombreApellido;
    private String sexo;
    private int edad;
    private int nSuspensos;
    private String residencia;
    private int ingresosAnualesFamilia;

    //CONSTRUCTORES
    public SupuestoBecario() {
        rellenarSupuestoBecario();
    }

    public SupuestoBecario(String nombreApellido, String sexo, int edad, int nSuspensos, String residencia, int ingresosAnualesFamilia) {
        this.nombreApellido = nombreApellido;
        this.sexo = sexo;
        this.edad = edad;
        this.nSuspensos = nSuspensos;
        this.residencia = residencia;
        this.ingresosAnualesFamilia = ingresosAnualesFamilia;
    }

    //SETTER Y GETTER
    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setnSuspensos(int nSuspensos) {
        this.nSuspensos = nSuspensos;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public void setIngresosAnualesFamilia(int ingresosAnualesFamilia) {
        this.ingresosAnualesFamilia = ingresosAnualesFamilia;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public String getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public int getnSuspensos() {
        return nSuspensos;
    }

    public String getResidencia() {
        return residencia;
    }

    public int getIngresosAnualesFamilia() {
        return ingresosAnualesFamilia;
    }

    //METODOS RELLENAR
    private void rellenarSupuestoBecario() {
        this.nombreApellido = pedirCadena("nombre y apellido");
        this.sexo = pedirCadena("sexo", "H-M");
        this.edad = pedirEntero("edad", "20-60");
        this.nSuspensos = pedirEntero("numero de suspensos del curso anterior", "0-4");
        this.residencia = pedirCadena("residencia familiar", "SI-NO");
        this.ingresosAnualesFamilia = pedirEntero("ingresos anuales de la familia");
    }

    private boolean comprobarValidacion(String valor, String[] validacion) {
        boolean valido = false;
        for (int i = 0; i < validacion.length; i++) {
            if (valor.equalsIgnoreCase(validacion[i])) {
                valido = true;
            }
        }
        return valido;
    }

    private boolean comprobarValidacion(int valor, String[] validacion) {
        boolean valido = false;

        if (valor >= Integer.parseInt(validacion[0]) && valor <= Integer.parseInt(validacion[1])) {
            valido = true;
        }

        return valido;
    }

    private String pedirCadena(String texto, String condicion) {
        Scanner entrada = new Scanner(System.in);
        String[] validacion = condicion.split("-");
        String input;
        System.out.println("Introduce " + texto + " " + condicion + ": ");
        input = entrada.nextLine();
        if (comprobarValidacion(input, validacion)) {
            return input;
        } else {
            System.out.println("ERROR.INTRODUCE DATOS VALIDOS.");
            return pedirCadena(texto, condicion);
        }
    }

    private String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        return entrada.nextLine();
    }

    private int pedirEntero(String texto, String condicion) {
        Scanner entrada = new Scanner(System.in);
        String[] validacion = condicion.split("-");
        int input = 0;

        System.out.println("Introduce " + texto + " " + condicion + ": ");
        try {
            input = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR. INTRODUCE UN NUMERO.");
            entrada.next();
            pedirEntero(texto, condicion);

        }

        if (comprobarValidacion(input, validacion)) {
            return input;

        } else {
            System.out.println("ERROR.INTRODUCE DATOS VALIDOS.");
            return pedirEntero(texto, condicion);
        }

    }

    private int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        int input = 0;
        try {
            input = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR. INTRODUCE UN NUMERO.");
            entrada.next();
            pedirEntero(texto);
        }
        return input;
    }
}
