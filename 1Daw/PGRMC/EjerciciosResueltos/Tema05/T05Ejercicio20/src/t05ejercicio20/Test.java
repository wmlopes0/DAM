package t05ejercicio20;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==========BIENVENIDO A WALBANK==========");
        System.out.println("1.- Crear cuenta vacía.\n"
                + "2.- Crear cuenta con saldo inicial.\n"
                + "3.- Ingresar dinero.\n"
                + "4.- Sacar dinero.\n"
                + "5.- Ver saldo.\n"
                + "6.- Salir.");
        System.out.println("----------------------------------------");
        return entrada.nextInt();
    }

    public static float pedirCantidad() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca una cantidad: ");
        return entrada.nextFloat();
    }

    /*public static boolean existeCuenta(Cuenta cuenta) {
        return cuenta == null;
    }*/

    public static void main(String[] args) {
        boolean salir = false;
        Cuenta cuenta = null;

        while (!salir) {
            switch (mostrarMenu()) {
                case 1:
                    if (cuenta == null) {
                        cuenta = new Cuenta();
                        System.out.println("¡Cuenta creada con exito!");
                    } else {
                        System.out.println("ERROR: ¡Ya tienes una cuenta creada!");
                    }
                    break;
                case 2:
                    if (cuenta == null) {
                        cuenta = new Cuenta(pedirCantidad());
                        System.out.println("¡Cuenta creada con exito!");
                    } else {
                        System.out.println("ERROR: ¡Ya tienes una cuenta creada!");
                    }
                    break;
                case 3:
                    if (cuenta != null) {
                        cuenta.ingresar(pedirCantidad());
                        System.out.println("¡Cantidad ingresada con exito!");
                    } else {
                        System.out.println("ERROR: ¡Primero tienes que crear una cuenta!");
                    }
                    break;
                case 4:
                    if (cuenta != null) {
                        cuenta.extraer(pedirCantidad());
                    } else {
                        System.out.println("ERROR: ¡Primero tienes que crear una cuenta!");
                    }
                    break;
                case 5:
                    if (cuenta != null) {
                        System.out.println("El saldo actual es de " + cuenta.getSaldo() + " euros.");
                    } else {
                        System.out.println("ERROR: ¡Primero tienes que crear una cuenta!");
                    }
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR: Por favor, introduzca una de las opciones.");
            }
        }

    }

}
