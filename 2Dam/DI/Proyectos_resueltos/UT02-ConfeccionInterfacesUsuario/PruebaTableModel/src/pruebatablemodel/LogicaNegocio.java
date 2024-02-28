package pruebatablemodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wmartinl01
 */
public class LogicaNegocio {

    private List<Alumno> listaAlumnos = new ArrayList<>();

    //Constructores
    public LogicaNegocio() {
        Alumno alumno1 = new Alumno("Walter", "2ºDam");
        Alumno alumno2 = new Alumno("Pepe", "1ºDam");
        Alumno alumno3 = new Alumno("Israel", "2ºDam");
        listaAlumnos.add(alumno1);
        listaAlumnos.add(alumno2);
        listaAlumnos.add(alumno3);
    }

    //Getter y Setter
    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

}
