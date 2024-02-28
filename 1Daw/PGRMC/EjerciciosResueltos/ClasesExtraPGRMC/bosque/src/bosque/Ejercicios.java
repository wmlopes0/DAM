package bosque;

public final class Ejercicios { //Si pones final estas diciendo que de esta clase nunca se van a crear objetos, es decir, es una manera de decir que esta clase es estática

    private Ejercicios() {

    }

    public static void pruebaPunteros() {
        Arbol.tronco = 2;
        final Arbol arbolCentenario = new Arbol(5);

        System.out.println("Puntero arbolCentenario: " + arbolCentenario);

        arbolCentenario.setHojas(500);

        Arbol arbolMilenario = arbolCentenario;

        System.out.println("ArbolCentenario: " + arbolCentenario.getHojas());

        System.out.println("ArbolMilenario: " + arbolMilenario.getHojas());

        System.out.println("Puntero a arbolCentenario: " + arbolCentenario);

        System.out.println("Puntero a arbolMilenario: " + arbolMilenario);

        arbolCentenario.setHojas(1000);

        System.out.println("ArbolCentenario: " + arbolCentenario.getHojas());

        System.out.println("ArbolMilenario: " + arbolMilenario.getHojas());
    }
}
