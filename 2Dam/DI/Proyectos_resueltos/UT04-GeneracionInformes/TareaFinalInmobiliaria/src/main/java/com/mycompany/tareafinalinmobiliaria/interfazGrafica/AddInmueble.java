package com.mycompany.tareafinalinmobiliaria.interfazGrafica;

import com.mycompany.tareafinalinmobiliaria.logica.Inmueble;
import com.mycompany.tareafinalinmobiliaria.logica.Utileria;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author wmartinl01
 */
public class AddInmueble extends javax.swing.JDialog {

    //Variables globales
    private VentanaPrincipal ventanaPrincipal;

    //Constructor
    public AddInmueble(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ventanaPrincipal = (VentanaPrincipal) parent;
        establecerFondo();//Establezco el fondo
        aniadirComponentes();//Añado los componentes al panelPrincipal
        setLocationRelativeTo(null);//Centro la ventana en el centro de la pantalla
    }

    //Establece un JPanel de fondo con una imagen
    public void establecerFondo() {
        panelPrincipal = new PanelFondo("src/main/java/com/mycompany/tareafinalinmobiliaria/interfazGrafica/IMG/3.png");
        panelPrincipal.setLayout(null);//Layout absoluto
        // Establecer el JPanel como el contenido del Jframe
        this.setContentPane(panelPrincipal);
    }

    //En este método se añaden los componentes al JPanel para que sean visibles
    public void aniadirComponentes() {
        panelPrincipal.add(jLabelAltaInmueble);
        panelPrincipal.add(jLabelTitulo);
        panelPrincipal.add(jLabelDescripcion);
        panelPrincipal.add(jLabelFoto);
        panelPrincipal.add(jLabelTransaccion);
        panelPrincipal.add(jLabelPrecio);
        panelPrincipal.add(jLabelTelefono);

        panelPrincipal.add(jTextFieldTitulo);
        panelPrincipal.add(jTextFieldDescripcion);
        panelPrincipal.add(jTextFieldFoto);
        panelPrincipal.add(jTextFieldTransaccion);
        panelPrincipal.add(jTextFieldPrecio);
        panelPrincipal.add(jTextFieldTelefono);
        panelPrincipal.add(jButtonAdd);
        panelPrincipal.add(jButtonAyuda);
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jLabelAltaInmueble = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelTransaccion = new javax.swing.JLabel();
        jLabelPrecio = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jTextFieldTitulo = new javax.swing.JTextField();
        jTextFieldTransaccion = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jLabelFoto = new javax.swing.JLabel();
        jTextFieldFoto = new javax.swing.JTextField();
        jLabelDescripcion = new javax.swing.JLabel();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jButtonAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelAltaInmueble.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        jLabelAltaInmueble.setForeground(new java.awt.Color(38, 60, 61));
        jLabelAltaInmueble.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAltaInmueble.setText("ALTA INMUEBLE");

        jLabelTitulo.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(38, 60, 61));
        jLabelTitulo.setText("Título");

        jLabelTransaccion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelTransaccion.setForeground(new java.awt.Color(38, 60, 61));
        jLabelTransaccion.setText("Transacción");

        jLabelPrecio.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelPrecio.setForeground(new java.awt.Color(38, 60, 61));
        jLabelPrecio.setText("Precio");

        jLabelTelefono.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelTelefono.setForeground(new java.awt.Color(38, 60, 61));
        jLabelTelefono.setText("Teléfono");

        jTextFieldPrecio.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldPrecio.setForeground(new java.awt.Color(67, 112, 114));

        jTextFieldTitulo.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldTitulo.setForeground(new java.awt.Color(67, 112, 114));

        jTextFieldTransaccion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldTransaccion.setForeground(new java.awt.Color(67, 112, 114));

        jTextFieldTelefono.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldTelefono.setForeground(new java.awt.Color(67, 112, 114));

