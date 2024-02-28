package ejercicio1;

import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {
    //    Variables globales
    public static void main(String[] args) {
        System.out.println("****************************************");
        System.out.println("********* E J E R C I C I O  1 *********");
        System.out.println("****************************************");
        File ficheroOrigen = pedirFichero("origen");//Pido fichero de origen
        File ficheroDestino = pedirFichero("destino");//Pido fichero de destino
        //Copio el contenido de uno al otro
        try {
            copiarContenido(ficheroOrigen, ficheroDestino);
        } catch (IOException e) {
            System.out.println("ERROR: No se ha podido copiar el fichero.");
        }
    }

    public static File pedirFichero(String texto) {
        Scanner s = new Scanner(System.in);
        System.out.println("Por favor, introduzca el nombre del fichero de " + texto + " sin extensión: ");
        String nombreFichero = s.nextLine();
        File fichero = new File("src/ejercicio1/" + nombreFichero + ".txt");
        //Si el fichero es el de origen y no existe lo vuelvo a pedir.Si es el de destino no, porque se crea automaticamente
        if (texto.equalsIgnoreCase("origen")) {
            //Si no existe el fichero vuelvo a pedir
            if (!fichero.exists()) {
                fichero = pedirFichero(texto);
            }
        }
        return fichero;
    }

    public static void copiarContenido(File ficheroOrigen, File ficheroDestino) throws IOException {
        System.out.println("COPIANDO CONTENIDO .....");
//        Abro flujos de lectura
        BufferedReader br = new BufferedReader(new FileReader(ficheroOrigen));
//        Abro flujos de escritura
        PrintWriter pw = new PrintWriter(new FileWriter(ficheroDestino, true));
//       Procedo a leer en el fichero de origen y escribir en el fichero de destino
        String linea = br.readLine();//Leo la primera linea
        while (linea != null) {
            pw.println(linea);//Escribo la linea en fichero destino
            linea = br.readLine();//Leo otra linea en fichero de origen
        }
//        Cierro flujos de lectura y escritura
        pw.close();
        br.close();

        System.out.println("¡CONTENIDO COPIADO CON ÉXITO!");

        //Muestro listado de archivos de la carpeta donde se encuentra el fichero de destino
        mostrarListadoArchivos(ficheroDestino);
    }

    public static void mostrarListadoArchivos(File ficheroDestino) {
        File rutaDirectorio = new File(ficheroDestino.getParent());
        System.out.println("LISTADO DE ARCHIVOS QUE SE ENCUENTRAN EN EL DIRECTORIO DEL FICHERO DESTINO:");
        for (File archivo : rutaDirectorio.listFiles()) {
            System.out.println(archivo.getName());
        }

    }
}
