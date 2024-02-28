/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ampliacion01;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static boolean crearArma() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("¿Desea crear una nueva arma?");
        String eleccion = entrada.nextLine();

        return eleccion.equalsIgnoreCase("si");
    }

    public static int menuCrearArma() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Pulse 1 para crear una bomba.");
        System.out.println("Pulse 2 para crear un bote de cloroformo.");
        System.out.println("Pulse 3 para crear una pistola.");

        return entrada.nextInt();
    }

    //Pedir atributos bomba
    public static int pedirAlcance() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Alcance?");
        return entrada.nextInt();
    }

    public static boolean pedirAccionado() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Accionado a distancia?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    //Pedir atributos cloroformo
    public static int pedirTiempoEfectivo() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Tiempo efectivo?");
        return entrada.nextInt();
    }
    //Pedir atributos pistola
    public static int pedirBalas() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Numero de balas?");
        return entrada.nextInt();
    }
    
    public static void main(String[] args) {
        System.out.println("=====COMANDOS: BEHIND MONUMENTS MEN=====");
        while (crearArma()) {
            switch (menuCrearArma()) {
                case 1:
                    Bomba bomba = new Bomba(pedirAlcance(), pedirAccionado());
                    System.out.println(bomba.toString());
                    break;
                case 2:
                    Cloroformo cloroformo = new Cloroformo(pedirTiempoEfectivo());
                    System.out.println(cloroformo.toString());
                    break;
                case 3:
                    Pistola pistola = new Pistola(pedirBalas());
                    System.out.println(pistola.toString());
                    break;
                default:
                    System.out.println("ERROR: Por favor introduzca una opcion valida.");
            }
        }
        System.out.println("Armas creadas: " + Arma.getNumeroArmas() + ".");
    }
}
