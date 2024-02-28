/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio6;

/**
 *
 * @author wmartinl01
 */
public class Main {

    public static void main(String[] args) {
        CorredorAnimal tortuga = new CorredorAnimal("Tortuga");
        tortuga.setName("Tortuga");
        tortuga.setPriority(1);
        CorredorAnimal liebre = new CorredorAnimal("Liebre");
        liebre.setName("Liebre");
        liebre.setPriority(5);
        CorredorAnimal guepardo = new CorredorAnimal("Guepardo");
        guepardo.setName("Guepardo");
        guepardo.setPriority(10);
        
        tortuga.start();
        liebre.start();
        guepardo.start();
        
    }
}
