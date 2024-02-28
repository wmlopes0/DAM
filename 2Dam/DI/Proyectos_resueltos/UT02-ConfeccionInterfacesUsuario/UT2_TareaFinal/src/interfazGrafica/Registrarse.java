package interfazGrafica;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;

/**
 *
 * @author Walter
 */
public class Registrarse extends javax.swing.JDialog {

    //Variables globales
    private VentanaPrincipal ventanaPrincipal;

    //Constructor
    public Registrarse(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.ventanaPrincipal = (VentanaPrincipal) parent;
        establecerFondo();//Establezco el JPanel de fondo
        aniadirComponentes();//Añado los componentes al JPanels
        validationGroup();//Implemento el validationGroup
        setLocationRelativeTo(null);//Centro la ventana en el centro de la pantalla
    }

    //Establece un JPanel de fondo con una imagen
    public void establecerFondo() {
        //PanelFondo panelPrincipal = new PanelFondo("src/interfazGrafica/IMG/3.png");
        panelPrincipal = new PanelFondo("src/interfazGrafica/IMG/3.png");
        panelPrincipal.setLayout(null);//Layout absoluto
        // Establecer el JPanel como el contenido del Jframe
        this.setContentPane(panelPrincipal);
    }

    //En este método se añaden los componentes al JPanel para que sean visibles
    public void aniadirComponentes() {
        panelPrincipal.add(validationPanel1);
        panelPrincipal.add(jTextFieldUsuario);
        panelPrincipal.add(jPasswordFieldContrasenia);
        panelPrincipal.add(jButtonRegistrarse);
        panelPrincipal.add(jLabelUsuario);
        panelPrincipal.add(jLabelContrasenia);
        panelPrincipal.add(jLabelRegistrarse);
    }

    //Este método implementa el validationGroup
    public void validationGroup() {
        ValidationGroup group = this.validationPanel1.getValidationGroup();
        group.add(this.jTextFieldUsuario, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jPasswordFieldContrasenia, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jTextFieldUsuario, StringValidators.NO_WHITESPACE);
        group.add(this.jPasswordFieldContrasenia, StringValidators.NO_WHITESPACE);
        jButtonRegistrarse.setEnabled(false);

        validationPanel1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (validationPanel1.getProblem() == null) {
                    jButtonRegistrarse.setEnabled(true);
                } else {
                    jButtonRegistrarse.setEnabled(false);
                }
            }
        });
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        validationPanel1 = new org.netbeans.validation.api.ui.swing.ValidationPanel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jPasswordFieldContrasenia = new javax.swing.JPasswordField();
        jButtonRegistrarse = new javax.swing.JButton();
        jLabelRegistrarse = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelContrasenia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 500));

        panelPrincipal.setOpaque(false);

        validationPanel1.setOpaque(false);

        jTextFieldUsuario.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldUsuario.setForeground(new java.awt.Color(67, 112, 114));
        jTextFieldUsuario.setText(org.openide.util.NbBundle.getMessage(Registrarse.class, "Registrarse.jTextFieldUsuario.text")); // NOI18N
        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });

        jPasswordFieldContrasenia.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jPasswordFieldContrasenia.setForeground(new java.awt.Color(67, 112, 114));
        jPasswordFieldContrasenia.setText(org.openide.util.NbBundle.getMessage(Registrarse.class, "Registrarse.jPasswordFieldContrasenia.text")); // NOI18N

        jButtonRegistrarse.setBackground(new java.awt.Color(251, 242, 242));
        jButtonRegistrarse.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonRegistrarse.setForeground(new java.awt.Color(38, 60, 61));
        jButtonRegistrarse.setText(org.openide.util.NbBundle.getMessage(Registrarse.class, "Registrarse.jButtonRegistrarse.text")); // NOI18N
        jButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarseActionPerformed(evt);
            }
        });

        jLabelRegistrarse.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        jLabelRegistrarse.setForeground(new java.awt.Color(38, 60, 61));
        jLabelRegistrarse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRegistrarse.setText(org.openide.util.NbBundle.getMessage(Registrarse.class, "Registrarse.jLabelRegistrarse.text")); // NOI18N
        jLabelRegistrarse.setToolTipText(org.openide.util.NbBundle.getMessage(Registrarse.class, "Registrarse.jLabelRegistrarse.toolTipText")); // NOI18N

        jLabelUsuario.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(38, 60, 61));
        jLabelUsuario.setText(org.openide.util.NbBundle.getMessage(Registrarse.class, "Registrarse.jLabelUsuario.text")); // NOI18N

        jLabelContrasenia.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelContrasenia.setForeground(new java.awt.Color(38, 60, 61));
        jLabelContrasenia.setText(org.openide.util.NbBundle.getMessage(Registrarse.class, "Registrarse.jLabelContrasenia.text")); // NOI18N

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelContrasenia)
                            .addComponent(jLabelUsuario))
                        .addGap(44, 44, 44)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordFieldContrasenia)
                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jButtonRegistrarse)))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelRegistrarse)
                .addGap(51, 51, 51)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUsuario))
                .addGap(38, 38, 38)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPasswordFieldContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelContrasenia))
                .addGap(86, 86, 86)
                .addComponent(jButtonRegistrarse)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botón registrarse
    private void jButtonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarseActionPerformed
        //Recupero los datos que introduce el usuario
        String nombre = jTextFieldUsuario.getText();
        String psw = new String(jPasswordFieldContrasenia.getPassword());
        psw = logica.Utileria.encodeToMD5(psw);//Codifico la contraseña
        //Compruebo si el usuario que intenta registrar ya existe o no
        if (ventanaPrincipal.usuarios.containsKey(nombre)) {
            JOptionPane.showMessageDialog(this, "¡El usuario que intentas registrar ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //Creo flujos
            PrintWriter pw = crearFlujos();
            //Escribo
            String nuevoUsuario = nombre + "-" + psw;
            pw.println(nuevoUsuario);
            //Cierro flujos
            pw.close();
            //Muestro mensaje de registro correcto
            JOptionPane.showMessageDialog(this, "¡Usuario registrado correctamente!", "Registro correcto", JOptionPane.INFORMATION_MESSAGE);
        }
        ventanaPrincipal.usuarios = logica.Utileria.cargarDatosLogin();
        dispose();//Cierro ventana
    }//GEN-LAST:event_jButtonRegistrarseActionPerformed

    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

    //Método auxiliar para cerrar los flujos de escritura
    private PrintWriter crearFlujos() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(logica.Constantes.FICHERO_USERS, true));
            return pw;
        } catch (IOException ex) {
            return null;
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
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registrarse dialog = new Registrarse(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonRegistrarse;
    private javax.swing.JLabel jLabelContrasenia;
    private javax.swing.JLabel jLabelRegistrarse;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPasswordFieldContrasenia;
    private javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JPanel panelPrincipal;
    private org.netbeans.validation.api.ui.swing.ValidationPanel validationPanel1;
    // End of variables declaration//GEN-END:variables
}
