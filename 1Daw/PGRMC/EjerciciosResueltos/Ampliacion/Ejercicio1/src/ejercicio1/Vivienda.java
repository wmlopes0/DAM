/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class Vivienda {

    private String direccion;
    private List<Actividad> actividades;

    //Constructores
    public Vivienda() {
        direccion = "";
        actividades = new ArrayList<>();
    }

    public Vivienda(String direccion) {
        this.direccion = direccion;
        this.actividades = new ArrayList<>();
    }

    //Setter y getter
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    //Metodos propios
}
