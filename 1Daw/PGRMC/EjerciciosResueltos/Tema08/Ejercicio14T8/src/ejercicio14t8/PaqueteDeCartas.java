/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio14t8;

/**
 *
 * @author admin
 */
public class PaqueteDeCartas {
    private Carta[] baraja;
    private int cartaActual;

    PaqueteDeCartas() {
        String[] vValores = {"AS", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Jack", "Queen", "King"};
        String[] vPalos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        int [] vPuntos = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        this.cartaActual = 0;
        baraja = new Carta[52];
        for(int i = 0;i < vValores.length;i++){
            for(int j = 0;j < vPalos.length;j++){
                baraja[i*4+j] = new Carta(vValores[i], vPalos[j], vPuntos[i]);
            }
        }
    }
    
    PaqueteDeCartas(int cartaActual) {
        this.baraja = baraja;
        baraja = new Carta[52];
        for(int i = 0;i < baraja.length;i++){
            baraja[i] = new Carta();
        }
    }

    public Carta[] getBaraja() {
        return baraja;
    }

    public void setBaraja(Carta[] baraja) {
        this.baraja = baraja;
    }

    public int getCartaActual() {
        return cartaActual;
    }

    public void setCartaActual(int cartaActual) {
        this.cartaActual = cartaActual;
    }
    
    public void mostrarBaraja(){
        int i;
        for(i = 0;i < this.baraja.length;i++){
            System.out.println(baraja[i].getValor()+" de "+baraja[i].getPalo()+" "+baraja[i].getPuntos()+" puntos");
        }
    }
    
    public void barajar(){
        Carta aux;
        int azar;
        for(int i = 0;i < this.baraja.length;i++){
            azar = (int)(Math.random()*52); //Número aleatorio entre 0 y 51
            aux = baraja[i];
            baraja[i] = baraja[azar];
            baraja[azar] = aux;
        }
    }
    
    public Carta repartirCarta(){
        Carta repartida = baraja[this.cartaActual];
        this.cartaActual++;
        return repartida;
    }
}
