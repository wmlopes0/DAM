package tarea2extensioncomponentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiPanel extends JPanel {

    //Atributos
    private JTable table;
    private DefaultTableModel model;
    private JTextField jTextFieldAlumno;
    private JTextField jTextFieldCurso;
    private JButton botonAniadir;
    private JButton botonEliminar;

    public MiPanel() {
        //Establezco un borderLayout en el panel principal
        setLayout(new BorderLayout());

        // Creo modelo de la tabla y añado las columnas Alumno y Curso
        model = new DefaultTableModel();
        model.addColumn("Alumno");
        model.addColumn("Curso");

        // Creo JTable
        table = new JTable(model);

        //Creo un panel para los campos de texto y botones
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 1));//Establezco un gridLayout con una columna 
        jTextFieldAlumno = new JTextField(10);
        jTextFieldCurso = new JTextField(10);
        botonAniadir = new JButton("Añadir");
        botonEliminar = new JButton("Eliminar");

        // Añadir componentes al panel de entrada
        inputPanel.add(new JLabel("Alumno:"));
        inputPanel.add(jTextFieldAlumno);
        inputPanel.add(new JLabel("Curso:"));
        inputPanel.add(jTextFieldCurso);
        inputPanel.add(botonAniadir);
        inputPanel.add(botonEliminar);

        // Añadir acciones a los botones de añadir y eliminar
        aniadirAccionesBotones();

        // Añado componentes al panel principal
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public void aniadirAccionesBotones() {
        botonAniadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Añado alumno
                addAlumno(jTextFieldAlumno.getText(), jTextFieldCurso.getText());
                //Vacio los jTextField
                jTextFieldAlumno.setText("");
                jTextFieldCurso.setText("");
            }
        });
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Recupero fila seleccionada
                int selectedRow = table.getSelectedRow();
                //Si hay alguna fila seleccionada la elimino
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                }
            }
        });
    }

    public void addAlumno(String alumno, String curso) {
        model.addRow(new String[]{alumno, curso});//Añado el Alumno
    }

    public JTable getTable() {
        return table;
    }
}
