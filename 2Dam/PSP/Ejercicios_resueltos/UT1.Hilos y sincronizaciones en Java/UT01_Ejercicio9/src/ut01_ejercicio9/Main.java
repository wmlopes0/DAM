/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut01_ejercicio9;

/**
 *
 * @author Walter
 */
public class Main {

    public static final int NUM_COCHES = 4;
  

    public static void main(String[] args) {
        for (int i = 0; i < NUM_COCHES; i++) {
            Coche coche = new Coche(i);
            coche.start();
        }
    }

}
