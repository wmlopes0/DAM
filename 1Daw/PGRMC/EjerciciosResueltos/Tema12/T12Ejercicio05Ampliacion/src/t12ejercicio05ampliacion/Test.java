package t12ejercicio05ampliacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        System.out.println("1.- Añadir un contacto.");
        System.out.println("2.- Mostrar contactos.");
        System.out.println("3.- Salir.");
        System.out.println("--------------------------");
        System.out.println("Por favor, introduzca una opcion: ");
        return entrada.nextInt();
    }

    public static void anadirContacto(List<Contacto> lAgenda) {
        Contacto contacto = new Contacto();
        contacto.rellenar();
        lAgenda.add(contacto);
        System.out.println("Contacto añadido correctamente...");
    }

    public static void mostrarContactos(List<Contacto> lAgenda) {
        for (Contacto contacto : lAgenda) {
            contacto.mostrar();
        }
    }

    public static void cargarDatos(File agenda, List<Contacto> lAgenda) throws IOException {
        String linea;
        Contacto contacto;

        //Abro flujos
        FileReader fr = new FileReader(agenda);
        BufferedReader br = new BufferedReader(fr);

        linea = br.readLine();
        while (linea != null) {
            lAgenda.add(crearObjeto(linea));
            linea = br.readLine();
        }

        //Cierro flujos
        br.close();
        fr.close();
    }

    public static Contacto crearObjeto(String linea) {
        Contacto contacto = new Contacto();
        String[] atributos = linea.split(" - ");

        contacto.setNombre(atributos[0]);
        contacto.setEdad(Integer.parseInt(atributos[1]));
        contacto.setTelefono(atributos[2]);

        return contacto;
    }

    public static void volcarDatos(File agenda, List<Contacto> lAgenda) throws IOException {
        Contacto contacto;
        String linea;

        //Abro flujos
        FileWriter fw = new FileWriter(agenda);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < lAgenda.size(); i++) {
            contacto = lAgenda.get(i);
            linea = contacto.getNombre() + " - " + contacto.getEdad() + " - " + contacto.getTelefono();
            pw.println(linea);
        }

        //Cierro flujos
        pw.close();
        fw.close();
    }

    public static void main(String[] args) {
        List<Contacto> lAgenda = new ArrayList<>();
        File agenda = new File("agenda.txt");
        int opcion;

        //CARGO DATOS
        try {
            cargarDatos(agenda, lAgenda);
        } catch (IOException ex) {
            System.out.println("ERROR DE ENTRADA/SALIDA");
        }

        do {
            try {
                opcion = mostrarMenu();
            } catch (InputMismatchException e) {
                opcion = 4;
            }
            switch (opcion) {
                case 1:
                    System.out.println("=========AÑADIR CONTACTO=========");
                    anadirContacto(lAgenda);
                    break;
                case 2:
                    System.out.println("=========MOSTRAR CONTACTOS=========");
                    mostrarContactos(lAgenda);
                    break;
                case 3:
                    System.out.println("¡HASTA PRONTO!");
                    try {
                        volcarDatos(agenda, lAgenda);
                    } catch (IOException e) {
                        System.out.println("ERROR DE ENTRADA/SALIDA");
                    }
                    break;
                default:
                    System.out.println("ERROR.Introduzca una opcion valida.");
            }
        } while (opcion != 3);
    }
}
