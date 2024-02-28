package interfazGrafica;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import logica.Cliente;
import logica.Utileria;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;

/**
 *
 * @author Walter
 */
public class AltaCliente extends javax.swing.JDialog {

    //Variables Globales
    private VentanaPrincipal ventanaPrincipal;
    private List<Cliente> listaClientes;

    //Constructor
    public AltaCliente(java.awt.Frame parent, boolean modal) {
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
        panelPrincipal = new PanelFondo("src/interfazGrafica/IMG/3.png");
        panelPrincipal.setLayout(null);//Layout absoluto
        // Establecer el JPanel como el contenido del Jframe
        this.setContentPane(panelPrincipal);
    }

    //En este método se añaden los componentes al JPanel para que sean visibles
    public void aniadirComponentes() {
        panelPrincipal.add(validationPanel1);
        panelPrincipal.add(jLabelAltaCliente);
        panelPrincipal.add(jLabelNumCliente);
        panelPrincipal.add(jTextFieldNumCliente);
        panelPrincipal.add(jLabelDni);
        panelPrincipal.add(jTextFieldDni);
        panelPrincipal.add(jLabelFechaAlta);
        panelPrincipal.add(jFormattedTextFieldFechaAlta);
        panelPrincipal.add(jLabelDireccion);
        panelPrincipal.add(jTextFieldDireccion);
        panelPrincipal.add(jLabelTelefono);
        panelPrincipal.add(jTextFieldTelefono);
        panelPrincipal.add(jLabelFoto);
        panelPrincipal.add(jTextFieldFoto);
        panelPrincipal.add(jLabelFormato);
        panelPrincipal.add(jButtonAlta);
        panelPrincipal.add(jButtonAyuda);

    }

    //Este método implementa el validationGroup
    public void validationGroup() {
        ValidationGroup group = this.validationPanel1.getValidationGroup();
        group.add(this.jTextFieldNumCliente, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jTextFieldNumCliente, StringValidators.NO_WHITESPACE);
        group.add(this.jTextFieldDni, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jTextFieldDni, StringValidators.NO_WHITESPACE);
        group.add(this.jFormattedTextFieldFechaAlta, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jFormattedTextFieldFechaAlta, StringValidators.NO_WHITESPACE);
        group.add(this.jTextFieldDireccion, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jTextFieldTelefono, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jTextFieldTelefono, StringValidators.NO_WHITESPACE);
        jButtonAlta.setEnabled(false);

        validationPanel1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (validationPanel1.getProblem() == null) {
                    jButtonAlta.setEnabled(true);
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
        jLabelAltaCliente = new javax.swing.JLabel();
        jLabelDni = new javax.swing.JLabel();
        jTextFieldDni = new javax.swing.JTextField();
        jButtonAlta = new javax.swing.JButton();
        jLabelNumCliente = new javax.swing.JLabel();
        jTextFieldNumCliente = new javax.swing.JTextField();
        jLabelFoto = new javax.swing.JLabel();
        jLabelFechaAlta = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jTextFieldFoto = new javax.swing.JTextField();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jFormattedTextFieldFechaAlta = new javax.swing.JFormattedTextField();
        jLabelFormato = new javax.swing.JLabel();
        jButtonAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 700));

        validationPanel1.setOpaque(false);

        jLabelAltaCliente.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        jLabelAltaCliente.setForeground(new java.awt.Color(38, 60, 61));
        jLabelAltaCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAltaCliente.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelAltaCliente.text")); // NOI18N
        jLabelAltaCliente.setToolTipText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelAltaCliente.toolTipText")); // NOI18N

        jLabelDni.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelDni.setForeground(new java.awt.Color(38, 60, 61));
        jLabelDni.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelDni.text")); // NOI18N

        jTextFieldDni.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldDni.setForeground(new java.awt.Color(67, 112, 114));

