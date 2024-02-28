package pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wmartinl01
 */
public class Entrenador {

    private String nombre;
    private List<Pokemon> lPokemon;
    private static int pociones = Constantes.NUM_POCIONES;

    //CONSTRUCTORES
    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.lPokemon = new ArrayList<>();
        rellenarPokemons();
    }

    //SETTER Y GETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLpokemon(List<Pokemon> lpokemon) {
        this.lPokemon = lpokemon;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pokemon> getLpokemon() {
        return lPokemon;
    }

    public static int getPociones() {
        return pociones;
    }

    //MÉTODOS PROPIOS
    private void rellenarPokemons() {
        int aleatorio;
        for (int i = 0; i < Constantes.MAX_POKEMON; i++) {
            aleatorio = (int) Math.round(Math.random() * (Juego.listaPokemons.size() - 1));
            lPokemon.add(Juego.listaPokemons.get(aleatorio));
            Juego.listaPokemons.remove(aleatorio);
        }
    }

    public void mostrarEntrenador() {
        List<String[]> representacion = new ArrayList<>();
        System.out.println("\n                             " + this.nombre.toUpperCase() + "      ");
        System.out.println(
                "                   ⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣠⣤⣤⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "                ⠀⠀⠀ ⠀⠀⠀⢀⣤⡶⠟⠛⠉⠉⠉⠉⠉⠙⠻⣷⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "               ⠀⠀ ⠀⠀   ⣰⡟⠁⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀\n"
                + "           ⠀⠀     ⠀⠀  ⢰⣿⠀⠀⠀⠀⣠⣴⢶⣦⠀⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\n"
                + "              ⠀ ⠀⠀⠀  ⢸⣿⠀⠀⢀⣼⣟⣁⣀⣀⣀⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\n"
                + "           ⠀⠀    ⠀⠀  ⢸⣿⠀⠀⠙⠛⠛⠛⠛⠛⠋⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\n"
                + "           ⠀⠀    ⠀  ⠀⣸⣿⣶⣶⣾⣿⣿⣿⣿⣶⣶⣤⣤⣀⣀⣸⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀\n"
                + "               ⠀⣰⣾⣿⣿⣿⡿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⡷⠆⠀⠀⠀⠀\n"
                + "                 ⠀⠈⠉⣩⣿⣿⡄⠀⠀⠀⢸⡿⠟⠋⠉⠉⠉⠀⠀⢸⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀\n"
                + "                ⠀⠀⠘⣿⣿⣿⡇⣾⠟⣷⡄⠀⠀⠀⣾⠛⣷⡀⠀⠛⠋⣿⣿⣿⣿⣿⣦⡀⠀⠀\n"
                + "                ⠀⢀⣴⣿⣿⣿⡇⣿⣦⣿⡇⠀⠀⠀⣿⣆⣿⡇⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⠗⠀\n"
                + "                ⠀⠙⠻⢿⣿⣿⠁⠛⠛⠛⠁⠀⠀⠀⠛⠛⠛⠀⠀⣤⣾⣿⣿⣿⣯⠉⠀⠀⠀⠀\n"
                + "             ⠀⠀   ⠀⠀⠈⣿⡀⠀⠀⢾⣿⣿⣿⣿⣿⠀⠀⠀⠀⢸⣿⣿⠿⠿⠛⠃⠀⠀⠀⠀\n"
                + "             ⠀⠀   ⠀⠀⠀⠙⢷⣦⣄⡈⠛⠿⠿⠟⢁⣀⣤⣴⡶⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "              ⠀⠀⠀   ⠀⠀⠀⠀⠈⠙⠻⢷⣶⣶⠾⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");

        for (Pokemon pokemon : lPokemon) {
            pokemon.mostrarRepresentacionPokemon();
        }
    }

    public void mostrarInfoPokemons() {
        for (Pokemon pokemon : lPokemon) {
            pokemon.mostrarInfoPokemon();
        }
    }

    public void gastarPocion() {
        pociones--;
    }
}
