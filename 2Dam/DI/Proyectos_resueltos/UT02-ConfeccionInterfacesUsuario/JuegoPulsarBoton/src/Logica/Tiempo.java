package Logica;

import InterfazGrafica.VentanaJuego;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author wmartinl01
 */
public class Tiempo {

    //Atributo VentanaJuego
    private VentanaJuego ventanaJuego;
    private Timer timer;
    private int segundos = 0;
    
    public Tiempo(VentanaJuego ventanaJuego) {
        this.timer = new Timer();
        this.ventanaJuego = ventanaJuego;
    }

    //Clase interna que funciona como contador
    class Contador extends TimerTask {

        //Atributo VentanaJuego
        private VentanaJuego ventanaJuego;
        
        public Contador(VentanaJuego ventanaJuego) {
            this.ventanaJuego = ventanaJuego;
        }
        
        public void run() {
            segundos++;
            ventanaJuego.actualizarSegundos(segundos);
        }
    }

    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar() {
        this.segundos = 0;
        timer = new Timer();
        timer.schedule(new Contador(ventanaJuego), 0, 1000);
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
