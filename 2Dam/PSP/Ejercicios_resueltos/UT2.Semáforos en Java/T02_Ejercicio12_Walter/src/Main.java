import java.util.ArrayList;
import java.util.List;

public class Main {
    //Variables globales
    private final static int PARTICIPANTES = 4;
    private static List<String> nombres = retornarNombres();
    private static List<Jugador> jugadores = new ArrayList<>();

    public static void main(String[] args) {
        //Instancio Cancha
        Cancha cancha = new Cancha(PARTICIPANTES);

        //Instancio jugadores
        nombres.forEach(nombre -> {
            jugadores.add(new Jugador(nombre, cancha));
        });

        //Inicio jugadores
        jugadores.forEach(jugador -> {
            jugador.start();
        });

        //Espero a que terminen los hilos
        try {
            for (Jugador jugador : jugadores) {
                jugador.join();
            }
        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
        }

        //CHECK
        System.out.println("PROGRAMA FINALIZADO.");
    }

    //Método que rellena la lista de nombres de persona, obviamente me he ayudado de ChatGpt no estoy tan zumbado
    public static List<String> retornarNombres() {
        List<String> nombres = new ArrayList<>();
        nombres.add("María");
        nombres.add("Juan");
        nombres.add("Ana");
        nombres.add("Carlos");
        nombres.add("Laura");
        nombres.add("José");
        nombres.add("Carmen");
        nombres.add("Luis");
        nombres.add("Sofía");
        nombres.add("David");
        nombres.add("Patricia");
        nombres.add("Daniel");
        nombres.add("Lucía");
        nombres.add("Miguel");
        nombres.add("Andrea");
        nombres.add("Fernando");
        return nombres;
    }
}
