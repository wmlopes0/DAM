/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ampliacion01;

/**
 *
 * @author Walter
 */
public class Cloroformo extends Arma {
    private int tiempoEfectivo;

    public Cloroformo() {
        super("Cloroformo");
        tiempoEfectivo = 0;
    }

    public Cloroformo(int tiempoEfectivo) {
        super("Cloroformo");
        this.tiempoEfectivo = tiempoEfectivo;
    }

    public void setTiempoEfectivo(int tiempoEfectivo) {
        this.tiempoEfectivo = tiempoEfectivo;
    }

    public int getTiempoEfectivo() {
        return tiempoEfectivo;
    }

    @Override
    public String toString() {
        String disponible;
        if (super.isDisponible()) {
            disponible="SI";
        } else{
            disponible="NO";
        }
        return "-----CLOROFORMO-----\n" + "Disponible: " +disponible+".\n"+"Tiempo efectivo: " +tiempoEfectivo+".\n"+"---------------";
    }
    
}
