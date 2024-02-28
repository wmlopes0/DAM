package t12ejercicio04;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class Test {

    public static String pedirExtension() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor, introduzca una extension en formato '.txt': ");
        return entrada.nextLine();
    }

    public static void mostrarFicheros(String directorio, String consulta) {
        int posicionPunto;
        String extension;
        //Creo File con la ruta del directorio donde quiero buscar
        File path = new File(directorio);
        //Extraigo la lista de ficheros en el vector ficheros
        String[] ficheros = path.list();
        
        //Muestro ficheros con la extension dada.
        System.out.println("Los ficheros con la extension dada son: ");
        for (int i = 0; i < ficheros.length; i++) {
            posicionPunto = ficheros[i].lastIndexOf(".");
            extension = ficheros[i].substring(posicionPunto);
            if (extension.equalsIgnoreCase(consulta)) {
                System.out.println(ficheros[i]);
            }
        }
    }

    public static void main(String[] args) {
        String consulta = pedirExtension();

        mostrarFicheros("DiasSemana", consulta);
    }
}
