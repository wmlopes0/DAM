package com.mycompany.tareafinalinmobiliaria.logica;

import static com.mycompany.tareafinalinmobiliaria.logica.Constantes.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Walter
 */
public class InmuebleTableModel extends AbstractTableModel {

    //Atributos
    private List<Inmueble> listInmuebles;
    private String columnas[] = {" Id", "                        Título", " Transacción", "  Precio €", "    Teléfono"};

    //Constructor
    public InmuebleTableModel() {
        listInmuebles = new ArrayList<>();
        actualizarDatos();//Cargo los datos de la BD
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public int getRowCount() {
        return listInmuebles.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listInmuebles.get(rowIndex).getIdInmueble();
            case 1:
                return listInmuebles.get(rowIndex).getTitulo();
            case 2:
                return listInmuebles.get(rowIndex).getTransaccion();
            case 3:
                return listInmuebles.get(rowIndex).getPrecio();
            case 4:
                return listInmuebles.get(rowIndex).getTelefono();
        }
        return null;
    }

    //ACCIONES
    //Este método retorna un objeto según un índice
    public Inmueble retornarInmueble(int filaSeleccionada) {
        return listInmuebles.get(filaSeleccionada);
    }

    //Este método actualiza los inmuebles en la tabla cargandolos de nuevo de la BD
    public void actualizarDatos() {
        //Limpio lista para evitar duplicados
        listInmuebles.clear();
        //Establezco la conexión
        Connection conexion = Utileria.establecerConexion();
        //Realizo consulta
        try {
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM inmuebles";
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()) {
                //Creo objeto Inmueble
                Inmueble inmueble = new Inmueble();
                inmueble.setIdInmueble(resultado.getInt("idInmueble"));
                inmueble.setTitulo(resultado.getString("titulo"));
                inmueble.setDescripcion(resultado.getString("descripcion"));
                inmueble.setFoto(resultado.getString("foto"));
                inmueble.setTransaccion(resultado.getString("ventaAlquiler"));
                inmueble.setPrecio(resultado.getInt("precio"));
                inmueble.setTelefono(resultado.getInt("telefono"));
                //Añado a la lista
                listInmuebles.add(inmueble);
            }
            //Liberamos recursos
            sentencia.close();
            resultado.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
        //Actualizo tabla
        fireTableDataChanged();
    }

}
