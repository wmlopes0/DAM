/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio07t15;

import java.io.Serializable;

/**
 *
 * @author Quique Pineda
 */
public class Empleado implements Serializable {
    private String dniEmpleado;
    private int sueldo;

    public Empleado() {
        this.dniEmpleado = "";
        this.sueldo = 0;
    }
    
    public Empleado(String dniEmpleado, int sueldo) {
        this.dniEmpleado = dniEmpleado;
        this.sueldo = sueldo;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }
    
    public void mostrarEmpleado(){
        System.out.println("    DNI: "+this.dniEmpleado+" - Sueldo: "+this.sueldo);
    }
    
}
