package com.mycompany.tareafinalinmobiliaria.interfazGrafica;

import com.mycompany.tareafinalinmobiliaria.logica.Inmueble;
import com.mycompany.tareafinalinmobiliaria.logica.Utileria;
import javax.swing.JOptionPane;

/**
 *
 * @author wmartinl01
 */
public class ModificarInmueble extends javax.swing.JDialog {

    //Variables globales
    private VentanaPrincipal ventanaPrincipal;
    private static Inmueble inmuebleOriginal;

    //Constructor
    public ModificarInmueble(java.awt.Frame parent, boolean modal, Inmueble inmueble) {
        super(parent, modal);
        initComponents();
        ventanaPrincipal = (VentanaPrincipal) parent;
        inmuebleOriginal = inmueble;
        establecerFondo();//Establezco el fondo
        aniadirComponentes();//Añado los componentes al panelPrincipal
        mostrarDatosInmueble();//Añado la información actual del inmueble a los campos JTextField
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
        panelPrincipal.add(jLabelIdInmueble);
        panelPrincipal.add(jLabelValorIdInmueble);
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
        panelPrincipal.add(jButtonModificar);
    }

    //En este método Añado la información actual del inmueble a los campos JTextField correspondientes
    public void mostrarDatosInmueble() {
        jLabelValorIdInmueble.setText(String.valueOf(inmuebleOriginal.getIdInmueble()));
        jTextFieldTitulo.setText(inmuebleOriginal.getTitulo());
        jTextFieldDescripcion.setText(inmuebleOriginal.getDescripcion());
        jTextFieldFoto.setText(inmuebleOriginal.getFoto());
        jTextFieldTransaccion.setText(inmuebleOriginal.getTransaccion());
        jTextFieldPrecio.setText(String.valueOf(inmuebleOriginal.getPrecio()));
        jTextFieldTelefono.setText(String.valueOf(inmuebleOriginal.getTelefono()));
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
        jButtonModificar = new javax.swing.JButton();
        jLabelFoto = new javax.swing.JLabel();
        jTextFieldFoto = new javax.swing.JTextField();
        jLabelDescripcion = new javax.swing.JLabel();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jLabelIdInmueble = new javax.swing.JLabel();
        jLabelValorIdInmueble = new javax.swing.JLabel();
        jButtonAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelAltaInmueble.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        jLabelAltaInmueble.setForeground(new java.awt.Color(38, 60, 61));
        jLabelAltaInmueble.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAltaInmueble.setText("MODIFICAR INMUEBLE");

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

        jButtonModificar.setBackground(new java.awt.Color(251, 242, 242));
        jButtonModificar.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonModificar.setForeground(new java.awt.Color(38, 60, 61));
        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
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

        jLabelIdInmueble.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 24)); // NOI18N
        jLabelIdInmueble.setForeground(new java.awt.Color(38, 60, 61));
        jLabelIdInmueble.setText("Id Inmueble");

        jLabelValorIdInmueble.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jLabelValorIdInmueble.setForeground(new java.awt.Color(67, 112, 114));

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
                        .addGap(35, 35, 35)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelDescripcion)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTelefono)
                                    .addComponent(jLabelTitulo)
                                    .addComponent(jLabelTransaccion)
                                    .addComponent(jLabelPrecio)
                                    .addComponent(jLabelFoto)))
                            .addComponent(jLabelIdInmueble))
                        .addGap(42, 42, 42)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabelValorIdInmueble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAyuda))
                            .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(jTextFieldTelefono)
                            .addComponent(jTextFieldFoto)
                            .addComponent(jTextFieldTransaccion)
                            .addComponent(jTextFieldDescripcion)
                            .addComponent(jTextFieldTitulo))
                        .addGap(23, 23, 23)))
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jButtonModificar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabelAltaInmueble)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelIdInmueble)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                            .addComponent(jLabelValorIdInmueble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(1, 1, 1)))
                    .addComponent(jButtonAyuda))
                .addGap(28, 28, 28)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButtonModificar)
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
    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        //Controlo que estén rellenados los campos necesarios
        if (jTextFieldTitulo.getText().equalsIgnoreCase("") || jTextFieldTransaccion.getText().equalsIgnoreCase("") || jTextFieldDescripcion.getText().equalsIgnoreCase("") || jTextFieldPrecio.getText().equalsIgnoreCase("")|| jTextFieldFoto.getText().equalsIgnoreCase("")|| jTextFieldTelefono.getText().equalsIgnoreCase("")) {
            //Mensaje Error
            JOptionPane.showMessageDialog(this, "¡Ningún campo debe estar vacío!", "Error", JOptionPane.ERROR_MESSAGE);
            return;//Salgo del método
        }
        //Recupero los valores
        String titulo = jTextFieldTitulo.getText();
        String descripcion = jTextFieldDescripcion.getText();
        String foto = jTextFieldFoto.getText();
        String transaccion = jTextFieldTransaccion.getText();
        int precio = Integer.parseInt(jTextFieldPrecio.getText());
        int telefono = Integer.parseInt(jTextFieldTelefono.getText());
        //Creo objeto Inmueble
        Inmueble inmuebleModificado = new Inmueble(titulo, descripcion, foto, transaccion, precio, telefono);
        //Modifico el inmueble en la bd
        Utileria.modificarInmueble(inmuebleOriginal, inmuebleModificado);
        //Actualizo la tabla
        ventanaPrincipal.model.actualizarDatos();
        //Mensaje Check
        JOptionPane.showMessageDialog(this, "¡Inmueble modificado correctamente!", "Inmueble modificado", JOptionPane.INFORMATION_MESSAGE);
        //Cierro la ventana
        dispose();
    }//GEN-LAST:event_jButtonModificarActionPerformed

    //Botón ayuda
    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        JOptionPane.showMessageDialog(this, "- Todos los campos son obligatorios.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(ModificarInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModificarInmueble dialog = new ModificarInmueble(new javax.swing.JFrame(), true, inmuebleOriginal);
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
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JLabel jLabelAltaInmueble;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelFoto;
    private javax.swing.JLabel jLabelIdInmueble;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTransaccion;
    private javax.swing.JLabel jLabelValorIdInmueble;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldFoto;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JTextField jTextFieldTitulo;
    private javax.swing.JTextField jTextFieldTransaccion;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
