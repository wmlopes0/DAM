/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejerciciopersonaje;

/**
 *
 * @author Walter
 */
public class Test {

    public static void main(String[] args) {

        //Creamos dos personajes de prueba
        Personaje personaje1 = new Personaje();
        Personaje personaje2 = new Personaje();

        //Establecemos atributos
        personaje1.setNombre("Nacho");
        personaje1.setFuerza(7);
        personaje1.setNivel(1);

        personaje2.setNombre("Hugo");
        personaje2.setFuerza(1);
        personaje2.setNivel(2);

        //Mostramos información
        System.out.println("==============DATOS DE LOS PERSONAJES==============");
        System.out.println("--------------------Personaje 1--------------------");
        System.out.println("Nombre: " + personaje1.getNombre());
        System.out.println("Fuerza: " + personaje1.getFuerza());
        System.out.println("Nivel: " + personaje1.getNivel());
        System.out.println("--------------------Personaje 2--------------------");
        System.out.println("Nombre: " + personaje2.getNombre());
        System.out.println("Fuerza: " + personaje2.getFuerza());
        System.out.println("Nivel: " + personaje2.getNivel());
        System.out.println("===================================================");

    }
}
