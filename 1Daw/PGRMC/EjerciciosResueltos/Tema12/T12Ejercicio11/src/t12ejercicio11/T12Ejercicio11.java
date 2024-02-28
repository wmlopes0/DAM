package t12ejercicio11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Walter
 */
public class T12Ejercicio11 {

    public static int obtenerSuma(File fichero) throws FileNotFoundException, IOException {
        int suma = 0;
        String linea;

        //ABRO FLUJOS
        FileReader fr = new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr);

        //LEO Y SUMO
        linea = br.readLine();
        while (linea != null) {
            suma += Integer.parseInt(linea);
            linea = br.readLine();
        }

        //CIERRO FLUJOS
        br.close();
        fr.close();

        return suma;
    }

    public static void main(String[] args) {
        File fichero = new File("numeros.txt");
        try {
            System.out.println("La suma de los numeros de 'numeros.txt' es: " + obtenerSuma(fichero));
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR, FICHERO NO ENCONTRADO.");
        } catch (IOException ex) {
            System.out.println("ERROR DE ENTRADA/SALIDA");
        }
    }

}
