package t11ejercicio06;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n"
                + "*************************************************\n"
                + "******************** M E N U ********************\n"
                + "*************************************************\n"
                + "1. Añadir donación. \n"
                + "2. Mostrar donaciones. \n"
                + "3. Mostrar donaciones por nombre de donante.\n"
                + "4. Mostrar número de donaciones. \n"
                + "5. Mostrar total dinero recaudado. \n"
                + "6. Ordenar donaciones (de mayor a menor importe de las donaciones) \n"
                + "7. Salir.");
        System.out.println("----------------------------------------------");
        System.out.println("Introduce una opcion: ");
        return entrada.nextInt();
    }

    //OPCIÓN 1=======================================================================
    /*Al implementar la interfaz comparable en la clase donacion, cuando añadimos 
    una donacion a la campaña se ordena automaticamente.*/
    public static void anadirDonacion(Campania campania) {
        String nombre;
        float cantidad;
        do {
            System.out.println("-----DONACION-----");
            nombre = pedirNombre();
            cantidad = pedirCantidad();
            campania.introducirDonacion(nombre, cantidad);
            System.out.println("------------------");
        } while (seguir());
    }

    //OPCIÓN 2=======================================================================
    public static void mostrarDonaciones(Campania campania) {
        campania.mostrarDonaciones();
    }

    //OPCIÓN 3=======================================================================
    //Metodo que muestra solo las donaciones cuyo nombre coincide
    public static void mostrarDonaciones(Campania campania, String nombre) {
        campania.mostrarDonaciones(nombre);
    }

    //METODOS PEDIR---------------------------------------------
    public static boolean seguir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Desea seguir añadiendo donaciones? ");
        return entrada.nextLine().equalsIgnoreCase("si");
    }

    public static String pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el nombre del donante: ");
        return entrada.nextLine();
    }

    public static float pedirCantidad() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce la cantidad donada: ");
        return entrada.nextFloat();
    }
    //-----------------------------------------------------------

    public static void main(String[] args) {
        //Patron singleton
        Campania campania = Campania.getInstance();
        campania.setNombre("Campaña #AprobemosPGRMC");

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("=======AÑADIR DONACION=======");
                    System.out.println("#CAMPAÑA -" + campania.getNombre() + "-");
                    anadirDonacion(campania);
                    break;
                case 2:
                    System.out.println("=======MOSTRAR DONACIONES=======");
                    mostrarDonaciones(campania);
                    break;
                case 3:
                    System.out.println("=======MOSTRAR DONACIONES POR NOMBRE=======");
                    mostrarDonaciones(campania, pedirNombre());
                    break;
                case 4:
                    System.out.println("=======MOSTRAR NUMERO DONACIONES=======");
                    System.out.println("El numero total de donaciones es: " + campania.numeroDonaciones());
                    break;
                case 5:
                    System.out.println("=======MOSTRAR TOTAL DINERO RECAUDADO=======");
                    System.out.println("El total de dinero recaudado en la campaña -" + campania.getNombre() + "- asciende a " + campania.totalRecaudacion() + " euros.");
                    break;
                case 6:
                    System.out.println("=======ORDENAR DONACIONES=======");
                    System.out.println("Las donaciones ya están ordenadas de mayor a menor importe.");
                    break;
                case 7:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. Introduzca una opcion valida.");
            }
        } while (opcion != 7);

    }
}
