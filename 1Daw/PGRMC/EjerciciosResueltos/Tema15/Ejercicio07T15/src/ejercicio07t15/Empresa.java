/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio07t15;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Quique Pineda
 */
public class Empresa implements Serializable {
    private String cifEmpresa;
    private String nombreEmpresa;
    private ArrayList<Empleado> lEmpleados;

    public Empresa() {
        this.cifEmpresa = "";
        this.nombreEmpresa = "";
        this.lEmpleados = new ArrayList<>();
    }
    
    public Empresa(String cifEmpresa, String nombreEmpresa) {
        this.cifEmpresa = cifEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.lEmpleados = new ArrayList<>();
    }

    public String getCifEmpresa() {
        return cifEmpresa;
    }

    public void setCifEmpresa(String cifEmpresa) {
        this.cifEmpresa = cifEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public ArrayList<Empleado> getLEmpleados(){
        return this.lEmpleados;
    }
    
    public static String pedirDni(){
        Scanner teclado = new Scanner(System.in);
        String dni;
        System.out.print("DNI: ");
        dni = teclado.nextLine();
        return dni;
    }
    
    public static int pedirSueldo(){
        Scanner teclado = new Scanner(System.in);
        int sueldo;
        System.out.print("Sueldo: ");
        sueldo = teclado.nextInt();
        return sueldo;
    }
    
    public void rellenarEmpleados(){
        Scanner teclado = new Scanner(System.in);
        String resp, dni;
        int sueldo;
        Empleado nEmpleado;
        do{
            dni = pedirDni();
            sueldo = pedirSueldo();
            nEmpleado = new Empleado(dni, sueldo);
            this.lEmpleados.add(nEmpleado);
            System.out.println("¿Desea añadir más empleados?");
            resp = teclado.nextLine();
        }while(resp.equalsIgnoreCase("SI"));
    }
    
    public void mostrarEmpresa(){
        System.out.println("CIF: "+this.cifEmpresa);
        System.out.println("Empresa: "+this.nombreEmpresa);
        for(int i = 0;i < this.lEmpleados.size();i++){
            this.lEmpleados.get(i).mostrarEmpleado();
        }
    }
    
    public int getNumEmpleados(){
        return this.lEmpleados.size();
    }
    
    public int getTotalSueldos(){
        int total = 0;
        for(int i = 0;i < this.lEmpleados.size();i++){
            total = total + this.lEmpleados.get(i).getSueldo();
        }
        return total;
    }
}
