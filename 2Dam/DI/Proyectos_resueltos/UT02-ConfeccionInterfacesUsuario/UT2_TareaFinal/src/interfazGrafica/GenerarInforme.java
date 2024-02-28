package interfazGrafica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import logica.Cliente;
import logica.Constantes;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;

/**
 *
 * @author Walter
 */
public class GenerarInforme extends javax.swing.JDialog {

    //Variables Globales
    private VentanaPrincipal ventanaPrincipal;
    private static Cliente cliente;

    //Constructor
    public GenerarInforme(java.awt.Frame parent, boolean modal, Cliente cliente) {
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
        panelPrincipal.add(jLabelGenerarInfome);
        panelPrincipal.add(jLabelImagen);
        panelPrincipal.add(jLabelNumCliente);
        panelPrincipal.add(jLabelValorNumCliente);
        panelPrincipal.add(jLabelDni);
        panelPrincipal.add(jLabelValorDni);
        panelPrincipal.add(jLabelDireccion);
        panelPrincipal.add(jLabelValorDireccion);
        panelPrincipal.add(jLabelTelefono);
        panelPrincipal.add(jLabelValorTelefono);
        panelPrincipal.add(jLabelDiaContacto);
        panelPrincipal.add(jFormattedTextFieldDiaContacto);
        panelPrincipal.add(jLabelHoraContacto);
        panelPrincipal.add(jLabelFormatoDia);
        panelPrincipal.add(jLabelFormatoHora);
        panelPrincipal.add(jFormattedTextFieldHora);
        panelPrincipal.add(jLabelObservaciones);
        panelPrincipal.add(jTextFieldObservaciones);
        panelPrincipal.add(jButtonGenerarInforme);
    }

