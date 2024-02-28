package tresenraya;

import java.util.Scanner;

/**
 *
 * @author Walter
 */
public class TresEnRaya {

    private static String nombre;
    private static boolean finPartida = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //GENERO TABLERO 
        char[][] tablero = new char[3][3];
        rellenarTablero(tablero);
        //---------------------
        System.out.println("=========BIENVENIDO AL 3 EN RAYA DE WALTER=========");
        pedirNombre();
        mostrarTablero(tablero);
        do {
            turnoUsuario(tablero);
            mostrarTablero(tablero);
            comprobarJugada(tablero, 'O');
            System.out.println("");//SALTO DE LINEA
            if (!finPartida) {
                turnoCPU(tablero);
                mostrarTablero(tablero);
                comprobarJugada(tablero, 'X');
            }
        } while (!finPartida);
    }

    public static void pedirNombre() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Por favor, introduzca su nombre: ");
        nombre = entrada.nextLine();
    }

    public static void rellenarTablero(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public static void mostrarTablero(char[][] tablero) {
        System.out.println("\n-TABLERO-");
        for (int i = 0; i < tablero.length; i++) {
            System.out.println("-------------");
            System.out.print("|");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(" " + tablero[i][j] + " |");
            }
            System.out.println("");
        }
        System.out.println("-------------");
    }

    public static void turnoUsuario(char[][] tablero) {
        Scanner entrada = new Scanner(System.in);
        int fila, columna;
        do {
            System.out.println("\n-TURNO DE " + nombre.toUpperCase() + "-");
            System.out.print("Elige una fila: ");
            fila = entrada.nextInt() - 1;
            System.out.print("Elige una columna: ");
            columna = entrada.nextInt() - 1;
        } while (!comprobarPosicion(fila, columna, tablero));

        //ASIGNO LA POSICION AL TABLERO O
        tablero[fila][columna] = 'O';
    }

    public static void turnoCPU(char[][] tablero) {
        int fila, columna;
        System.out.println("-TURNO DE LA CPU-");
        do {
            fila = ((int) Math.round(Math.random() * 2));
            columna = ((int) Math.round(Math.random() * 2));
        } while (!comprobarPosicion(fila, columna, tablero));

        //ASIGNO LA POSICION AL TABLERO X
        tablero[fila][columna] = 'X';
    }

    public static boolean comprobarPosicion(int fila, int columna, char[][] tablero) {
        boolean posicion = true;

        if (fila < 0 || fila > 2 || columna < 0 || columna > 2) {
            posicion = false;
        } else {
            if (tablero[fila][columna] != ' ') {
                posicion = false;
            }
        }

        return posicion;
    }

    public static void comprobarJugada(char[][] tablero, char jugador) {
        //DECLARO VARIABLES NECESARIAS
        boolean empate = true;
        boolean ganar = false;
        //COMPRUEBO SI HA QUEDADO EMPATE
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == ' ') {
                    empate = false;
                }
            }
        }
        //COMPRUEBO SI HA GANADO

        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) {
                ganar = true;
            }
        }
        for (int j = 0; j < tablero[0].length; j++) {
            if (tablero[0][j] == jugador && tablero[1][j] == jugador && tablero[2][j] == jugador) {
                ganar = true;
            }
        }
        if (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) {
            ganar = true;
        } else {
            if (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador) {
                ganar = true;
            }
        }

        //RESULTADO
        if (empate) {
            finPartida = true;
            System.out.println("¡HABEIS QUEDADO EMPATE!");
            System.out.println("¡GRACIAS POR JUGAR!");
        } else {
            if (ganar && jugador == 'O') {
                finPartida = true;
                System.out.println("¡HAS GANADO!");
                System.out.println("¡GRACIAS POR JUGAR!");
            } else {
                if (ganar && jugador == 'X') {
                    finPartida = true;
                    System.out.println("LO SIENTO,¡HAS PERDIDO!");
                    System.out.println("¡GRACIAS POR JUGAR!");
                }

            }
        }

    }

}
