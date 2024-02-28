package t09ejercicio04;

import java.time.Duration;
import java.time.LocalTime;

/**
 *
 * @author Walter
 */
public class T09Ejercicio04 {

    final static LocalTime APERTURA = LocalTime.of(8, 0, 0);
    final static LocalTime CIERRE = LocalTime.of(21, 20, 0);

    public static String calcularTiempos(LocalTime ahora) {
        Duration tiempoAbierto;
        Duration tiempoParaCierre;
        if (ahora.isAfter(APERTURA) && ahora.isBefore(CIERRE)) {
            tiempoAbierto = Duration.between(APERTURA, ahora);
            tiempoParaCierre = Duration.between(ahora, CIERRE);
            return "El instituto esta abierto, lleva " + tiempoAbierto.toHours() + " horas y " + (tiempoAbierto.toHours() % 60) + " minutos abierto, y faltan " + tiempoParaCierre.toHours() + " horas y " + (tiempoParaCierre.toHours() % 60) + " minutos para que cierre";
        } else {
            return "El instituto esta cerrado, abrira a las " + APERTURA.getHour();
        }
    }

    public static void main(String[] args) {
        LocalTime ahora = LocalTime.now();
        System.out.println(calcularTiempos(ahora));
    }

}
