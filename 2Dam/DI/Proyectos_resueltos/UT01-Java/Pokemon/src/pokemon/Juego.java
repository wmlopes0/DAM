package pokemon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static pokemon.Constantes.*;

/**
 *
 * @author wmartinl01
 */
public class Juego {

    //CREO LAS LISTAS DONDE SE ALMACENARÁN TODOS LOS OBJETOS CREADOS A PARTIR DE LA CARGA DE DATOS
    public static List<Entrenador> listaEntrenadores = new ArrayList<>();
    public static List<Pokemon> listaPokemons = new ArrayList<>();
    public static List<Ataque> listaAtaques = new ArrayList<>();
    public static Entrenador usuario;
    public static Pokemon pokemonUsuario;
    public static Entrenador maquina;
    public static Pokemon pokemonMaquina;
    public static boolean finJuego = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        //Cargo los datos del fichero
        cargarDatos();
        //Muestro los entrenadores disponibles,el usuario elige uno y lo elimino de la lista de entrenadores.
        usuario = listaEntrenadores.get(mostrarInicio());
        listaEntrenadores.remove(usuario);
        //Asigno aleatoriamente un entrenador a la máquina
        maquina = listaEntrenadores.get((int) Math.round(Math.random() * (listaEntrenadores.size() - 1)));
        listaEntrenadores.remove(maquina);
        mostrarInfoEntrenadoresElegidos();
        //Inicio el juego
        while (!finJuego) {
            asignarPokemons();
            turnoUsuario();
            comprobarFinJuego();
            actualizarEstadoPokemons();
            turnoMaquina();
            actualizarEstadoPokemons();
        }
    }

    //Realiza todo lo necesario del turno usuario
    public static void turnoUsuario() throws InterruptedException {
        int ataque;
        if (!finJuego) {
            limpiarPantalla();//Método que limpia la consola
            System.out.println("\nESTÁS JUGANDO CON EL POKEMON " + pokemonUsuario.getNombre());
            pokemonUsuario.mostrarInfoPokemon();
            //Pedimos el ataque al usuario y atacamos
            ataque = pedirAtaque("\nIntroduce el ataque que deseas, pulsa 0 para gastar una poción: ");
            //Si elige tomar una pocion y tiene pociones gasta el turno curando el pokemon sino elige ataque y hace un daño dependiendo del estado del pokemon y el tipo de pokemon contrincante
            if (ataque == -1) {
                if (usuario.getPociones() > 0) {
                    pokemonUsuario.setVida(VIDA_MAX);
                    usuario.gastarPocion();
                    System.out.println("¡HAS USADO UNA POCION Y AHORA LA VIDA DE " + pokemonUsuario.getNombre() + " ES DE 100!");
                    System.out.println("¡LE TOCA EL TURNO A LA MÁQUINA!");
                } else {
                    System.out.println("¡NO TIENES POCIONES!");
                    ataque = pedirAtaque("\nIntroduce el ataque que deseas: ");
                    Ataque ataqueElegido = pokemonUsuario.getLataques().get(ataque);
                    if (pokemonMaquina.getEstado().equalsIgnoreCase("NORMAL")) {
                        pokemonMaquina.setVida(pokemonMaquina.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoNormal(), true));
                        Thread.sleep(1000);
                        System.out.println("\n¡HAS ATACADO A " + pokemonMaquina.getNombre() + " con " + ataqueElegido.getNombre() + "!");
                        Thread.sleep(1000);
                        System.out.println("\n¡LE HAS INFRINGIDO " + ataqueElegido.getPuntosDeDanoNormal() + " PUNTOS DE DAÑO!");
                        Thread.sleep(2000);
                    } else {
                        if (pokemonMaquina.getEstado().equalsIgnoreCase("VULNERABLE")) {
                            pokemonMaquina.setVida(pokemonMaquina.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoVulnerable(), true));
                            Thread.sleep(1000);
                            System.out.println("\n¡HAS ATACADO A " + pokemonMaquina.getNombre() + " con " + ataqueElegido.getNombre() + "!");
                            Thread.sleep(1000);
                            System.out.println("\n¡LE HAS INFRINGIDO " + ataqueElegido.getPuntosDeDanoVulnerable() + " PUNTOS DE DAÑO!");
                            Thread.sleep(2000);
                        } else {
                            pokemonMaquina.setVida(pokemonMaquina.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoInofensivo(), true));
                            Thread.sleep(1000);
                            System.out.println("\n¡HAS ATACADO A " + pokemonMaquina.getNombre() + " con " + ataqueElegido.getNombre() + "!");
                            Thread.sleep(1000);
                            System.out.println("\n¡LE HAS INFRINGIDO " + ataqueElegido.getPuntosDeDanoInofensivo() + " PUNTOS DE DAÑO!");
                            Thread.sleep(2000);
                        }
                    }
                    if (pokemonMaquina.getVida() > 0) {
                        System.out.println("\nAHORA SU VIDA ES DE " + pokemonMaquina.getVida());
                        Thread.sleep(2000);
                    } else {
                        System.out.println("\n¡HAS MATADO A !" + pokemonMaquina.getNombre() + "!");
                        Thread.sleep(2000);
                    }
                }

            } else {
                Ataque ataqueElegido = pokemonUsuario.getLataques().get(ataque);
                if (pokemonMaquina.getEstado().equalsIgnoreCase("NORMAL")) {
                    pokemonMaquina.setVida(pokemonMaquina.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoNormal(), true));
                    Thread.sleep(1000);
                    System.out.println("\n¡HAS ATACADO A " + pokemonMaquina.getNombre() + " con " + ataqueElegido.getNombre() + "!");
                    Thread.sleep(1000);
                    System.out.println("\n¡LE HAS INFRINGIDO " + ataqueElegido.getPuntosDeDanoNormal() + " PUNTOS DE DAÑO!");
                    Thread.sleep(2000);
                } else {
                    if (pokemonMaquina.getEstado().equalsIgnoreCase("VULNERABLE")) {
                        pokemonMaquina.setVida(pokemonMaquina.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoVulnerable(), true));
                        Thread.sleep(1000);
                        System.out.println("\n¡HAS ATACADO A " + pokemonMaquina.getNombre() + " con " + ataqueElegido.getNombre() + "!");
                        Thread.sleep(1000);
                        System.out.println("\n¡LE HAS INFRINGIDO " + ataqueElegido.getPuntosDeDanoVulnerable() + " PUNTOS DE DAÑO!");
                        Thread.sleep(2000);
                    } else {
                        pokemonMaquina.setVida(pokemonMaquina.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoInofensivo(), true));
                        Thread.sleep(1000);
                        System.out.println("\n¡HAS ATACADO A " + pokemonMaquina.getNombre() + " con " + ataqueElegido.getNombre() + "!");
                        Thread.sleep(1000);
                        System.out.println("\n¡LE HAS INFRINGIDO " + ataqueElegido.getPuntosDeDanoInofensivo() + " PUNTOS DE DAÑO!");
                        Thread.sleep(2000);
                    }
                }
                if (pokemonMaquina.getVida() > 0) {
                    System.out.println("\nAHORA SU VIDA ES DE " + pokemonMaquina.getVida());
                    Thread.sleep(2000);
                } else {
                    System.out.println("\n¡HAS MATADO A !" + pokemonMaquina.getNombre() + "!");
                    Thread.sleep(2000);
                }
            }
        }
    }

    //Muestra los nombres de los entrenadores elegidos
    public static void mostrarInfoEntrenadoresElegidos() throws InterruptedException {
        System.out.println("¡HAS ELEGIDO A " + usuario.getNombre() + "!");
        Thread.sleep(1000);
        System.out.println("LA MÁQUINA HA ELEGIDO AL ENTRENADOR " + maquina.getNombre());
        Thread.sleep(1000);
    }

    //Realiza todo lo necesario del turno maquina
    public static void turnoMaquina() throws InterruptedException {
        if (!finJuego) {
            Thread.sleep(1000);
            //Elige el ataque aleatoriamente y ataca
            Ataque ataqueElegido = pokemonMaquina.getLataques().get((int) Math.round(Math.random() * (pokemonMaquina.getLataques().size())));
            if (pokemonUsuario.getEstado().equalsIgnoreCase("NORMAL")) {
                pokemonUsuario.setVida(pokemonUsuario.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoNormal(), false));
                Thread.sleep(1000);
                System.out.println("\n¡TE HA ATACADO CON " + ataqueElegido.getNombre() + "!");
                Thread.sleep(1000);
                System.out.println("\n¡TE HA INFRINGIDO " + ataqueElegido.getPuntosDeDanoNormal() + " PUNTOS DE DAÑO!");
                Thread.sleep(2000);
            } else {
                if (pokemonUsuario.getEstado().equalsIgnoreCase("VULNERABLE")) {
                    pokemonUsuario.setVida(pokemonUsuario.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoVulnerable(), false));
                    Thread.sleep(1000);
                    System.out.println("\n¡TE HA ATACADO CON " + ataqueElegido.getNombre() + "!");
                    Thread.sleep(1000);
                    System.out.println("\n¡TE HA INFRINGIDO " + ataqueElegido.getPuntosDeDanoVulnerable() + " PUNTOS DE DAÑO!");
                    Thread.sleep(2000);
                } else {
                    pokemonUsuario.setVida(pokemonUsuario.getVida() - danoSegunTipoPokemon(ataqueElegido.getPuntosDeDanoInofensivo(), false));
                    Thread.sleep(1000);
                    System.out.println("\n¡TE HA ATACADO CON " + ataqueElegido.getNombre() + "!");
                    Thread.sleep(1000);
                    System.out.println("\n¡TE HA INFRINGIDO " + ataqueElegido.getPuntosDeDanoInofensivo() + " PUNTOS DE DAÑO!");
                    Thread.sleep(2000);
                }
            }
            if (pokemonUsuario.getVida() > 0) {
                System.out.println("\nAHORA TU VIDA ES DE " + pokemonUsuario.getVida());
                Thread.sleep(2000);
            } else {
                System.out.println("\n¡LA MAQUINA HA MATADO A !" + pokemonUsuario.getNombre() + "!");
                Thread.sleep(2000);
            }
        }
    }

    //Método para asignar los pokemon, el usuario lo elige y la maquina lo asigna de manera ordenada, usa el mismo hasta que se le acabe la vida
    public static void asignarPokemons() throws InterruptedException {
        Thread.sleep(1000);
        //Me recorro la lista de pokemon de la máquina para dar valor a la variable pokemonMaquina
        for (Pokemon p : maquina.getLpokemon()) {
            if (p.getVida() > 0) {
                pokemonMaquina = p;
            }
        }
        //Muestro info
        System.out.println("\n===============================================================================");
        Thread.sleep(200);
        System.out.println("LA MÁQUINA A ELEGIDO EL POKEMON " + pokemonMaquina.getNombre() + " de tipo " + pokemonMaquina.getTipo() + " y su estado es " + pokemonMaquina.getEstado());
        Thread.sleep(200);
        pokemonMaquina.mostrarRepresentacionPokemon();
        Thread.sleep(200);
        System.out.println("===============================================================================\n");
        Thread.sleep(3000);

        //Me recorro la lista de pokemon del usuario para dar valor a la variable pokemonUsuario
        System.out.println("ELIGE UN POKEMON:");
        for (int i = 0; i < usuario.getLpokemon().size(); i++) {
            System.out.println("PULSA " + (i + 1) + " para elegir a " + usuario.getLpokemon().get(i).getNombre());
            System.out.println("Tipo: " + usuario.getLpokemon().get(i).getTipo());
            System.out.println("Estado: " + usuario.getLpokemon().get(i).getEstado());
            usuario.getLpokemon().get(i).mostrarRepresentacionPokemon();
        }
        pokemonUsuario = usuario.getLpokemon().get(elegirPokemonUsuario());
    }

    //Método que pide y retorna el pokemon elegido por el usuario controlando excepciones como la vida, fuera de rango y InputMismatchException
    public static int elegirPokemonUsuario() {
        Scanner s = new Scanner(System.in);
        int num;
        try {
            System.out.println("INTRODUCE UNA OPCION:");
            num = s.nextInt() - 1;
            usuario.getLpokemon().get(num);
        } catch (InputMismatchException e) {
            System.out.println("ERROR.NO PUEDES INTRODUCIR UNA LETRA");
            num = elegirPokemonUsuario();
        } catch (IndexOutOfBoundsException n) {
            System.out.println("ERROR.INTRODUCE UNA POSICION VÁLIDA");
            num = elegirPokemonUsuario();
        }
        if (usuario.getLpokemon().get(num).getVida() <= 0) {
            System.out.println("EL POKEMON ELEGIDO NO TIENE VIDA, ELIGE OTRO");
            num = elegirPokemonUsuario();
        }
        return num;
    }

    //Método que actualiza el estado del pokemon segun la vida, se actualiza despues de cada turno
    public static void actualizarEstadoPokemons() {
        //Actualizo el estado del pokemon de la maquina según su vida
        if (pokemonMaquina.getVida() > Constantes.ESTADO_NORMAL) {
            pokemonMaquina.setEstado("NORMAL");
        } else {
            if (pokemonMaquina.getVida() > Constantes.ESTADO_VULNERABLE) {
                pokemonMaquina.setEstado("VULNERABLE");
            } else {
                pokemonMaquina.setEstado("INOFENSIVO");
            }
        }
        //Actualizo el estado del pokemon del usuario según su vida
        if (pokemonUsuario.getVida() > Constantes.ESTADO_NORMAL) {
            pokemonUsuario.setEstado("NORMAL");
        } else {
            if (pokemonUsuario.getVida() > Constantes.ESTADO_VULNERABLE) {
                pokemonUsuario.setEstado("VULNERABLE");
            } else {
                pokemonUsuario.setEstado("INOFENSIVO");
            }
        }

    }

    //Este metodo recibe el daño teórico y retorna el daño corregido segun el tipo de pokemon al que esté atacando
    public static int danoSegunTipoPokemon(int danio, boolean usuario) {
        int fila = 0;
        int columna = 0;
        int danioCorregido;
        double[][] danios
                = {{1, 1, 0.9, 1, 1}, // TIERRA
                {1.2, 1, 0.8, 0.9, 1}, // AGUA
                {1, 1.2, 1, 1.2, 1}, // FUEGO
                {0.9, 1.2, 0.8, 1, 1}, // PLANTA
                {1, 1, 1, 1, 1}}; // NORMAL

        if (usuario) {
            //Busco el incide del tipo del pokemon del usuario y de la maquina para ver que multiplicador se tiene que aplicar
            for (int i = 0; i < Constantes.TIPO.length; i++) {
                if (pokemonUsuario.getTipo().equalsIgnoreCase(Constantes.TIPO[i])) {
                    fila = i;
                }
                if (pokemonMaquina.getTipo().equalsIgnoreCase(Constantes.TIPO[i])) {
                    columna = i;
                }
            }
            danioCorregido = (int) (danio * danios[fila][columna]);
        } else {
            for (int i = 0; i < Constantes.TIPO.length; i++) {
                if (pokemonMaquina.getTipo().equalsIgnoreCase(Constantes.TIPO[i])) {
                    fila = i;
                }
                if (pokemonUsuario.getTipo().equalsIgnoreCase(Constantes.TIPO[i])) {
                    columna = i;
                }
            }
            danioCorregido = (int) (danio * danios[fila][columna]);
        }

        return danioCorregido;
    }

    //Este método muestra los entrenadores y devuelve el entrenador que ha elegido el usuario
    public static int mostrarInicio() throws InterruptedException {
        System.out.println(" -----------------------------------------------------------------------");
        Thread.sleep(200);
        System.out.println("|                                   ,'\\                                 |");
        Thread.sleep(200);
        System.out.println("|      _.----.        ____         ,'  _\\   ___    ___     ____         |");
        Thread.sleep(200);
        System.out.println("|  _,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.	|");
        Thread.sleep(200);
        System.out.println("|  \\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |	|");
        Thread.sleep(200);
        System.out.println("|   \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |	|");
        Thread.sleep(200);
        System.out.println("|     \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |	|");
        Thread.sleep(200);
        System.out.println("|      \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |	|");
        Thread.sleep(200);
        System.out.println("|       \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |	|");
        Thread.sleep(200);
        System.out.println("|        \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |	|");
        Thread.sleep(200);
        System.out.println("|         \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |	|");
        Thread.sleep(200);
        System.out.println("|          \\_.-'       |__|    `-._ |              '-.|     '-.| |   |	|");
        Thread.sleep(200);
        System.out.println("|                                  `'                            '-._|	|");
        Thread.sleep(200);
        System.out.println(" -----------------------------------------------------------------------");
        Thread.sleep(400);
        System.out.println("\n            E L I G E    A    T U    E N T R E N A D O R \n");
        Thread.sleep(3000);
        mostrarEntrenadores();
        System.out.println("=======================================================================");
        int opcion = pedirEntero("INTRODUCE UNA OPCION:");
        while (opcion > listaEntrenadores.size() || opcion < 0) {
            System.out.println("ERROR, ELIGE UNA OPCION VALIDA.");
            opcion = pedirEntero("INTRODUCE UNA OPCION:");
        }
        return opcion;
    }

    //Limpia la pantalla
    public static void limpiarPantalla() {
        for (int i = 0; i < 60; i++) {
            System.out.println("");
        }
    }

    public static void mostrarEntrenadores() throws InterruptedException {
        for (int i = 0; i < listaEntrenadores.size(); i++) {
            System.out.println("============================ O P C I O N  " + (i + 1) + " ============================");
            Thread.sleep(100);
            listaEntrenadores.get(i).mostrarEntrenador();
            Thread.sleep(100);
        }

    }

    public static int pedirEntero(String texto) {
        Scanner s = new Scanner(System.in);
        int opcion;
        try {
            System.out.println(texto);
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR.NO PUEDES INTRODUCIR UNA LETRA.");
            opcion = pedirEntero(texto);
        }
        return opcion - 1;
    }

    public static int pedirAtaque(String texto) {
        Scanner s = new Scanner(System.in);
        int opcion;
        try {
            System.out.println(texto);
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR.NO PUEDES INTRODUCIR UNA LETRA.");
            opcion = pedirEntero(texto);
        }
        if (opcion < 0 || opcion > 4) {
            System.out.println("ERROR.INTRODUCE UN ATAQUE VALIDO.");
            opcion = pedirEntero(texto);
        }
        return opcion - 1;
    }

    //Método que se encarga de cargar los datos desde un único fichero de texto
    public static void cargarDatos() throws FileNotFoundException, IOException {
        String linea;
        String[] objeto;

        //ABRO FLUJOS
        BufferedReader br = new BufferedReader(new FileReader(FICHERO));

        //Leo la primera línea
        linea = br.readLine();

        while (linea != null) {
            //Separo los datos por , y los introduzco en un vector
            objeto = linea.split(",");
            //Mando el vector al método que se encarga de crear el objeto e introducirlo en la respectiva lista
            crearObjeto(objeto);
            //Vuelvo a leer otra línea
            linea = br.readLine();
        }

        //CIERRO FLUJO
        br.close();
    }

    //Método que recibe un string de cada linea del fichero y crea el objeto correspondiente
    public static void crearObjeto(String[] objeto) {
        switch (objeto[0]) {
            case "ATAQUE":
                //Creo el objeto ataque
                var ataque = new Ataque(objeto[1], objeto[2], Integer.parseInt(objeto[3]), Integer.parseInt(objeto[4]), Integer.parseInt(objeto[5]));
                //Introduzco el objeto ataque en la lista
                listaAtaques.add(ataque);
                break;

            case "POKEMON":
                //Creo el objeto pokemon
                Pokemon pokemon = new Pokemon(objeto[1], objeto[3], objeto[4]);
                //Introduzco el objeto pokemon a la lista
                listaPokemons.add(pokemon);
                break;
            case "ENTRENADOR":
                //Creo el objeto entrenador
                Entrenador entrenador = new Entrenador(objeto[1]);
                //Introduzco el objeto entrenador a la lista
                listaEntrenadores.add(entrenador);
                break;
            default:
                throw new AssertionError();
        }
    }

    //Comprueba si el juego ha llegado a su fin comprobando si alguno de los dos se ha quedado sin pokemons con vida
    public static void comprobarFinJuego() {
        boolean vidaUsuario = false;
        boolean vidaMaquina = false;
        for (Pokemon pokemon : usuario.getLpokemon()) {
            if (pokemon.getVida() > 0) {
                vidaUsuario = true;
            }
        }
        for (Pokemon pokemon : maquina.getLpokemon()) {
            if (pokemon.getVida() > 0) {
                vidaMaquina = true;
            }
        }

        if (vidaUsuario && !vidaMaquina) {
            finJuego = true;
            System.out.println("\n******************************************************");
            System.out.println("************** ¡E N H O R A B U E N A! ***************");
            System.out.println("*************** ¡H A S   G A N A D O! ****************");
            System.out.println("******************************************************");
        } else {
            if (vidaMaquina && !vidaUsuario) {
                finJuego = true;
                System.out.println("\n******************************************");
                System.out.println("*********** G A M E    O V E R ***********");
                System.out.println("******************************************");
            }
        }
    }
}
