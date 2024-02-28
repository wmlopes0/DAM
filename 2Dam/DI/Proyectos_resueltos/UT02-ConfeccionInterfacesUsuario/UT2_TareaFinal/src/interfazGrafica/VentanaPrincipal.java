package interfazGrafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import logica.Cliente;
import logica.Constantes;
import logica.Utileria;

/**
 *
 * @author wmartinl01
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //Variables globales
    public Map<String, String> usuarios = new LinkedHashMap<>();
    public List<Cliente> listaClientes;
    private VentanaPrincipal ventanaPrincipal = this;
    public boolean loginCorrecto = false;

    //Constructor
    public VentanaPrincipal() {
        initComponents();
        usuarios = Utileria.cargarDatosLogin();//Cargo los datos de los usuarios
        this.listaClientes = Utileria.cargarDatosClientes();//Cargo los datos de los clientes
        login();//Lanzo el login
        actualizarPanelClientes();
        establecerFondo();//Establezco el JPanel de fondo
        aniadirComponentes();//Añado los componentes al JPanel
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
        panelPrincipal.add(jMenuBar);
        //Panel fotos
        jPanelClientes.setLayout(new GridLayout(3, 5, 10, 10));
        panelPrincipal.add(jPanelClientes);
        panelPrincipal.add(jButtonAyuda);
    }

    //Este método actualiza el panel de los botones con imagenes de los clientes
    public void actualizarPanelClientes() {
        jPanelClientes.removeAll();
        jPanelClientes.revalidate();
        jPanelClientes.repaint();
        //Pinto
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            JButton boton = new JButton(cliente.getRepresentacion());
            generarActionListenerBoton(boton);
            boton.setName(String.valueOf(i));
            boton.setPreferredSize(new Dimension(Constantes.ANCHO_IMAGEN, Constantes.ALTO_IMAGEN));
            boton.setMargin(new Insets(0, 0, 0, 0)); // Ajustar márgenes
            boton.setBorderPainted(false);      // Quitar el borde
            boton.setFocusPainted(false);       // Quitar el efecto de enfoque
            boton.setContentAreaFilled(false);  // Quitar el fondo del botón
            jPanelClientes.add(boton);
            jPanelClientes.revalidate();
            jPanelClientes.repaint();
        }
    }

    //Método que recibe un JButton (Cliente) por parámetro y le añade el ActionListener
    private void generarActionListenerBoton(JButton boton) {
        //Genero un actionListener para el botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botonUsado = (JButton) e.getSource(); // Obtengo el botón que disparó el evento
                int clienteSeleccionado = Integer.valueOf(botonUsado.getName());//Obtengo el texto del boton (Indice del cliente) y lo convierto en Integer
                GenerarInforme generarInforme = new GenerarInforme(ventanaPrincipal, true, listaClientes.get(clienteSeleccionado));
                generarInforme.setVisible(true);
            }
        });
    }

    //Este método lanza el login antes de pintar todos los componentes, si el login es correcto accede a la aplicación, si no lo es volvera a pedir, y si cierra el Dialog, también se cerrará la aplicación.
    public void login() {
        Login login = new Login(this, true);
        login.setVisible(true);
        //Si el usuario no ha conseguido logearse correctamente e intenta cerrar la ventana de login, también se cierra la VentanaPrincipal
        if (!loginCorrecto) {
            System.exit(0);
        }
    }

    //Setter de loginCorrecto
    public void setLoginCorrecto(boolean loginCorrecto) {
        this.loginCorrecto = loginCorrecto;
    }

    public void actualizarDatos() {
        this.listaClientes = Utileria.cargarDatosClientes();
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanelClientes = new javax.swing.JPanel();
        jButtonAyuda = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemAltaCliente = new javax.swing.JMenuItem();
        jMenuItemModificarCliente = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(900, 650));

        jPanelClientes.setOpaque(false);

        javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
        jPanelClientes.setLayout(jPanelClientesLayout);
        jPanelClientesLayout.setHorizontalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );
        jPanelClientesLayout.setVerticalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

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
                .addGap(63, 63, 63)
                .addComponent(jPanelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAyuda)
                .addGap(26, 26, 26))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButtonAyuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jMenu1.setText("Clientes");

        jMenuItemAltaCliente.setText("Alta cliente");
        jMenuItemAltaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAltaClienteActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemAltaCliente);

        jMenuItemModificarCliente.setText("Modificar cliente");
        jMenuItemModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemModificarCliente);

        jMenuBar.add(jMenu1);

        setJMenuBar(jMenuBar);

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

    //Botón Alta Cliente
    private void jMenuItemAltaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAltaClienteActionPerformed
        AltaCliente altaCliente = new AltaCliente(this, true);
        altaCliente.setVisible(true);
        actualizarPanelClientes();
    }//GEN-LAST:event_jMenuItemAltaClienteActionPerformed

    //Botón modificar Cliente
    private void jMenuItemModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarClienteActionPerformed
        ModificarCliente modificarCliente = new ModificarCliente(this, true);
        modificarCliente.setVisible(true);
        actualizarPanelClientes();
    }//GEN-LAST:event_jMenuItemModificarClienteActionPerformed

    //Botón ayuda
    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        JOptionPane.showMessageDialog(this, "Pulsa sobre cualquier cliente para generar un informe.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
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
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemAltaCliente;
    private javax.swing.JMenuItem jMenuItemModificarCliente;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
