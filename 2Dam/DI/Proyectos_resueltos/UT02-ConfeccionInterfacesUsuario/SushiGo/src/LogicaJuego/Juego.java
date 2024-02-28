package LogicaJuego;

import InterfazGrafica.VentanaJuego;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wmartinl01
 */
//Aquí se implementará toda la lógica principal del juego
public class Juego {

    //Variables globales
    private VentanaJuego ventanaJuego;
    public Jugador usuario;
    public Jugador cpu1;
    public Jugador cpu2;

    //Atributos
    private Mazo mazo;
    private List<Jugador> jugadores;
    private int ronda;

    //Constructor
    public Juego(String nombreUsuario) {
        this.mazo = new Mazo();
        this.jugadores = new ArrayList<>();
        crearJugadorUsuario(nombreUsuario);//Creo el jugador usuario
        crearJugadoresCPU();//Creo los jugadores CPU
        this.ronda = 1;
    }

    //Getter y Setter
    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    //MÉTODOS PROPIOS
    //Método que 'comunica' la logica del juego con la ventana del juego para tener acceso a los métodos de VentanaJuego
    public void comunicarVentanaJuego(VentanaJuego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
    }

    //Método que crea el jugador usuario y lo añade a la lista de jugadores
    private void crearJugadorUsuario(String nombre) {
        usuario = new Jugador(nombre, mazo.repartirCartas(Constantes.NUM_CARTAS), 0, 0, "USER");
        jugadores.add(usuario);
    }

    //Método que crea los jugadores CPU y los añade a la lista de jugadores
    private void crearJugadoresCPU() {
        cpu1 = new Jugador(Constantes.CPU1, mazo.repartirCartas(Constantes.NUM_CARTAS), 0, 0, "CPU");
        cpu2 = new Jugador(Constantes.CPU2, mazo.repartirCartas(Constantes.NUM_CARTAS), 0, 0, "CPU");
        jugadores.add(cpu1);
        jugadores.add(cpu2);
    }

    //Método que se ejecuta desde el ActionListener implementado para los botones carta.
    public void jugarTurnoUsuario(int cartaSeleccionada) {
        //Muevo la carta seleccionada a mano
        usuario.getMano().add(usuario.getCartasVisibles().remove(cartaSeleccionada));
        //Paso el turno a los jugadores CPU
        jugarTurnoCPU();
    }

    //Método que contiene la lógica del turno de la CPU
    private void jugarTurnoCPU() {
        //Muevo la carta seleccionada a mano
        cpu1.getMano().add(cpu1.getCartasVisibles().remove(0));
        cpu2.getMano().add(cpu2.getCartasVisibles().remove(0));
        //Terminar turno
        terminarTurno();
    }

    //Este método termina el turno
    private void terminarTurno() {
        //Compruebo si hay cartas todavía
        if (noHayCartas()) {
            //Actualizo cartas
            ventanaJuego.actualizarCartas();
            //Finalizo la ronda
            finalizarRonda();
        } else {
            //Cambio las cartas con el jugador de la izquierda
            intercambiarCartasJugadores();
            //Actualizo cartas
            ventanaJuego.actualizarCartas();
        }
    }

    //Método que comprueba si todavía hay cartas que jugar en la ronda
    private boolean noHayCartas() {
        return usuario.getCartasVisibles().isEmpty() && cpu1.getCartasVisibles().isEmpty() && cpu2.getCartasVisibles().isEmpty();
    }

    //Método que intercambia las cartas entre los jugadores.
    public void intercambiarCartasJugadores() {
        //Introduzco las cartas de usuario en una lista auxiliar
        List<Carta> usuarioAux = new ArrayList<>(usuario.getCartasVisibles());
        //Limpio la lista de cartas de usuario e introduzco las cartas de Cpu1
        usuario.getCartasVisibles().clear();
        usuario.getCartasVisibles().addAll(cpu1.getCartasVisibles());
        //Limpio la lista de cartas de cpu1 e introduzco las cartas de Cpu2
        cpu1.getCartasVisibles().clear();
        cpu1.getCartasVisibles().addAll(cpu2.getCartasVisibles());
        //Por ultimo limpio las cartas de cpu2 e introduzco las de usuario que previamente había almacenado en la lista auxiliar
        cpu2.getCartasVisibles().clear();
        cpu2.getCartasVisibles().addAll(usuarioAux);
    }

