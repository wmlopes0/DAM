package bosque;

import java.util.Random;

public class Arbol {

    private int hojas;
    private int ramas;
    public static int tronco = 1;

    public Arbol(int ramas) {
        this.ramas = ramas;
        this.hojas = generateHojas(ramas);
    }

    public void setHojas(final int numHojas) {
        this.hojas = numHojas;
    }

    public int getHojas() {
        return this.hojas;
    }

    public int getRamas() {
        return this.ramas;
    }
    
    private int generateHojas(int ramas){
        Random random = new Random();
        int numHojas = random.nextInt(5000);
        return numHojas * ramas;
    }

}
