/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoguiadogestionclientes;

import java.util.Date;

/**
 *
 * @author wmartinl01
 */
public class Cliente {

    private String nombre;
    private String apellidos;
    private Date fechaAlta;
    private String provincia;

    //Constructor
    public Cliente(String nombre, String apellidos, Date fechaAlta, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaAlta = fechaAlta;
        this.provincia = provincia;
    }

    //Getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    //Metodos propios
    public String[] toArrayString() {
        String[] s = new String[4];
        s[0] = nombre;
        s[1] = apellidos;
        s[2] = provincia;
        s[3] = fechaAlta.toString();
        return s;
    }
}
