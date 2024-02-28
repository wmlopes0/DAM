package com.mycompany.proyectofinal;

import static com.mycompany.proyectofinal.Constantes.*;
import com.mycompany.proyectofinal.modelos.Clase;
import com.mycompany.proyectofinal.modelos.TipoClase;
import com.mycompany.proyectofinal.modelos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Walter
 */
public class Utileria {

    //ESTABLECER CONEXIÓN
    public static Connection establecerConexion() {
        //Cargamos el driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(CLASS_NOT_FOUND_EXCEPTION);
        }
        //Establecemos la conexión
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + PUERTO + "/" + BD, USUARIO, "");
        } catch (SQLException e) {
            System.out.println(CONEXION_FALLIDA);
            return conexion;
        }
        return conexion;
    }

    //MÉTODO PARA GENERAR EL HASH DE UNA CADENA UTILIZANCO SHA-256
    public static String generarHash(String input) throws NoSuchAlgorithmException {
        //Crear instancia de SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // Generar el hash de la cadena
        byte[] hash = digest.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        // Convertir el hash de byte a hexadecimal y retornarlo
        return toHexString(hash);
    }

    // MÉTODO PARA CONVERTIR DE BYTE A HEXADECIMAL
    private static String toHexString(byte[] hash) {
        // Inicializar StringBuilder para construir la cadena hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) { // Recorrer cada byte del hash
            String hex = Integer.toHexString(0xff & b); // Convertir el byte a hexadecimal
            if (hex.length() == 1) {
                hexString.append('0'); // Agregar un 0 si es necesario para mantener el formato
            }
            hexString.append(hex); // Agregar el hexadecimal al StringBuilder
        }
        return hexString.toString(); // Convertir StringBuilder a String y retornarlo
    }

    //MÉTODO PARA OBTENER EL OBJETO USUARIO POR SU USUARIO Y CONTRASEÑA
    public static Usuario obtenerUsuario(String usuario, String contrasena) {
        Usuario user = null;
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setString(1, usuario);
                sentenciaPreparada.setString(2, contrasena);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                if (resultado.next()) {
                    user = new Usuario(resultado.getInt("usuario_id"), resultado.getString("nombre_usuario"), resultado.getString("contrasena"), resultado.getString("url_fotografia"), resultado.getString("datos"), resultado.getInt("n_clases_pagadas"), resultado.getString("tipo_usuario"));
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
        return user;
    }

    //MÉTODO PARA OBTENER EL OBJETO USUARIO POR SU ID
    public static Usuario obtenerUsuarioId(int id) {
        Usuario user = null;
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM usuarios WHERE usuario_id = ?";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setInt(1, id);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                if (resultado.next()) {
                    user = new Usuario(resultado.getInt("usuario_id"), resultado.getString("nombre_usuario"), resultado.getString("contrasena"), resultado.getString("url_fotografia"), resultado.getString("datos"), resultado.getInt("n_clases_pagadas"), resultado.getString("tipo_usuario"));
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
        return user;
    }

    //MÉTODO QUE RELLENA EL OBSERVABLELIST CON LOS USUARIOS DE LA BD
    public static void cargarDatosUsuarios(ObservableList<Usuario> listaUsuarios) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM usuarios";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                while (resultado.next()) {
                    listaUsuarios.add(new Usuario(resultado.getInt("usuario_id"), resultado.getString("nombre_usuario"), resultado.getString("contrasena"), resultado.getString("url_fotografia"), resultado.getString("datos"), resultado.getInt("n_clases_pagadas"), resultado.getString("tipo_usuario")));
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
    }

    //MÉTODO PARA AÑADIR UN USUARIO A LA BASE DE DATOS
    public static void anadirUsuario(Usuario usuario) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia insert
        try {
            PreparedStatement sentenciaPreparada = null;
            String sql = "INSERT INTO usuarios (nombre_usuario,contrasena,url_fotografia,datos,n_clases_pagadas,tipo_usuario) VALUES (?,?,?,?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, usuario.getNombre());
            sentenciaPreparada.setString(2, usuario.getContrasena());
            if (!usuario.getFotografia().equalsIgnoreCase("")) {
                sentenciaPreparada.setString(3, usuario.getFotografia());
            } else {
                sentenciaPreparada.setString(3, "avatarUsuario.png");
            }
            sentenciaPreparada.setString(4, usuario.getDatos());
            sentenciaPreparada.setInt(5, usuario.getClasesPagadas());
            sentenciaPreparada.setString(6, usuario.getTipoUsuario());
            sentenciaPreparada.executeUpdate();

            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //MÉTODO PARA ELIMINAR UN USUARIO A LA BASE DE DATOS
    public static void bajaUsuario(Usuario usuario) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia delete
        try {
            PreparedStatement sentenciaPreparada = null;
            String sql = "DELETE FROM usuarios WHERE usuario_id = ?";
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, usuario.getId());
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //MÉTODO QUE ACTUALIZA LAS CLASES PAGADAS EN LA BD
    public static void actualizarClasesPagadas(Usuario usuario) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia
        try {
            PreparedStatement sentenciaPreparada = null;
            String sql = "UPDATE usuarios SET n_clases_pagadas = ? WHERE usuario_id = ?";
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, usuario.getClasesPagadas());
            sentenciaPreparada.setInt(2, usuario.getId());
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //MÉTODO QUE OBTIENE EL NOMBRE DEL TIPO DE LA CLASE EN BASE A SU ID
    public static String obtenerTipoClase(int tipo_clase_id) {
        String nombre = "";
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM tiposdeclase WHERE tipo_clase_id = ?";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setInt(1, tipo_clase_id);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                if (resultado.next()) {
                    nombre = resultado.getString("nombre");
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
        return nombre;
    }

    //MÉTODO QUE CARGA LOS DATOS DE LAS CLASES 
    public static void cargarDatosClases(ObservableList<Clase> listaClases) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM clases";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                while (resultado.next()) {
                    listaClases.add(new Clase(resultado.getInt("clase_id"), resultado.getInt("tipo_clase_id"), resultado.getDate("fecha"), resultado.getTime("hora")));
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
    }

    //MÉTODO QUE REEMBOLSA LA CLASE PAGADA A LOS USUARIOS CORRESPONDIENTES
    public static void reembolsarClase(Clase claseSeleccionada) {
        //Consulta para obtener la lista de usuarios a los que hay que reembolsar la clase
        List<Usuario> listaUsuarios = new ArrayList<>();
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM reservas WHERE clase_id = ?";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setInt(1, claseSeleccionada.getClase_id());
                ResultSet resultado = sentenciaPreparada.executeQuery();
                while (resultado.next()) {
                    listaUsuarios.add(obtenerUsuarioId(resultado.getInt("usuario_id")));
                }
                //Liberamos recursos
                sentenciaPreparada.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }

            //Creamos sentencia para reembolsar la clase
            try {
                sql = "UPDATE usuarios SET n_clases_pagadas = ? WHERE usuario_id = ?";
                sentenciaPreparada = conexion.prepareStatement(sql);
                for (Usuario usuario : listaUsuarios) {
                    sentenciaPreparada.setInt(1, (usuario.getClasesPagadas() - 1));
                    sentenciaPreparada.setInt(2, usuario.getId());
                    sentenciaPreparada.executeUpdate();
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
    }

    //MÉTODO QUE ELIMINA LA CLASE DE LA BD
    public static void eliminarClase(Clase claseSeleccionada) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia delete
        try {
            PreparedStatement sentenciaPreparada = null;
            String sql = "DELETE FROM clases WHERE clase_id = ?";
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, claseSeleccionada.getClase_id());
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //MÉTODO QUE INICIA EL COMBOBOX CON LOS TIPOS DE CLASE QUE EXISTEN EN LA BD
    public static void iniciarComboBoxTiposClase(ComboBox<String> comboBoxTipoClase) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM tiposdeclase";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                while (resultado.next()) {
                    comboBoxTipoClase.getItems().add(resultado.getString("nombre"));
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
    }

    //MÉTODO QUE RETORNA EL ID DEL TIPO DE CLASE SEGÚN SU NOMBRE
    public static int obtenerIdTipoClase(String nombre) {
        int idClase = 0;
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM tiposdeclase WHERE nombre = ?";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setString(1, nombre);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                if (resultado.next()) {
                    idClase = resultado.getInt("tipo_clase_id");
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
        return idClase;
    }

    //MÉTODO PARA AÑADIR UNA CLASE A LA BD
    public static void anadirClase(Clase clase) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia insert
        try {
            PreparedStatement sentenciaPreparada = null;
            String sql = "INSERT INTO clases (tipo_clase_id,fecha,hora) VALUES (?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, clase.getTipo_clase_id());
            sentenciaPreparada.setDate(2, clase.getFecha());
            sentenciaPreparada.setTime(3, clase.getHora());
            sentenciaPreparada.executeUpdate();

            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //MÉTODO PARA AÑADIR UN TIPO DE CLASE A LA BD
    public static void anadirTipoClase(TipoClase tipoClase) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia insert
        try {
            PreparedStatement sentenciaPreparada = null;
            String sql = "INSERT INTO tiposdeclase (nombre,cupos_disponibles,url_fotografia) VALUES (?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setString(1, tipoClase.getNombre());
            sentenciaPreparada.setInt(2, tipoClase.getCupos_disponibles());
            if (tipoClase.getFotografia().equalsIgnoreCase("")) {
                sentenciaPreparada.setString(3, "avatarClase.png");
            } else {
                sentenciaPreparada.setString(3, tipoClase.getFotografia());
            }
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

    //MÉTODO QUE CARGA LOS DATOS DE LAS CLASES EN UN PERIODO DE FECHAS CONCRETO 
    public static void cargarDatosClasesFecha(ObservableList<Clase> listaClases, Date fechaInicio, Date fechaFin) {
        // Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            // Completamos la consulta para filtrar por el rango de fechas
            String sql = "SELECT * FROM clases WHERE fecha BETWEEN ? AND ?";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                // Establecemos los valores para los parámetros de la consulta
                sentenciaPreparada.setDate(1, fechaInicio);
                sentenciaPreparada.setDate(2, fechaFin);

                ResultSet resultado = sentenciaPreparada.executeQuery();
                while (resultado.next()) {
                    listaClases.add(new Clase(resultado.getInt("clase_id"), resultado.getInt("tipo_clase_id"), resultado.getDate("fecha"), resultado.getTime("hora")));
                }
                // Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
    }

    //MÉTODO QUE CARGA LA LISTA DE USUARIOS QUE HAN RESERVADO UNA CLASE
    public static void cargarDatosUsuariosClase(ObservableList<Usuario> listaUsuarios, Clase claseSeleccionada) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM usuarios WHERE usuario_id IN (SELECT usuario_id FROM reservas WHERE clase_id = ?)";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setInt(1, claseSeleccionada.getClase_id());
                ResultSet resultado = sentenciaPreparada.executeQuery();
                while (resultado.next()) {
                    listaUsuarios.add(new Usuario(resultado.getInt("usuario_id"), resultado.getString("nombre_usuario"), resultado.getString("contrasena"), resultado.getString("url_fotografia"), resultado.getString("datos"), resultado.getInt("n_clases_pagadas"), resultado.getString("tipo_usuario")));
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
    }

    //MÉTODO QUE CARGA LA LISTA DE LAS CLASES A LAS QUE EL USUARIO ESTÁ APUNTADO
    public static void cargarDatosClasesApuntado(ObservableList<Clase> listaClases, Usuario user) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM clases WHERE clase_id IN(SELECT clase_id FROM reservas WHERE usuario_id=? AND estado='Reservada')";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setInt(1, user.getId());
                ResultSet resultado = sentenciaPreparada.executeQuery();
                while (resultado.next()) {
                    listaClases.add(new Clase(resultado.getInt("clase_id"), resultado.getInt("tipo_clase_id"), resultado.getDate("fecha"), resultado.getTime("hora")));
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
    }

    //MÉTODO QUE ACTUALIZA EL ESTADO DE LA RESERVA A CANCELADA
    public static void cambiarEstadoReservaCancelada(Usuario user, Clase claseSeleccionada) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "UPDATE reservas SET estado='Cancelada' WHERE usuario_id = ? AND clase_id=? AND estado='Reservada'";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setInt(1, user.getId());
                sentenciaPreparada.setInt(2, claseSeleccionada.getClase_id());
                sentenciaPreparada.executeUpdate();
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
    }

    //MÉTODO QUE DEVUELVE LA RUTA DE LA CLASE POR SU TIPO DE CLASE ID
    public static String obtenerRutaImagenClase(int tipo_clase_id) {
        String ruta = "";
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM tiposdeclase WHERE tipo_clase_id = ?";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setInt(1, tipo_clase_id);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                if (resultado.next()) {
                    ruta = resultado.getString("url_fotografia");
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
        return ruta;
    }

    //MÉTODO QUE DEVUELVE TRUE SI EL USUARIO YA HA RESERVADO ESA CLASE Y FALSE EN CASO CONTRARIO
    public static boolean usuarioReservadoClase(Clase clase, Usuario user) {
        boolean reservado = false;
        //Creamos conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            //Creamos consulta
            String sql = "SELECT * FROM  reservas WHERE clase_id = ? AND usuario_id = ? AND estado = 'Reservada'";
            PreparedStatement sentenciaPreparada = null;
            try {
                sentenciaPreparada = conexion.prepareStatement(sql);
                sentenciaPreparada.setInt(1, clase.getClase_id());
                sentenciaPreparada.setInt(2, user.getId());
                ResultSet resultado = sentenciaPreparada.executeQuery();
                if (resultado.next()) {
                    reservado = true;
                }
                //Liberamos recursos
                sentenciaPreparada.close();
                conexion.close();
            } catch (SQLException e) {
                System.out.println(SQLEXCEPTION);
            }
        }
        return reservado;
    }

    //MÉTODO QUE CREA E INSERTA UNA RESERVA EN LA BD
    public static void reservarClase(Clase clase, Usuario user) {
        //Creamos conexión
        Connection conexion = establecerConexion();
        //Creamos sentencia insert
        try {
            PreparedStatement sentenciaPreparada = null;
            String sql = "INSERT INTO reservas (usuario_id,clase_id,estado) VALUES (?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, user.getId());
            sentenciaPreparada.setInt(2, clase.getClase_id());
            sentenciaPreparada.setString(3, "Reservada");
            sentenciaPreparada.executeUpdate();
            //Liberamos recursos
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(SQLEXCEPTION);
        }
    }

}
