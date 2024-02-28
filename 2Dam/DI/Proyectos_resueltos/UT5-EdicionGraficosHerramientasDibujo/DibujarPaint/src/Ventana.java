
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author rdlrosac01
 */
public class Ventana extends javax.swing.JFrame {

    private Graphics graficos;
    private Color color = Color.black;
    private Color color1 = Color.black;
    private Color color2 = Color.white;
    private BufferedImage buffNuevo;
    private Graphics2D g2d;
    private int x; 
    private int y; 

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        buffNuevo = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graficos = buffNuevo.getGraphics();

        g2d = (Graphics2D) graficos; // TODO?

        this.jLabel1.setIcon(new ImageIcon(buffNuevo));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        primario = new javax.swing.JButton();
        secundario = new javax.swing.JButton();
        borrarTodo = new javax.swing.JButton();
        seleccionar = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        crearCuadrado = new javax.swing.JButton();
        Crearcírculo = new javax.swing.JButton();
        crearHexagono = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setForeground(new java.awt.Color(242, 242, 242));

        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        primario.setBackground(new java.awt.Color(255, 0, 0));
        primario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primarioActionPerformed(evt);
            }
        });

        secundario.setBackground(new java.awt.Color(0, 0, 0));
        secundario.setForeground(new java.awt.Color(242, 242, 242));
        secundario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secundarioActionPerformed(evt);
            }
        });

        borrarTodo.setText("Borrar todo");
        borrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarTodoActionPerformed(evt);
            }
        });

        seleccionar.setText("Seleccionar color");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });

        reset.setText("Reset color");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        guardar.setText("Guardar imagen");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        crearCuadrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuadrado.png"))); // NOI18N
        crearCuadrado.setMaximumSize(new java.awt.Dimension(50, 50));
        crearCuadrado.setMinimumSize(new java.awt.Dimension(50, 50));
        crearCuadrado.setPreferredSize(new java.awt.Dimension(50, 50));
        crearCuadrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearCuadradoActionPerformed(evt);
            }
        });

        Crearcírculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circulo.png"))); // NOI18N
        Crearcírculo.setMaximumSize(new java.awt.Dimension(50, 50));
        Crearcírculo.setMinimumSize(new java.awt.Dimension(50, 50));
        Crearcírculo.setPreferredSize(new java.awt.Dimension(50, 50));

        crearHexagono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hexagono.png"))); // NOI18N
        crearHexagono.setMaximumSize(new java.awt.Dimension(50, 50));
        crearHexagono.setMinimumSize(new java.awt.Dimension(50, 50));
        crearHexagono.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(primario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(secundario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(seleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reset))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(borrarTodo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crearCuadrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(Crearcírculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(crearHexagono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardar)
                            .addComponent(borrarTodo))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seleccionar)
                            .addComponent(reset))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(primario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secundario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(crearCuadrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Crearcírculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(crearHexagono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        int x = evt.getX();
        int y = evt.getY();

        graficos.setColor(color);
        graficos.fillOval(x, y, 20, 20);
        this.jLabel1.updateUI();
    }//GEN-LAST:event_jLabel1MouseDragged

    private void primarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primarioActionPerformed
        color1 = Color.RED;

        JColorChooser selectColor = new JColorChooser();
        color1 = selectColor.showDialog(null, "Seleccione color", color);
        color = color1;
        this.primario.setBackground(color1);
    }//GEN-LAST:event_primarioActionPerformed

    private void secundarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secundarioActionPerformed
        color2 = Color.BLACK;

        JColorChooser selectColor = new JColorChooser();
        color2 = selectColor.showDialog(null, "Seleccione color", color);
        color = color2;
        this.secundario.setBackground(color2);

    }//GEN-LAST:event_secundarioActionPerformed

    private void borrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarTodoActionPerformed
        buffNuevo = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graficos = buffNuevo.getGraphics();

        this.jLabel1.setIcon(new ImageIcon(buffNuevo));
        this.jLabel1.updateUI();
    }//GEN-LAST:event_borrarTodoActionPerformed

    private void seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarActionPerformed
        JColorChooser selectColor = new JColorChooser();
        color = selectColor.showDialog(null, "Seleccione color", color);
    }//GEN-LAST:event_seleccionarActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (evt.getButton() == 3) {
            graficos.setColor(color2);
            
            x = evt.getX();
            y = evt.getY();
        } else {
            graficos.setColor(color1);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        color1 = Color.BLACK;
        color = Color.black;
        color2 = Color.white;
        this.primario.setBackground(color1);
        this.secundario.setBackground(color2);
    }//GEN-LAST:event_resetActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            ImageIO.write(buffNuevo, "png", new File("foto.png"));
            System.out.println("Guardado");
        } catch (IOException e) {

        }
    }//GEN-LAST:event_guardarActionPerformed

    private void crearCuadradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearCuadradoActionPerformed

        g2d.setColor(color1);
        g2d.fillRect (x, y, 50, 50);
        g2d.setColor(color2);
        g2d.drawRect(x, y, 50, 50);
    }//GEN-LAST:event_crearCuadradoActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Crearcírculo;
    private javax.swing.JButton borrarTodo;
    private javax.swing.JButton crearCuadrado;
    private javax.swing.JButton crearHexagono;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton primario;
    private javax.swing.JButton reset;
    private javax.swing.JButton secundario;
    private javax.swing.JButton seleccionar;
    // End of variables declaration//GEN-END:variables
}
