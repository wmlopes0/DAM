/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t12ejercicio05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static int mostrarMenu() throws InputMismatchException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==========MENU==========");
        System.out.println("1.- Añadir un contacto a 'agenda.txt'.");
        System.out.println("2.- Mostrar 'agenda.txt'.");
        System.out.println("3.- Salir.");
        System.out.println("--------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        return entrada.nextInt();
    }

    public static void anadirContacto(File agenda) throws FileNotFoundException, IOException {
        String nombre;
        int edad, telefono;

        //Abro flujos
        FileWriter fw = new FileWriter(agenda, true);
        PrintWriter pw = new PrintWriter(fw);

        //Pido datos
        nombre = pedirCadena("un nombre");
        edad = pedirNumero("la edad");
        telefono = pedirNumero("el telefono");

        //Los escribo en el documento
        pw.println("===== " + nombre + " =====");
        pw.println("Edad: " + edad);
        pw.println("Telefono: " + telefono);

        //Cierro flujos
        pw.close();
        fw.close();

        System.out.println("Contacto añadido correctamente.");
    }

    public static String pedirCadena(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        return entrada.nextLine();
    }

    public static int pedirNumero(String texto) throws InputMismatchException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce " + texto + ": ");
        return entrada.nextInt();
    }

    public static void mostrarContactos(File agenda) throws IOException {
        String linea;
        //Abro flujos
        FileReader fr = new FileReader(agenda);
        BufferedReader br = new BufferedReader(fr);

        linea = br.readLine();
        while (linea != null) {
            System.out.println(linea);
            linea = br.readLine();
        }

        //Cierro flujos
        br.close();
        fr.close();
    }

    public static void main(String[] args) {
        File agenda = new File("agenda.txt");
        int opcion;

        do {
            try {
                opcion = mostrarMenu();
            } catch (InputMismatchException e) {
                opcion = 4;
            }
            switch (opcion) {
                case 1:
                    try {
                    anadirContacto(agenda);
                } catch (IOException ex) {
                    System.out.println("ERROR DE ENTRADA/SALIDA.");
                }
                break;
                case 2:
                    try {
                    mostrarContactos(agenda);
                } catch (FileNotFoundException ex) {
                        System.out.println("ERROR, NO SE ENCUENTRA EL FICHERO.");
                } catch (IOException ex) {
                    System.out.println("ERROR DE ENTRADA/SALIDA.");
                }
                break;
                case 3:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR.Introduzca una opcion valida.");
            }
        } while (opcion != 3);
    }
}
