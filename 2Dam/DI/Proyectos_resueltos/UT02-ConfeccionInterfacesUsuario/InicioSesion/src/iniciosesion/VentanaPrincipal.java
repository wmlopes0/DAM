package iniciosesion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;
import org.openide.util.Exceptions;

/**
 *
 * @author wmartinl01
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //Variables globales
    public final static File FICHERO = new File("Users.txt");
    public Map<String, String> usuarios = new LinkedHashMap<>();

    //Constructor
    public VentanaPrincipal() {
        initComponents();

        //Muestro contraseñas de prueba, ya que estan codificadas en el fichero
        mostrarPswConsola();

        //Cargo los datos
        usuarios = cargarDatos();

        //ValidationGroup====================================================================================
        ValidationGroup group = this.validationPanel1.getValidationGroup();
        group.add(this.jTextFieldNombre, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jPasswordFieldContrasenia, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jTextFieldNombre, StringValidators.NO_WHITESPACE);
        group.add(this.jPasswordFieldContrasenia, StringValidators.NO_WHITESPACE);
        jButtonValidar.setEnabled(false);

        validationPanel1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (validationPanel1.getProblem() == null) {
                    jButtonValidar.setEnabled(true);
                    jButtonRegistrarse.setEnabled(false);
                } else {
                    jButtonValidar.setEnabled(false);
                    jButtonRegistrarse.setEnabled(true);
                }
            }
        });
        //===================================================================================================
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonRegistrarse = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jPasswordFieldContrasenia = new javax.swing.JPasswordField();
        jButtonValidar = new javax.swing.JButton();
        validationPanel1 = new org.netbeans.validation.api.ui.swing.ValidationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonRegistrarse.setFont(new java.awt.Font("Rubik", 3, 14)); // NOI18N
        jButtonRegistrarse.setText("Registrarse");
        jButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarseActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Rubik", 3, 24)); // NOI18N
        jLabel3.setText("INICIO DE SESIÓN");

        jLabel1.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña");

        jTextFieldNombre.setFont(new java.awt.Font("Rubik", 2, 14)); // NOI18N

        jPasswordFieldContrasenia.setFont(new java.awt.Font("Rubik", 2, 14)); // NOI18N

        jButtonValidar.setFont(new java.awt.Font("Rubik", 3, 14)); // NOI18N
        jButtonValidar.setText("Validar");
        jButtonValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(jButtonValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(jButtonRegistrarse))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addGap(60, 60, 60)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldNombre)
                                        .addComponent(jPasswordFieldContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addComponent(jLabel3))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordFieldContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegistrarse)
                    .addComponent(jButtonValidar))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Validar usuario
    private void jButtonValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidarActionPerformed
        //Obtengo los datos que el usuario ha introducido en los campos
        String nombre = jTextFieldNombre.getText();
        String psw = new String(jPasswordFieldContrasenia.getPassword());
        //Codifico a MD5 para comparar
        psw = Utileria.encodeToMD5(psw);
        //Controlo si el usuario existe
        if (usuarios.containsKey(nombre)) {
            //Controlo si la contraseña es correcta
            if (usuarios.get(nombre).equals(psw)) {
                //Muestro mensaje de login correcto
                JOptionPane.showMessageDialog(this, "¡Bienvenido " + nombre + "!", "Inicio de sesión correcto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //Muestro mensaje de contraseña incorrecta
                JOptionPane.showMessageDialog(this, "¡Contraseña incorrecta!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            //Muestro mensaje de error, ya que no existe ese usuario
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonValidarActionPerformed

    //Método que lee los datos del fichero y los almacena en el mapa
    private Map<String, String> cargarDatos() {
        Map<String, String> lUsuarios = new LinkedHashMap<>();
        try {
            //Abro flujos
            BufferedReader br = new BufferedReader(new FileReader(FICHERO));
            String linea = br.readLine();
            String[] usuario;
            //Leo mientras haya usuarios
            while (linea != null) {
                usuario = linea.split("-");
                lUsuarios.put(usuario[0], usuario[1]);
                linea = br.readLine();
            }
            //Cierro flujos
            br.close();
        } catch (IOException ex) {
            System.out.println("ERROR: NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO.");
        }
        //Retorno mapa
        return lUsuarios;
    }

    //Método que muestra las contraseñas de prueba por consola
    public static void mostrarPswConsola() {
        System.out.println("*************************************");
        System.out.println("******* C O N T R A S E Ñ A S *******");
        System.out.println("*************************************");
        System.out.println("USUARIO: Walter | CONTRASEÑA: 'papita'");
        System.out.println("USUARIO: Izzy | CONTRASEÑA: 'coco'");
        System.out.println("USUARIO: Pepe | CONTRASEÑA: 'dInterfaces'");
    }

    //Registrarse
    private void jButtonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarseActionPerformed
        VentanaRegistrarse ventanaRegistrarse = new VentanaRegistrarse(this, true);
        ventanaRegistrarse.setVisible(true);
        //Refresco los datos de usuarios
        usuarios = cargarDatos();
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
    private javax.swing.JButton jButtonRegistrarse;
    private javax.swing.JButton jButtonValidar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordFieldContrasenia;
    private javax.swing.JTextField jTextFieldNombre;
    private org.netbeans.validation.api.ui.swing.ValidationPanel validationPanel1;
    // End of variables declaration//GEN-END:variables
}
