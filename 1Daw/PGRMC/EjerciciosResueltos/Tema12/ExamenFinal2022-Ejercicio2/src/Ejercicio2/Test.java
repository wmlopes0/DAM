package Ejercicio2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    private static final File FICHERO = new File("pacientes.txt");
    private static final char SEPARADOR = '#';
    private static Ambulancia[] ambulancias = new Ambulancia[4];

    //GENERAL
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("*****************************************************");
        System.out.println("****** L O S   A N G E L E S   D A   S A U D E ******");
        System.out.println("*****************************************************");
        System.out.println("1.- Llegada de ambulancia.");
        System.out.println("2.- Registrar llamada.");
        System.out.println("3.- Buscar paciente.");
        System.out.println("4.- Eliminar ambulancia.");
        System.out.println("5.- Salir del programa.");
        System.out.println("-----------------------------------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    private static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        return entrada.nextLine();
    }

    private static int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        int entero = 0;
        boolean valido;

        do {
            valido = true;
            System.out.println("Introduce " + texto + ": ");
            try {
                entero = entrada.nextInt();
            } catch (InputMismatchException e) {
                valido = false;
                entrada.next();
            }
        } while (!valido);

        return entero;
    }

    /*Este metodo me retorna un -1 si no hay ningun hueco libre
    y la posicion en cuestion del primer hueco libre si existe*/
    public static int primerHuecoLibre() {
        boolean encontrado = false;
        int posicion = -1;
        int contador = 0;
        while (contador < ambulancias.length && !encontrado) {
            if (ambulancias[contador] == null) {
                encontrado = true;
                posicion = contador;
            } else {
                contador++;
            }
        }
        return posicion;
    }

    public static boolean algunaAmbulancia() {
        boolean algunaAmbulancia = false;
        int contador = 0;
        while (contador < ambulancias.length && !algunaAmbulancia) {
            if (ambulancias[contador] != null) {
                algunaAmbulancia = true;
            } else {
                contador++;
            }
        }
        return algunaAmbulancia;
    }

    public static int pedirPosicionLlena() {
        int posicion;
        boolean valido;
        do {
            valido = true;
            posicion = pedirEntero("una posicion") - 1;
            try {
                if (ambulancias[posicion] == null) {
                    System.out.println("ESA POSICION ESTA VACIA, POR FAVOR INTRODUZCA UNA POSICION OCUPADA.");
                    valido = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ERROR.INTRODUZA UNA POSICION VALIDA, RECUERDA QUE HAY 4.");
                valido = false;
            }
        } while (!valido);

        return posicion;
    }

    //OPCION 1
    public static void llegadaAmbulancia() {
        Ambulancia ambulancia;
        int posicion = primerHuecoLibre();
        if (posicion != -1) {
            ambulancia = new Ambulancia();
            ambulancia.rellenarInfo();
            ambulancias[posicion] = ambulancia;
        } else {
            System.out.println("LO SIENTO,¡NO HAY NINGUN HUECO LIBRE!");
        }
    }

    //OPCION 2
    public static void registrarLlamada() {
        String nombrePaciente;
        int posicion;
        if (algunaAmbulancia()) {
            nombrePaciente = pedirCadena("el nombre del paciente");
            posicion = pedirPosicionLlena();
            ambulancias[posicion].insertarLlamada(nombrePaciente);
        } else {
            System.out.println("ERROR. NO HAY NINGUNA AMBULANCIA REGISTRADA, AÑADA UNA AMBULANCIA ANTES.");
        }
    }

    //OPCION 3
    public static void buscarPaciente() {
        String clave;
        boolean encontrado = false;
        if (algunaAmbulancia()) {
            clave = pedirCadena("la clave del paciente");
            System.out.println("AMBULANCIAS: ");
            for (Ambulancia ambulancia : ambulancias) {
                if (ambulancia != null) {
                    if (ambulancia.getLlamadas().containsKey(clave)) {
                        System.out.println(ambulancia.getMatricula());
                        encontrado = true;
                    }
                }
            }
            if (!encontrado) {
                System.out.println("LO SIENTO, NO SE HA ENCONTRADO NINGUN PACIENTE CON LA CLAVE " + clave);
            }
        } else {
            System.out.println("ERROR. NO HAY NINGUNA AMBULANCIA REGISTRADA, AÑADA UNA AMBULANCIA ANTES.");
        }

    }

    //OPCION 4
    public static void eliminarAmbulancia() {
        String matricula;
        boolean encontrado = false;
        int contador = 0;

        if (algunaAmbulancia()) {
            matricula = pedirCadena("una matricula");
            while (contador < ambulancias.length && !encontrado) {
                if (ambulancias[contador] != null) {
                    if (ambulancias[contador].getMatricula().equalsIgnoreCase(matricula)) {
                        encontrado = true;
                        ambulancias[contador] = null;
                    }
                }
                contador++;
            }
            if (!encontrado) {
                System.out.println("NO EXISTE NINGNA AMBULANCIA CON LA MATRICULA [" + matricula + "]");
            }
        } else {
            System.out.println("ERROR. NO HAY NINGUNA AMBULANCIA REGISTRADA, AÑADA UNA AMBULANCIA ANTES.");
        }
    }

    //OPCION 5
    public static void escribirFichero() throws IOException {
        Map<String, Llamada> llamadas;
        Llamada llamadaAux;
        String linea;
        //ABRO FLUJOS
        FileWriter fw = new FileWriter(FICHERO);
        PrintWriter pw = new PrintWriter(fw);

        //ESCRIBO
        for (Ambulancia ambulancia : ambulancias) {
            if (ambulancia != null) {
                llamadas = ambulancia.getLlamadas();
                for (String clave : llamadas.keySet()) {
                    llamadaAux = llamadas.get(clave);
                    linea = llamadaAux.getFechaLlamada().toString() + SEPARADOR + llamadaAux.getNombrePaciente() + SEPARADOR + clave;
                    pw.println(linea);
                }
            }
        }

        //CIERRO FLUJOS
        pw.close();
        fw.close();
    }

    public static void main(String[] args) {
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******LLEGADA AMBULANCIA******");
                    llegadaAmbulancia();
                    break;
                case 2:
                    System.out.println("******REGISTRAR LLAMADA******");
                    registrarLlamada();
                    break;
                case 3:
                    System.out.println("******BUSCAR PACIENTE******");
                    buscarPaciente();
                    break;
                case 4:
                    System.out.println("******ELIMINAR AMBULANCIA******");
                    eliminarAmbulancia();
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    try {
                        escribirFichero();
                    } catch (IOException ex) {
                        System.out.println("ERROR DE ENTRADA/SALIDA.");
                    }
                    break;
                default:
                    System.out.println("ERROR. INTRODUZCA UNA OPCIÓN VALIDA.");
            }

        } while (opcion != 5);
    }
}
