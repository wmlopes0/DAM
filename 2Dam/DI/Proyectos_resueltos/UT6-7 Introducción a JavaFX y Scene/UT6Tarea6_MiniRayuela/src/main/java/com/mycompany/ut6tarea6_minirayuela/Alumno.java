/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ut6tarea6_minirayuela;

/**
 *
 * @author Walter
 */
public class Alumno {

    //Atributos
    private String nombre;
    private String apellido;
    private String rutaFoto;
    private int faltas;

    //Constructores
    public Alumno(String nombre, String apellido, String rutaFoto, int faltas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rutaFoto = rutaFoto;
        this.faltas = faltas;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    //Métodos propios
    public void ponerFalta() {
        faltas++;
    }

    public void quitarFalta() {
        faltas--;
    }

}
