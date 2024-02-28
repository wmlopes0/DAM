package t12ejercicio09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class T12Ejercicio09 {

    public static String intercambiarOrden(String linea) {
        String cadenaCambiada = "";
        char caracter;
        for (int i = linea.length() - 1; i >= 0; i--) {
            caracter = linea.charAt(i);
            cadenaCambiada += caracter;
        }
        return cadenaCambiada;
    }

    public static void leerFichero(File fichero) throws FileNotFoundException, IOException {
        String linea;
        //Abrimos flujos
        FileReader fr = new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr);

        //LEEMOS E INTERCAMBIAMOS
        linea = br.readLine();

        if (linea != null) {
            linea = intercambiarOrden(linea);
            escribirFichero(linea);
        }

        //Cerramos flujos
        br.close();
        fr.close();
    }

    public static void escribirFichero(String linea) throws IOException {
        File ficheroNuevo = new File("fraseinvertida.txt");

        //Abro flujos
        FileWriter fw = new FileWriter(ficheroNuevo);
        PrintWriter pw = new PrintWriter(fw);

        //ESCRIBO
        pw.println(linea);

        //Cierro flujos
        pw.close();
        fw.close();

    }

    public static void main(String[] args) {
        File fichero = new File("frase.txt");
        try {
            leerFichero(fichero);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR FICHERO NO ENCONTRADO");
        } catch (IOException ex) {
            System.out.println("ERROR ENTRADA/SALIDA");
        }
    }

}
