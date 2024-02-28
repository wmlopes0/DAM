package com.clase.ejemploassertj;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author wmartinl01
 */
public class AppDemo extends JFrame {

    private JButton btnConvertir;
    private JComboBox<String> cboOpcion;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JFormattedTextField txtNumero;
    private JTextField txtResultado;

    public AppDemo() {
        initComponents();
    }

    private void initComponents() {
        initLookAndFeel();
        setTitle("App Demo");
        GridBagConstraints gridBagConstraints;
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        txtNumero = new JFormattedTextField();
        jLabel2 = new JLabel();
        cboOpcion = new JComboBox<>();
        btnConvertir = new JButton();
        jPanel2 = new JPanel();
        txtResultado = new JTextField();

        txtNumero.setName("numero");
        cboOpcion.setName("opcion");
        btnConvertir.setName("convertir");
        txtResultado.setName("resultado");

        getContentPane().setLayout(new GridBagLayout());
        jPanel1.setBorder(createEtchedBorder());
        jPanel1.setLayout(new GridBagLayout());

        jLabel1.setText("Número: ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 6, 0, 6);
        jPanel1.add(jLabel1, gridBagConstraints);

        txtNumero.setFormatterFactory(
                new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        txtNumero.setPreferredSize(new Dimension(200, 22));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(6, 0, 3, 6);
        jPanel1.add(txtNumero, gridBagConstraints);

        jLabel2.setText("Convertir a :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 6);
        jPanel1.add(jLabel2, gridBagConstraints);

        cboOpcion.setModel(new DefaultComboBoxModel<>(
                new String[]{"Hexadecimal", "Octal"}));
        cboOpcion.setPreferredSize(new Dimension(200, 22));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 0, 6, 6);
        jPanel1.add(cboOpcion, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 12, 12, 12);
        getContentPane().add(jPanel1, gridBagConstraints);

        btnConvertir.setText("Convertir");
        btnConvertir.addActionListener((ActionEvent evt) -> {
            conversion();
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(btnConvertir, gridBagConstraints);

        jPanel2.setBorder(BorderFactory.createTitledBorder(null, "<   Resultado   >",
                TitledBorder.CENTER, TitledBorder.TOP));
        jPanel2.setLayout(new GridBagLayout());

        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Tahoma", 1, 24));
        txtResultado.setHorizontalAlignment(JTextField.CENTER);
        txtResultado.setText("0");
        txtResultado.setPreferredSize(new Dimension(200, 32));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        jPanel2.add(txtResultado, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(12, 12, 12, 12);
        getContentPane().add(jPanel2, gridBagConstraints);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void conversion() {
        if (txtNumero.getText().isEmpty()) {
            JOptionPane.showOptionDialog(this,
                    "Debe ingresar un valor numerico valido entre 1 y 99."
                    + " \nReinicie el programa para continuar.",
                    "Atención",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"No lo vuelvo hacer"},
                    null);
            txtNumero.setEnabled(false);
            cboOpcion.setEnabled(false);
            btnConvertir.setEnabled(false);
            return;
        }

        try {
            int numero = Integer.valueOf(txtNumero.getText());
            if (numero <= 0 || numero >= 100) {
                JOptionPane.showMessageDialog(this,
                        "Solo numeros entre 1 y 99. No se puede continuar.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtResultado.setText("0");
                txtNumero.setText("0");
                return;
            }

            switch (cboOpcion.getSelectedItem().toString()) {
                case "Hexadecimal":
                    txtResultado.setText(String.valueOf(Integer.toHexString(numero)).toUpperCase());
                    break;
                case "Octal":
                    txtResultado.setText(String.valueOf(Integer.toOctalString(numero)).toUpperCase());
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Se ha producido un error [NumberFormatException]. No se puede realizar la conversión.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            txtNumero.setText("0");
        }
    }

    private void initLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new AppDemo().setVisible(true);
        });
    }

}
