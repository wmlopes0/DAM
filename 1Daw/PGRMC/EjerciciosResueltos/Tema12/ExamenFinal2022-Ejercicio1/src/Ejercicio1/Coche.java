package Ejercicio1;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Coche implements Serializable{

    private String matricula;
    private int kms;
    private LocalDate fechaMatriculacion;

    //CONSTRUCTORES
    public Coche() {
        this.matricula = "";
        this.kms = 0;
        this.fechaMatriculacion = LocalDate.now();
    }

    public Coche(String matricula, int kms, LocalDate fechaMatriculacion) {
        this.matricula = matricula;
        this.kms = kms;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    //SETTER Y GETTER
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setKms(int kms) {
        this.kms = kms;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getKms() {
        return kms;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    //MÉTODOS PROPIOS
    public void rellenarInfo() {
        System.out.println("=======COCHE=======");
        matricula = pedirCadena("la matricula");
        kms = pedirEntero("los kilometros");
        fechaMatriculacion = pedirFecha();
    }

    private String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        return entrada.nextLine();
    }

    private int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        int entero = 0;
        boolean valido;

        do {
            valido = true;
            System.out.println("Introduce " + texto + ": ");
            try {
                entero = entrada.nextInt();
            } catch (InputMismatchException e) {
                valido = false;
                entrada.next();
            }
        } while (!valido);

        return entero;
    }

    private LocalDate pedirFecha() {
        LocalDate fecha = null;
        int ano, mes, dia;
        boolean valido;
        do {
            valido = true;
            ano = pedirEntero("el año de matriculacion");
            mes = pedirEntero("el mes de matriculacion");
            dia = pedirEntero("el dia de matriculacion");
            try {
                fecha = LocalDate.of(ano, mes, dia);
            } catch (DateTimeException e) {
                valido = false;
                System.out.println("ERROR.EL FORMATO DE LA FECHA NO ES VALIDO, INTENTALO DE NUEVO MANOLO.");
            }
        } while (!valido);

        return fecha;
    }

    public void mostrarInfo() {
        System.out.println("=======COCHE=======");
        System.out.println("Matricula: " + matricula);
        System.out.println("Kms: " + kms);
        System.out.println("Fecha de matriculacion: " + fechaMatriculacion);
        System.out.println("-------------------");
    }
}
