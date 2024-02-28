package t04ejercicio20;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio20 {

    final static String SOLUCION1 = "Madrid";
    final static String SOLUCION2 = "Colon";

    public static boolean pregunta1() {
        Scanner entrada = new Scanner(System.in);
        String respuesta;
        boolean acierto;

        System.out.println("1ª PREGUNTA: ¿Cuál es la capital de España?:");
        respuesta = entrada.nextLine();

        acierto = respuesta.equals(SOLUCION1);

        if (acierto) {
            System.out.println("Muy bien, respuesta correcta.");
        } else {
            System.out.println("No es correcto. La respuesta correcta sería: " + SOLUCION1);
        }

        return acierto;
    }

    public static boolean pregunta2() {
        Scanner entrada = new Scanner(System.in);
        String respuesta;
        boolean acierto;

        System.out.println("2ª PREGUNTA: ¿Quién descubrió América?:");
        respuesta = entrada.nextLine();

        acierto = respuesta.equals(SOLUCION2);

        if (acierto) {
            System.out.println("Muy bien, respuesta correcta.");
        } else {
            System.out.println("No es correcto. La respuesta correcta sería: " + SOLUCION2);
        }

        return acierto;
    }

    public static void main(String[] args) {
        int nota = 0;
        System.out.println("EXAMEN DE CULTURA GENERAL");
        if (pregunta1()) {
            nota += 5;
        }
        if (pregunta2()) {
            nota += 5;
        }
        System.out.println("NOTA DEL EXÁMEN: " + nota);
    }

}
