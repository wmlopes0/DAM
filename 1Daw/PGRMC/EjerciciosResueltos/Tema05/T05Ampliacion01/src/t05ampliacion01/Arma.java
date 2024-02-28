/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ampliacion01;

/**
 *
 * @author Walter
 */
public class Arma {

    private String tipo;
    private boolean disponible;
    private static int numeroArmas;

    public Arma(String tipo) {
        this.tipo = tipo;
        disponible = true;
        numeroArmas++;
    }

    public Arma(String tipo, boolean disponible) {
        this.tipo = tipo;
        this.disponible = disponible;
        numeroArmas++;
    }

    public void setNombre(String tipo) {
        this.tipo = tipo;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public static int getNumeroArmas() {
        return numeroArmas;
    }

    public void usarArma() {
        if (disponible) {
            this.quitarSeguro();
            System.out.println("Utilizando arma.");
        } else {
            System.out.println("El arma NO esta disponible.");
        }
    }

    public void quitarSeguro() {
        System.out.println("Quitando el seguro de " + tipo + ".");
    }

}
