package Logica;

import InterfazGrafica.NivelDificil;
import InterfazGrafica.NivelFacil;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author wmartinl01
 */
public class Tiempo {

    //Atributo ventana NivelFacil y NivelDificil
    private NivelFacil nivelFacil;
    private NivelDificil nivelDificil;
    //Atributos propios
    private Timer timer;
    private int segundos = 0;

    //Constructores
    public Tiempo(NivelFacil nivelFacil) {
        this.timer = new Timer();
        this.nivelFacil = nivelFacil;
    }

    public Tiempo(NivelDificil nivelDificil) {
        this.timer = new Timer();
        this.nivelDificil = nivelDificil;
    }

    //Clase interna que funciona como contador
    class Contador extends TimerTask {

        //Atributo ventana NivelFacil y NivelDificil
        private NivelFacil nivelFacil;
        private NivelDificil nivelDificil;

        //Atributo VentanaJuego, que recibia por constructor
        public Contador(NivelFacil nivelFacil, NivelDificil nivelDificil) {
            this.nivelFacil = nivelFacil;
            this.nivelDificil = nivelDificil;
        }

        public void run() {
            segundos++;
            //Compruebo en que ventana estoy jugando y actualizo segundos
            if (nivelFacil != null) {
                nivelFacil.actualizarSegundos(segundos);
            } else {
                nivelDificil.actualizarSegundos(segundos);
            }
        }
    }

    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar() {
        this.segundos = 0;
        timer = new Timer();
        timer.schedule(new Contador(nivelFacil, nivelDificil), 0, 1000);
    }

    //Detiene el contador
    public void Detener() {
        timer.cancel();
    }

    //Metodo que retorna los segundos transcurridos
    public int getSegundos() {
        return this.segundos;
    }
}
