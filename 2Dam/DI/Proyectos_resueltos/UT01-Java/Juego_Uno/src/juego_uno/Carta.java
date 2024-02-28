package juego_uno;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wmartinl01
 */
public class Carta {

    private int numero;
    private String color;

    //CONSTRUCTORES
    public Carta() {
        numero = 0;
        color = "";
    }

    public Carta(int numero, String color) {
        this.numero = numero;
        this.color = color;
    }

    //SETTER Y GETTER
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public String getColor() {
        return color;
    }

    //MÉTODOS PROPIOS
    //Este método nos comprobará si la carta es jugable con la carta del centro de la mesa
    public boolean valida(Carta cartaMesa) {
        return this.color.equals(cartaMesa.getColor()) || this.numero == cartaMesa.getNumero();
    }

    public void mostrarCarta() {
        switch (color) {
            case "ROJO":
                System.out.println("                      ---- M E S A -----");
                System.out.println(Juego.negro + "                     |                  |");
                System.out.println(Juego.negro + "                     |" + Juego.rojo + "      -----       " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.rojo + "     |'''''|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.rojo + "     |' " + Juego.negro + numero + Juego.rojo + " '|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.rojo + "     |'''''|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.rojo + "      -----       " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |                  |");
                System.out.println(Juego.negro + "                      ------------------");
                break;
            case "AZUL":
                System.out.println("                      ---- M E S A -----");
                System.out.println(Juego.negro + "                     |                  |");
                System.out.println(Juego.negro + "                     |" + Juego.azul + "      -----       " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.azul + "     |'''''|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.azul + "     |' " + Juego.negro + numero + Juego.azul + " '|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.azul + "     |'''''|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.azul + "      -----       " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |                  |");
                System.out.println(Juego.negro + "                      ------------------");
                break;
            case "AMARILLO":
                System.out.println("                      ---- M E S A -----");
                System.out.println(Juego.negro + "                     |                  |");
                System.out.println(Juego.negro + "                     |" + Juego.amarillo + "      -----       " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.amarillo + "     |'''''|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.amarillo + "     |' " + Juego.negro + numero + Juego.amarillo + " '|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.amarillo + "     |'''''|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.amarillo + "      -----       " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |                  |");
                System.out.println(Juego.negro + "                      ------------------");
                break;
            case "VERDE":
                System.out.println("                      ---- M E S A -----");
                System.out.println(Juego.negro + "                     |                  |");
                System.out.println(Juego.negro + "                     |" + Juego.verde + "      -----       " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.verde + "     |'''''|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.verde + "     |' " + Juego.negro + numero + Juego.verde + " '|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.verde + "     |'''''|      " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |" + Juego.verde + "      -----       " + Juego.negro + "|");
                System.out.println(Juego.negro + "                     |                  |");
                System.out.println(Juego.negro + "                      ------------------");
                break;
            default:
                throw new AssertionError();
        }
    }

    public List<String> mostrarCartaUsuario() {
        List<String> cartas = new ArrayList<>();
        switch (color) {
            case "ROJO":
                cartas.add(Juego.rojo + " ----- ");
                cartas.add(Juego.rojo + "|'''''|");
                cartas.add(Juego.rojo + "|' " + Juego.negro + numero + Juego.rojo + " '|");
                cartas.add(Juego.rojo + "|'''''|");
                cartas.add(Juego.rojo + " ----- ");
                break;
            case "AZUL":
                cartas.add(Juego.azul + " ----- ");
                cartas.add(Juego.azul + "|'''''|");
                cartas.add(Juego.azul + "|' " + Juego.negro + numero + Juego.azul + " '|");
                cartas.add(Juego.azul + "|'''''|");
                cartas.add(Juego.azul + " ----- ");
                break;
            case "AMARILLO":
                cartas.add(Juego.amarillo + " ----- ");
                cartas.add(Juego.amarillo + "|'''''|");
                cartas.add(Juego.amarillo + "|' " + Juego.negro + numero + Juego.amarillo + " '|");
                cartas.add(Juego.amarillo + "|'''''|");
                cartas.add(Juego.amarillo + " ----- ");
                break;
            case "VERDE":
                cartas.add(Juego.verde + " ----- ");
                cartas.add(Juego.verde + "|'''''|");
                cartas.add(Juego.verde + "|' " + Juego.negro + numero + Juego.verde + " '|");
                cartas.add(Juego.verde + "|'''''|");
                cartas.add(Juego.verde + " ----- ");
                break;
            default:
                throw new AssertionError();
        }
        return cartas;
    }
}
