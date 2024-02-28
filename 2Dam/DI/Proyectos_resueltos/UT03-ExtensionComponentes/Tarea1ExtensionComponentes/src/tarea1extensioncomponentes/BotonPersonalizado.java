package tarea1extensioncomponentes;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author wmartinl01
 */
public class BotonPersonalizado extends JButton implements Serializable {

    private static int num = 0;

    public BotonPersonalizado() {
        //Establecemos el color y el texto del botón
        this.setBackground(Color.red);
        this.setText("Botón " + num);
        //Alineamos
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        //Incrementamos la variable num
        num++;
    }

}
