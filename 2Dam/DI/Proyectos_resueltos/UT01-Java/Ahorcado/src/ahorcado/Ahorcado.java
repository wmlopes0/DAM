package ahorcado;

import java.util.Scanner;

/**
 *
 * @author wmartinl01
 */
public class Ahorcado {

    final static String[] PALABRAS = {"GATO", "PERRO", "CASA", "SILLA", "MESA", "AUTO", "PLUMA", "LIBRO", "AGUA", "LUZ"};
    final static int MAX_FALLOS = 7;
    public static int FALLOS = 0;

    public static void main(String[] args) {
        System.out.println("**************************************");
        System.out.println("******** B I E N VE N I D O **********");
        System.out.println("**** A L   A H O R C A D O   D E *****");
        System.out.println("************ W A L T E R *************");
        System.out.println("**************************************\n");
        String palabraSeleccionada = PALABRAS[(int) Math.round(Math.random() * (PALABRAS.length - 1))];
        Character[] palabraUsuario = new Character[palabraSeleccionada.length()];
        do {
            pintarAhorcado(FALLOS);
            palabraUsuario = actualizarPalabraUsuario(palabraSeleccionada, pedirLetra(), palabraUsuario);
            limpiarSalida();
            pintarPalabra(palabraUsuario);
        } while (FALLOS < MAX_FALLOS && !comprobarPalabra(palabraSeleccionada, palabraUsuario));

        if (comprobarPalabra(palabraSeleccionada, palabraUsuario)) {
            System.out.println("¡ENHORABUENA HAS ACERTADO LA PALABRA!");
        } else {
            System.out.println("\n¡LO SIENTO, HAS SUPERADO EL MAXIMO NUMERO DE INTENTOS!");
            System.out.println("\nLA PALABRA ERA '" + palabraSeleccionada + "'.");
            System.out.println("\n******************************************");
            System.out.println("*********** G A M E    O V E R ***********");
            System.out.println("******************************************");
        }
    }

    public static char pedirLetra() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("INTRODUCE UNA LETRA: ");
        return entrada.nextLine().toUpperCase().charAt(0);
    }

    // Método que dibuja el ahorcado según el número de fallos
    public static void pintarAhorcado(int fallos) {
        switch (fallos) {
            case 0:
                System.out.println("--------------------------");
                System.out.println("|       ________         |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|  -------               |");
                System.out.println("--------------------------");
                break;
            case 1:
                System.out.println("--------------------------");
                System.out.println("|       ________         |");
                System.out.println("|      |        |        |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|      |                 |");
                System.out.println("|  -------               |");
                System.out.println("--------------------------");
                break;
            case 2:
                System.out.println("----------------------------");
                System.out.println("|       ________           |");
                System.out.println("|      |      __|__        |");
                System.out.println("|      |     ( . . )       |");
                System.out.println("|      |                   |");
                System.out.println("|      |                   |");
                System.out.println("|      |                   |");
                System.out.println("|      |                   |");
                System.out.println("|  -------                 |");
                System.out.println("----------------------------");
                break;
            case 3:
                System.out.println("----------------------------");
                System.out.println("|       ________           |");
                System.out.println("|      |      __|__        |");
                System.out.println("|      |     ( . . )       |");
                System.out.println("|      |        |          |");
                System.out.println("|      |        |          |");
                System.out.println("|      |                   |");
                System.out.println("|      |                   |");
                System.out.println("|  -------                 |");
                System.out.println("----------------------------");
                break;
            case 4:
                System.out.println("----------------------------");
                System.out.println("|       ________           |");
                System.out.println("|      |      __|__        |");
                System.out.println("|      |     ( . . )       |");
                System.out.println("|      |  ----- |          |");
                System.out.println("|      |        |          |");
                System.out.println("|      |                   |");
                System.out.println("|      |                   |");
                System.out.println("|  -------                 |");
                System.out.println("----------------------------");
                break;
            case 5:
                System.out.println("----------------------------");
                System.out.println("|       ________           |");
                System.out.println("|      |      __|__        |");
                System.out.println("|      |     ( . . )       |");
                System.out.println("|      |  ----- |-----     |");
                System.out.println("|      |        |          |");
                System.out.println("|      |                   |");
                System.out.println("|      |                   |");
                System.out.println("|  -------                 |");
                System.out.println("----------------------------");
                break;
            case 6:
                System.out.println("----------------------------");
                System.out.println("|       ________           |");
                System.out.println("|      |      __|__        |");
                System.out.println("|      |     ( . . )       |");
                System.out.println("|      |  ----- |-----     |");
                System.out.println("|      |        |          |");
                System.out.println("|      |       /           |");
                System.out.println("|      |      /            |");
                System.out.println("|  -------                 |");
                System.out.println("----------------------------");
                break;
            case 7:
                System.out.println("----------------------------");
                System.out.println("|       ________           |");
                System.out.println("|      |      __|__        |");
                System.out.println("|      |     ( . . )       |");
                System.out.println("|      |  ----- |-----     |");
                System.out.println("|      |        |          |");
                System.out.println("|      |       / |         |");
                System.out.println("|      |      /   |        |");
                System.out.println("|  -------                 |");
                System.out.println("----------------------------");
                break;
            default:
                break;
        }
    }

    // Método que verifica si una letra está en la palabra y devuelve su posición, si no está devuelve -1
    public static int comprobarLetra(char letra, String palabra) {
        boolean encontrada = false;
        int contador = 0;
        while (!encontrada && contador < palabra.length()) {
            if (palabra.charAt(contador) == letra) {
                encontrada = true;
            } else {
                contador++;
            }
        }
        if (encontrada == true) {
            return contador;
        } else {
            return -1;
        }

    }

    // Método que actualiza la palabra del usuario con la letra ingresada
    public static Character[] actualizarPalabraUsuario(String palabraSeleccionada, char letra, Character[] palabraUsuario) {
        Character[] aux = palabraUsuario;
        if (comprobarLetra(letra, palabraSeleccionada) != -1) {
            System.out.println("Enhorabuena! la letra se encuentra en la palabra.");
            aux[comprobarLetra(letra, palabraSeleccionada)] = letra;
        } else {
            System.out.println("Lo siento, esa letra no existe en la palabra.");
            FALLOS++;
        }
        return aux;
    }

    // Método que compara la palabra seleccionada con la del usuario y devuelve true si ha acertado la palabra
    public static boolean comprobarPalabra(String palabraSeleccionada, Character[] palabraUsuarioVector) {
        String palabraUsuario = "";
        for (Character c : palabraUsuarioVector) {
            palabraUsuario += c;
        }

        return palabraSeleccionada.equalsIgnoreCase(palabraUsuario);
    }

    // Método que imprime la palabra del usuario en la consola, con las letras que ha acertado en la posición correspondiente
    public static void pintarPalabra(Character[] palabraUsuario) {
        System.out.print("PALABRA --->  ");
        for (int i = 0; i < palabraUsuario.length; i++) {
            if (palabraUsuario[i] == null) {
                palabraUsuario[i] = '_';
            }
            System.out.print(palabraUsuario[i] + " ");
        }
        //Salto de línea
        System.out.println("");
    }

    // Método que simula limpiar la consola
    public static void limpiarSalida() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Ignorar excepción
        }
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

}
