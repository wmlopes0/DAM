package InterfazGrafica;

import Logica.Juego;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wmartinl01
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //Atributos para crear el objeto Juego
    private String nombre;
    private int tiempo;
    private String nivel;

    //Constructor
    public VentanaPrincipal() {
        initComponents();
        this.getContentPane().setBackground(Color.decode("#001F3F"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonNivelFacil = new javax.swing.JButton();
        jButtonNivelDificil = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableResultados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonNivelFacil.setBackground(new java.awt.Color(204, 255, 204));
        jButtonNivelFacil.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jButtonNivelFacil.setForeground(new java.awt.Color(0, 153, 0));
        jButtonNivelFacil.setText("Nivel Fácil");
        jButtonNivelFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNivelFacilActionPerformed(evt);
            }
        });

        jButtonNivelDificil.setBackground(new java.awt.Color(255, 204, 204));
        jButtonNivelDificil.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jButtonNivelDificil.setForeground(new java.awt.Color(204, 0, 0));
        jButtonNivelDificil.setText("Nivel Difícil");
        jButtonNivelDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNivelDificilActionPerformed(evt);
            }
        });

        jTableResultados.setBackground(new java.awt.Color(0, 51, 102));
        jTableResultados.setFont(new java.awt.Font("Consolas", 2, 14)); // NOI18N
        jTableResultados.setForeground(new java.awt.Color(224, 224, 224));
        jTableResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tiempo", "Nivel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableResultados);

        jLabel1.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 215, 0));
        jLabel1.setText("Resultados");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel2.setFont(new java.awt.Font("Gabriola", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 215, 0));
        jLabel2.setText("EL LABERINTO DE WALTER");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jButtonNivelFacil)
                        .addGap(100, 100, 100)
                        .addComponent(jButtonNivelDificil))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNivelDificil)
                    .addComponent(jButtonNivelFacil))
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //NIVEL FÁCIL
    private void jButtonNivelFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNivelFacilActionPerformed
        NivelFacil nivelFacil = new NivelFacil(this, true);
        nivelFacil.setVisible(true);
        //Creo nuevo juego si lo ha conseguido
        if (tiempo != 0) {
            Juego juegoFacil = new Juego();
            juegoFacil.setNombre(nombre);
            juegoFacil.setTiempo(tiempo);
            juegoFacil.setNivel(nivel);
            //Añado la información a la tabla
            aniadirRegistroTabla(juegoFacil);
            //Reseteo el atributo tiempo (Que es el que uso para saber si ha perdido o ganado el usuario)
            tiempo = 0;
        } else {
            GameOver gameOver = new GameOver(this, true);
            gameOver.setVisible(true);
        }

    }//GEN-LAST:event_jButtonNivelFacilActionPerformed

    //NIVEL DIFÍCIL
    private void jButtonNivelDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNivelDificilActionPerformed
        NivelDificil nivelDificil = new NivelDificil(this, true);
        nivelDificil.setVisible(true);
        //Creo nuevo juego si lo ha conseguido
        if (tiempo != 0) {
            Juego juegoDificil = new Juego();
            juegoDificil.setNombre(nombre);
            juegoDificil.setTiempo(tiempo);
            juegoDificil.setNivel(nivel);
            //Añado la información a la tabla
            aniadirRegistroTabla(juegoDificil);
            //Reseteo el atributo tiempo (Que es el que uso para saber si ha perdido o ganado el usuario)
            tiempo = 0;
        } else {
            GameOver gameOver = new GameOver(this, true);
            gameOver.setVisible(true);
        }
    }//GEN-LAST:event_jButtonNivelDificilActionPerformed

    //SETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    //AÑADIR DATOS A LA TABLA
    public void aniadirRegistroTabla(Juego juego) {
        DefaultTableModel tabla = (DefaultTableModel) jTableResultados.getModel();
        tabla.addRow(juego.toArrayString());
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
    private javax.swing.JButton jButtonNivelDificil;
    private javax.swing.JButton jButtonNivelFacil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableResultados;
    // End of variables declaration//GEN-END:variables
}
