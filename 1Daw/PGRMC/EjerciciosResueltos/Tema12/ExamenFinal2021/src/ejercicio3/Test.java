package ejercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    private static Plantacion[] plantaciones = new Plantacion[5];

    //GENERAL
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("=====================================================");
        System.out.println("===============AGRICULTORES WALTER S.L===============");
        System.out.println("=====================================================");
        System.out.println("1.- Añadir plantacion.");
        System.out.println("2.- Mostrar plantaciones que superen los 1000 kg.");
        System.out.println("3.- Mostrar total kg por cultivo.");
        System.out.println("4.- Mostrar posicion del ultimo cultivo.");
        System.out.println("5.- Mostrar la plantacion segun posicion.");
        System.out.println("6.- Salir del programa.");
        System.out.println("-----------------------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    public static int pedirPosicionVacia() {
        int posicion;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.println("Introduce una posicion: ");
            try {
                posicion = entrada.nextInt();
            } catch (InputMismatchException e) {
                posicion = -1;
            }
        } while (!comprobarPosicionVacia(posicion));
        return posicion;
    }

    public static int pedirPosicionLlena() {
        int posicion;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.println("Introduce una posicion: ");
            try {
                posicion = entrada.nextInt();
            } catch (InputMismatchException e) {
                posicion = -1;
            }
        } while (!comprobarPosicionLlena(posicion));
        return posicion;
    }

    private static boolean comprobarPosicionVacia(int posicion) {
        boolean valida = true;
        if (posicion < 0 || posicion >= plantaciones.length) {
            System.out.println("ERROR. LA POSICION INDICADA ESTA FUERA DE RANGO.");
            valida = false;
        }
        if (valida == true && plantaciones[posicion] != null) {
            System.out.println("ERROR. LA POSICION INDICADA ESTA LLENA.");
            valida = false;
        }
        return valida;
    }

    private static boolean comprobarPosicionLlena(int posicion) {
        boolean valida = true;
        if (posicion < 0 || posicion >= plantaciones.length) {
            System.out.println("ERROR. LA POSICION INDICADA ESTA FUERA DE RANGO.");
            valida = false;
        }
        if (valida == true && plantaciones[posicion] == null) {
            System.out.println("ERROR. LA POSICION INDICADA ESTA VACIA.");
            valida = false;
        }
        return valida;
    }

    public static boolean todasOcupadas() {
        boolean ocupadas = true;
        int i = 0;
        while (i < plantaciones.length && ocupadas == true) {
            if (plantaciones[i] == null) {
                ocupadas = false;
            } else {
                i++;
            }
        }
        return ocupadas;
    }

    private static boolean existeCultivo(String cultivo) {
        boolean existe = false;
        int i = 0;
        while (i < plantaciones.length && existe == false) {
            if (plantaciones[i] != null) {
                if (plantaciones[i].getCultivo().equalsIgnoreCase(cultivo)) {
                    existe = true;
                }
            }
            i++;
        }

        if (!existe) {
            System.out.println("ERROR. EL CULTIVO NO EXISTE.");
        }

        return existe;
    }

    public static String pedirCultivo() {
        String cultivo;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.println("Introduce el nombre del cultivo: ");
            cultivo = entrada.nextLine();
        } while (!existeCultivo(cultivo));

        return cultivo;
    }

    //OPCION 1
    public static void anadirPlantacion() {
        int posicion = pedirPosicionVacia();
        Plantacion plantacion = new Plantacion();
        plantacion.rellenarInfoPlantacion();
        plantaciones[posicion] = plantacion;
    }

    //OPCION 2
    public static void mostrarPlantaciones1000kg() {
        for (Plantacion plantacion : plantaciones) {
            if (plantacion != null) {
                if (plantacion.totalKg() >= 1000) {
                    plantacion.mostrar();
                }
            }
        }
    }

    //OPCION 3
    public static void mostrarTotalKgCultivo() {
        String cultivo = pedirCultivo();
        int totalKG = 0;
        for (Plantacion plantacion : plantaciones) {
            if (plantacion != null) {
                if (plantacion.getCultivo().equalsIgnoreCase(cultivo)) {
                    totalKG += plantacion.totalKg();
                }
            }
        }
        System.out.println("EL TOTAL DE KG ES: " + totalKG);
    }

    //OPCION 4
    public static void mostrarPosicionUltimoCultivo() {
        String cultivo = pedirCultivo();
        int posicion = 0;
        for (int i = 0; i < plantaciones.length; i++) {
            if (plantaciones[i] != null) {
                if (plantaciones[i].getCultivo().equalsIgnoreCase(cultivo)) {
                    posicion = i;
                }
            }
        }
        System.out.println("LA ULTIMA POSICION DE ESTE CULTIVO ES " + posicion);
    }

    //OPCION 5
    public static void mostrarPlantacionPorPosicion() {
        int posicion = pedirPosicionLlena();
        boolean mostrada = false;
        int i = 0;
        while (i < plantaciones.length && mostrada == false) {
            if (plantaciones[i] != null) {
                if (i == posicion) {
                    plantaciones[i].mostrar();
                    mostrada = true;
                }
            }
            i++;
        }
    }

    public static void main(String[] args) {
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******AÑADIR UNA PLANTACION******");
                    if (todasOcupadas()) {
                        System.out.println("NO PUEDES AÑADIR MAS PLANTACIONES.");
                    } else {
                        anadirPlantacion();
                    }
                    break;
                case 2:
                    System.out.println("******MOSTRAR PLANTACIONES QUE SUPEREN 1000KG******");
                    mostrarPlantaciones1000kg();
                    break;
                case 3:
                    System.out.println("******MOSTRAR TOTAL KG POR CULTIVO******");
                    mostrarTotalKgCultivo();
                    break;
                case 4:
                    System.out.println("******MOSTRAR POSICION DEL ULTIMO CULTIVO******");
                    mostrarPosicionUltimoCultivo();
                    break;
                case 5:
                    System.out.println("******MOSTRAR PLANTACION SEGUN POSICION******");
                    mostrarPlantacionPorPosicion();
                    break;
                case 6:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. INTRODUZCA UNA OPCIÓN VALIDA.");
            }

        } while (opcion != 6);
    }
}
