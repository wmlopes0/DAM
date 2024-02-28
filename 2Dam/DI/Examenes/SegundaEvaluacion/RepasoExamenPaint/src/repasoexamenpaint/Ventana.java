package repasoexamenpaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Walter
 */
public class Ventana extends javax.swing.JFrame {

    //Variables globales
    private BufferedImage buffNuevo;
    private Graphics graficos;
    private Graphics2D g2d;
    private Color colorSeleccionado;
    private int tamanioCursor;
    private boolean modoCuadrado = false;

    //Constructor
    public Ventana() {
        initComponents();
        pizarraNueva();
        colorSeleccionado = Color.BLACK;
        tamanioCursor = 10;
    }

    private void pizarraNueva() {
        buffNuevo = new BufferedImage(this.jLabelPizarra.getWidth(), this.jLabelPizarra.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graficos = buffNuevo.getGraphics();
        g2d = (Graphics2D) graficos;
        this.jLabelPizarra.setIcon(new ImageIcon(buffNuevo));
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPizarra = new javax.swing.JLabel();
        jButtonGuardarImagen = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jButtonSeleccionarColor = new javax.swing.JButton();
        jButtonBorrarTodo = new javax.swing.JButton();
        jButtonCuadrado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelPizarra.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        jLabelPizarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabelPizarraMouseDragged(evt);
            }
        });

        jButtonGuardarImagen.setText("Guardar Imagen");
        jButtonGuardarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarImagenActionPerformed(evt);
            }
        });

        jSlider1.setMaximum(30);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(10);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel1.setText("Tamaó cursor");

        jButtonSeleccionarColor.setText("Seleccionar color");
        jButtonSeleccionarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarColorActionPerformed(evt);
            }
        });

        jButtonBorrarTodo.setText("Borrar");
        jButtonBorrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarTodoActionPerformed(evt);
            }
        });

        jButtonCuadrado.setText("Cuadrado");
        jButtonCuadrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCuadradoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabelPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(94, 94, 94))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonBorrarTodo)
                                    .addComponent(jButtonGuardarImagen))
                                .addGap(54, 54, 54))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jButtonSeleccionarColor)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCuadrado)
                .addGap(210, 210, 210))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCuadrado)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButtonSeleccionarColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jButtonGuardarImagen)
                .addGap(56, 56, 56)
                .addComponent(jButtonBorrarTodo)
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelPizarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPizarraMouseDragged
        int x = evt.getX();
        int y = evt.getY();
        g2d.setColor(colorSeleccionado);
        if (modoCuadrado) {
//            g2d.fillRect(x, y, 50, 50);
            g2d.drawLine(x, y, 100, 0);
            modoCuadrado = false;
        } else {
            g2d.fillOval(x, y, tamanioCursor, tamanioCursor);
        }

        jLabelPizarra.updateUI();
    }//GEN-LAST:event_jLabelPizarraMouseDragged


    private void jButtonGuardarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarImagenActionPerformed
        try {
            ImageIO.write(buffNuevo, "png", new File("imagen.png"));
        } catch (IOException ex) {
//            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonGuardarImagenActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        tamanioCursor = jSlider1.getValue();
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButtonSeleccionarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarColorActionPerformed
        JColorChooser colores = new JColorChooser();
        colorSeleccionado = colores.showDialog(null, "Seleccione un color", colorSeleccionado);
    }//GEN-LAST:event_jButtonSeleccionarColorActionPerformed

    private void jButtonBorrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarTodoActionPerformed
        pizarraNueva();
    }//GEN-LAST:event_jButtonBorrarTodoActionPerformed

    private void jButtonCuadradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCuadradoActionPerformed
        modoCuadrado = true;
    }//GEN-LAST:event_jButtonCuadradoActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBorrarTodo;
    private javax.swing.JButton jButtonCuadrado;
    private javax.swing.JButton jButtonGuardarImagen;
    private javax.swing.JButton jButtonSeleccionarColor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelPizarra;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
