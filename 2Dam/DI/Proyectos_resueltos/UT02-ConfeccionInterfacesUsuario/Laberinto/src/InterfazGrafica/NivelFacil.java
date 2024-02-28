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
public class NivelFacil extends javax.swing.JDialog {

    //Variables globales
    VentanaPrincipal ventanaPrincipal;
    Tiempo tiempo = new Tiempo(this);
    MiMousseListener miMousseListener = new MiMousseListener(this);

    //Constructor
    public NivelFacil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setLayout(null);//Establezco diseño absoluto
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
                }else{//Cambio el color del tiempo
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nivel Fácil");
        setBounds(new java.awt.Rectangle(0, 0, 700, 700));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonInicio.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButtonInicio.setText("Inicio");
        jButtonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, -1));

        jButtonFin.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButtonFin.setText("Fin");
        jButtonFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, 50, -1));

        jLabelTiempo.setFont(new java.awt.Font("Consolas", 3, 12)); // NOI18N
        getContentPane().add(jLabelTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, 150, 51));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 567, 20));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 20, 580));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 20, 580));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 567, 20));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 500, 60));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 16, 490, 70));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 520, 90));

        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 180, 290, 70));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 76, 220, 240));

        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 500, 200));

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
        ventanaPrincipal.setNivel("Nivel Fácil");
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
            java.util.logging.Logger.getLogger(NivelFacil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NivelFacil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NivelFacil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NivelFacil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NivelFacil dialog = new NivelFacil(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTiempo;
    // End of variables declaration//GEN-END:variables
}
