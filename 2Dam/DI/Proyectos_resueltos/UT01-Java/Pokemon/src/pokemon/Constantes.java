package pokemon;

import java.io.File;

/**
 *
 * @author wmartinl01
 */
public class Constantes {

    final static File FICHERO = new File("cargaDeDatos.txt");
    final static String[] TIPO = {"TIERRA", "AGUA", "FUEGO", "PLANTA", "NORMAL"};
    final static int VIDA_MIN = 0;
    final static int VIDA_MAX = 100;
    final static int MAX_POKEMON = 3;
    final static int MAX_ATAQUES = 4;
    final static int NUM_POCIONES = 3;
    final static int ESTADO_NORMAL = 60;
    final static int ESTADO_VULNERABLE = 20;
}
