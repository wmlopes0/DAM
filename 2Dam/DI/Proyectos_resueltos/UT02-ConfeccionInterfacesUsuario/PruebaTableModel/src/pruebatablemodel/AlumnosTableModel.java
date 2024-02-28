package pruebatablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author wmartinl01
 */
public class AlumnosTableModel extends AbstractTableModel {

    private List<Alumno> listaAlumnos;
    private String[] columnas = {"Nombre", "Curso"};

    public AlumnosTableModel(List<Alumno> alumnos) {
        this.listaAlumnos = alumnos;
    }

    @Override
    public int getRowCount() {
        return listaAlumnos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listaAlumnos.get(rowIndex).getNombre();
            case 1:
                return listaAlumnos.get(rowIndex).getCurso();
            default:
                return null;
        }
    }

}