    //Método que finaliza la ronda, comprueba si el juego se ha acabado, sino actualiza las puntuaciones y reparte cartas nuevas.
    private void finalizarRonda() {
        if (finJuego()) {
            actualizarPuntuacionRonda();//Actualizo puntuaciones y puddings
            limpiarManos();//Limpio las listas mano de cada jugador
            ventanaJuego.actualizarInfo();//Actualizo información
            ventanaJuego.actualizarCartas();//Actualizo
            ventanaJuego.finalizarJuego();//Finalizo el juego
        } else {
            actualizarPuntuacionRonda();//Actualizo puntuaciones y puddings
            limpiarManos();//Limpio las listas mano de cada jugador
            repartirCartasNuevas();//Reparto cartas nuevas
            ronda++;//Incremento ronda
            ventanaJuego.actualizarInfo();//Actualizo información
        }
    }

    //Este método comprueba si el juego ha terminado, es decir, si las 3 rondas se han completado.
    private boolean finJuego() {
        return ronda == Constantes.NUM_RONDAS;
    }

    //Este método limpia las listas donde se almacena las cartas elegidas despues de finalizar la ronda y actualizar la puntuación
    private void limpiarManos() {
        //Limpio las listas mano
        usuario.getMano().clear();
        cpu1.getMano().clear();
        cpu2.getMano().clear();
    }

    //Método que reparte cartas nuevas al finalizar la ronda.
    private void repartirCartasNuevas() {
        //RepartoCartasNuevas
        usuario.getCartasVisibles().addAll(mazo.repartirCartas(Constantes.NUM_CARTAS));
        cpu1.getCartasVisibles().addAll(mazo.repartirCartas(Constantes.NUM_CARTAS));
        cpu2.getCartasVisibles().addAll(mazo.repartirCartas(Constantes.NUM_CARTAS));
        ventanaJuego.actualizarCartas();//Actualizo
    }

    //Método que calcula y actualiza la puntuación al final de cada ronda
    private void actualizarPuntuacionRonda() {
        //Variables
        int puntuacionUsuario = 0, puntuacionCpu1 = 0, puntuacionCpu2 = 0;
        Map<String, Integer> cartasUsuario = contarTiposCarta(usuario.getMano());
        Map<String, Integer> cartasCpu1 = contarTiposCarta(cpu1.getMano());
        Map<String, Integer> cartasCpu2 = contarTiposCarta(cpu2.getMano());
        //CÁLCULO PUNTUACIONES============================================================
        //MAKIS ------------------------------------------------------------------------
        //El jugador que tenga más rollos gana 6 puntos. Si varios jugadores
        //empatan en primera posición, se reparten los 6 puntos a partes iguales.
        int makiUsuario = cartasUsuario.get(Constantes.MAKIS);
        int makiCpu1 = cartasCpu1.get(Constantes.MAKIS);
        int makiCpu2 = cartasCpu2.get(Constantes.MAKIS);
        // Determino el máximo número de makis
        int maxMakis = Math.max(makiUsuario, Math.max(makiCpu1, makiCpu2));
        // Determino cuántos jugadores tienen ese máximo valor
        int jugadoresConMaxMakis = 0;
        if (makiUsuario == maxMakis) {
            jugadoresConMaxMakis++;
        }
        if (makiCpu1 == maxMakis) {
            jugadoresConMaxMakis++;
        }
        if (makiCpu2 == maxMakis) {
            jugadoresConMaxMakis++;
        }
        // Reparto los puntos entre los jugadores que tienen el máximo número de makis
        int puntosPorMaki = Constantes.VALOR_MAKIS / jugadoresConMaxMakis;
        if (makiUsuario == maxMakis) {
            puntuacionUsuario += puntosPorMaki;
        }
        if (makiCpu1 == maxMakis) {
            puntuacionCpu1 += puntosPorMaki;
        }
        if (makiCpu2 == maxMakis) {
            puntuacionCpu2 += puntosPorMaki;
        }
        //SASHIMI ------------------------------------------------------------------------
        //Un trío (3 cartas) de sashimi otorga 10 puntos. Una única carta o una pareja de sashimi no da puntos.
        puntuacionUsuario += (cartasUsuario.get(Constantes.SASHIMI) / 3) * Constantes.VALOR_SASHIMI;
        puntuacionCpu1 += (cartasCpu1.get(Constantes.SASHIMI) / 3) * Constantes.VALOR_SASHIMI;
        puntuacionCpu2 += (cartasCpu2.get(Constantes.SASHIMI) / 3) * Constantes.VALOR_SASHIMI;
        //DUMPLING ------------------------------------------------------------------------
        //Cuantas más cartas de gyoza tengas, más puntos ganarás.
        puntuacionUsuario += calcularPuntuacionDumplings(cartasUsuario.get(Constantes.DUMPLING));
        puntuacionCpu1 += calcularPuntuacionDumplings(cartasCpu1.get(Constantes.DUMPLING));
        puntuacionCpu2 += calcularPuntuacionDumplings(cartasCpu2.get(Constantes.DUMPLING));
        //NIGIRI_CALAMAR ------------------------------------------------------------------------
        //Un nigiri de calamar otorga 3 puntos.
        puntuacionUsuario += cartasUsuario.get(Constantes.NIGIRI_CALAMAR) * Constantes.VALOR_NIGIRI_CALAMAR;
        puntuacionCpu1 += cartasCpu1.get(Constantes.NIGIRI_CALAMAR) * Constantes.VALOR_NIGIRI_CALAMAR;
        puntuacionCpu2 += cartasCpu2.get(Constantes.NIGIRI_CALAMAR) * Constantes.VALOR_NIGIRI_CALAMAR;
        //NIGIRI_SALMON ------------------------------------------------------------------------
        //Un nigiri de salmón otorga 2 puntos.
        puntuacionUsuario += cartasUsuario.get(Constantes.NIGIRI_SALMON) * Constantes.VALOR_NIGIRI_SALMON;
        puntuacionCpu1 += cartasCpu1.get(Constantes.NIGIRI_SALMON) * Constantes.VALOR_NIGIRI_SALMON;
        puntuacionCpu2 += cartasCpu2.get(Constantes.NIGIRI_SALMON) * Constantes.VALOR_NIGIRI_SALMON;
        //NIGIRI_HUEVO ------------------------------------------------------------------------
        //Un nigiri de huevo otorga 1 punto.
        puntuacionUsuario += cartasUsuario.get(Constantes.NIGIRI_HUEVO) * Constantes.VALOR_NIGIRI_HUEVO;
        puntuacionCpu1 += cartasCpu1.get(Constantes.NIGIRI_HUEVO) * Constantes.VALOR_NIGIRI_HUEVO;
        puntuacionCpu2 += cartasCpu2.get(Constantes.NIGIRI_HUEVO) * Constantes.VALOR_NIGIRI_HUEVO;
        //FIN CÁLCULO PUNTUACIONES============================================================
        //Actualizo puntuaciones
        usuario.setPuntuacion(usuario.getPuntuacion() + puntuacionUsuario);
        cpu1.setPuntuacion(cpu1.getPuntuacion() + puntuacionCpu1);
        cpu2.setPuntuacion(cpu2.getPuntuacion() + puntuacionCpu2);
    }