        jButtonAlta.setBackground(new java.awt.Color(251, 242, 242));
        jButtonAlta.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonAlta.setForeground(new java.awt.Color(38, 60, 61));
        jButtonAlta.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jButtonAlta.text")); // NOI18N
        jButtonAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAltaActionPerformed(evt);
            }
        });

        jLabelNumCliente.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelNumCliente.setForeground(new java.awt.Color(38, 60, 61));
        jLabelNumCliente.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelNumCliente.text")); // NOI18N

        jTextFieldNumCliente.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldNumCliente.setForeground(new java.awt.Color(67, 112, 114));

        jLabelFoto.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelFoto.setForeground(new java.awt.Color(38, 60, 61));
        jLabelFoto.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelFoto.text")); // NOI18N

        jLabelFechaAlta.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelFechaAlta.setForeground(new java.awt.Color(38, 60, 61));
        jLabelFechaAlta.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelFechaAlta.text")); // NOI18N

        jLabelDireccion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelDireccion.setForeground(new java.awt.Color(38, 60, 61));
        jLabelDireccion.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelDireccion.text")); // NOI18N

        jLabelTelefono.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelTelefono.setForeground(new java.awt.Color(38, 60, 61));
        jLabelTelefono.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelTelefono.text")); // NOI18N

        jTextFieldFoto.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldFoto.setForeground(new java.awt.Color(67, 112, 114));

        jTextFieldDireccion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldDireccion.setForeground(new java.awt.Color(67, 112, 114));

        jTextFieldTelefono.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldTelefono.setForeground(new java.awt.Color(67, 112, 114));

        jFormattedTextFieldFechaAlta.setForeground(new java.awt.Color(67, 112, 114));
        jFormattedTextFieldFechaAlta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/M/yyyy"))));
        jFormattedTextFieldFechaAlta.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N

        jLabelFormato.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 14)); // NOI18N
        jLabelFormato.setForeground(new java.awt.Color(38, 60, 61));
        jLabelFormato.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jLabelFormato.text")); // NOI18N

        jButtonAyuda.setBackground(new java.awt.Color(251, 242, 242));
        jButtonAyuda.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonAyuda.setForeground(new java.awt.Color(38, 60, 61));
        jButtonAyuda.setText(org.openide.util.NbBundle.getMessage(AltaCliente.class, "AltaCliente.jButtonAyuda.text")); // NOI18N
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDni)
                            .addComponent(jLabelNumCliente)
                            .addComponent(jLabelDireccion)
                            .addComponent(jLabelTelefono)
                            .addComponent(jLabelFoto)
                            .addComponent(jLabelFechaAlta))
                        .addGap(42, 42, 42)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldFechaAlta, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDni, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNumCliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabelFormato)
                        .addGap(40, 40, 40)))
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addComponent(jButtonAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAyuda)
                        .addGap(23, 23, 23))))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelAltaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAyuda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelAltaCliente)
                .addGap(50, 50, 50)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumCliente)
                    .addComponent(jTextFieldNumCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDni)
                    .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelFormato)
                .addGap(2, 2, 2)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaAlta)
                    .addComponent(jFormattedTextFieldFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDireccion)
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefono)
                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFoto)
                    .addComponent(jTextFieldFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(jButtonAlta)
                .addContainerGap(90, Short.MAX_VALUE))
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

    //Botón Alta
    private void jButtonAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAltaActionPerformed
        //En primer lugar controlo si ya existe un cliente con ese dni o con ese numCliente, si es así no creo el cliente e informo
        //al usuario con un JOptionPane. Si por contra no existe ninguno creo un nuevo cliente y lo escribo en el fichero

        //Recupero los datos
        int numCliente = Integer.parseInt(jTextFieldNumCliente.getText());
        String dni = jTextFieldDni.getText();
        //Fecha (dd/mm/yyyy)
        String[] textoFecha = jFormattedTextFieldFechaAlta.getText().split("/");
        LocalDate fechaAlta = LocalDate.of(Integer.parseInt(textoFecha[2]), Integer.parseInt(textoFecha[1]), Integer.parseInt(textoFecha[0]));
        String direccion = jTextFieldDireccion.getText();
        int telefono = Integer.parseInt(jTextFieldTelefono.getText());
        String foto = jTextFieldFoto.getText();

        //Controlo si existe un cliente con el mismo numCliente o con el mismo Dni
        if (Utileria.existeCliente(dni)) {
            //Muestro mensaje de error
            JOptionPane.showMessageDialog(this, "¡Ya existe un cliente con ese DNI!", "Error", JOptionPane.ERROR_MESSAGE);
            return;//Salgo del método
        }
        if (Utileria.existeCliente(numCliente)) {
            //Muestro mensaje de error
            JOptionPane.showMessageDialog(this, "¡Ya existe un cliente con ese 'numCliente'!", "Error", JOptionPane.ERROR_MESSAGE);
            return;//Salgo del método
        }

        //Creo cliente y lo escribo en el fichero y actualizo los datos de la lista en VentanaPrincipal
        Cliente cliente = new Cliente(numCliente, dni, fechaAlta, direccion, telefono, foto);
        Utileria.escribirCliente(cliente);
        ventanaPrincipal.actualizarDatos();
        //Muestro mensaje satisfactorio
        JOptionPane.showMessageDialog(this, "¡Cliente registrado correctamente!", "Alta", JOptionPane.INFORMATION_MESSAGE);
        dispose();//Cierro ventana
    }//GEN-LAST:event_jButtonAltaActionPerformed

    //Botón ayuda
    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        JOptionPane.showMessageDialog(this, "- El campo 'Foto' no es obligatorio.\n- Para añadir una foto debes introducirla en la carpeta '/interfazGrafica/IMG' y seguidamente poner su ruta en el campo 'Foto'.\n- Puedes poner la ruta completa, o simplemente el nombre de la imagen con su extensión (imagenEjemplo.png).\n- Si no añades una foto al cliente se asignará automaticamente una imagen por defecto.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonAyudaActionPerformed

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
            java.util.logging.Logger.getLogger(AltaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AltaCliente dialog = new AltaCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonAlta;
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaAlta;
    private javax.swing.JLabel jLabelAltaCliente;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelFechaAlta;
    private javax.swing.JLabel jLabelFormato;
    private javax.swing.JLabel jLabelFoto;
    private javax.swing.JLabel jLabelNumCliente;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldFoto;
    private javax.swing.JTextField jTextFieldNumCliente;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JPanel panelPrincipal;
    private org.netbeans.validation.api.ui.swing.ValidationPanel validationPanel1;
    // End of variables declaration//GEN-END:variables
}
