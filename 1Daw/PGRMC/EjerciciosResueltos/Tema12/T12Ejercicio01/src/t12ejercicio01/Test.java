/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t12ejercicio01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() throws InputMismatchException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==========MENU==========");
        System.out.println("1.- Añadir contactos a la agenda.");
        System.out.println("2.- Visualizar la lista de contactos.");
        System.out.println("3.- Eliminar contactos de la lista.");
        System.out.println("4.- Mostrar todos los contactos ordenados por su edad.");
        System.out.println("5.- Salir.");
        System.out.println("--------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        return entrada.nextInt();
    }

    public static void anadirContacto(List<Contacto> agenda) {
        Contacto contacto = new Contacto();

        try {
            contacto.rellenarContacto();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: El tipo de dato introducido no es valido, por favor, introduzca de nuevo los datos.");
            anadirContacto(agenda);
        }

        agenda.add(contacto);
    }

    public static void mostrarContactos(List<Contacto> agenda) {
        for (Contacto contacto : agenda) {
            contacto.mostrar();
        }
    }

    public static void eliminarContactoNumero(int telefono, List<Contacto> agenda) {
        int pos = existeContacto(telefono, agenda);

        if (pos >= 0) {
            agenda.remove(agenda.get(pos));
            System.out.println("CONTACTO ELIMINADO CORRECTAMENTE");
        } else {
            System.out.println("¡CONTACTO NO EXISTENTE! O LA LISTA ESTA VACIA, QUE TAMBIEN PUESER");
        }
    }

    //Devuelve la posicion del contacto si existe, sino un numero negativo 
    public static int existeContacto(int telefono, List<Contacto> agenda) {
        Contacto contacto;
        boolean enc = false;
        int pos = 0;
        while (pos < agenda.size() && !enc) {
            contacto = agenda.get(pos);
            if (contacto.getNumeroMovil() == telefono) {
                enc = true;
            } else {
                pos++;
            }
        }
        if (!enc) {
            pos = -1;
        }
        return pos;
    }

    public static int pedirNumero() throws InputMismatchException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el telefono: ");
        return entrada.nextInt();
    }

    public static void mostrarContactosOrdenados(List<Contacto> agenda) {
        Collections.sort(agenda);
        mostrarContactos(agenda);
    }

    public static void main(String[] args) {
        List<Contacto> agenda = new ArrayList<>();

        int opcion, telefono;

        do {
            try {
                opcion = mostrarMenu();
            } catch (InputMismatchException e) {
                opcion = 6;
            }

            switch (opcion) {
                case 1:
                    System.out.println("========AÑADIR CONTACTO========");
                    anadirContacto(agenda);
                    break;
                case 2:
                    System.out.println("========MOSTRAR CONTACTOS========");
                    mostrarContactos(agenda);
                    break;
                case 3:
                    System.out.println("========ELIMINAR CONTACTO POR NUMERO========");
                    try {
                        telefono = pedirNumero();
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, no introduzcas letras que somos mayorcitos.");
                        telefono = pedirNumero();
                    }
                    eliminarContactoNumero(telefono, agenda);
                    break;
                case 4:
                    System.out.println("========MOSTRAR CONTACTOS ORDENADOS POR EDAD========");
                    mostrarContactosOrdenados(agenda);
                    break;
                case 5:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("Por favor, introduzca una opcion valida.");
            }
        } while (opcion != 5);
    }
}