    //Método auxiliar para simplificar el calculo de la puntuación de los dumplings
    private int calcularPuntuacionDumplings(int numDumplings) {
        return switch (numDumplings) {
            case 0 ->
                0;
            case 1 ->
                1;
            case 2 ->
                3;
            case 3 ->
                6;
            case 4 ->
                10;
            default ->
                15;
        };
    }

    //Método auxiliar que recibe una mano por parámetro y devuelve un mapa con el nombre del tipo de carta como clave, y el numero de cartas del mismo tipo como valor.
    private Map<String, Integer> contarTiposCarta(List<Carta> mano) {
        //Variables
        int numMakis = 0, numSashimi = 0, numDumpling = 0, numNigiriCalamar = 0, numNigiriHuevo = 0, numNigiriSalmon = 0;
        //Mapa donde almacenare las cartas organizadas
        Map<String, Integer> cartas = new LinkedHashMap<>();
        //Recorro la mano
        for (Carta carta : mano) {
            switch (carta.getNombre()) {
                case Constantes.MAKI1:
                    numMakis += 1;
                    break;
                case Constantes.MAKI2:
                    numMakis += 2;
                    break;
                case Constantes.MAKI3:
                    numMakis += 3;
                    break;
                case Constantes.SASHIMI:
                    numSashimi += 1;
                    break;
                case Constantes.DUMPLING:
                    numDumpling += 1;
                    break;
                case Constantes.NIGIRI_CALAMAR:
                    numNigiriCalamar += 1;
                    break;
                case Constantes.NIGIRI_HUEVO:
                    numNigiriHuevo += 1;
                    break;
                case Constantes.NIGIRI_SALMON:
                    numNigiriSalmon += 1;
                    break;
                case Constantes.PUDDING:
                    usuario.aniadirPudding();
                    break;
            }
        }
        //Relleno el mapa
        cartas.put(Constantes.MAKIS, numMakis);
        cartas.put(Constantes.SASHIMI, numSashimi);
        cartas.put(Constantes.DUMPLING, numDumpling);
        cartas.put(Constantes.NIGIRI_CALAMAR, numNigiriCalamar);
        cartas.put(Constantes.NIGIRI_HUEVO, numNigiriHuevo);
        cartas.put(Constantes.NIGIRI_SALMON, numNigiriSalmon);

        return cartas;
    }

