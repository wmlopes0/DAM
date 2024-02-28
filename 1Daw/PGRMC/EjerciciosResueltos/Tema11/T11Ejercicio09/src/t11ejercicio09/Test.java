/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t11ejercicio09;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
                + "1. Realizar sorteo hoy. \n"
                + "2. Repetir sorteo hoy. \n"
                + "3. Mostrar sorteos del mes actual.\n"
                + "4. Mostrar sorteo dada una fecha. \n"
                + "5. Realizar sorteo por fecha. \n"
                + "6. Mostrar todos los sorteos. \n"
                + "7. Salir.");
        System.out.println("----------------------------------------------");
        System.out.println("Introduce una opcion: ");
        return entrada.nextInt();
    }

    //OPCIÓN 1=======================================================================
    public static void realizarSorteo(Map<LocalDate, Sorteo> sorteos) {
        //Declaro variables
        LocalDate hoy;
        Sorteo sorteo;

        //Asigno a hoy la fecha actual y creo un Sorteo
        hoy = LocalDate.now();
        sorteo = new Sorteo(hoy);

        //Añado el sorteo al Set
        sorteos.put(hoy, sorteo);
    }

    //OPCIÓN 2=======================================================================
    public static void repetirSorteo(Map<LocalDate, Sorteo> sorteos) {
        LocalDate hoy = LocalDate.now();
        Sorteo sorteo;
        if (sorteos.containsKey(hoy)) {
            sorteo = new Sorteo(hoy);
            sorteos.replace(hoy, sorteo);
        } else {
            System.out.println("¡NO HAY NINGUN SORTEO CON FECHA DE HOY!");
        }
    }

    //OPCIÓN 3=======================================================================
    public static void mostrarSorteosMesActual(Map<LocalDate, Sorteo> sorteos) {
        LocalDate hoy = LocalDate.now();
        int mesActual = hoy.getMonthValue();
        LocalDate fecha;
        int mes;
        Sorteo sorteo;

        for (LocalDate clave : sorteos.keySet()) {
            sorteo = sorteos.get(clave);
            fecha = sorteo.getFecha();
            mes = fecha.getMonthValue();
            if (mes == mesActual) {
                sorteo.mostrar();
            }
        }

    }

    //OPCIÓN 4=======================================================================
    public static void mostrarSorteos(LocalDate fecha, Map<LocalDate, Sorteo> sorteos) {
        Sorteo sorteo;
        LocalDate fechaAux;

        for (LocalDate clave : sorteos.keySet()) {
            sorteo = sorteos.get(clave);
            fechaAux = sorteo.getFecha();
            if (fechaAux.isEqual(fecha)) {
                sorteo.mostrar();
            }
        }
    }

    //OPCIÓN 5=======================================================================
    public static void realizarSorteoFecha(Map<LocalDate, Sorteo> sorteos) {
        LocalDate fecha = pedirFecha();
        Sorteo sorteo = new Sorteo(fecha);
        sorteos.put(fecha, sorteo);
    }

    //OPCIÓN 6=======================================================================
    public static void mostrarSorteos(Map<LocalDate, Sorteo> sorteos) {
        Sorteo sorteo;

        for (LocalDate clave : sorteos.keySet()) {
            sorteo = sorteos.get(clave);
            sorteo.mostrar();
        }
    }

    //METODOS PEDIR--------------------------------------------------------------------
    public static LocalDate pedirFecha() {
        LocalDate fecha;
        int ano, mes, dia;

        ano = pedirNumero("un año");
        mes = pedirNumero("un mes");
        dia = pedirNumero("un dia");

        fecha = LocalDate.of(ano, mes, dia);

        return fecha;
    }

    public static int pedirNumero(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduce " + texto + ": ");
        return entrada.nextInt();
    }

    public static void main(String[] args) {
        //Creo Set de sorteos
        Map<LocalDate, Sorteo> sorteos = new TreeMap<>();
  
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("=======REALIZAR SORTEO=======");
                    realizarSorteo(sorteos);
                    System.out.println("¡SORTEO REALIZADO CON EXITO!");
                    break;
                case 2:
                    System.out.println("=======REPETIR SORTEO=======");
                    repetirSorteo(sorteos);
                    System.out.println("¡SORTEO REPETIDO CON EXITO!");
                    break;
                case 3:
                    System.out.println("=======MOSTRAR SORTEOS DEL MES ACTUAL=======");
                    mostrarSorteosMesActual(sorteos);
                    break;
                case 4:
                    System.out.println("=======MOSTRAR SORTEO DADA UNA FECHA=======");
                    mostrarSorteos(pedirFecha(), sorteos);
                    break;
                case 5:
                    System.out.println("=======REALIZAR SORTEO POR FECHA=======");
                    realizarSorteoFecha(sorteos);
                    break;
                case 6:
                    System.out.println("=======MOSTRAR TODOS LOS SORTEOS=======");
                    mostrarSorteos(sorteos);
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
