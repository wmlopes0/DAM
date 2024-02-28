package t10ejercicio14;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Alumno {

    final static String[] NOMBRE_MATERIAS = {"Lengua", "Mates", "Fisica"};

    private String nombre;
    private Asignatura[] asignaturas;

    public Alumno() {
        nombre = "";
        asignaturas = rellenarAsignaturas();
    }

    public Alumno(String nombre) {
        this.nombre = nombre;
        asignaturas = rellenarAsignaturas();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAsignaturas(Asignatura[] asignaturas) {
        this.asignaturas = asignaturas;
    }

    public String getNombre() {
        return nombre;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    //====================METODOS PROPIOS====================
    //Método para rellenar el atributo asignaturas, es privado porque solo se puede usar en el constructor,
    private Asignatura[] rellenarAsignaturas() {
        Asignatura[] materias = new Asignatura[NOMBRE_MATERIAS.length];
        for (int i = 0; i < materias.length; i++) {
            materias[i] = new Asignatura(NOMBRE_MATERIAS[i]);
        }
        return materias;
    }

    public void introducirNotas() {
        for (Asignatura a : asignaturas) {
            System.out.print(a.getNombre() + ": ");
            a.setNota(pedirNota());
            System.out.println("");//Salto de linea
        }
    }

    public float pedirNota() {
        Scanner entrada = new Scanner(System.in);
        return entrada.nextFloat();
    }

    public String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca su nombre: ");
        return entrada.nextLine();
    }

    public void rellenarAlumno() {
        nombre = pedirNombre();
        System.out.println("");//Salto de linea
        this.introducirNotas();
    }

    public void mostrarAlumno() {
        System.out.println("=======" + nombre + "=======");
        for (Asignatura a : asignaturas) {
            System.out.println(a.getNombre() + ": " + a.getNota());
        }
    }

    public float notaMedia() {
        float media = 0;
        for (Asignatura a : asignaturas) {
            media += a.getNota();
        }
        return media / asignaturas.length;
    }
}
