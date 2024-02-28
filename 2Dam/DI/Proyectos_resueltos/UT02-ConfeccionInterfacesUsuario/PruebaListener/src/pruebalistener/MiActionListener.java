package pruebalistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author wmartinl01
 */
public class MiActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //Hacer cosas
        System.out.println(e.getActionCommand());//El Action comand saca el nombre del boton por pantalla
    }

}
