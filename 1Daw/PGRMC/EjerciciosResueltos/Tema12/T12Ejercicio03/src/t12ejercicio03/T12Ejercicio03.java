package t12ejercicio03;

import java.io.File;

/**
 *
 * @author Walter
 */
public class T12Ejercicio03 {

    public static void mostrarVector(String[] ficheros) {
        for (int i = 0; i < ficheros.length; i++) {
            System.out.println(ficheros[i]);
        }
    }

    public static void main(String[] args) {
        File directorio = new File("DiasSemana");
        String[] ficheros = directorio.list();
        mostrarVector(ficheros);
        System.out.println("El numero total de ficheros es " + ficheros.length);
    }

}
