package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Plantacion {

    private String cultivo;
    private List<Sector> sectores;

    //CONSTRUCTORES
    public Plantacion() {
        this.cultivo = "";
        this.sectores = new ArrayList<>();
    }

    public Plantacion(String cultivo) {
        this.cultivo = cultivo;
        this.sectores = new ArrayList<>();
    }

    //SETTER Y GETTER
    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    public String getCultivo() {
        return cultivo;
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    //MÉTODOS PROPIOS
    public void rellenarInfoPlantacion() {
        Sector sector;
        System.out.println("====================");
        System.out.println("=====PLANTACION=====");
        System.out.println("====================");
        this.cultivo = pedirCultivo();
        do {
            sector = new Sector();
            System.out.println("----------------------");
            System.out.println("Rellenando sector... ");
            System.out.println("----------------------");
            sector.rellenarInfoSector();
            sectores.add(sector);
        } while (seguir());
    }

    private String pedirCultivo() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el nombre del cultivo: ");
        return entrada.nextLine();
    }

    private boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public void mostrar(){
        System.out.println("====================");
        System.out.println("=====PLANTACION=====");
        System.out.println("====================");
        System.out.println("Cultivo: "+this.cultivo);
        for (Sector sector : sectores) {
            sector.mostrar();
        }
    }
    public int totalKg() {
        int totalKg = 0;
        for (Sector sector : sectores) {
            totalKg += sector.getCantidadKg();
        }
        return totalKg;
    }
}
