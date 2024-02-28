package com.mycompany.alumnostablemodel;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author jjaronesb01
 */
public class LogicaNegocio {

    public List<Alumno> listaAlumnos = new ArrayList<>();

    public LogicaNegocio() {

        listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno("Jose", "DAM2"));
        listaAlumnos.add(new Alumno("Eduardo", "DAM2"));
        listaAlumnos.add(new Alumno("Javier", "DAM2"));
        listaAlumnos.add(new Alumno("Walter", "DAW2"));
        listaAlumnos.add(new Alumno("Samuel", "DAW2"));
        listaAlumnos.add(new Alumno("Hugo", "DAW1"));
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
}
