package metodosPrincipalesThread;

import java.util.Random;

public class Ejemplo6_Random {
    public static void main(String[] args) {
        for (int i = 0; i < 23; i++) {
            System.out.println(getAleatorio(1,10));
        }
    }

    public static int getAleatorio(int inicio, int fin){
        return new Random().nextInt((fin+1)-inicio)+inicio;
    }
}
