package t12ejercicio19;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class T12Ejercicio19 {

    public static int mostrarMenu(Scanner entrada) {
        System.out.println("========MENU========");
        System.out.println("1. Añadir supuesto becario.");
        System.out.println("2. Mostrar por pantalla supuestos becarios.");
        System.out.println("3. Salir del programa.");
        System.out.println("----------------------");
        System.out.println("Por favor introduce una opcion: ");
        return entrada.nextInt();
    }

    public static void escribirFichero(SupuestoBecario supuestoBecario, File fichero) throws FileNotFoundException, IOException {
        //ABRIMOS FLUJOS NECESARIOS
        FileOutputStream fos = new FileOutputStream(fichero, true);
        DataOutputStream dos = new DataOutputStream(fos);

        //ESCRIBIMOS
        dos.writeUTF(supuestoBecario.getNombreApellido());
        dos.writeUTF(supuestoBecario.getSexo().toUpperCase());
        dos.writeInt(supuestoBecario.getEdad());
        dos.writeInt(supuestoBecario.getnSuspensos());
        dos.writeUTF(supuestoBecario.getResidencia().toUpperCase());
        dos.writeInt(supuestoBecario.getIngresosAnualesFamilia());

        //CERRAMOS FLUJOS
        dos.close();
        fos.close();
    }

    public static void leerFichero(File fichero) throws FileNotFoundException, IOException {
        //ABRIMOS FLUJOS NECESARIOS
        FileInputStream fis = new FileInputStream(fichero);
        DataInputStream dis = new DataInputStream(fis);

        //LEEMOS
        while (dis.available() > 0) {
            System.out.println("---------SUPUESTO BECARIO---------");
            System.out.println("Nombre y Apellidos: "+dis.readUTF());
            System.out.println("Sexo: "+dis.readUTF());
            System.out.println("Edad: "+dis.readInt());
            System.out.println("Numero de suspensos del curso anterior: "+dis.readInt());
            System.out.println("Residencia familiar: "+dis.readUTF());
            System.out.println("Ingresos anuales de la familia: "+dis.readInt());
        }

        //CERRAMOS FLUJOS
        dis.close();
        fis.close();

    }
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        File fichero = new File("datosBeca.bin");
        SupuestoBecario supuestoBecario;
        int opcion;

        do {
            opcion = mostrarMenu(entrada);
            switch (opcion) {
                case 1:
                    supuestoBecario = new SupuestoBecario();
                    try {
                        escribirFichero(supuestoBecario, fichero);
                    } catch (FileNotFoundException ex) {
                        System.out.println("ERROR.FICHERO NO ENCONTRADO.");
                    } catch (IOException ex) {
                        System.out.println("ERROR DE ESCRITURA.");
                    }
                    break;
                case 2:
                    System.out.println("LEYENDO FICHERO...");
                    try {
                        leerFichero(fichero);
                    } catch (FileNotFoundException e) {
                        System.out.println("ERROR.FICHERO NO ENCONTRADO.");
                    } catch (IOException ex) {
                        System.out.println("ERROR DE ESCRITURA.");
                    }
                    break;

                case 3:
                    System.out.println("¡HASTA PRONTO!");
                    break;
                default:
                    System.out.println("ERROR. POR FAVOR INTRODUZCA UNA OPCION VALIDA.");
            }

        } while (opcion
                != 3);
    }
}
