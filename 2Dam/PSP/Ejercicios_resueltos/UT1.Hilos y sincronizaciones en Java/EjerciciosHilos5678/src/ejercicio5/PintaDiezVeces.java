/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio5;

/**
 *
 * @author wmartinl01
 */
public class PintaDiezVeces extends Thread {

    private String palabra;

    public PintaDiezVeces(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Palabra: " + palabra + " - ID: " + this.getId() + " - Prioridad: " + this.getPriority() + " - Nombre: " + this.getName());
        }
    }

}
