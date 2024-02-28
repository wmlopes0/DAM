/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

/**
 *
 * @author Walter
 */
public class Sonido extends Thread {

    @Override
    public void run() {
        /* código concurrente */
        for (int i = 0; i < 10; i++) {
            System.out.println("Beep!!");
            java.awt.Toolkit.getDefaultToolkit().beep();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
