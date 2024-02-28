package repasoexamenextensionjpanel;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Walter
 */
public class MiJPanel extends JPanel {

    public MiJPanel() {
        super(new FlowLayout(), true);
        JLabel labelTiempo = new JLabel("0");//Creo JLabel
        Tiempo tiempo = new Tiempo(labelTiempo);//Clase tiempo, le paso el Label para actualizar su texto en el run
        tiempo.Contar();//Inicio el contador
        this.add(labelTiempo);//Añado el label al panel
    }

}
