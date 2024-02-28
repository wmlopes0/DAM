/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejerciciopizarra01;

/**
 *
 * @author Walter
 */
public class Paciente {

    private String nombre;
    private String sintomas;
    
    public Paciente() {
        nombre = "";
        sintomas = "";
    }

    public Paciente(String nombre) {
        this.nombre = nombre;
        sintomas = "";
    }

    public Paciente(String nombre, String sintomas) {
        this.nombre = nombre;
        this.sintomas = sintomas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSintomas() {
        return sintomas;
    }
}
