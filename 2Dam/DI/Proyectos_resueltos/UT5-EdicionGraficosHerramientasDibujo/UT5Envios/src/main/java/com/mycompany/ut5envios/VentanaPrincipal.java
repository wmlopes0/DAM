package com.mycompany.ut5envios;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Walter
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //Variables globales
    private Graphics graficos;
    private BufferedImage buffNuevo;
    private Graphics2D g2d;
    private int tamanioCursor = 5;

    //Constructor
    public VentanaPrincipal() {
        initComponents();
        pizarraNueva();
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNumeroEnvio = new javax.swing.JTextField();
        jTextFieldDni = new javax.swing.JTextField();
        jPanelFirma = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanelPizarra = new javax.swing.JPanel();
        jLabelPizarra = new javax.swing.JLabel();
        jSliderGrosor = new javax.swing.JSlider();
        jButtonLimpiarPizarra = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtonValidar = new javax.swing.JButton();
        jButtonGenerarInforme = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORMULARIO DE ENTREGA");

        jLabel2.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jLabel2.setText("Número de envio: ");

        jLabel3.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jLabel3.setText("Documento de identidad:");

        jTextFieldNumeroEnvio.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 18)); // NOI18N

        jTextFieldDni.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 18)); // NOI18N

        jPanelFirma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("FIRMA");

        jPanelPizarra.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPizarra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabelPizarra.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPizarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabelPizarraMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout jPanelPizarraLayout = new javax.swing.GroupLayout(jPanelPizarra);
        jPanelPizarra.setLayout(jPanelPizarraLayout);
        jPanelPizarraLayout.setHorizontalGroup(
            jPanelPizarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPizarraLayout.createSequentialGroup()
                .addComponent(jLabelPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelPizarraLayout.setVerticalGroup(
            jPanelPizarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPizarraLayout.createSequentialGroup()
                .addComponent(jLabelPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jSliderGrosor.setMaximum(15);
        jSliderGrosor.setPaintLabels(true);
        jSliderGrosor.setPaintTicks(true);
        jSliderGrosor.setSnapToTicks(true);
        jSliderGrosor.setValue(5);
        jSliderGrosor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderGrosorStateChanged(evt);
            }
        });

        jButtonLimpiarPizarra.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 14)); // NOI18N
        jButtonLimpiarPizarra.setText("Borrar");
        jButtonLimpiarPizarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarPizarraActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Grosor pincel");

        javax.swing.GroupLayout jPanelFirmaLayout = new javax.swing.GroupLayout(jPanelFirma);
        jPanelFirma.setLayout(jPanelFirmaLayout);
        jPanelFirmaLayout.setHorizontalGroup(
            jPanelFirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFirmaLayout.createSequentialGroup()
                .addGroup(jPanelFirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFirmaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelFirmaLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanelFirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSliderGrosor, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 42, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFirmaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonLimpiarPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        jPanelFirmaLayout.setVerticalGroup(
            jPanelFirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFirmaLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jPanelPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSliderGrosor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonLimpiarPizarra)
                .addGap(20, 20, 20))
        );

        jButtonValidar.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 18)); // NOI18N
        jButtonValidar.setText("Validar");
        jButtonValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidarActionPerformed(evt);
            }
        });

        jButtonGenerarInforme.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 18)); // NOI18N
        jButtonGenerarInforme.setText("Generar Informe");
        jButtonGenerarInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarInformeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldNumeroEnvio, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                    .addComponent(jTextFieldDni)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jPanelFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 76, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jButtonValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButtonGenerarInforme)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNumeroEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jPanelFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGenerarInforme)
                    .addComponent(jButtonValidar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //PIZARRA ===================================================================================
    //Método que inicializa/resetea la pizarra
    public void pizarraNueva() {
        buffNuevo = new BufferedImage(this.jLabelPizarra.getWidth(), this.jLabelPizarra.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graficos = buffNuevo.getGraphics();

        g2d = (Graphics2D) graficos;

        this.jLabelPizarra.setIcon(new ImageIcon(buffNuevo));
    }
    private void jButtonLimpiarPizarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPizarraActionPerformed
        pizarraNueva();
    }//GEN-LAST:event_jButtonLimpiarPizarraActionPerformed

    private void jSliderGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderGrosorStateChanged
        tamanioCursor = jSliderGrosor.getValue();
    }//GEN-LAST:event_jSliderGrosorStateChanged

    private String guardarImagenFirma(int numEnvio, String dni) {
        String nombreImagen = numEnvio + "_" + dni + ".png";
        try {
            ImageIO.write(buffNuevo, "png", new File("FIRMAS/" + nombreImagen));
            ImageIO.write(buffNuevo, "png", new File("jasperReport/" + nombreImagen));
        } catch (IOException e) {
            System.out.println("ERROR: IOException");
        }
        return nombreImagen;
    }

    //ACCIONES GENERALES ========================================================================
    private void jButtonValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidarActionPerformed
        //Recupero y valido que los dos campos tengan valor y que el numero de envio no exista ya
        int numEnvio = Integer.parseInt(jTextFieldNumeroEnvio.getText());
        String dni = jTextFieldDni.getText();
        String imagenFirma;
        //Controlo que los dos campos esten llenos
        if (numEnvio != 0 && !dni.equalsIgnoreCase("")) {
            if (!existeNumEnvio(numEnvio)) {
                //Guardo la imagen de la firma
                imagenFirma = guardarImagenFirma(numEnvio, dni);
                //Inserto en la bd
                if (insertarEntrega(numEnvio, dni, imagenFirma)) {
                    JOptionPane.showMessageDialog(this, "¡Entrega insertada correctamente!", "CHECK", JOptionPane.INFORMATION_MESSAGE);
                    //Limpio campos
                    jTextFieldNumeroEnvio.setText("");
                    jTextFieldDni.setText("");
                    pizarraNueva();
                } else {
                    JOptionPane.showMessageDialog(this, "ERROR: No se pudo insertar la entrega en la base de datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "ERROR: El número de envio proporcionado ya existe.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "¡Debes rellenar los dos campos!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonValidarActionPerformed

    //Devuelve true si ya existe ese numEnvio en la BD
    private boolean existeNumEnvio(int numEnvio) {
        boolean existe = false;
        //Establezco conexión a la BD
        Connection conexion = establecerConexion();
        //Creamos consulta
        String sql = "SELECT * FROM entregas WHERE num_envio = ?";
        try {
            // Preparo un statement
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, numEnvio);
            // Ejecuto la consulta
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                existe = true;
            }
            //Liberamos recursos
            statement.close();
            resultSet.close();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: SQLException.");
        }

        return existe;
    }

    //Inserta la entrega en la BD, devuelve true si todo ha salido correctamente
    private boolean insertarEntrega(int numEnvio, String dni, String imagenFirma) {
        boolean insertCorrecto = false;
        //Establezco conexión a la BD
        Connection conexion = establecerConexion();
        //Creamos consulta
        String sql = "INSERT INTO entregas VALUES(?,?,?)";
        try {
            // Preparo un statement
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, numEnvio);
            statement.setString(2, dni);
            statement.setString(3, imagenFirma);
            // Ejecuto la consulta
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas != 0) {
                insertCorrecto = true;
            }
            //Liberamos recursos
            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: SQLException.");
        }
        return insertCorrecto;
    }

    private void jButtonGenerarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarInformeActionPerformed
        //Genero el informe
        try {
            File file = new File(getClass().getClassLoader().getResource("informeEntregas.jrxml").getFile());
            System.out.println(file.getCanonicalPath());
            JasperReport archivo = JasperCompileManager.compileReport(file.getAbsolutePath());
            Connection cnx;
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/entregas?user=root&password=");
            JasperPrint prin = JasperFillManager.fillReport(archivo, null, cnx);
            //Creo carpeta
            File carpeta = new File("INFORMES");
            carpeta.mkdir();
            //Genero el nombre del pdf
            String nombrePdf = "INFORMES/InformeGeneralEntregas.pdf";
            //Genero el pdf
            JasperExportManager.exportReportToPdfFile(prin, nombrePdf);
            //Liberamos recursos
            cnx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "¡Informe generado correctamente!", "Informe Generado", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonGenerarInformeActionPerformed

    private void jLabelPizarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPizarraMouseDragged
        int x = evt.getX();
        int y = evt.getY();

        graficos.setColor(Color.BLACK);
        graficos.fillOval(x, y, tamanioCursor, tamanioCursor);
        this.jLabelPizarra.updateUI();// TODO add your handling code here:
    }//GEN-LAST:event_jLabelPizarraMouseDragged

    //UTILERIA========================================================================
    //ESTABLECER CONEXIÓN
    public static Connection establecerConexion() {
        //Cargamos el driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: El Driver no se pudo cargar correctamente.");
        }
        //Establecemos la conexión
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/entregas", "root", "");
        } catch (SQLException e) {
            System.out.println("ERROR: Conexión fallida.");
            return conexion;
        }
        return conexion;
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
    private javax.swing.JButton jButtonGenerarInforme;
    private javax.swing.JButton jButtonLimpiarPizarra;
    private javax.swing.JButton jButtonValidar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelPizarra;
    private javax.swing.JPanel jPanelFirma;
    private javax.swing.JPanel jPanelPizarra;
    private javax.swing.JSlider jSliderGrosor;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldNumeroEnvio;
    // End of variables declaration//GEN-END:variables
}
