/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio06t15;

import java.io.Serializable;

/**
 *
 * @author Quique Pineda
 */
public class Rueda implements Serializable {
    private int pulgadas;
    private String material;

    public Rueda() {
        this.pulgadas = 0;
        this.material = "";
    }
    
    public Rueda(int pulgadas, String material) {
        this.pulgadas = pulgadas;
        this.material = material;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void mostrarRueda() {
        System.out.println("    Rueda==> " + "pulgadas: " + pulgadas + ", material: " + material);
    }
}
