package com.mycompany.alumnostablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jjaronesb01
 */
public class AlumnoTableModel extends AbstractTableModel {

    private List<Alumno> listAlumno;
    private String columnas[] = {"Nombre", "Curso"};

    @Override
    public String getColumnName(int column) {
        return columnas[column]; //To change body of generated methods, choose Tools | Templates.
    }

    public AlumnoTableModel(List<Alumno> listAlumno) {
        this.listAlumno = listAlumno;
    }

    @Override
    public int getRowCount() {
        return listAlumno.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return listAlumno.get(rowIndex).getNombre();
            case 1:
                return listAlumno.get(rowIndex).getCurso();
        }
        return null;

    }

}
