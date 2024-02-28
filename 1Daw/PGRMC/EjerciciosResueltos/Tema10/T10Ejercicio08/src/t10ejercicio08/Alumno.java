/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t10ejercicio08;

/**
 *
 * @author Walter
 */
public class Alumno {

    private String nombre;
    private int curso;

    public Alumno() {
        nombre = "";
        curso = 0;
    }

    public Alumno(String nombre, int curso) {
        this.nombre = nombre;
        this.curso = curso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "----------------\n" + "Nombre: " + nombre + "\nCurso: " + curso;
    }

}