        jButtonAdd.setBackground(new java.awt.Color(251, 242, 242));
        jButtonAdd.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonAdd.setForeground(new java.awt.Color(38, 60, 61));
        jButtonAdd.setText("AÑADIR");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jLabelFoto.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelFoto.setForeground(new java.awt.Color(38, 60, 61));
        jLabelFoto.setText("Foto");

        jTextFieldFoto.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldFoto.setForeground(new java.awt.Color(67, 112, 114));

        jLabelDescripcion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelDescripcion.setForeground(new java.awt.Color(38, 60, 61));
        jLabelDescripcion.setText("Descripción");

        jTextFieldDescripcion.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldDescripcion.setForeground(new java.awt.Color(67, 112, 114));

        jButtonAyuda.setBackground(new java.awt.Color(251, 242, 242));
        jButtonAyuda.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonAyuda.setForeground(new java.awt.Color(38, 60, 61));
        jButtonAyuda.setText("?");
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
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelAltaInmueble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(jButtonAdd))
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDescripcion)
                                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelTelefono)
                                        .addComponent(jLabelTitulo)
                                        .addComponent(jLabelTransaccion)
                                        .addComponent(jLabelPrecio)
                                        .addComponent(jLabelFoto)))
                                .addGap(45, 45, 45)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonAyuda)
                .addGap(20, 20, 20))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAyuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAltaInmueble)
                .addGap(40, 40, 40)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitulo)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTransaccion)
                    .addComponent(jTextFieldTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescripcion)
                    .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFoto)
                    .addComponent(jTextFieldFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrecio)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefono)
                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButtonAdd)
                .addGap(36, 36, 36))
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

    //Botón añadir inmueble
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        //Controlo que estén rellenados los campos necesarios
        if (jTextFieldTitulo.getText().equalsIgnoreCase("") || jTextFieldTransaccion.getText().equalsIgnoreCase("") || jTextFieldDescripcion.getText().equalsIgnoreCase("") || jTextFieldPrecio.getText().equalsIgnoreCase("")) {
            //Mensaje Error
            JOptionPane.showMessageDialog(this, "Debes rellenar todos los campos obligatorios. Son obligatorios todos excepto 'Foto' y 'Teléfono'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;//Salgo del método
        }
        //Recupero los valores
        String titulo = jTextFieldTitulo.getText();
        String transaccion = jTextFieldTransaccion.getText();
        String descripcion = jTextFieldDescripcion.getText();
        String foto = jTextFieldFoto.getText();
        int precio = Integer.parseInt(jTextFieldPrecio.getText());
        int telefono;
        if (jTextFieldTelefono.getText().equalsIgnoreCase("")) {
            telefono = 0;
        } else {
            telefono = Integer.parseInt(jTextFieldTelefono.getText());
        }

        //Creo objeto Inmueble
        Inmueble inmueble = new Inmueble(titulo, descripcion, foto, transaccion, precio, telefono);
        //Inserto el inmueble en la bd
        Utileria.addInmueble(inmueble);
        //Actualizo la tabla
        ventanaPrincipal.model.actualizarDatos();
        //Mensaje Check
        JOptionPane.showMessageDialog(this, "¡Inmueble añadido correctamente!", "Inmueble añadido", JOptionPane.INFORMATION_MESSAGE);
        //Cierro la ventana
        dispose();
    }//GEN-LAST:event_jButtonAddActionPerformed

    //Botón ayuda
    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        JOptionPane.showMessageDialog(this, "- El campo 'Foto' no es obligatorio, se asignará una por defecto.\n- Para añadir una foto debes introducir una ruta absoluta url.\n- El campo 'Teléfono' no es obligatorio, se asignará por defecto el teléfono de la inmobiliaria.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(AddInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Locale.setDefault(new Locale("es", "ES"));
                AddInmueble dialog = new AddInmueble(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JLabel jLabelAltaInmueble;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelFoto;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTransaccion;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldFoto;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JTextField jTextFieldTitulo;
    private javax.swing.JTextField jTextFieldTransaccion;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
