package ejercicio18;

import java.util.Random;

public class Estanquero extends Thread{
    private final Banio banio;

    public Estanquero(Banio banio) {
        this.banio = banio;
    }

    @Override
    public void run() {
        while (true) {
            int ingredienteFaltante = new Random().nextInt(3) + 1;//Elige el ingrediente faltante aleatoriamente
            banio.ponerIngredientes(ingredienteFaltante);//Pone los ingredientes en la repisa si puede, es decir si el baño no esta ocupado y si la repisa esta a 0
        }
    }
}
