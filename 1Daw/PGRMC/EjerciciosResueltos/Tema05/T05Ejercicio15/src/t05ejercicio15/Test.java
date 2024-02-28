/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio15;

import java.util.Objects;

/**
 *
 * @author Walter
 */
public class Test {

    public static void mostrarEmpleados(Empleado empleado1, Empleado empleado2, Empleado empleado3) {
        String empleado = "empleado";

        System.out.println("===================EMPLEADOS====================");
        System.out.println("-------------------Empleado 1-------------------");
        System.out.println("Nombre: " + empleado1.getNombre());
        System.out.println("Salario: " + empleado1.getSalario());
        System.out.println("Direccion: C/" + empleado1.getDireccion().getCalle() + "/N" + empleado1.getDireccion().getNumero()
                + "/P" + empleado1.getDireccion().getPiso() + "  " + empleado1.getDireccion().getCiudad());
        System.out.println("-------------------Empleado 2-------------------");
        System.out.println("Nombre: " + empleado2.getNombre());
        System.out.println("Salario: " + empleado2.getSalario());
        System.out.println("Direccion: C/" + empleado2.getDireccion().getCalle() + "/N" + empleado2.getDireccion().getNumero()
                + "/P" + empleado2.getDireccion().getPiso() + "  " + empleado2.getDireccion().getCiudad());
        System.out.println("-------------------Empleado 3-------------------");
        System.out.println("Nombre: " + empleado3.getNombre());
        System.out.println("Salario: " + empleado3.getSalario());
        System.out.println("Direccion: C/" + empleado3.getDireccion().getCalle() + "/N" + empleado3.getDireccion().getNumero()
                + "/P" + empleado3.getDireccion().getPiso() + "  " + empleado3.getDireccion().getCiudad());

    }

    public static void main(String[] args) {

        //Creamos 3 direcciones
        Direccion direccion1 = new Direccion("Niscalo", 3, 5, "Plasencia");
        Direccion direccion2 = new Direccion("Sol", 7, 3, "Plasencia");
        Direccion direccion3 = new Direccion("Factor", 2, 4, "Plasencia");

        //Creamos 3 empleados
        Empleado empleado1 = new Empleado("Pepe", 1500, direccion2);
        Empleado empleado2 = new Empleado("Eustaquio", 1800, direccion3);
        Empleado empleado3 = new Empleado("Manolo", 2500, direccion1);

        //Muestro los empleados
        mostrarEmpleados(empleado1, empleado2, empleado3);

    }

}
