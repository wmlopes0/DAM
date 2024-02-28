package interfazGrafica;

/**
 *
 * @author Walter
 */
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;

//Esta clase sirve para crear un Renderizador personalizado para centrar el texto de las celdas
public class CenterTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(CENTER); // Centra el texto
        return this;
    }
}
