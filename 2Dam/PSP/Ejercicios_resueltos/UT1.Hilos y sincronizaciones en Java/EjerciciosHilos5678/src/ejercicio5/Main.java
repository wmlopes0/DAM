/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio5;

/**
 *
 * @author wmartinl01
 */
public class Main {

    public static void main(String[] args) {
        String[] palabras = {"Gato", "Perro", "Walter", "Pokemon", "Cerveza"};

        for (int i = 0; i < palabras.length; i++) {
            PintaDiezVeces hilo = new PintaDiezVeces(palabras[i]);
            hilo.start();
        }

    }
}
