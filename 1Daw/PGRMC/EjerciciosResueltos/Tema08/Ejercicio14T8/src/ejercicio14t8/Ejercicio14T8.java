/*
 * Esta clase sería equivalente la clase juego
 */
package ejercicio14t8;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Ejercicio14T8 {

    public static int turnoJugador(PaqueteDeCartas P, int sumaUsuario){
        Scanner teclado = new Scanner(System.in);
        Scanner tecladoInt = new Scanner(System.in);
        String respuesta, stringJugador = "Carta del jugador: ";
        Carta cartaUsuario;
        int valorAs;
        boolean fin;

        System.out.println(" -- TURNO DEL JUGADOR -- ");
        System.out.print("Suma "+sumaUsuario+" puntos ¿Desea otra carta más? ");
        respuesta = teclado.nextLine();
        fin = false;
        while(respuesta.equalsIgnoreCase("si")&&(!fin)){
            //Repartimos carta para el usuario
            cartaUsuario = P.repartirCarta();
            if  (cartaUsuario.getValor().equalsIgnoreCase("AS")){
                System.out.print("¿Prefieres 1 u 11? ");
                valorAs = tecladoInt.nextInt();
                /* Se podría mejorar forzando que valorAs fuese 1 u 11 */
                cartaUsuario.setPuntos(valorAs);
            }
            sumaUsuario += cartaUsuario.getPuntos();
            cartaUsuario.mostrarCarta(stringJugador);
            if  (sumaUsuario < 21){
                System.out.print("Suma "+sumaUsuario+" puntos ¿Desea otra carta más? ");
                respuesta = teclado.nextLine();
            }
            else{
                fin = true;
            }
        }
        return sumaUsuario;
   
    }

    public static int turnoPC(PaqueteDeCartas P, int sumaPC, int sumaUsuario){
        String stringPC = "Carta del PC: ";
        boolean fin = false;
        Carta cartaPC;
        System.out.println(" -- TURNO DEL PC -- ");
        System.out.print("Suma "+sumaPC+" puntos ");
        while((sumaPC < sumaUsuario)&&(!fin)){
            //Repartimos carta para la máquina
            cartaPC = P.repartirCarta();
            if  ((sumaPC + cartaPC.getPuntos()) <= 21){
                sumaPC += cartaPC.getPuntos();
                cartaPC.mostrarCarta(stringPC);
                System.out.print("Suma "+sumaPC+" puntos ");
            }
            else{
                fin = true;
            }
        }
        return sumaPC;
    }
    
    public static void mostrarGanador(int sumaUsuario, int sumaPC){
        if  (sumaUsuario > 21){
            System.out.print("Suma "+sumaUsuario+" puntos ¡PC GANA! ");
        }
        else{
            if  (sumaUsuario == 21){
                System.out.print("Suma "+sumaUsuario+" puntos ¡¡BLACKJACK!! ");
            }
            else{
                if  (sumaPC > sumaUsuario){
                    System.out.println("-- PC GANA --");
                }
                else{
                    System.out.println("-- ¡¡GANASTE!! --");
                }
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String respuesta, stringJugador = "Carta del jugador: ", stringPC = "Carta del PC: ";
        Scanner teclado = new Scanner(System.in);
        PaqueteDeCartas P = new PaqueteDeCartas();
        Carta cartaUsuario, cartaPC;
        int sumaUsuario, sumaPC;
        boolean fin;
        
        /* Barajamos las cartas */
        System.out.println("Espere mientras barajamos...");
        P.barajar();
        
        /* Repartimos las dos primeras cartas y actualizamos las sumas */
        cartaUsuario = P.repartirCarta();
        sumaUsuario = cartaUsuario.getPuntos();
        cartaUsuario.mostrarCarta(stringJugador);
        cartaPC = P.repartirCarta();
        cartaPC.mostrarCarta(stringPC);
        sumaPC = cartaPC.getPuntos();
        
        sumaUsuario= turnoJugador(P, sumaUsuario);
        if  (sumaUsuario < 21){  //Si no se ha pasado el usuario de 21 ni ha hecho BlackJack, turno de la máquina
            sumaPC= turnoPC(P, sumaPC, sumaUsuario);
        }
        mostrarGanador(sumaUsuario, sumaPC);
    }
}
