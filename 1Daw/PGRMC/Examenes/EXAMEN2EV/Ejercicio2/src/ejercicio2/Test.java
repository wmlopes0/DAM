package ejercicio2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author java2
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==================MENU==================");
        System.out.println("1.- Rellenar averias."
                + "\n2.- Mostrar todas las averias."
                + "\n3.- Retornar el importe total de todas las averias que estan pendientes por cobrar."
                + "\n4.- Añadir averia urgente."
                + "\n5.- Salir del programa.");
        System.out.println("---------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        return entrada.nextInt();
    }

    public static boolean seguirRellenando() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.println("¿Desea seguir introduciendo averias?");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static void rellenarAverias(ArrayList<Averia> averias) {
        do {
            Averia averia = new Averia();
            averia.rellenarAveria();
            averias.add(averia);
        } while (seguirRellenando());
    }

    public static void mostrarAverias(ArrayList<Averia> averias) {
        Iterator<Averia> it = averias.iterator();
        while (it.hasNext()) {
            Averia averia = it.next();
            averia.mostrarAveria();
        }

    }

    public static float retornarImporteAverias(ArrayList<Averia> averias) {
        float importe = 0;
        //Recorro el ArrayList y compruebo si cada averia esta cobrada o no, si no lo esta sumo el importe a "importe"
        for (Averia averia : averias) {
            if (averia.isCobrada() == false) {
                importe += averia.getImporte();
            }
        }
        return importe;
    }

    public static void averiaUrgente(ArrayList<Averia> averias) {
        //Creo averia nueva
        Averia averia = new Averia();
        //Relleno averia
        averia.rellenarAveria();
        //Añado la averia al indice 0, de esta manera el resto de averias se colocan solas
        averias.add(0, averia);
    }

    public static void main(String[] args) {
        //Declaro ArrayList de averias
        ArrayList<Averia> averias = new ArrayList<>();

        int opcion;

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========RELLENA AVERIAS==========");
                    rellenarAverias(averias);
                    break;
                case 2:
                    System.out.println("==========MOSTRANDO AVERIAS==========");
                    mostrarAverias(averias);
                    break;
                case 3:
                    System.out.println("==========MOSTRANDO IMPORTE==========");
                    System.out.println("El importe total de las averias que no estan cobradas es: " + retornarImporteAverias(averias));
                    break;
                case 4:
                    System.out.println("==========AÑADIR AVERIA URGENTE==========");
                    averiaUrgente(averias);
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR.Por favor, introduzca una opcion valida.");
                    break;
            }
        } while (opcion != 5);

    }
}
