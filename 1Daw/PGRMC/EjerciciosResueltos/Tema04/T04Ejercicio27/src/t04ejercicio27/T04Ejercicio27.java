package t04ejercicio27;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class T04Ejercicio27 {

    public static String pedirCara() {
        Scanner entrada = new Scanner(System.in);
        String caraUsuario;
        System.out.println("Por favor, elige 'Cara' o 'Cruz': ");
        caraUsuario = entrada.nextLine();

        return caraUsuario;
    }

    public static String caraAleatoria() {
        int numAleatorio;
        String caraAleatoria;

        numAleatorio = (int) Math.floor(Math.random() * 2);

        if (numAleatorio == 0) {
            caraAleatoria = "Cara";
        } else {
            caraAleatoria = "Cruz";
        }

        return caraAleatoria;
    }

    public static void main(String[] args) {
        String caraAleatoria = caraAleatoria();
        String caraUsuario = pedirCara();
        int contador = 1;

        while (!caraUsuario.equalsIgnoreCase(caraAleatoria)) {
            System.out.println("GAME OVER.La cara aleatoria era " + caraAleatoria + ", intentelo de nuevo.");
            caraAleatoria = caraAleatoria();
            caraUsuario = pedirCara();
            contador++;
        }

        System.out.println("¡ENHORABUENA! Has acertado. El numero de intentos ha sido "+contador);
    }

}
