package interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import logica.Cliente;
import logica.Utileria;

/**
 *
 * @author Walter
 */
public class ModificarCliente extends javax.swing.JDialog {

    //Variables globales
    private VentanaPrincipal ventanaPrincipal;
    private TableRowSorter<ClienteTableModel> sorter;
    private List<Cliente> listaClientes;

    //Constructor
    public ModificarCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.ventanaPrincipal = (VentanaPrincipal) parent;
        this.listaClientes = ventanaPrincipal.listaClientes;
        establecerFondo();//Establezco el JPanel de fondo
        crearTablaClientes();//Creo la jTable con los datos de los clientes
        aniadirComponentes();//Añado los componentes al JPanels
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
        //Soluciono problema boton texto
        jButtonEliminar.setText(org.openide.util.NbBundle.getMessage(ModificarCliente.class, "ModificarCliente.jButtonEliminar.text"));
        //Añado componentes
        panelPrincipal.add(jScrollPane1);
        panelPrincipal.add(jLabelListaClientes);
        panelPrincipal.add(jButtonModificar);
        panelPrincipal.add(jButtonEliminar);
        panelPrincipal.add(jButtonFiltrar);
        panelPrincipal.add(jButtonAyuda);
        panelPrincipal.add(jTextFieldFiltrar);
    }

    public void crearTablaClientes() {
        //Instancio un ClienteTableModel 
        ClienteTableModel ctm = new ClienteTableModel(listaClientes);
        this.jTableClientes.setModel(ctm);//Añado el ClienteTableModel al JTableClientes
        //Para poder ordenar creo el tableRowShorter
        sorter = new TableRowSorter<>(ctm);
        //Añado el tablerowsorter a mi Jtable
        this.jTableClientes.setRowSorter(sorter);
        //Establezco el color de fondo de la tabla
        jScrollPane1.getViewport().setBackground(new Color(255, 255, 255));
        // Obteniendo el TableHeader del JTable para modificar los estilos
        JTableHeader header = jTableClientes.getTableHeader();
        header.setFont(new Font("Microsoft PhasPa", Font.BOLD, 15));
        header.setForeground(new Color(38, 60, 61));
        //sheader.setDefaultRenderer(new HeaderRenderer());//Aplico el renderizador de centrado para el header 
        // Obteniendo el ColumModel del JTable para modificar las dimensiones de las celdas
        TableColumnModel columnModel = jTableClientes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(70);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(300);
        columnModel.getColumn(4).setPreferredWidth(100);
        //Centrar celdas
        aplicarRenderizadorCentrado();
    }

    //Aplica el renderizador creado para centrar el texto de las celdas
    public void aplicarRenderizadorCentrado() {
        CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < jTableClientes.getColumnCount(); i++) {
            jTableClientes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jLabelListaClientes = new javax.swing.JLabel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jTextFieldFiltrar = new javax.swing.JTextField();
        jButtonFiltrar = new javax.swing.JButton();
        jButtonAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(900, 650));

        panelPrincipal.setPreferredSize(new java.awt.Dimension(1000, 700));
        panelPrincipal.setRequestFocusEnabled(false);

        jTableClientes.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 14)); // NOI18N
        jTableClientes.setForeground(new java.awt.Color(38, 60, 61));
        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableClientes.setGridColor(new java.awt.Color(127, 199, 206));
        jScrollPane1.setViewportView(jTableClientes);

        jLabelListaClientes.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 30)); // NOI18N
        jLabelListaClientes.setForeground(new java.awt.Color(38, 60, 61));
        jLabelListaClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelListaClientes.setText(org.openide.util.NbBundle.getMessage(ModificarCliente.class, "ModificarCliente.jLabelListaClientes.text")); // NOI18N
        jLabelListaClientes.setToolTipText(org.openide.util.NbBundle.getMessage(ModificarCliente.class, "ModificarCliente.jLabelListaClientes.toolTipText")); // NOI18N

        jButtonEliminar.setBackground(new java.awt.Color(251, 242, 242));
        jButtonEliminar.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonEliminar.setForeground(new java.awt.Color(38, 60, 61));
        jButtonEliminar.setText(org.openide.util.NbBundle.getMessage(ModificarCliente.class, "ModificarCliente.jButtonModificar.text")); // NOI18N
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonModificar.setBackground(new java.awt.Color(251, 242, 242));
        jButtonModificar.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonModificar.setForeground(new java.awt.Color(38, 60, 61));
        jButtonModificar.setText(org.openide.util.NbBundle.getMessage(ModificarCliente.class, "ModificarCliente.jButtonModificar.text")); // NOI18N
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jTextFieldFiltrar.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 18)); // NOI18N
        jTextFieldFiltrar.setForeground(new java.awt.Color(67, 112, 114));
        jTextFieldFiltrar.setText(org.openide.util.NbBundle.getMessage(ModificarCliente.class, "ModificarCliente.jTextFieldFiltrar.text")); // NOI18N

        jButtonFiltrar.setBackground(new java.awt.Color(251, 242, 242));
        jButtonFiltrar.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonFiltrar.setForeground(new java.awt.Color(38, 60, 61));
        jButtonFiltrar.setText(org.openide.util.NbBundle.getMessage(ModificarCliente.class, "ModificarCliente.jButtonFiltrar.text")); // NOI18N
        jButtonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarActionPerformed(evt);
            }
        });

        jButtonAyuda.setBackground(new java.awt.Color(251, 242, 242));
        jButtonAyuda.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jButtonAyuda.setForeground(new java.awt.Color(38, 60, 61));
        jButtonAyuda.setText(org.openide.util.NbBundle.getMessage(ModificarCliente.class, "ModificarCliente.jButtonAyuda.text")); // NOI18N
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
                    .addComponent(jLabelListaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonAyuda)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 76, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jButtonModificar)
                .addGap(85, 85, 85)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jTextFieldFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButtonFiltrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabelListaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonAyuda)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Método que actualiza los datos del fichero y de la tabla después de cualquier modificación
    public void actualizarDatos() {
        AbstractTableModel model = (AbstractTableModel) this.jTableClientes.getModel();//Recupero el TableModel del JTable
        model.fireTableDataChanged();//Este método 'Refresca' la tabla
        Utileria.volcarDatosClientes(listaClientes);//Actualizo fichero
    }

    //Botón Eliminar
    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        if (jTableClientes.getSelectedRow() != -1) {//Compruebo si hay alguna fila seleccionada
            if (JOptionPane.showConfirmDialog(this, "¿Quiere eliminar este registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0) {
                int i = this.jTableClientes.getSelectedRow();// Obtengo la fila seleccionada
                i = this.jTableClientes.convertRowIndexToModel(i);//Convierto la fila seleccionada al índice correcto (sirve cuando la información está filtrada)
                //Elimino el índice de la tabla
                listaClientes.remove(i);
                //Actualizo datos
                actualizarDatos();
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    //Botón modificar 
    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        if (jTableClientes.getSelectedRow() != -1) {//Compruebo si hay alguna fila seleccionada
            int i = this.jTableClientes.getSelectedRow();// Obtengo la fila seleccionada
            i = this.jTableClientes.convertRowIndexToModel(i);//Convierto la fila seleccionada al índice correcto (sirve cuando la información está filtrada)
            //Abro ventana actualizar cliente con los datos del cliente
            ActualizarCliente actualizarCliente = new ActualizarCliente(ventanaPrincipal, true, listaClientes.get(i));
            actualizarCliente.setVisible(true);
            //Actualizo datos
            actualizarDatos();
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    //Botón filtrar
    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarActionPerformed
        int columnaSeleccionada = 0;//Columna filtrada por defecto es la 0
        //Si el usuario selecciona una columna se filtra por esa
        if (jTableClientes.getSelectedColumn() != -1) {
            columnaSeleccionada = jTableClientes.getSelectedColumn();
        }
        RowFilter<ClienteTableModel, Integer> rf = RowFilter.regexFilter(this.jTextFieldFiltrar.getText(), columnaSeleccionada);
        sorter.setRowFilter(rf);
    }//GEN-LAST:event_jButtonFiltrarActionPerformed

    //Botón ayuda
    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        JOptionPane.showMessageDialog(this, "- Pulsa sobre cualquier columna para filtrar sobre esa misma, por defecto se filtra por la columna 'Número cliente'.\n- Pulsa sobre cualquier columna en la cabecera para ordenar por esa misma.\n- Para resetear el filtro, vacía el campo de texto y pulsa 'Filtrar'.\n- Cierra la ventana para volver a la Ventana Principal.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(ModificarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModificarCliente dialog = new ModificarCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonFiltrar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JLabel jLabelListaClientes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextFieldFiltrar;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
