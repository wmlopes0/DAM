package InterfazGrafica;

import Logica.Tiempo;
import java.awt.Color;

/**
 *
 * @author wmartinl01
 */
public class VentanaJuego extends javax.swing.JDialog {

    private Tiempo tiempo = new Tiempo(this);
    private VentanaPrincipal ventanaPrincipal;

    //Constructor
    public VentanaJuego(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getContentPane().setBackground(Color.decode("#926349"));
        ventanaPrincipal = (VentanaPrincipal) parent;
        actualizarRecord();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelSegundos = new javax.swing.JLabel();
        jButtonJuego = new javax.swing.JButton();
        jLabelMensaje1 = new javax.swing.JLabel();
        jLabelMensaje2 = new javax.swing.JLabel();
        jLabelMensaje3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelSegundos.setFont(new java.awt.Font("Lucida Calligraphy", 0, 24)); // NOI18N

        jButtonJuego.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jButtonJuego.setText("PÚLSAME");
        jButtonJuego.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonJuegoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButtonJuegoMouseReleased(evt);
            }
        });
        jButtonJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJuegoActionPerformed(evt);
            }
        });

        jLabelMensaje1.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N

        jLabelMensaje2.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N

        jLabelMensaje3.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabelSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelMensaje3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addComponent(jLabelMensaje2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMensaje1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabelMensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMensaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMensaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jLabelSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //ESTABLECER SEGUNDOS
    public void actualizarSegundos(int segundos) {
        jLabelSegundos.setText(String.valueOf(segundos) + " segundos");
    }

    //ACTUALIZAR RECORD
    public void actualizarRecord() {
        if (Logica.Juego.mejorJuego.getTiempo() != 0) {
            String nombre = Logica.Juego.mejorJuego.getNombre();
            String tiempo = String.valueOf(Logica.Juego.mejorJuego.getTiempo());
            jLabelMensaje1.setText("Desafía tus límites apretando el botón y mantén");
            jLabelMensaje2.setText("la presión el mayor tiempo posible. ¿Podrás superar");
            jLabelMensaje3.setText("el récord de " + nombre + " con " + tiempo + " segundos?");
        } else {
            jLabelMensaje1.setText("Desafía tus límites apretando el botón y mantén");
            jLabelMensaje2.setText("la presión el mayor tiempo posible.");
        }
    }

    //BOTÓN JUEGO ===============================================================
    private void jButtonJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJuegoActionPerformed

    }//GEN-LAST:event_jButtonJuegoActionPerformed

    private void jButtonJuegoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonJuegoMousePressed
        //Empiezo a contar
        tiempo.Contar();
    }//GEN-LAST:event_jButtonJuegoMousePressed

    private void jButtonJuegoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonJuegoMouseReleased
        //Paro de contar
        tiempo.Detener();
        //Paso la info a VentanaPrincipal
        ventanaPrincipal.setTiempo(tiempo.getSegundos());
        //Creo ventanaNombre y la abro
        VentanaNombre ventanaNombre = new VentanaNombre(ventanaPrincipal, true);
        ventanaNombre.mostrarMensaje(tiempo.getSegundos());
        ventanaNombre.setVisible(true);
        //Cierro
        dispose();

    }//GEN-LAST:event_jButtonJuegoMouseReleased
    //==============================================================================
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
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaJuego dialog = new VentanaJuego(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonJuego;
    private javax.swing.JLabel jLabelMensaje1;
    private javax.swing.JLabel jLabelMensaje2;
    private javax.swing.JLabel jLabelMensaje3;
    private javax.swing.JLabel jLabelSegundos;
    // End of variables declaration//GEN-END:variables
}
