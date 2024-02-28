package generico;

import java.time.LocalDate;

/**
 *
 * @author Walter
 */
public class Utilidad {

    public static int calcularFila(int idCelda) {
        return idCelda / Constantes.FILAS;
    }

    public static int calcularColumna(int idCelda) {
        return idCelda % Constantes.COLUMNAS;
    }

    public static String generarNombreFichero(String nombre) {
        int dia, mes;
        LocalDate hoy;

        //Obtengo la fecha actual
        hoy = LocalDate.now();
        dia = hoy.getDayOfMonth();
        mes = hoy.getMonthValue();

        return nombre + "_" + dia + mes + Constantes.EXTENSION_LOG;
    }

}
