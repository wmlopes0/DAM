package ejercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author final23
 */
public class Test {

    public static final int LONGITUD = 4;

    //GENERAL
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("*************************************");
        System.out.println("****** V A C U N A C I O N E S ******");
        System.out.println("*************************************");
        System.out.println("1.- Rellenar una residencia.");
        System.out.println("2.- Mostrar residencia.");
        System.out.println("3.- Buscar por localidad.");
        System.out.println("4.- Salir.");
        System.out.println("-------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    private static int pedirCeldaVacia(Residencia[] residencias) {
        Scanner entrada = new Scanner(System.in);
        int posicion;
        boolean valida;

        do {
            posicion = -1;
            valida = true;
            System.out.println("Introduce una celda vacia: ");
            //CONTROLO QUE INTRODUCE UN NUMERO
            try {
                posicion = entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Debes introducir un numero.");
                valida = false;
                entrada.next();
            }
            //CONTROLO QUE LA CELDA SE ENCUENTRE EN RANGO 
            if (valida && posicion < 0 || posicion >= LONGITUD) {
                System.out.println("ERROR: La posicion esta fuera de rango.");
                valida = false;
            }
            //CONTROLO QUE LA CELDA ESTE VACIA 
            if (valida && residencias[posicion] != null) {
                System.out.println("ERROR: La posicion está ocupada.");
                valida = false;
            }
        } while (!valida);

        return posicion;
    }

    public static boolean vectorLleno(Residencia[] residencias) {
        boolean lleno = true;
        int contador = 0;

        while (contador < residencias.length && lleno) {
            if (residencias[contador] == null) {
                lleno = false;
            } else {
                contador++;
            }
        }
        return lleno;
    }

    public static boolean algunaResidencia(Residencia[] residencias) {
        boolean encontrado = false;
        int contador = 0;

        while (contador < residencias.length && !encontrado) {
            if (residencias[contador] != null) {
                encontrado = true;
            } else {
                contador++;
            }
        }
        return encontrado;
    }

    public static String pedirCadena(String mensaje) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        return entrada.nextLine();
    }

    //OPCION 1
    public static void rellenarResidencia(Residencia[] residencias) {
        int posicion;
        Residencia residencia;

        //Pido posicion vacia
        posicion = pedirCeldaVacia(residencias);
        //Creo objeto residencia y lo relleno
        residencia = new Residencia();
        residencia.rellenarInfo();
        //Añado la residencia a la posicion 
        residencias[posicion] = residencia;
    }

    //OPCION 2
    public static void mostrarResidencias(Residencia[] residencias) {
        for (Residencia residencia : residencias) {
            if (residencia != null) {
                residencia.mostrarInfoResumida();
            }
        }
    }

    //OPCION 3
    //Este metodo me devuelve el objeto residencia si es encontrada la localidad, en caso contrario me devuelve null
    public static Residencia buscarPorLocalidad(Residencia[] residencias, String localidad) {
        Residencia residencia = null;
        boolean encontrado = false;
        int contador = 0;

        while (contador < residencias.length && !encontrado) {
            if (residencias[contador] != null) {
                if (residencias[contador].getLocalidad().equalsIgnoreCase(localidad)) {
                    encontrado = true;
                    residencia = residencias[contador];
                }
            }
            contador++;
        }
        return residencia;
    }

    public static void main(String[] args) {
        int opcion;
        String localidad;
        Residencia residencia;

        //CREO VECTOR DE OBJETOS
        Residencia[] residencias = new Residencia[LONGITUD];

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******* RELLENAR RESIDENCIA *******");
                    if (!vectorLleno(residencias)) {
                        rellenarResidencia(residencias);
                    } else {
                        System.out.println("LO SIENTO! NO PUEDES INTRODUCIR MAS RESIDENCIAS, EL VECTOR ESTA LLENO.");
                    }
                    break;
                case 2:
                    System.out.println("******* MOSTRAR RESIDENCIAS *******");
                    if (algunaResidencia(residencias)) {
                        mostrarResidencias(residencias);
                    } else {
                        System.out.println("ERROR: Introduce antes alguna residencia.");
                    }
                    break;
                case 3:
                    System.out.println("******* BUSCAR POR LOCALIDAD *******");
                    if (algunaResidencia(residencias)) {
                        localidad = pedirCadena("Introduce el nombre de la localidad: ");
                        residencia = buscarPorLocalidad(residencias, localidad);

                        if (residencia != null) {
                            residencia.mostrarInfoCompleta();
                        } else {
                            System.out.println("ERROR: La localidad no esta registrada.");
                        }
                    } else {
                        System.out.println("ERROR: Introduce antes alguna residencia.");
                    }
                    break;
                case 4:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR: Introduzca una opcion valida.");
            }
        } while (opcion != 4);
    }
}
