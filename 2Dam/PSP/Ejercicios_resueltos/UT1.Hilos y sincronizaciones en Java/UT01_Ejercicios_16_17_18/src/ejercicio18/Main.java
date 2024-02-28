package ejercicio18;

public class Main {
    public static void main(String[] args) {
        /*ACLARACIÓN:
         * Este programa es infinito y hay que pararlo manualmente, ya que es complicado que se pare en un momento determinado, la explicación es la siguiente:
         *
         * En el programa tenemos dos hilos distintos, los fumadores y el estanquero, si yo digo que los fumadores fumen 3 veces cada uno, y el estanquero que rellene 9 veces la repisa
         * no tengo la seguridad de que consigan fumar todos 3 veces ya que el estanquero rellena la repisa aleatoriamente como dice en el enunciado.
         *
         * La unica manera de solucionar esto sería hacer un bucle infinito en el estanquero como está, y controlar que si no se cogen los ingredientes en un periodo de tiempo
         * forzar la muerte del hilo del estanquero para que el programa finalice.
         * */

        //Baño
        Banio banio = new Banio();
        //Estanquero
        Estanquero estanquero = new Estanquero(banio);
        //Fumetas
        Fumador fumador1 = new Fumador(1, banio);
        Fumador fumador2 = new Fumador(2, banio);
        Fumador fumador3 = new Fumador(3, banio);

        //Inicio los hilos
        estanquero.start();
        fumador1.start();
        fumador2.start();
        fumador3.start();
    }
}
