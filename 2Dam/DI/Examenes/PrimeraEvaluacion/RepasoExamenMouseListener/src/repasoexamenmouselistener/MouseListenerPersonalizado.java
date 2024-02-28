package repasoexamenmouselistener;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author wmartinl01
 */
public class MouseListenerPersonalizado implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Button boton = (Button) e.getSource();
        boton.setBackground(Color.green);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Button boton = (Button) e.getSource();
        boton.setBackground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Button boton = (Button) e.getSource();
        boton.setBackground(Color.yellow);
    }

}
