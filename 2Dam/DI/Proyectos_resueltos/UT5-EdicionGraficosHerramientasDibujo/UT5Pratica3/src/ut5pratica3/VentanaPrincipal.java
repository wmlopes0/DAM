package ut5pratica3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author wmartinl01
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //Constructor
    public VentanaPrincipal() {
        initComponents();
        generarRadioGroup();
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelPrincipal = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();
        jButtonCargarImagen = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jPanelRadioButtons = new javax.swing.JPanel();
        jRadioButtonNegro = new javax.swing.JRadioButton();
        jRadioButtonAzul = new javax.swing.JRadioButton();
        jRadioButtonBlanco = new javax.swing.JRadioButton();
        jRadioButtonVerde = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButtonCargarImagen.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 14)); // NOI18N
        jButtonCargarImagen.setText("Cargar Imagen");
        jButtonCargarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarImagenActionPerformed(evt);
            }
        });

        jButtonNuevo.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 14)); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jPanelRadioButtons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75, 55, 199)));
        jPanelRadioButtons.setAlignmentY(1.0F);

        jRadioButtonNegro.setText("Negro");

        jRadioButtonAzul.setText("Azul");

        jRadioButtonBlanco.setText("Blanco");

        jRadioButtonVerde.setText("Verde");

        javax.swing.GroupLayout jPanelRadioButtonsLayout = new javax.swing.GroupLayout(jPanelRadioButtons);
        jPanelRadioButtons.setLayout(jPanelRadioButtonsLayout);
        jPanelRadioButtonsLayout.setHorizontalGroup(
            jPanelRadioButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRadioButtonsLayout.createSequentialGroup()
                .addGroup(jPanelRadioButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonNegro)
                    .addComponent(jRadioButtonAzul)
                    .addComponent(jRadioButtonVerde)
                    .addComponent(jRadioButtonBlanco))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanelRadioButtonsLayout.setVerticalGroup(
            jPanelRadioButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRadioButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButtonNegro)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonAzul)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonBlanco)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonVerde)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 14)); // NOI18N
        jLabel1.setText("Colores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButtonCargarImagen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(143, 143, 143))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanelRadioButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCargarImagen)
                            .addComponent(jButtonNuevo))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelRadioButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void generarRadioGroup() {
        buttonGroup1.add(jRadioButtonAzul);
        buttonGroup1.add(jRadioButtonBlanco);
        buttonGroup1.add(jRadioButtonNegro);
        buttonGroup1.add(jRadioButtonVerde);
    }

    private void jButtonCargarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarImagenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            //Controlo que el archivo elegido sea una imagen en jpg o png
            if (esImagen(archivo)) {
                try {
                    BufferedImage img = ImageIO.read(archivo);
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(452, 452, Image.SCALE_SMOOTH));
                    jLabelImagen.setIcon(icon);
                } catch (IOException ex) {
//                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error.Debes elegir una imagen!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonCargarImagenActionPerformed


    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        if (jRadioButtonAzul.isSelected()) {
            pintarLabelPrincipal(Color.BLUE);
        } else if (jRadioButtonBlanco.isSelected()) {
            pintarLabelPrincipal(Color.WHITE);
        } else if (jRadioButtonNegro.isSelected()) {
            pintarLabelPrincipal(Color.BLACK);
        } else if (jRadioButtonVerde.isSelected()) {
            pintarLabelPrincipal(Color.GREEN);
        } else {
            JOptionPane.showMessageDialog(this, "Error.Debes elegir algun color!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    //Pinto el label principal de un color seleccionado
    public void pintarLabelPrincipal(Color color) {
        BufferedImage bufNuevo = new BufferedImage(452, 452, BufferedImage.TYPE_INT_ARGB);
        Graphics graficos = bufNuevo.getGraphics();
        graficos.setColor(color);
        graficos.fillRect(0, 0, bufNuevo.getWidth(), bufNuevo.getHeight());
        graficos.dispose();
        jLabelImagen.setIcon(new ImageIcon(bufNuevo));
    }

    //Método que comprueba si el archivo elegido es una imagen
    private boolean esImagen(File archivo) {
        String ruta = archivo.getAbsolutePath();
        if (ruta.endsWith("jpg") || ruta.endsWith("png")) {
            return true;
        } else {
            return false;
        }
    }

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCargarImagen;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelRadioButtons;
    private javax.swing.JRadioButton jRadioButtonAzul;
    private javax.swing.JRadioButton jRadioButtonBlanco;
    private javax.swing.JRadioButton jRadioButtonNegro;
    private javax.swing.JRadioButton jRadioButtonVerde;
    // End of variables declaration//GEN-END:variables
}
