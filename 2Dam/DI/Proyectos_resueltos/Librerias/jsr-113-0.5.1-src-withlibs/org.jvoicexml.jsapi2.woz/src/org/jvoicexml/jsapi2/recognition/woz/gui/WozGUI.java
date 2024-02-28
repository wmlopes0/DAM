package org.jvoicexml.jsapi2.recognition.woz.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WozGUI extends JFrame {

	private static final long serialVersionUID = 4153650324439371356L;
	private JPanel contentPane;
	private JLabel statusLabel;
	private JTextField textField;
	private JComboBox comboBox;
	private WozASRModel asrModel = new WozASRModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WozGUI frame = new WozGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WozGUI() {
		setVisible(true);
		setTitle("Wizard of ASR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 99);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{98, 395, 0};
		gbl_contentPane.rowHeights = new int[]{28, 27, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		comboBox = new JComboBox(asrModel);

		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
//				System.out.println("Key Event / Code: '" + (int)e.getKeyChar() + "' / '" + (int)e.getKeyCode() + "'");
				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					asrModel.undo();
				} else if (e.getKeyChar() == KeyEvent.VK_ENTER) { 
					asrModel.select();
				} else {
//					 //selection via keyboard is not updated otherwise
//					comboBox.updateUI();
//					comboBox.setPopupVisible(true);					
//					e.consume();
				}
				comboBox.setPopupVisible(true);
				comboBox.requestFocus();
			}
		});
		
		textField = new JTextField();
		textField.setEnabled(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);
		
		statusLabel = new JLabel("... ");
		GridBagConstraints gbc_statusLabel = new GridBagConstraints();
		gbc_statusLabel.anchor = GridBagConstraints.EAST;
		gbc_statusLabel.gridx = 1;
		gbc_statusLabel.gridy = 1;
		contentPane.add(statusLabel, gbc_statusLabel);
		
		asrModel.setResultTextField(textField);
		asrModel.setStatusLabel(statusLabel);

	}

	public WozASRModel getAsrModel() {
		return asrModel;
	}

	public void setAsrModel(WozASRModel asrModel) {
		this.asrModel = asrModel;
	}

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}
