package examenfinal2022.ejercicio3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class ExamenFinal2022Ejercicio3 {

    private static final String[] MESES = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
    private static List<Integer> ventas = new ArrayList<>();

    //GENERAL
    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("*****************************************************");
        System.out.println("********** V E N T A S   D E   C O C H E S **********");
        System.out.println("*****************************************************");
        System.out.println("1.- Rellenar ventas aleatorias 1-100.");
        System.out.println("2.- Mostrar ventas.");
        System.out.println("3.- Ventas totales de los meses cuyo nombre contenga la letra 'a'.");
        System.out.println("4.- Mostrar mes con mas ventas.");
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

    //OPCION1
    public static void rellenarVentas() {
        int aleatorio;
        for (int i = 0; i < MESES.length; i++) {
            aleatorio = (int) Math.round(Math.random() * 100);
            ventas.add(aleatorio);
        }
    }

    //OPCION2
    public static void mostrarVentas() {
        for (int i = 0; i < MESES.length; i++) {
            System.out.println(MESES[i] + ": " + ventas.get(i));
        }
    }

    //OPCION3
    public static void mostrarVentasMesesA() {
        for (int i = 0; i < MESES.length; i++) {
            if (MESES[i].indexOf('A') != -1) {
                System.out.println(MESES[i] + ": " + ventas.get(i));
            }
        }
    }

    //OPCION4
    public static void mesConMasVentas() {
        String mesConMasVentas = "";
        int ventasMes = 0;
        for (int i = 0; i < MESES.length; i++) {
            if (ventas.get(i) > ventasMes) {
                ventasMes = ventas.get(i);
                mesConMasVentas = MESES[i];
            }
        }
        System.out.println("EL MES CON MAS VENTAS HA SIDO " + mesConMasVentas + " CON " + ventasMes + " VENTAS.");
    }

    public static void main(String[] args) throws InterruptedException {
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("******RELLENAR VENTAS******");
                    rellenarVentas();
                    System.out.print("RELLENANDO VENTAS ");
                    for (int i = 0; i < 5; i++) {
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                    System.out.println("\n¡VENTAS RELLENADAS CON EXITO!");
                    break;
                case 2:
                    System.out.println("******MOSTRAR VENTAS******");
                    mostrarVentas();
                    break;
                case 3:
                    System.out.println("******MOSTRAR VENTAS MESES CON 'A'******");
                    mostrarVentasMesesA();
                    break;
                case 4:
                    System.out.println("******MOSTRAR MES CON MAS VENTAS******");
                    mesConMasVentas();
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. INTRODUZCA UNA OPCIÓN VALIDA.");
            }

        } while (opcion != 5);
    }

}
