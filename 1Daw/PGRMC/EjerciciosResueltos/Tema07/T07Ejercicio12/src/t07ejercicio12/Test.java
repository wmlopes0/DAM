package t07ejercicio12;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n====================MENU====================");
        System.out.println("1. Rellenar muebles.\n"
                + "2. Mostrar muebles.\n"
                + "3. Mostrar muebles por precio.\n"
                + "4. Salir del programa.");
        System.out.println("============================================");
        System.out.print("Por favor, introduzca una opcion: ");
        System.out.println("");//Salto de linea
        return entrada.nextInt();
    }

    public static void rellenarMuebles(Mueble[] catalogo) {
        for (int i = 0; i < catalogo.length; i++) {
            System.out.println("\n------MUEBLE " + (i+1) + "------");
            catalogo[i] = new Mueble();
            catalogo[i].setPrecio(pedirPrecio());
            System.out.println("");//Salto de linea
            catalogo[i].setDescripcion(pedirDescripcion());
        }
    }

    public static float pedirPrecio() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca un precio: ");
        return entrada.nextFloat();
    }

    public static String pedirDescripcion() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca una descripcion: ");
        return entrada.nextLine();
    }

    public static void mostrarMuebles(Mueble[] catalogo) {
        for (int i = 0; i < catalogo.length; i++) {
            System.out.println(catalogo[i].toString());
        }
    }

    public static void buscadorMuebles(Mueble[] catalogo) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Por favor, introduzca un precio: ");
        float precio = entrada.nextFloat();
        System.out.println("");//Salto de linea
        for (int i = 0; i < catalogo.length; i++) {
            if (catalogo[i].getPrecio() <= precio) {
                System.out.println(catalogo[i].toString());
            }
        }
    }

    public static void main(String[] args) {
        //Creamos el array muebles
        Mueble[] catalogo = new Mueble[4];

        //Variables
        int opcion;

        //MENU
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("==========RELLENA EL CATALOGO==========");
                    rellenarMuebles(catalogo);
                    break;
                case 2:
                    System.out.println("==========MOSTRANDO EL CATALOGO==========");
                    mostrarMuebles(catalogo);
                    break;
                case 3:
                    System.out.println("============BUSCADOR DE MUEBLES POR PRECIO============");
                    buscadorMuebles(catalogo);
                    break;
                case 4:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Por favor, introduzca una opcion valida.");
            }
        } while (opcion != 4);
    }
}
