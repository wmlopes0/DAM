package repasoexamentablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author wmartinl01
 */
public class MensajeTableModel extends AbstractTableModel {

    private List<Mensaje> mensajes;
    private String[] columnas = {"Emisor", "Destinatario", "Asunto", "Mensaje"};

    public MensajeTableModel(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public int getRowCount() {
        return mensajes.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public void addMsg(Mensaje mensaje) {
        mensajes.add(mensaje);
        fireTableDataChanged();
    }

    public List<String> getEmisores() {
        List<String> emisores = new ArrayList<>();
        for (Mensaje mensaje : mensajes) {
            emisores.add(mensaje.getEmisor());
        }
        return emisores;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return mensajes.get(rowIndex).getEmisor();
            case 1:
                return mensajes.get(rowIndex).getDestinatario();
            case 2:
                return mensajes.get(rowIndex).getAsunto();
            case 3:
                return mensajes.get(rowIndex).getMensaje();
            default:
                return null;
        }
    }

}
