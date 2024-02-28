/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio05t15;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Alumno implements Serializable {
    private String dni;
    private String nombre;
    private Fecha fMatricula;

    public Alumno() {
        this.dni = "";
        this.nombre = "";
        this.fMatricula = null;
    }
    
    public Alumno(String dni, String nombre, Fecha fMatricula) {
        this.dni = dni;
        this.nombre = nombre;
        this.fMatricula = fMatricula;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Fecha getfMatricula() {
        return fMatricula;
    }

    public void setfMatricula(Fecha fMatricula) {
        this.fMatricula = fMatricula;
    }

    @Override
    public String toString() {
        try{
            return "ALUMNO: " + nombre + "\n   DNI: " + dni + "\n" + fMatricula.toString();
        }
        catch(NullPointerException e){
            return "ALUMNO: " + nombre + "\n   DNI: " + dni + "\n   Fecha Matricula: NO MATRICULADO/A" ;
        }
    }
    
}
