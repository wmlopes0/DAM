package ejercicioimagenesclase;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author wmartinl01
 */
public class VentanaPrincipal extends javax.swing.JFrame {
   
    //Variables globales
    final static int ANCHO_MINIATURA = 125;
    final static int ALTO_MINIATURA = 125;

    //Constructor
    public VentanaPrincipal() {
        initComponents();
        try {
            recuperarMiniaturas();
        } catch (IOException ex) {
//            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void recuperarMiniaturas() throws IOException {
        jPanelMiniaturas.setLayout(new BoxLayout(jPanelMiniaturas, BoxLayout.Y_AXIS));
        //Imagen1
        BufferedImage img1 = ImageIO.read(new File("src/IMG/imagen1.jpg"));
        ImageIcon imagen1 = new ImageIcon(img1.getScaledInstance(ANCHO_MINIATURA, ALTO_MINIATURA, Image.SCALE_SMOOTH));
        Dimension dim = new Dimension(ANCHO_MINIATURA, ALTO_MINIATURA);
        JButton miniatura1 = new JButton(imagen1);
        establecerListener(miniatura1,img1);
        miniatura1.setSize(dim);
        jPanelMiniaturas.add(miniatura1);
        //Imagen2
        BufferedImage img2 = ImageIO.read(new File("src/IMG/imagen2.jpg"));
        ImageIcon imagen2 = new ImageIcon(img2.getScaledInstance(ANCHO_MINIATURA, ALTO_MINIATURA, Image.SCALE_SMOOTH));
        JButton miniatura2 = new JButton(imagen2);
        establecerListener(miniatura2,img2);
        miniatura1.setSize(dim);
        jPanelMiniaturas.add(miniatura2);
        //Imagen3
        BufferedImage img3 = ImageIO.read(new File("src/IMG/imagen3.jpg"));
        ImageIcon imagen3 = new ImageIcon(img3.getScaledInstance(ANCHO_MINIATURA, ALTO_MINIATURA, Image.SCALE_SMOOTH));
        JButton miniatura3 = new JButton(imagen3);
        establecerListener(miniatura3,img3);
        miniatura1.setSize(dim);
        jPanelMiniaturas.add(miniatura3);
        //Imagen4
        BufferedImage img4 = ImageIO.read(new File("src/IMG/imagen4.jpg"));
        ImageIcon imagen4 = new ImageIcon(img4.getScaledInstance(ANCHO_MINIATURA, ALTO_MINIATURA, Image.SCALE_SMOOTH));
        JButton miniatura4 = new JButton(imagen4);
        establecerListener(miniatura4,img4);
        miniatura1.setSize(dim);
        jPanelMiniaturas.add(miniatura4);
    }
    
    public void establecerListener(JButton miniatura,BufferedImage img){
        miniatura.addActionListener((ActionEvent e) -> {
            jLabelImagen.setIcon(new ImageIcon(img.getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
        });
    }
    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMiniaturas = new javax.swing.JPanel();
        jPanelImagen = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMiniaturas.setBackground(new java.awt.Color(255, 102, 52));

        jPanelImagen.setBackground(new java.awt.Color(254, 102, 203));

        javax.swing.GroupLayout jPanelImagenLayout = new javax.swing.GroupLayout(jPanelImagen);
        jPanelImagen.setLayout(jPanelImagenLayout);
        jPanelImagenLayout.setHorizontalGroup(
            jPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagenLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanelImagenLayout.setVerticalGroup(
            jPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImagenLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanelMiniaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelMiniaturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Main
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JPanel jPanelImagen;
    private javax.swing.JPanel jPanelMiniaturas;
    // End of variables declaration//GEN-END:variables
}
