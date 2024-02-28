package ficheros;

import generico.CentralNuclear;
import generico.Constantes;
import generico.Utilidad;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import personajes.Minero;
import personajes.Personaje;
import personajes.Robot;

/**
 *
 * @author Walter
 */
public class Escritura {

    //ESCRITURA SIMULACION
    public static FileWriter abrirFlujoFW() {
        //Creo directorio para almacenar simulaciones, si existe no se crea de nuevo.
        File dirSimulaciones = new File(Constantes.DIRECTORIO_SIMULACION);
        dirSimulaciones.mkdir();
        //Genero el nombre del fichero
        String nombreFichero = Utilidad.generarNombreFichero(Constantes.FICHERO_SIMULACION);
        File ficheroSimulacion = new File(nombreFichero);
        //Abro el flujo
        FileWriter fw = null;
        try {
            fw = new FileWriter(ficheroSimulacion);
        } catch (IOException ex) {
            System.out.println(Constantes.ERROR_ENTRADA_SALIDA);
        }

        return fw;
    }

    public static PrintWriter abrirFlujoPW(FileWriter fw) {
        PrintWriter pw = new PrintWriter(fw);
        return pw;
    }

    public static void cerrarFicheroSimulacion(FileWriter fw, PrintWriter pw) {
        try {
            pw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println(Constantes.ERROR_ENTRADA_SALIDA);
        }
    }

    //ESCRITURA INFORMES
    public static void escribirInformesEnFicheroLog() {
        //Patrón singleton
        CentralNuclear central = CentralNuclear.getInstancia();
        //Obtengo la lista de personajes de la central
        ArrayList<Personaje> personajes = central.getlPersonajes();
        //Creo directorio para almacenar informes, si existe no se crea de nuevo.
        File dirInformes = new File(Constantes.DIRECTORIO_INFORMES);
        dirInformes.mkdir();
        //Genero el nombre del fichero
        String nombreFichero = Utilidad.generarNombreFichero(Constantes.FICHERO_INFORME);
        File ficheroInforme = new File(nombreFichero);
        //Escribo
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(ficheroInforme);
            pw = new PrintWriter(fw);
            //Bomberos
            escribirInformeBomberos(pw, central);
            //Mineros
            escribirInformeMineros(pw, personajes);
            //Robots
            escribirInformeRobots(pw, personajes);
        } catch (IOException ex) {
            System.out.println(Constantes.ERROR_ENTRADA_SALIDA);
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    System.out.println(Constantes.ERROR_ENTRADA_SALIDA);
                }
            }
        }
    }

    private static void escribirInformeBomberos(PrintWriter pw, CentralNuclear central) {
        pw.println(Constantes.INFORMEBOMBEROS);
        central.mostrarCeldasRefrigeradas(pw);
    }

    private static void escribirInformeMineros(PrintWriter pw, ArrayList<Personaje> personajes) {
        pw.println(Constantes.INFORMEMINERO);
        for (Personaje personaje : personajes) {
            if (personaje instanceof Minero) {
                ((Minero) personaje).mostrarEscombrosRecogidos(pw);
            }
        }
    }

    private static void escribirInformeRobots(PrintWriter pw, ArrayList<Personaje> personajes) {
        pw.println(Constantes.INFORMEROBOTS);
        for (Personaje personaje : personajes) {
            if (personaje instanceof Robot) {
                ((Robot) personaje).mostrarCeldasVisitadas(pw);
                pw.print("\n");
            }
        }
    }
}
