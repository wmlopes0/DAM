/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wmartinl01
 */
public class CorredorAnimal extends Thread {

    private String palabra;

    public CorredorAnimal(String palabra) {
        this.palabra = palabra.toUpperCase();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(palabra);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(CorredorAnimal.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
}
