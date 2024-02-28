package t07ejercicio09;

/**
 *
 * @author Walter
 */
public class Alumno {

    private String nombreAlumno;
    private Asignatura[] notas;

    public Alumno() {
        nombreAlumno = "";
        notas = new Asignatura[4];
    }

    public Alumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
        notas = new Asignatura[4];
    }

    public Alumno(String nombreAlumno, Asignatura[] notas) {
        this.nombreAlumno = nombreAlumno;
        this.notas = notas;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public void setNotas(Asignatura[] notas) {
        this.notas = notas;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public Asignatura[] getNotas() {
        return notas;
    }

    // METODOS ESPECIALES-------------------------------
    public void mostrarNotas() {
        for (int i = 0; i < notas.length; i++) {
            System.out.println(notas[i].getNombreAsignatura() + ": " + notas[i].getNota());
        }
    }

    public float calcularMedia() {
        float media = 0;
        for (int i = 0; i < notas.length; i++) {
            media += notas[i].getNota();
        }
        return media / notas.length;
    }

    public int numeroSuspensos() {
        int numeroSuspensos = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i].getNota() < 5) {
                numeroSuspensos++;
            }
        }
        return numeroSuspensos;
    }

}
