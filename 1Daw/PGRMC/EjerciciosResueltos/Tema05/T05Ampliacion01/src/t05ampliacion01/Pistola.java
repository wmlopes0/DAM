/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ampliacion01;

/**
 *
 * @author Walter
 */
public class Pistola extends Arma {

    private int balas;

    public Pistola() {
        super("Pistola");
        balas = 0;
    }

    public Pistola(int balas) {
        super("Pistola");
        this.balas = balas;
    }

    public void setBalas(int balas) {
        this.balas = balas;
    }

    public int getBalas() {
        return balas;
    }

    @Override
    public String toString() {
        String disponible;
        if (super.isDisponible()) {
            disponible="SI";
        } else{
            disponible="NO";
        }
        return "-----PISTOLA-----\n" + "Disponible: " +disponible+".\n"+"Numero de balas: " +balas+".\n"+"---------------";
    }

}
