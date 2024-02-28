/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

/**
 *
 * @author Walter
 */
public class Reloj {

    public static void main(String[] args) {
        Hora hora = new Hora();
        Sonido sonido = new Sonido();
        Frase frase = new Frase();
        hora.start();
        sonido.start();
        frase.start();
    }
}
