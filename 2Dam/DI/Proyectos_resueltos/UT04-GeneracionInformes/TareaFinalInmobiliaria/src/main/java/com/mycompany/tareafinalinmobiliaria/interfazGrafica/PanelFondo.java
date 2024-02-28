package com.mycompany.tareafinalinmobiliaria.interfazGrafica;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author wmartinl01
 */
//Esta clase personalizada es para crear un JPanel con una imagen de fondo
public class PanelFondo extends JPanel {

    private Image fondo;

    public PanelFondo(String rutaImagen) {
        fondo = new ImageIcon(rutaImagen).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, WIDTH, WIDTH, this);
    }

}
