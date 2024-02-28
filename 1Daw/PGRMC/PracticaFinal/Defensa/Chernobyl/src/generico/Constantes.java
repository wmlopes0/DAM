package generico;

/**
 *
 * @author Walter
 */
public class Constantes {

    //General
    final public static int FILAS = 8;
    final public static int COLUMNAS = 8;
    final public static String EXTENSION_LOG = ".log";
    final public static char N = 'N';
    final public static char S = 'S';
    final public static char E = 'E';
    final public static char O = 'O';
    final public static String SEPARADOR = "#";
    final public static String COMENTARIO = "-- ";
    final public static int PRIMERACELDA = 0;
    final public static int ULTIMACELDA = 63;

    //Simulación
    final public static String MENSAJEOK = "EJECUCION FINALIZADA CON EXITO.";
    final static String TURNO = "TURNO: ";
    final public static String DOSPUNTOS = ":";
    final public static String NOPUEDO = " ¡NO PUEDO! ";

    //Pintar matriz
    final static String CERO = "0";
    final static String SIMBOLO_LLAVE = "?";

    //Bombero
    final public static int DESCARGA_REFRIGERADOR = 1;
    final public static int RECARGA_REFRIGERADOR = 5;
    final public static String RECARGANDO_REFRIGERADOR = " Recargando batería refrigerador.";

    //Minero
    final public static String MENSAJE_MINERO = " Escombros recogidos ";
    final public static String MENSAJE_MINERO1 = " Llevo ";
    final public static String MENSAJE_MINERO2 = " kilos de escombros.";

    //Científico
    final public static String MENSAJE_CIENTIFICO = " Llave recogida en celda ";
    final public static String MENSAJE_ABRIR_PUERTA = " ¡Puerta abierta!";

    //Oficial
    final public static String MENSAJE_OFICIAL = " Llave destruida en celda ";

    //Robot
    final public static String MENSAJE_ROBOT_REGISTRAR = "INFORME ";
    final public static String MENSAJE_ROBOT_ESCANEAR1 = " Detectadas ";
    final public static String MENSAJE_ROBOT_ESCANEAR2 = " personas en la zona de influencia. ";

    //Ficheros
    final public static String DIRECTORIO_SIMULACION = "SIMULACIONES";
    final public static String FICHERO_SIMULACION = "SIMULACIONES/simulacionEntregaFinal";
    final public static String DIRECTORIO_INFORMES = "INFORMES";
    final public static String FICHERO_INFORME = "INFORMES/informes";
    final public static String FICHERO_INFORME_OFICIALES = "INFORMES/celdasCatalogados";
    final public static String ERROR_FICHERO_NO_ENCONTRADO = "ERROR.FICHERO NO ENCONTRADO.";
    final public static String ERROR_ENTRADA_SALIDA = "ERROR ENTRADA/SALIDA.";

    //Lectura
    final public static String FICHERO_INICIO = "inicio.txt";
    final public static String CENTRAL = "CENTRAL";
    final public static String BOMBERO = "BOMBERO";
    final public static String MINERO = "MINERO";
    final public static String CIENTIFICO = "CIENTIFICO";
    final public static String OFICIAL = "OFICIAL";
    final public static String VOLUNTARIO = "VOLUNTARIO";
    final public static String ROBOT = "ROBOT";
    final public static String LLAVE = "LLAVE";
    final public static String PUERTA = "PUERTA";

    //Escritura
    final public static String INFORMEBOMBEROS = "INFORME BOMBEROS";
    final public static String INFORMEMINERO = "INFORME MINEROS";
    final public static String INFORMEROBOTS = "INFORME ROBOTS";

}
