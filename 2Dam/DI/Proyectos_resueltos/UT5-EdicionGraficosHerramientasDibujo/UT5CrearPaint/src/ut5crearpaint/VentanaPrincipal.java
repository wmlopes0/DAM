package ut5crearpaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Walter
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //Variables globales
    private Graphics graficos;
    private Color colorSeleccionado = Color.black;
    private Color colorPrimario = Color.black;
    private Color colorSecundario = Color.red;
    private BufferedImage buffNuevo;
    private Graphics2D g2d;
    private int tamanioCursor = 10;
    private boolean modoCuadrado = false;
    private boolean modoCirculo = false;
    private boolean modoHexagono = false;
    private boolean modoLinea = false;
    private boolean puntoInicioLinea = false;
    private int startX, startY, endX, endY;

    //Constructor
    public VentanaPrincipal() {
        initComponents();
        pizarraNueva();
    }

    //Método que inicializa/resetea la pizarra
    public void pizarraNueva() {
        buffNuevo = new BufferedImage(this.jLabelPizarra.getWidth(), this.jLabelPizarra.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graficos = buffNuevo.getGraphics();

        g2d = (Graphics2D) graficos;

        this.jLabelPizarra.setIcon(new ImageIcon(buffNuevo));
    }

    //Código generado
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jLabelPizarra = new javax.swing.JLabel();
        jButtonBorrarTodo = new javax.swing.JButton();
        jButtonGuardarImagen = new javax.swing.JButton();
        jButtonSeleccionarColor = new javax.swing.JButton();
        jButtonColorPrimario = new javax.swing.JButton();
        jButtonColorSecundario = new javax.swing.JButton();
        jSliderTamanioCursor = new javax.swing.JSlider();
        jLabelGrosor = new javax.swing.JLabel();
        jPanelDibujar = new javax.swing.JPanel();
        jButtonCuadrado = new javax.swing.JButton();
        jButtonCirculo = new javax.swing.JButton();
        jButtonHexagono = new javax.swing.JButton();
        jButtonLinea = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelPizarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabelPizarraMouseDragged(evt);
            }
        });
        jLabelPizarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPizarraMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelPizarraMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelPizarraMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelPizarra, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelPizarra, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );

        jButtonBorrarTodo.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 14)); // NOI18N
        jButtonBorrarTodo.setText("Borrar todo");
        jButtonBorrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarTodoActionPerformed(evt);
            }
        });

        jButtonGuardarImagen.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 14)); // NOI18N
        jButtonGuardarImagen.setText("Guardar Imagen");
        jButtonGuardarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarImagenActionPerformed(evt);
            }
        });

        jButtonSeleccionarColor.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 14)); // NOI18N
        jButtonSeleccionarColor.setText("Seleccionar color");
        jButtonSeleccionarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarColorActionPerformed(evt);
            }
        });

        jButtonColorPrimario.setBackground(new java.awt.Color(0, 0, 0));
        jButtonColorPrimario.setForeground(new java.awt.Color(255, 0, 51));
        jButtonColorPrimario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorPrimarioActionPerformed(evt);
            }
        });

        jButtonColorSecundario.setBackground(new java.awt.Color(255, 0, 0));
        jButtonColorSecundario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorSecundarioActionPerformed(evt);
            }
        });

        jSliderTamanioCursor.setMaximum(50);
        jSliderTamanioCursor.setPaintLabels(true);
        jSliderTamanioCursor.setPaintTicks(true);
        jSliderTamanioCursor.setSnapToTicks(true);
        jSliderTamanioCursor.setValue(10);
        jSliderTamanioCursor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderTamanioCursorStateChanged(evt);
            }
        });

        jLabelGrosor.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 14)); // NOI18N
        jLabelGrosor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelGrosor.setText("Grosor del pincel");

        jPanelDibujar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonCuadrado.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 14)); // NOI18N
        jButtonCuadrado.setText("Cuadrado");
        jButtonCuadrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCuadradoActionPerformed(evt);
            }
        });

        jButtonCirculo.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 14)); // NOI18N
        jButtonCirculo.setText("Círculo");
        jButtonCirculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCirculoActionPerformed(evt);
            }
        });

        jButtonHexagono.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 14)); // NOI18N
        jButtonHexagono.setText("Hexágono");
        jButtonHexagono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHexagonoActionPerformed(evt);
            }
        });

        jButtonLinea.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 14)); // NOI18N
        jButtonLinea.setText("Línea");
        jButtonLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLineaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FIGURAS");

        javax.swing.GroupLayout jPanelDibujarLayout = new javax.swing.GroupLayout(jPanelDibujar);
        jPanelDibujar.setLayout(jPanelDibujarLayout);
        jPanelDibujarLayout.setHorizontalGroup(
            jPanelDibujarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDibujarLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanelDibujarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLinea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCuadrado, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDibujarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCirculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHexagono))
                .addGap(45, 45, 45))
            .addGroup(jPanelDibujarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDibujarLayout.setVerticalGroup(
            jPanelDibujarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDibujarLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanelDibujarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCuadrado)
                    .addComponent(jButtonCirculo))
                .addGap(32, 32, 32)
                .addGroup(jPanelDibujarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHexagono)
                    .addComponent(jButtonLinea))
                .addGap(27, 27, 27))
        );

        jLabel2.setFont(new java.awt.Font("Microsoft PhagsPa", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PAINT DE WALTER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanelDibujar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelGrosor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButtonGuardarImagen)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSliderTamanioCursor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonColorPrimario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jButtonColorSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonSeleccionarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarImagen)
                    .addComponent(jButtonBorrarTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSeleccionarColor)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonColorPrimario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonColorSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jLabelGrosor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSliderTamanioCursor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jPanelDibujar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Evento que gestiona el pintar
    private void jLabelPizarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPizarraMouseDragged
        if (!modoCuadrado && !modoCirculo && !modoHexagono && !modoLinea) {
            int x = evt.getX();
            int y = evt.getY();

            graficos.setColor(colorSeleccionado);
            graficos.fillOval(x, y, tamanioCursor, tamanioCursor);
            this.jLabelPizarra.updateUI();
        }
    }//GEN-LAST:event_jLabelPizarraMouseDragged

    //Botón color primario NEGRO
    private void jButtonColorPrimarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorPrimarioActionPerformed
        colorSeleccionado = colorPrimario;
    }//GEN-LAST:event_jButtonColorPrimarioActionPerformed

    //Botón color secundario RED
    private void jButtonColorSecundarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorSecundarioActionPerformed
        colorSeleccionado = colorSecundario;
    }//GEN-LAST:event_jButtonColorSecundarioActionPerformed

    //Botón para limpiar la pizarra
    private void jButtonBorrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarTodoActionPerformed
        pizarraNueva();
    }//GEN-LAST:event_jButtonBorrarTodoActionPerformed

    //Botón para seleccionar un color
    private void jButtonSeleccionarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarColorActionPerformed
        JColorChooser selectColor = new JColorChooser();
        colorSeleccionado = selectColor.showDialog(null, "Seleccione color", colorSeleccionado);
    }//GEN-LAST:event_jButtonSeleccionarColorActionPerformed

    //Botón para guardar la imagen
    private void jButtonGuardarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarImagenActionPerformed
        String nombre = JOptionPane.showInputDialog("Introduzca el nombre:");
        try {
            ImageIO.write(buffNuevo, "png", new File("IMG/" + nombre + ".png"));
        } catch (IOException e) {
            System.out.println("ERROR: IOException");
        }
    }//GEN-LAST:event_jButtonGuardarImagenActionPerformed

    //Evento para cambiar el grosor del puntero
    private void jSliderTamanioCursorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderTamanioCursorStateChanged
        tamanioCursor = jSliderTamanioCursor.getValue();
    }//GEN-LAST:event_jSliderTamanioCursorStateChanged

    //Botón modo cuadrado
    private void jButtonCuadradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCuadradoActionPerformed
        modoCuadrado = true;
        modoCirculo = false;
        modoHexagono = false;
        modoLinea = false;
    }//GEN-LAST:event_jButtonCuadradoActionPerformed

    //Mouse presionado
    private void jLabelPizarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPizarraMousePressed
        if (modoCuadrado || modoCirculo || modoHexagono) {
            startX = evt.getX();
            startY = evt.getY();
        }
    }//GEN-LAST:event_jLabelPizarraMousePressed

    //Mouse soltado
    private void jLabelPizarraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPizarraMouseReleased
        if (modoCuadrado) {
            endX = evt.getX();
            endY = evt.getY();
            dibujarCuadrado();
        }
        if (modoCirculo) {
            endX = evt.getX();
            endY = evt.getY();
            dibujarCirculo();
        }
        if (modoHexagono) {
            endX = evt.getX();
            endY = evt.getY();
            dibujarHexagono();
        }
    }//GEN-LAST:event_jLabelPizarraMouseReleased

    //Botón modo Circulo
    private void jButtonCirculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCirculoActionPerformed
        modoCirculo = true;
        modoCuadrado = false;
        modoHexagono = false;
        modoLinea = false;
    }//GEN-LAST:event_jButtonCirculoActionPerformed

    //Botón modo Hexágono
    private void jButtonHexagonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHexagonoActionPerformed
        modoHexagono = true;
        modoCirculo = false;
        modoCuadrado = false;
        modoLinea = false;
    }//GEN-LAST:event_jButtonHexagonoActionPerformed

    //Botón modo Línea
    private void jButtonLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLineaActionPerformed
        modoLinea = true;
        modoHexagono = false;
        modoCirculo = false;
        modoCuadrado = false;
    }//GEN-LAST:event_jButtonLineaActionPerformed

    private void jLabelPizarraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPizarraMouseClicked
        if (modoLinea) {
            if (!puntoInicioLinea) {
                startX = evt.getX();
                startY = evt.getY();
                puntoInicioLinea = true;
            } else {
                endX = evt.getX();
                endY = evt.getY();
                dibujarLinea();
                puntoInicioLinea = false; // Restablecer para la próxima línea
            }
        }
    }//GEN-LAST:event_jLabelPizarraMouseClicked

    //Método para dibujar Cuadrado
    private void dibujarCuadrado() {
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);

        g2d.setColor(colorSeleccionado);
        g2d.drawRect(x, y, width, height);
        this.jLabelPizarra.updateUI();
        modoCuadrado = false;
    }

    //Método para dibujar círculo
    private void dibujarCirculo() {
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);

        g2d.setColor(colorSeleccionado);
        g2d.drawOval(x, y, width, height);
        this.jLabelPizarra.updateUI();
        modoCirculo = false;
    }

    //Método para dibujar Hexágono
    private void dibujarHexagono() {
        int dx = endX - startX;
        int dy = endY - startY;
        int radio = (int) Math.sqrt(dx * dx + dy * dy);
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (startX + radio * Math.cos(i * Math.PI / 3));
            yPoints[i] = (int) (startY + radio * Math.sin(i * Math.PI / 3));
        }

        g2d.setColor(colorSeleccionado);
        g2d.drawPolygon(xPoints, yPoints, 6);
        this.jLabelPizarra.updateUI();
        modoHexagono = false;
    }

    //Método para dibujar Línea
    private void dibujarLinea() {
        g2d.setColor(colorSeleccionado);
        g2d.drawLine(startX, startY, endX, endY);
        this.jLabelPizarra.updateUI();
        modoLinea = false;
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
    private javax.swing.JButton jButtonBorrarTodo;
    private javax.swing.JButton jButtonCirculo;
    private javax.swing.JButton jButtonColorPrimario;
    private javax.swing.JButton jButtonColorSecundario;
    private javax.swing.JButton jButtonCuadrado;
    private javax.swing.JButton jButtonGuardarImagen;
    private javax.swing.JButton jButtonHexagono;
    private javax.swing.JButton jButtonLinea;
    private javax.swing.JButton jButtonSeleccionarColor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelGrosor;
    private javax.swing.JLabel jLabelPizarra;
    private javax.swing.JPanel jPanelDibujar;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JSlider jSliderTamanioCursor;
    // End of variables declaration//GEN-END:variables
}
