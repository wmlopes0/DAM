package tablacoches;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Walter
 */
public class CocheTableModel extends AbstractTableModel {

    private List<Coche> listaCoches;
    private String[] columnas = {"Modelo", "Color", "Precio", "Fecha Matriculación"};

    public CocheTableModel(List<Coche> listaCoches) {
        this.listaCoches = listaCoches;
    }

    @Override
    public int getRowCount() {
        return listaCoches.size();
    }

    @Override
    public int getColumnCount() {
        return Coche.class.getDeclaredFields().length;//Esto devuelve el número de atributos de la clase coche, es decir las columnas en este caso.
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    //Implemento un método que elimina el coche de la lista
    public void removeRow(int row) {
        listaCoches.remove(row);
        fireTableRowsDeleted(row, row);//Este método 'Refresca' la tabla 
    }

    //Implemento un método que devuelve el objeto coche de la fila seleccionada
    public Coche getSelectedCar(int row) {
        return listaCoches.get(row);
    }

    //Implemento un método que actualiza el coche deseado
    public void updateCar(int row, Coche cocheActualizado) {
        //Obtengo el coche de la lista
        Coche coche = getSelectedCar(row);
        //Acualizo los datos por los del cocheActualizado
        coche.setModelo(cocheActualizado.getModelo());
        coche.setColor(cocheActualizado.getColor());
        coche.setPrecio(cocheActualizado.getPrecio());
        coche.setFechaMatriculacion(cocheActualizado.getFechaMatriculacion());
        //Refresco la tabla
        fireTableRowsUpdated(row, row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listaCoches.get(rowIndex).getModelo();
            case 1:
                return listaCoches.get(rowIndex).getColor();
            case 2:
                return listaCoches.get(rowIndex).getPrecio();
            case 3:
                return listaCoches.get(rowIndex).getFechaMatriculacion();
            default:
                return null;
        }
    }

}
