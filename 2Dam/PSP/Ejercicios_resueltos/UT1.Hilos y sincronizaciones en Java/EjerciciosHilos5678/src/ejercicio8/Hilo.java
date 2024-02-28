/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wmartinl01
 */
public class Hilo extends Thread {

    private int num1;
    private int num2;
    private int sumaTotal;

    public Hilo(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1);
            sumaTotal = num1 + num2;
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getSumaTotal() {
        return sumaTotal;
    }

}
