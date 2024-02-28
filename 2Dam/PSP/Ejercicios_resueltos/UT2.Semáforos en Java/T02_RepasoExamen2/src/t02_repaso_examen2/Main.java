package t02_repaso_examen2;

public class Main {
    public static void main(String[] args) {
        Pantalla pantalla = new Pantalla();

        LetraA letraA = new LetraA(pantalla);
        LetraB letraB = new LetraB(pantalla);
        LetraC letraC = new LetraC(pantalla);

        letraA.start();
        letraB.start();
        letraC.start();
    }
}
