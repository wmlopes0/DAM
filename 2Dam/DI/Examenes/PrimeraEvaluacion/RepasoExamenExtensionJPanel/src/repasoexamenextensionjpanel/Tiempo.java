package repasoexamenextensionjpanel;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author wmartinl01
 */
public class Tiempo {

    //Atributo LabelTiempo
    private JLabel labelTiempo;
    private Timer timer;
    private int segundos = 0;
    
    public Tiempo(JLabel labelTiempo) {
        this.timer = new Timer();
        this.labelTiempo = labelTiempo;
    }

    //Clase interna que funciona como contador
    class Contador extends TimerTask {

        //Atributo LabelTiempo
        private JLabel labelTiempo;
        
        public Contador(JLabel labelTiempo) {
            this.labelTiempo = labelTiempo;
        }
        
        public void run() {
            segundos++;
            labelTiempo.setText(String.valueOf(segundos));
        }
    }

    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar() {
        this.segundos = 0;
        timer = new Timer();
        timer.schedule(new Contador(labelTiempo), 0, 1000);
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