    //Este método implementa el validationGroup
    public void validationGroup() {
        ValidationGroup group = this.validationPanel1.getValidationGroup();
        group.add(this.jFormattedTextFieldDiaContacto, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jFormattedTextFieldDiaContacto, StringValidators.NO_WHITESPACE);
        group.add(this.jFormattedTextFieldHora, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(this.jFormattedTextFieldHora, StringValidators.NO_WHITESPACE);
        jButtonGenerarInforme.setEnabled(false);
        validationPanel1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (validationPanel1.getProblem() == null) {
                    jButtonGenerarInforme.setEnabled(true);
                }
            }
        });
    }

    //Rellena el formulario con los datos del cliente actuales
    public void rellenarDatosCliente() {
        //Recupero los datos del cliente actuales en String para establecerlos en los JLabel
        String numCliente = String.valueOf(cliente.getNumCliente());
        jLabelValorNumCliente.setText(numCliente);
        jLabelValorDni.setText(cliente.getDni());
        jLabelValorDireccion.setText(cliente.getDireccion());
        String telefono = String.valueOf(cliente.getTelefono());
        jLabelValorTelefono.setText(telefono);
        jLabelImagen.setIcon(cliente.getRepresentacion());
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        validationPanel1 = new org.netbeans.validation.api.ui.swing.ValidationPanel();
        jLabelGenerarInfome = new javax.swing.JLabel();
        jLabelDni = new javax.swing.JLabel();
        jButtonGenerarInforme = new javax.swing.JButton();
        jLabelNumCliente = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jLabelValorNumCliente = new javax.swing.JLabel();
        jLabelValorDni = new javax.swing.JLabel();
        jLabelValorDireccion = new javax.swing.JLabel();
        jLabelValorTelefono = new javax.swing.JLabel();
        jLabelDiaContacto = new javax.swing.JLabel();
        jLabelHoraContacto = new javax.swing.JLabel();
        jLabelFormatoDia = new javax.swing.JLabel();
        jLabelFormatoHora = new javax.swing.JLabel();
        jFormattedTextFieldDiaContacto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldHora = new javax.swing.JFormattedTextField();
        jLabelObservaciones = new javax.swing.JLabel();
        jLabelImagen = new javax.swing.JLabel();
        jTextFieldObservaciones = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 700));

        validationPanel1.setOpaque(false);

        jLabelGenerarInfome.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        jLabelGenerarInfome.setForeground(new java.awt.Color(38, 60, 61));
        jLabelGenerarInfome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelGenerarInfome.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelGenerarInfome.text")); // NOI18N
        jLabelGenerarInfome.setToolTipText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelGenerarInfome.toolTipText")); // NOI18N

        jLabelDni.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelDni.setForeground(new java.awt.Color(38, 60, 61));
        jLabelDni.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelDni.text")); // NOI18N

        jButtonGenerarInforme.setBackground(new java.awt.Color(251, 242, 242));
        jButtonGenerarInforme.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonGenerarInforme.setForeground(new java.awt.Color(38, 60, 61));
        jButtonGenerarInforme.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jButtonGenerarInforme.text")); // NOI18N
        jButtonGenerarInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarInformeActionPerformed(evt);
            }
        });

        jLabelNumCliente.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelNumCliente.setForeground(new java.awt.Color(38, 60, 61));
        jLabelNumCliente.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelNumCliente.text")); // NOI18N

        jLabelDireccion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelDireccion.setForeground(new java.awt.Color(38, 60, 61));
        jLabelDireccion.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelDireccion.text")); // NOI18N

        jLabelTelefono.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelTelefono.setForeground(new java.awt.Color(38, 60, 61));
        jLabelTelefono.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelTelefono.text")); // NOI18N

        jLabelValorNumCliente.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jLabelValorNumCliente.setForeground(new java.awt.Color(67, 112, 114));
        jLabelValorNumCliente.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelValorNumCliente.text")); // NOI18N

        jLabelValorDni.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jLabelValorDni.setForeground(new java.awt.Color(67, 112, 114));
        jLabelValorDni.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelValorDni.text")); // NOI18N

        jLabelValorDireccion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jLabelValorDireccion.setForeground(new java.awt.Color(67, 112, 114));
        jLabelValorDireccion.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelValorDireccion.text")); // NOI18N

        jLabelValorTelefono.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jLabelValorTelefono.setForeground(new java.awt.Color(67, 112, 114));
        jLabelValorTelefono.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelValorTelefono.text")); // NOI18N

        jLabelDiaContacto.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelDiaContacto.setForeground(new java.awt.Color(38, 60, 61));
        jLabelDiaContacto.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelDiaContacto.text")); // NOI18N

        jLabelHoraContacto.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelHoraContacto.setForeground(new java.awt.Color(38, 60, 61));
        jLabelHoraContacto.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelHoraContacto.text")); // NOI18N

        jLabelFormatoDia.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 14)); // NOI18N
        jLabelFormatoDia.setForeground(new java.awt.Color(38, 60, 61));
        jLabelFormatoDia.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelFormatoDia.text")); // NOI18N

        jLabelFormatoHora.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 14)); // NOI18N
        jLabelFormatoHora.setForeground(new java.awt.Color(38, 60, 61));
        jLabelFormatoHora.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelFormatoHora.text")); // NOI18N

        jFormattedTextFieldDiaContacto.setForeground(new java.awt.Color(67, 112, 114));
        jFormattedTextFieldDiaContacto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/M/yyyy"))));
        jFormattedTextFieldDiaContacto.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jFormattedTextFieldDiaContacto.text")); // NOI18N
        jFormattedTextFieldDiaContacto.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N

        jFormattedTextFieldHora.setForeground(new java.awt.Color(38, 60, 61));
        jFormattedTextFieldHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        jFormattedTextFieldHora.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jFormattedTextFieldHora.text")); // NOI18N
        jFormattedTextFieldHora.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N

        jLabelObservaciones.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelObservaciones.setForeground(new java.awt.Color(38, 60, 61));
        jLabelObservaciones.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelObservaciones.text")); // NOI18N

        jLabelImagen.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jLabelImagen.text")); // NOI18N
        jLabelImagen.setOpaque(true);
        jLabelImagen.setPreferredSize(new java.awt.Dimension(150, 150));

        jTextFieldObservaciones.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldObservaciones.setForeground(new java.awt.Color(38, 60, 61));
        jTextFieldObservaciones.setText(org.openide.util.NbBundle.getMessage(GenerarInforme.class, "GenerarInforme.jTextFieldObservaciones.text")); // NOI18N

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGenerarInfome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDni)
                            .addComponent(jLabelNumCliente)
                            .addComponent(jLabelDireccion)
                            .addComponent(jLabelTelefono)
                            .addComponent(jLabelDiaContacto)
                            .addComponent(jLabelHoraContacto)
                            .addComponent(jLabelObservaciones))
                        .addGap(42, 42, 42)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelValorNumCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelValorDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelValorDni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelValorTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldDiaContacto)
                            .addComponent(jFormattedTextFieldHora)
                            .addComponent(jTextFieldObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabelFormatoHora)
                                .addGap(60, 60, 60))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabelFormatoDia)
                                .addGap(41, 41, 41))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jButtonGenerarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelGenerarInfome)
                .addGap(18, 18, 18)
                .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNumCliente)
                    .addComponent(jLabelValorNumCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelValorDni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDni, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelValorDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDireccion))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTelefono)
                    .addComponent(jLabelValorTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelFormatoDia)
                .addGap(8, 8, 8)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDiaContacto)
                    .addComponent(jFormattedTextFieldDiaContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabelFormatoHora)
                .addGap(10, 10, 10)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHoraContacto)
                    .addComponent(jFormattedTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelObservaciones)
                    .addComponent(jTextFieldObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(jButtonGenerarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
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
    private void jButtonGenerarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarInformeActionPerformed
        //Recupero datos necesarios
        String diaContacto = jFormattedTextFieldDiaContacto.getText();
        String horaContacto = jFormattedTextFieldHora.getText();
        String observaciones = jTextFieldObservaciones.getText();
        File informe = generarNombreInforme(diaContacto, horaContacto, cliente.getNumCliente());
        //Escritura
        try {
            //Abro flujos
            PrintWriter pw = new PrintWriter(new FileWriter(informe));
            //ESCRIBO
            pw.println("***********************************************");
            pw.println("**************** I N F O R M E ****************");
            pw.println("***********************************************");
            pw.println("NÚMERO CLIENTE: " + cliente.getNumCliente());
            pw.println("DNI: " + cliente.getDni());
            pw.println("DÍA DE CONTACTO: " + diaContacto);
            pw.println("HORA DE CONTACTO: " + horaContacto);
            pw.println("OBSERVACIONES: " + observaciones);

            //Cierro flujos
            pw.close();
        } catch (IOException ex) {
            System.out.println("ERROR: NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO.");
        }
        //Muestro mensaje satisfactorio
        JOptionPane.showMessageDialog(this, "¡Informe generado correctamente!", "Informe", JOptionPane.INFORMATION_MESSAGE);
        dispose();//Cierro ventana
    }//GEN-LAST:event_jButtonGenerarInformeActionPerformed

    //Método que devuelve un File del fichero informe que hay que crear según los datos
    public File generarNombreInforme(String diaContacto, String horaContacto, int numCliente) {
        String nombreInforme = numCliente + "_" + diaContacto.replaceAll("/", "_") + "_" + horaContacto.replaceAll(":", "_") + ".txt";
        return new File(Constantes.RUTA_INFORME + nombreInforme);
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
            java.util.logging.Logger.getLogger(GenerarInforme.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerarInforme.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerarInforme.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerarInforme.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GenerarInforme dialog = new GenerarInforme(new javax.swing.JFrame(), true, cliente);
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
    private javax.swing.JButton jButtonGenerarInforme;
    private javax.swing.JFormattedTextField jFormattedTextFieldDiaContacto;
    private javax.swing.JFormattedTextField jFormattedTextFieldHora;
    private javax.swing.JLabel jLabelDiaContacto;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelFormatoDia;
    private javax.swing.JLabel jLabelFormatoHora;
    private javax.swing.JLabel jLabelGenerarInfome;
    private javax.swing.JLabel jLabelHoraContacto;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelNumCliente;
    private javax.swing.JLabel jLabelObservaciones;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelValorDireccion;
    private javax.swing.JLabel jLabelValorDni;
    private javax.swing.JLabel jLabelValorNumCliente;
    private javax.swing.JLabel jLabelValorTelefono;
    private javax.swing.JTextField jTextFieldObservaciones;
    private javax.swing.JPanel panelPrincipal;
    private org.netbeans.validation.api.ui.swing.ValidationPanel validationPanel1;
    // End of variables declaration//GEN-END:variables
}
