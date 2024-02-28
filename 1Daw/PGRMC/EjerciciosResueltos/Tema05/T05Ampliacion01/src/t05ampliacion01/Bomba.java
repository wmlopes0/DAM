/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ampliacion01;

/**
 *
 * @author Walter
 */
public class Bomba extends Arma {

    private int alcance;
    private boolean accionarDistancia;

    public Bomba() {
        super("Bomba");
        alcance = 0;
        accionarDistancia = false;
    }

    public Bomba(int alcance, boolean accionarDistancia) {
        super("Bomba");
        this.alcance = alcance;
        this.accionarDistancia = accionarDistancia;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public void setAccionarDistancia(boolean accionarDistancia) {
        this.accionarDistancia = accionarDistancia;
    }

    public int getAlcance() {
        return alcance;
    }

    public boolean isAccionarDistancia() {
        return accionarDistancia;
    }

    @Override
    public String toString() {
        String disponible,accionadoDistancia;
        if (super.isDisponible()) {
            disponible="SI";
        } else{
            disponible="NO";
        }
        if (accionarDistancia) {
            accionadoDistancia = "SI";
        } else {
            accionadoDistancia = "NO";
        }
        return "-----BOMBA-----\n" + "Disponible: " +disponible+".\n"+"Alcance: " +alcance +".\n"+ "Accionado a distancia: " + accionadoDistancia + ".\n"+"---------------";
    }

}
