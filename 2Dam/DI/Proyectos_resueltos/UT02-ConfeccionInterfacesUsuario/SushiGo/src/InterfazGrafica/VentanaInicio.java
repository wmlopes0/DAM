package InterfazGrafica;

import LogicaJuego.Constantes;
import LogicaJuego.Juego;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Walter
 */
public class VentanaInicio extends javax.swing.JFrame {

    //Variables globales
    public Juego juego;
    private JLabel ventanaInicio;//Label que contiene todo, es el que uso para poner el fondo.

    //Constructor
    public VentanaInicio() {
        initComponents();
        establecerFondo();
        pintarComponentes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNombre = new javax.swing.JTextField();
        jButtonJugar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTextFieldNombre.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N

        jButtonJugar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButtonJugar.setText("Jugar");
        jButtonJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJugarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonJugar, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jTextFieldNombre))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonJugar)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botón para iniciar el juego
    private void jButtonJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJugarActionPerformed
        //Instancio objeto Juego
        this.juego = new Juego(obtenerNombreUsuario());
        //Creo una nueva VentanaJuego
        VentanaJuego ventanaJuego = new VentanaJuego(this, true);
        ventanaJuego.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonJugarActionPerformed

    //Método que retorna el texto del JTextField donde el usuario introduce el nombre
    public String obtenerNombreUsuario() {
        return jTextFieldNombre.getText();
    }

    //Método que establece el fondo de la pantalla
    public void establecerFondo() {
        // Cargar la imagen de fondo
        ImageIcon icon = new ImageIcon("src/IMG/fondo.png");
        //Redimensiono la imagen
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(Constantes.ANCHO_FONDO, Constantes.ALTO_FONDO, java.awt.Image.SCALE_SMOOTH);
        // Crear un JLabel con la imagen de fondo
        JLabel labelConFondo = new JLabel(new ImageIcon(newImg));
        this.ventanaInicio = labelConFondo;
        labelConFondo.setLayout(null);//Layout absoluto
        // Establecer el JLabel como el contenido del JDialog
        this.setContentPane(labelConFondo);
        this.pack();//Fuerzo que la ventana se redimensione para adaptarse al tamaño de la imagen de fondo.
    }

    //Método que configura y añade los componentes de la ventana
    public void pintarComponentes() {
        //Botón Jugar
        jButtonJugar.setForeground(Color.WHITE);
        jButtonJugar.setBackground(new Color(252, 102, 0));
        jButtonJugar.setBounds(460, 630, jButtonJugar.getWidth(), jButtonJugar.getHeight());
        ventanaInicio.add(jButtonJugar);
        //TextField
        jTextFieldNombre.setText("Tu nombre ...");
        jTextFieldNombre.setBounds(460, 590, jTextFieldNombre.getWidth(), jTextFieldNombre.getHeight());
        jTextFieldNombre.setForeground(new Color(252, 102, 0));
        ventanaInicio.add(jTextFieldNombre);
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
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonJugar;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