    //Método auxiliar que actualiza la puntuacion de cada jugador al finalizar el juego según los puddings que tenga.
    private void actualizarPuntuacionPudding() {
        //ACTUALIZO PUNTUACIÓN AÑADIENDO LOS PUNTOS DE PUDDING
        //El jugador con más cartas de pudin gana 6 puntos. Si varios jugadores empatan en primera posición,
        //se reparten los 6 puntos a partes iguales.
        //El jugador con menos cartas de pudin(incluyendo aquellos con ninguna carta)pierde 6 puntos. Si varios jugadores empatan
        //en última posición, se reparten los puntos perdidos a partes iguales.
        int puddingUsuario = usuario.getPuddings();
        int puddingCpu1 = cpu1.getPuddings();
        int puddingCpu2 = cpu2.getPuddings();
        // Determino el máximo número de makis
        int maxPuddings = Math.max(puddingUsuario, Math.max(puddingCpu1, puddingCpu2));
        // Determino cuántos jugadores tienen ese máximo valor
        int jugadoresConMaxPuddings = 0;
        if (puddingUsuario == maxPuddings) {
            jugadoresConMaxPuddings++;
        }
        if (puddingCpu1 == maxPuddings) {
            jugadoresConMaxPuddings++;
        }
        if (puddingCpu2 == maxPuddings) {
            jugadoresConMaxPuddings++;
        }
        // Reparto los puntos entre los jugadores que tienen el máximo número de makis
        int puntosPorPudding = Constantes.VALOR_PUDDING / jugadoresConMaxPuddings;
        if (puddingUsuario == maxPuddings) {
            usuario.setPuntuacion(usuario.getPuntuacion() + puntosPorPudding);
        }
        if (puddingCpu1 == maxPuddings) {
            cpu1.setPuntuacion(cpu1.getPuntuacion() + puntosPorPudding);
        }
        if (puddingCpu2 == maxPuddings) {
            cpu2.setPuntuacion(cpu2.getPuntuacion() + puntosPorPudding);
        }
        // Ahora, vamos a determinar el mínimo número de puddings
        int minPuddings = Math.min(puddingUsuario, Math.min(puddingCpu1, puddingCpu2));

        // Determinamos cuántos jugadores tienen ese valor mínimo
        int jugadoresConMinPuddings = 0;
        if (puddingUsuario == minPuddings) {
            jugadoresConMinPuddings++;
        }
        if (puddingCpu1 == minPuddings) {
            jugadoresConMinPuddings++;
        }
        if (puddingCpu2 == minPuddings) {
            jugadoresConMinPuddings++;
        }

        // Repartimos los puntos negativos entre los jugadores que tienen el mínimo número de puddings
        int puntosNegativosPorPudding = Constantes.VALOR_PUDDING / jugadoresConMinPuddings;
        if (puddingUsuario == minPuddings) {
            usuario.setPuntuacion(usuario.getPuntuacion() - puntosNegativosPorPudding);
        }
        if (puddingCpu1 == minPuddings) {
            cpu1.setPuntuacion(cpu1.getPuntuacion() - puntosNegativosPorPudding);
        }
        if (puddingCpu2 == minPuddings) {
            cpu2.setPuntuacion(cpu2.getPuntuacion() - puntosNegativosPorPudding);
        }
    }

    //Este método contiene la lógica que permite obtener el ganador del juego segun las normas de este
    public Jugador comprobarGanador() {
        //El jugador que haya acumulado más puntos después de tres rondas.
        //Si hay dos o más jugadores empatados, gana quien tenga más cartas de puddin.

        //En primer lugar actualizo la puntuacion sumando los respectivos puntos según los puddings que tengan cada uno
        actualizarPuntuacionPudding();

        //Comprobar quien es el ganador
        Jugador ganador = usuario;

        if (cpu1.getPuntuacion() > ganador.getPuntuacion()) {
            ganador = cpu1;
        }
        if (cpu2.getPuntuacion() > ganador.getPuntuacion()) {
            ganador = cpu2;
        }

        // Lista de jugadores empatados
        List<Jugador> jugadoresEmpatados = new ArrayList<>();
        if (ganador.getPuntuacion() == usuario.getPuntuacion()) {
            jugadoresEmpatados.add(usuario);
        }
        if (ganador.getPuntuacion() == cpu1.getPuntuacion()) {
            jugadoresEmpatados.add(cpu1);
        }
        if (ganador.getPuntuacion() == cpu2.getPuntuacion()) {
            jugadoresEmpatados.add(cpu2);
        }

        // Si hay más de un jugador empatado, decidir por cartas de pudding
        if (jugadoresEmpatados.size() > 1) {
            ganador = jugadoresEmpatados.get(0);
            for (Jugador jugador : jugadoresEmpatados) {
                if (jugador.getPuddings() > ganador.getPuddings()) {
                    ganador = jugador;
                }
            }
        }
        //Retorno el ganador
        return ganador;
    }
}
