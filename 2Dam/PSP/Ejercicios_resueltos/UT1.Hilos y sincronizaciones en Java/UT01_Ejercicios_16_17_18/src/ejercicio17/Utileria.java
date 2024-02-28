package ejercicio17;

import java.util.Random;

public class Utileria {

    //        Este método retorna una entero aleatorio entre el inicio y el fin que le pasamos por parámetro
    public static int tiempoAleatorio(int inicio, int fin) {
        return new Random().nextInt((fin + 1) - inicio) + inicio;
    }
}
