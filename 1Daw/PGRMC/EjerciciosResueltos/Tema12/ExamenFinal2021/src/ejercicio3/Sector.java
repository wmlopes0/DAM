package ejercicio3;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Sector {

    private int plantasCultivadas;
    private int cantidadKg;

    //CONSTRUCTORES
    public Sector() {
        this.plantasCultivadas = 0;
        this.cantidadKg = 0;
    }

    public Sector(int plantasCultivadas, int cantidadKg) {
        this.plantasCultivadas = plantasCultivadas;
        this.cantidadKg = cantidadKg;
    }

    //SETTER Y GETTER
    public void setPlantasCultivadas(int plantasCultivadas) {
        this.plantasCultivadas = plantasCultivadas;
    }

    public void setCantidadKg(int cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public int getPlantasCultivadas() {
        return plantasCultivadas;
    }

    public int getCantidadKg() {
        return cantidadKg;
    }

    //MÉTODOS PROPIOS
    public void rellenarInfoSector() {
        this.plantasCultivadas = pedirEntero("plantas cultivadas");
        this.cantidadKg = pedirEntero("kg");
    }

    private int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el numero de " + texto + ": ");
        return entrada.nextInt();
    }

    public void mostrar() {
        System.out.println("===SECTOR===");
        System.out.println("Numero de plantas cultivadas: " + plantasCultivadas);
        System.out.println("Numero Kg: " + cantidadKg);
    }
}
