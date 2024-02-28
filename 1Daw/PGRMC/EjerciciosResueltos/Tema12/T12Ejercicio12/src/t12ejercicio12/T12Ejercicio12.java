package t12ejercicio12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Walter
 */
public class T12Ejercicio12 {

    final static String SEPARADOR = ";";

    public static int obtenerSuma(String linea) {
        int suma = 0;
        String[] numeros;

        //OBTENGO UN VECTOR DE STRING CON LOS NUMEROS
        numeros = linea.split(SEPARADOR);

        //RECORRO Y SUMO
        for (int i = 0; i < numeros.length; i++) {
            suma += Integer.parseInt(numeros[i]);
        }
        
        return suma;
    }

    public static int leerFicheroSumando(File fichero) throws FileNotFoundException, IOException {
        int suma = 0;//Vamos a ir almacenando el valor de la suma de cada linea y lo vamos sumando
        String linea;

        //ABRIMOS FLUJOS
        FileReader fr = new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr);

        //LEEMOS
        linea = br.readLine();
        while (linea != null) {
            suma += obtenerSuma(linea);
            linea = br.readLine();
        }

        //CERRAMOS FLUJOS
        br.close();
        fr.close();

        return suma;
    }

    public static void main(String[] args) {
        File fichero = new File("masnumeros.txt");
        int suma;

        try {
            suma = leerFicheroSumando(fichero);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR.NO SE ENCONTRO EL ARCHIVO");
            suma = 0;
        } catch (IOException ex) {
            System.out.println("ERROR DE ENTRADA/SALIDA");
            suma = 0;
        }
        System.out.println("La suma de todos los numeros del fichero es " + suma);
    }

}
