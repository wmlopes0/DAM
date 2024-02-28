package interfazGrafica;

import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;

/**
 *
 * @author Walter
 */
public class Login extends javax.swing.JDialog {

    //Variables globales
    private final VentanaPrincipal ventanaPrincipal;
    public Map<String, String> usuarios;

    //Constructor
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.ventanaPrincipal = (VentanaPrincipal) parent;
        usuarios = this.ventanaPrincipal.usuarios;
        usuarios = logica.Utileria.cargarDatosLogin();//Cargo los datos de los usuarios registrados
        establecerFondo();//Establezco el JPanel de fondo
        aniadirComponentes();//Añado los componentes al JPanels
        validationGroup();//Implemento el validationGroup
        setLocationRelativeTo(null);//Centro la ventana en el centro de la pantalla
    }

    //Establece un JPanel de fondo con una imagen
    public void establecerFondo() {
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
        panelPrincipal.add(jButtonIniciarSesion);
        panelPrincipal.add(jButtonRegistrarse);
        panelPrincipal.add(jLabelUsuario);
        panelPrincipal.add(jLabelContrasenia);
        panelPrincipal.add(jLabelInicioSesion);
    }

    //Este método implementa el validationGroup
    public void validationGroup() {
        ValidationGroup group = this.validationPanel1.getValidationGroup();
        group.add(this.jTextFieldUsuario, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jPasswordFieldContrasenia, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jTextFieldUsuario, StringValidators.NO_WHITESPACE);
        group.add(this.jPasswordFieldContrasenia, StringValidators.NO_WHITESPACE);
        jButtonIniciarSesion.setEnabled(false);

        validationPanel1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (validationPanel1.getProblem() == null) {
                    jButtonIniciarSesion.setEnabled(true);
                    jButtonRegistrarse.setEnabled(false);
                } else {
                    jButtonIniciarSesion.setEnabled(false);
                    jButtonRegistrarse.setEnabled(true);
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
        jButtonIniciarSesion = new javax.swing.JButton();
        jButtonRegistrarse = new javax.swing.JButton();
        jLabelInicioSesion = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelContrasenia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 500));

        validationPanel1.setOpaque(false);

        jTextFieldUsuario.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldUsuario.setForeground(new java.awt.Color(67, 112, 114));

        jPasswordFieldContrasenia.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jPasswordFieldContrasenia.setForeground(new java.awt.Color(67, 112, 114));

        jButtonIniciarSesion.setBackground(new java.awt.Color(251, 242, 242));
        jButtonIniciarSesion.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonIniciarSesion.setForeground(new java.awt.Color(38, 60, 61));
        jButtonIniciarSesion.setText("Iniciar Sesión");
        jButtonIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarSesionActionPerformed(evt);
            }
        });

        jButtonRegistrarse.setBackground(new java.awt.Color(251, 242, 242));
        jButtonRegistrarse.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonRegistrarse.setForeground(new java.awt.Color(38, 60, 61));
        jButtonRegistrarse.setText("Registrarse");
        jButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarseActionPerformed(evt);
            }
        });

        jLabelInicioSesion.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        jLabelInicioSesion.setForeground(new java.awt.Color(38, 60, 61));
        jLabelInicioSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInicioSesion.setText(org.openide.util.NbBundle.getMessage(Login.class, "Registrarse.jLabelRegistrarse.text")); // NOI18N
        jLabelInicioSesion.setToolTipText(org.openide.util.NbBundle.getMessage(Login.class, "Registrarse.jLabelRegistrarse.toolTipText")); // NOI18N

        jLabelUsuario.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(38, 60, 61));
        jLabelUsuario.setText(org.openide.util.NbBundle.getMessage(Login.class, "Registrarse.jLabelUsuario.text")); // NOI18N

        jLabelContrasenia.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelContrasenia.setForeground(new java.awt.Color(38, 60, 61));
        jLabelContrasenia.setText(org.openide.util.NbBundle.getMessage(Login.class, "Registrarse.jLabelContrasenia.text")); // NOI18N

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelInicioSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(jButtonIniciarSesion)
                        .addGap(66, 66, 66)
                        .addComponent(jButtonRegistrarse))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelContrasenia)
                            .addComponent(jLabelUsuario))
                        .addGap(41, 41, 41)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jPasswordFieldContrasenia))))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelInicioSesion)
                .addGap(49, 49, 49)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIniciarSesion)
                    .addComponent(jButtonRegistrarse))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botón iniciar sesión
    private void jButtonIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarSesionActionPerformed
        //Obtengo los datos que el usuario ha introducido en los campos
        String nombre = jTextFieldUsuario.getText();
        String psw = new String(jPasswordFieldContrasenia.getPassword());
        //Codifico a MD5 para comparar
        psw = logica.Utileria.encodeToMD5(psw);
        //Controlo si el usuario existe
        if (usuarios.containsKey(nombre)) {
            //Controlo si la contraseña es correcta
            if (usuarios.get(nombre).equals(psw)) {
                ventanaPrincipal.setLoginCorrecto(true);//Establezco a true el login correcto para que deje acceder al programa
                dispose();
            } else {
                //Muestro mensaje de contraseña incorrecta
                JOptionPane.showMessageDialog(this, "¡Contraseña incorrecta!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            //Muestro mensaje de error, ya que no existe ese usuario
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonIniciarSesionActionPerformed

    //Botón registrarse
    private void jButtonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarseActionPerformed
        Registrarse ventanaRegistrarse = new Registrarse(ventanaPrincipal, true);
        ventanaRegistrarse.setVisible(true);
        //Refresco los datos de usuarios
        usuarios = logica.Utileria.cargarDatosLogin();
    }//GEN-LAST:event_jButtonRegistrarseActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonIniciarSesion;
    private javax.swing.JButton jButtonRegistrarse;
    private javax.swing.JLabel jLabelContrasenia;
    private javax.swing.JLabel jLabelInicioSesion;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPasswordFieldContrasenia;
    private javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JPanel panelPrincipal;
    private org.netbeans.validation.api.ui.swing.ValidationPanel validationPanel1;
    // End of variables declaration//GEN-END:variables
}
