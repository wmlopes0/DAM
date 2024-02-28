package InterfazGrafica;

import Logica.MiMousseListener;
import Logica.Tiempo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JLabel;

/**
 *
 * @author wmartinl01
 */
public class NivelDificil extends javax.swing.JDialog {

    //Variables globales
    VentanaPrincipal ventanaPrincipal;
    Tiempo tiempo = new Tiempo(this);
    MiMousseListener miMousseListener = new MiMousseListener(this);

    //Constructor
    public NivelDificil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ventanaPrincipal = (VentanaPrincipal) parent;
        cambiarFondoZonaProhibida(this);
    }

    //Método que cambia de color toda las zonas prohibidas
    private void cambiarFondoZonaProhibida(Container contenedor) {
        jLabelTiempo.setName("Tiempo");//Hago un set name porque de primeras todos los componentes tienen un name a null, y así no me cambia el color de los segundos
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof JLabel) {
                if (componente.getName() == null) {
                    ((JLabel) componente).setOpaque(true);  // Me aseguro que tenga opacidad
                    ((JLabel) componente).setBackground(Color.decode("#c20000"));  //Cambia el fondo
                } else {//Cambio el color del tiempo
                    ((JLabel) componente).setOpaque(true);  // Me aseguro que tenga opacidad
                    ((JLabel) componente).setBackground(Color.WHITE);  //Cambia el fondo
                }
            }
            // Si el componente es un contenedor, busca en el contenedor con recursividad
            if (componente instanceof Container) {
                cambiarFondoZonaProhibida((Container) componente);
            }
        }
    }

    //Método que añade el mousse listener a las zonas prohibidas
    private void aniadirMousseListener(Container contenedor) {
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof JLabel) {
                if (componente.getName() == null) {
                    ((JLabel) componente).addMouseListener(miMousseListener);
                }
            }
            // Si el componente es un contenedor, busca en el contenedor con recursividad
            if (componente instanceof Container) {
                aniadirMousseListener((Container) componente);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonInicio = new javax.swing.JButton();
        jButtonFin = new javax.swing.JButton();
        jLabelTiempo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nivel Difícil");
        setBounds(new java.awt.Rectangle(0, 0, 700, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonInicio.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButtonInicio.setText("Inicio");
        jButtonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 60, -1));

        jButtonFin.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButtonFin.setText("Fin");
        jButtonFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, 50, -1));

        jLabelTiempo.setFont(new java.awt.Font("Consolas", 3, 12)); // NOI18N
        getContentPane().add(jLabelTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, 150, 52));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 570, 20));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 20));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 20, 580));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 580));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 500, 60));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 420, 40));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 90, 100));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 250, 70));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 90, 70));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, 90, 20));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 430, 60));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 260, 100));
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 260, 60));
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 260, 100));
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 430, 60));
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 20, 70));
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 20, 70));
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 20, 70));
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 20, 70));
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 20, 70));
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 20, 70));
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 20, 70));
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 20, 70));
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 20, 70));
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 20, 40));
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, 20, 70));
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 20, 70));
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 40, 70));
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 20, 70));
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 30, 290));
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 20, 70));
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, 20, 70));
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 20, 70));
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 20, 70));
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 20, 70));
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 20, 70));
        getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 30, 90));
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 20, 70));
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 20, 70));
        getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 20, 70));
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 20, 70));
        getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 20, 70));
        getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 20, 70));
        getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 20, 70));
        getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 90, 20));
        getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 90, 20));
        getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 90, 20));
        getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 90, 20));
        getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 90, 20));
        getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 90, 20));
        getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 90, 20));
        getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 90, 20));
        getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 180, 70));
        getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 90, 20));
        getContentPane().add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 90, 60));
        getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 90, 20));
        getContentPane().add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 90, 20));
        getContentPane().add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 90, 20));
        getContentPane().add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 20, 70));
        getContentPane().add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 20, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //INICIO JUEGO
    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioActionPerformed
        tiempo.Contar();
        //Añado el MousseListener a los componentes que necesito
        aniadirMousseListener(this);
    }//GEN-LAST:event_jButtonInicioActionPerformed

    //FIN JUEGO
    private void jButtonFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinActionPerformed
        tiempo.Detener();
        //Actualizo el atributo tiempo y nivel en ventana principal
        ventanaPrincipal.setTiempo(tiempo.getSegundos());
        ventanaPrincipal.setNivel("Nivel Difícil");
        //Abro la ventana pedir nombre
        PedirNombre pedirNombre = new PedirNombre(ventanaPrincipal, true);
        pedirNombre.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonFinActionPerformed

    //Actualizar segundos
    public void actualizarSegundos(int segundos) {
        jLabelTiempo.setText(" TIEMPO: " + segundos + " segundos");
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
            java.util.logging.Logger.getLogger(NivelDificil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NivelDificil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NivelDificil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NivelDificil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NivelDificil dialog = new NivelDificil(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonFin;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTiempo;
    // End of variables declaration//GEN-END:variables
}
