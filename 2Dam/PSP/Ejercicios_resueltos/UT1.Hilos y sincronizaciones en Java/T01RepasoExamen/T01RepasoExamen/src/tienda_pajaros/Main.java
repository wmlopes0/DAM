package tienda_pajaros;

public class Main {
    public static void main(String[] args) {
        Comedero comedero = new Comedero(3);
        Pajaro pajaro1 = new Pajaro("Canario 0", comedero);
        Pajaro pajaro2 = new Pajaro("Canario 1", comedero);
        Pajaro pajaro3 = new Pajaro("Canario 2", comedero);
        Pajaro pajaro4 = new Pajaro("Canario 3", comedero);
        Pajaro pajaro5 = new Pajaro("Canario 4", comedero);
        Pajaro pajaro6 = new Pajaro("Canario 5", comedero);
        Pajaro pajaro7 = new Pajaro("Canario 6", comedero);
        Pajaro pajaro8 = new Pajaro("Canario 7", comedero);
        Pajaro pajaro9 = new Pajaro("Canario 8", comedero);
        Pajaro pajaro10 = new Pajaro("Canario 9", comedero);
        Encargado encargado = new Encargado(comedero);

        encargado.start();
        pajaro1.start();
        pajaro2.start();
        pajaro3.start();
        pajaro4.start();
        pajaro5.start();
        pajaro6.start();
        pajaro7.start();
        pajaro8.start();
        pajaro9.start();
        pajaro10.start();

    }
}
