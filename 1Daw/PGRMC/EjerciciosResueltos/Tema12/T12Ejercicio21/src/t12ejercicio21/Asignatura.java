package t12ejercicio21;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Asignatura implements Serializable {

    private String nombre;
    private float nota;

    //CONSTRUCTORES
    public Asignatura() {
        this.nombre = "";
        this.nota = 0;
    }

    public Asignatura(String nombre) {
        this.nombre = nombre;
        this.nota = 0;
    }

    public Asignatura(String nombre, float nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    //SETTER Y GETTER ATRIBUTOS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public float getNota() {
        return nota;
    }

    //METODOS PROPIOS
    //Rellenar
    public void rellenarAsignatura() {
        System.out.println("========" + this.nombre + "========");
        this.nota = pedirNota();
    }

    private int pedirNota() {
        Scanner entrada = new Scanner(System.in);
        int calificacion;
        do {
            System.out.println("Introduce la nota: ");
            try {
                calificacion = entrada.nextInt();
            } catch (InputMismatchException e) {
                calificacion = -1;
            }

        } while (!validarNota(calificacion));

        return calificacion;
    }

    private boolean validarNota(int nota) {
        boolean valida = false;
        if (nota >= 0 && nota <= 10) {
            valida = true;
        } else {
            if (nota == -1) {
                System.out.println("ERROR.Introduce un numero.");
            } else {
                System.out.println("ERROR.Introduce una nota valida.");
            }
        }
        return valida;
    }

}
