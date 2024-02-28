package interfazGrafica;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import logica.Cliente;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;

/**
 *
 * @author Walter
 */
public class ActualizarCliente extends javax.swing.JDialog {

    //Variables Globales
    private VentanaPrincipal ventanaPrincipal;
    private static Cliente cliente;

    //Constructor
    public ActualizarCliente(java.awt.Frame parent, boolean modal, Cliente cliente) {
        super(parent, modal);
        initComponents();
        this.cliente = cliente;
        this.ventanaPrincipal = (VentanaPrincipal) parent;
        rellenarDatosCliente();//Rellena el formulario con los datos del cliente actuales
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
        panelPrincipal.add(jButtonActualizar);

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
        jButtonActualizar.setEnabled(false);

        validationPanel1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (validationPanel1.getProblem() == null) {
                    jButtonActualizar.setEnabled(true);
                }
            }
        });
    }

    //Rellena el formulario con los datos del cliente actuales
    public void rellenarDatosCliente() {
        //Recupero los datos del cliente actuales en String para establecerlos en los JTextField
        String numCliente = String.valueOf(cliente.getNumCliente());
        jTextFieldNumCliente.setText(numCliente);
        jTextFieldDni.setText(cliente.getDni());
        String fechaAlta = cliente.getFechaAlta().getDayOfMonth() + "/" + cliente.getFechaAlta().getMonthValue() + "/" + cliente.getFechaAlta().getYear();
        jFormattedTextFieldFechaAlta.setText(fechaAlta);
        jTextFieldDireccion.setText(cliente.getDireccion());
        String telefono = String.valueOf(cliente.getTelefono());
        jTextFieldTelefono.setText(telefono);
        jTextFieldFoto.setText(cliente.getFoto());
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
        jButtonActualizar = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 700));

        validationPanel1.setOpaque(false);

        jLabelAltaCliente.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        jLabelAltaCliente.setForeground(new java.awt.Color(38, 60, 61));
        jLabelAltaCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAltaCliente.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelAltaCliente.text")); // NOI18N
        jLabelAltaCliente.setToolTipText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelAltaCliente.toolTipText")); // NOI18N

        jLabelDni.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelDni.setForeground(new java.awt.Color(38, 60, 61));
        jLabelDni.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelDni.text")); // NOI18N

        jTextFieldDni.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldDni.setForeground(new java.awt.Color(67, 112, 114));

        jButtonActualizar.setBackground(new java.awt.Color(251, 242, 242));
        jButtonActualizar.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonActualizar.setForeground(new java.awt.Color(38, 60, 61));
        jButtonActualizar.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jButtonActualizar.text")); // NOI18N
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jLabelNumCliente.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelNumCliente.setForeground(new java.awt.Color(38, 60, 61));
        jLabelNumCliente.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelNumCliente.text")); // NOI18N

        jTextFieldNumCliente.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldNumCliente.setForeground(new java.awt.Color(67, 112, 114));

        jLabelFoto.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelFoto.setForeground(new java.awt.Color(38, 60, 61));
        jLabelFoto.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelFoto.text")); // NOI18N

        jLabelFechaAlta.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelFechaAlta.setForeground(new java.awt.Color(38, 60, 61));
        jLabelFechaAlta.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelFechaAlta.text")); // NOI18N

        jLabelDireccion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelDireccion.setForeground(new java.awt.Color(38, 60, 61));
        jLabelDireccion.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelDireccion.text")); // NOI18N

        jLabelTelefono.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelTelefono.setForeground(new java.awt.Color(38, 60, 61));
        jLabelTelefono.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelTelefono.text")); // NOI18N

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
        jLabelFormato.setText(org.openide.util.NbBundle.getMessage(ActualizarCliente.class, "ActualizarCliente.jLabelFormato.text")); // NOI18N

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelAltaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                        .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(66, 66, 66)
                .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        //Recupero datos
        int numCliente = Integer.parseInt(jTextFieldNumCliente.getText());
        String dni = jTextFieldDni.getText();
        String[] fecha = jFormattedTextFieldFechaAlta.getText().split("/");
        LocalDate fechaAlta = LocalDate.of(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));
        int telefono = Integer.parseInt(jTextFieldTelefono.getText());
        //Actualizo datos cliente
        cliente.setNumCliente(numCliente);
        cliente.setDni(dni);
        cliente.setFechaAlta(fechaAlta);
        cliente.setDireccion(jTextFieldDireccion.getText());
        cliente.setTelefono(telefono);
        cliente.setFoto(jTextFieldFoto.getText());
        //Muestro mensaje satisfactorio
        JOptionPane.showMessageDialog(this, "¡Cliente actualizado correctamente!", "Actualización", JOptionPane.INFORMATION_MESSAGE);
        dispose();//Cierro ventana
    }//GEN-LAST:event_jButtonActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(ActualizarCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ActualizarCliente dialog = new ActualizarCliente(new javax.swing.JFrame(), true, cliente);
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
    private javax.swing.JButton jButtonActualizar;
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
