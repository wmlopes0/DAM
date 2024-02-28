package t02_ejercicio10_walter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    //Variables globales
    final static int CAPACIDAD = 200;
    final static int CANTIDAD_TUBERIAS_METER = 10;
    final static int CANTIDAD_TUBERIAS_SACAR = 10;
    private static List<Thread> tuberias = new ArrayList<>();


    public static void main(String[] args) {
        //Instancio Deposito
        Deposito deposito = new Deposito(CAPACIDAD);

        //Instancio tuberías meter
        for (int i = 0; i < CANTIDAD_TUBERIAS_METER; i++) {
            tuberias.add(new TuberiaMeterAgua("M-" + i, deposito));
        }

        //Instancio tuberías sacar
        for (int i = 0; i < CANTIDAD_TUBERIAS_SACAR; i++) {
            tuberias.add(new TuberiaSacarAgua("S-" + i, deposito));
        }

        //Barajo las tuberias y las inicio
        Collections.shuffle(tuberias);
        tuberias.forEach(tuberia -> {
            tuberia.start();
        });

    }
}
