package Logica;

import InterfazGrafica.GameOver;
import InterfazGrafica.NivelDificil;
import InterfazGrafica.NivelFacil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author wmartinl01
 */
public class MiMousseListener implements MouseListener {

    //Atributo ventana NivelFacil y NivelDificil
    private NivelFacil nivelFacil;
    private NivelDificil nivelDificil;

    //Constructores
    public MiMousseListener(NivelFacil nivelFacil) {
        this.nivelFacil = nivelFacil;
    }

    public MiMousseListener(NivelDificil nivelDificil) {
        this.nivelDificil = nivelDificil;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Cuando el ratón entra en alguna de las partes prohibidas a las que he añadido este mousseListener, entra en este método
        //Comprueba en que ventana se encuentra y la cierra, de tal manera que el objeto juego se crea con los segundos a 0, 
        //En la ventana principal controlo que si los segundos son = 0 salte la ventana de GameOver y no guarde datos simulando que ha perdido el juego.
        if (nivelFacil != null) {
            nivelFacil.dispose();
        } else {
            nivelDificil.dispose();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
