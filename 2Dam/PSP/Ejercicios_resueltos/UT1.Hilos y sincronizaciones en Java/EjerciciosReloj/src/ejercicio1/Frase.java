/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Walter
 */
public class Frase extends Thread {

    @Override
    public void run() {
        while (true) {            
            try {
                sleep(5000);//Pausa de 5 segundos
                System.out.println("Se te está acabando el tiempo.");
            } catch (InterruptedException ex) {
                Logger.getLogger(Frase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
