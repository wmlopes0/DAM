package ordenacionarrays;

/**
 *
 * @author Walter
 */
public class Alumno {

    //Constantes para variar el rango del curso aleatorio
    final private static int MIN_CURSO = 1;
    final private static int MAX_CURSO = 4;

    //Variable static global que permite el incremento del atributo idAlumno
    private static int contador = 1;

    //Atributos
    private int idAlumno;
    private String nombre;
    private int curso;

    //Constructores
    public Alumno() {
        this.idAlumno = contador;
        this.nombre = "";
        this.curso = generarCursoAleatorio();
        //Incremento contador
        contador++;
    }

    public Alumno(String nombre) {
        this.idAlumno = contador;
        this.nombre = nombre;
        this.curso = generarCursoAleatorio();
        //Incremento contador
        contador++;
    }

    //Getter y Setter
    public int getIdAlumno() {
        return idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCurso() {
        return curso;
    }

    //Modifico el setCurso para que actualice a un curso nuevo aleatorio dentro de los rangos establecidos
    public void setCurso() {
        this.curso = generarCursoAleatorio();
    }

    private int generarCursoAleatorio() {
        return (int) Math.round(Math.random() * (MAX_CURSO - MIN_CURSO) + MIN_CURSO);
    }

}
