package botonimagenpropio;

import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author wmartinl01
 */
public class BotonImagenPropio extends JButton implements Serializable {

    private String ruta;

    public BotonImagenPropio() {
        //Ruta imagen
        ruta = "/imagen/3.png";
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        //Redimensionar imagen
        ImageIcon icon = new ImageIcon(icono.getImage().getScaledInstance(50, 50, 100));
        this.setIcon(icon);
        this.setIconTextGap(2);
        //Alineamos
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
    }

}
