package juego_uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wmartinl01
 */
public class Juego {

    final static String[] COLORES = {"ROJO", "AZUL", "AMARILLO", "VERDE"};
    final static int[] NUMEROS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    final static int CARTAS_REPARTIR = 7;
    public static final String rojo = "\u001B[31m";
    public static final String azul = "\u001B[34m";
    public static final String amarillo = "\u001B[33m";
    public static final String verde = "\u001B[32m";
    public static final String morado = "\u001B[35m";
    public static final String negro = "\u001B[30m";
    public static boolean juegoTerminado = false;
    public static List<Carta> mazo = new ArrayList<>();
    public static List<Carta> mesa = new ArrayList<>();
    public static List<Carta> cartasUsuario = new ArrayList<>();
    public static List<Carta> cartasMaquina = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(morado + "          ****************************************");
        System.out.println(morado + "          *********" + negro + " B I E N V E N I D O " + morado + "**********");
        System.out.println(morado + "          **********" + negro + " A L   U N O   D E " + morado + "***********");
        System.out.println(morado + "          *************" + negro + " W A L T E R " + morado + "**************");
        System.out.println(morado + "          ****************************************\n");

        //Creamos el mazo e iniciamos el juego barajando y repartiendo las cartas.
        rellenarMazo();
        iniciarJuego();
        while (!juegoTerminado) {
            mostrarCartas();
            turnoUsuario();
            comprobarJugada();
            turnoMaquina();
            comprobarJugada();
        }
    }

    //Todas las acciones necesarias del turno de la máquina
    public static void turnoMaquina() {
        boolean jugada = false;
        if (!juegoTerminado) {
            //Animacion
            System.out.println("\n\n==================== TURNO DE LA MÁQUINA ====================");
            for (int i = 0; i < 5; i++) {
                System.out.print(".  ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Primero compruebo si el juego ha terminado, si no es asi actua
            for (Carta carta : cartasMaquina) {
                if (carta.valida(mesa.get(mesa.size() - 1))) {
                    mesa.add(carta);
                    cartasMaquina.remove(carta);
                    jugada = true;
                    break;
                }
            }
            if (!jugada) {
                cartasMaquina.add(mazo.remove(0));
                System.out.println("\n----------------- LA MÁQUINA HA ROBADO CARTA  -----------------");
            } else {
                System.out.println("\n----------------- LA MÁQUINA HA JUGADO CARTA  -----------------");
            }
        }

        //Pauso el programa para ver resultado y limpio pantalla
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("");
        }
    }

    //Todas las acciones necesarias del turno del usuario
    public static void turnoUsuario() {
        Carta cartaElegida;
        int carta;
        if (!juegoTerminado) {
            carta = pedirNumeroCarta();
            //Compruebo si ha elegido la opción robar si es asi robamos y pasamos turno
            if (carta == -1) {
                cartasUsuario.add(mazo.remove(0));
                System.out.println("\n----------------- HAS ROBADO UNA CARTA  -----------------");
            } else {
                cartaElegida = cartasUsuario.get(carta);
                //Compruebo si el jugador puede jugar esa carta o debe realizar otra opcion
                if (cartaElegida.valida(mesa.get(mesa.size() - 1))) {
                    mesa.add(cartasUsuario.remove(carta));
                } else {
                    System.out.println("----- ¡NO PUEDES JUGAR ESA CARTA!, ¡ELIGE OTRA O ROBA! -----");
                    turnoUsuario();
                }
            }
        }
    }

    //Este método comprueba si se ha terminado el juego y quien es el ganador
    public static void comprobarJugada() {
        if (!juegoTerminado) {
            // Condiciones de finalización
            if (cartasUsuario.isEmpty() || cartasMaquina.isEmpty() || mazo.isEmpty()) {
                juegoTerminado = true; // Termina el juego

                if (cartasUsuario.size() < cartasMaquina.size()) {
                    System.out.println("\n================= ¡ENHORABUENA! ¡HAS GANADO! =================");
                } else if (cartasUsuario.size() > cartasMaquina.size()) {
                    System.out.println("\n================= ¡LO SIENTO! ¡LA MÁQUINA HA GANADO! =================");
                    System.out.println("\n******************************************");
                    System.out.println("*********** G A M E    O V E R ***********");
                    System.out.println("******************************************");
                } else {
                    System.out.println("\n================= EMPATE =================");
                }
            }
        }
    }

    //Rellena la lista de mazo con las respectivas cartas y luego las baraja.
    public static void rellenarMazo() {
        for (String c : COLORES) {
            for (int n : NUMEROS) {
                mazo.add(new Carta(n, c));
                mazo.add(new Carta(n, c));
            }
        }
        //Barajamos con el Collections.shuffle
        Collections.shuffle(mazo);
    }

    //Reparte 7 cartas a cada usuario, eliminandolas del mazo y añade una al centro de la mesa
    public static void iniciarJuego() {
        for (int i = 0; i < CARTAS_REPARTIR; i++) {
            cartasUsuario.add(mazo.remove(0));
            cartasMaquina.add(mazo.remove(0));
        }
        //Añadimos una carta al centro de la mesa
        mesa.add(mazo.remove(0));
    }

    //Muestra todas las cartas de la mesa en filas de 7
    public static void mostrarCartas() {
        List<List<String>> cartas = new ArrayList<>();
        if (!juegoTerminado) {
            //Muestro la carta centro de mesa
            System.out.println("");
            mesa.get((mesa.size() - 1)).mostrarCarta();
            //Cartas de la máquina
            System.out.println("                      MáQUINA: " + cartasMaquina.size() + " cartas.");
            System.out.println("");
            //Muestro mensaje
            System.out.println("==================== T U S   C A R T A S ====================");

            // Obtiene las representaciones de todas las cartas del usuario
            for (Carta carta : cartasUsuario) {
                cartas.add(carta.mostrarCartaUsuario());
            }

            int cartasPorFila = 7; // Número de cartas por fila

            // Itera sobre las cartas en filas de 7
            for (int inicio = 0; inicio < cartas.size(); inicio += cartasPorFila) {
                int fin = Math.min(inicio + cartasPorFila, cartas.size());
                List<List<String>> subLista = cartas.subList(inicio, fin); //SubList te devuelve una lista creada por los indices que indiques a partir de otra lista

                for (int i = 0; i < subLista.get(0).size(); i++) {
                    for (List<String> c : subLista) {
                        System.out.print(c.get(i) + "  ");
                    }
                    System.out.println(""); // Salto de línea después de imprimir todas las cartas en la misma fila
                    if (i == (subLista.get(0).size() - 1)) {
                        for (int j = inicio; j < fin; j++) {
                            System.out.print("   " + (j + 1) + "     ");
                        }
                        System.out.println("");//Salto de línea
                    }
                }
            }
        }
    }

    //Pedir un numero de carta
    public static int pedirNumeroCarta() {
        Scanner entrada = new Scanner(System.in);
        int carta;
        System.out.println("\n----------------- PULSE 0 PARA ROBAR UNA CARTA  -----------------");
        System.out.println("Por favor, elige la carta:");
        carta = entrada.nextInt() - 1;

        //Comprobamos si la carta existe si no la volvemos a pedir
        if (carta < -1 || carta > (cartasUsuario.size() + 1)) {
            System.out.println("ERROR.Introduce una carta válida.");
            carta = pedirNumeroCarta();
        }
        return carta;
    }

}
