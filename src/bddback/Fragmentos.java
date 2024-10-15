package bddback;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author soule
 */
import Rutinas.MensajesPantalla;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Fragmentos extends Thread{

    private String tipoBaseDatos;
    private String url;
    private String host;
    private String usuario;
    private String contraseña;
    private Connection conn;
    private String query;
    private int queryStatus = 0;

    public Fragmentos(String tipoBaseDatos, String host, String nombreBaseDatos, String usuario, String contraseña) {
        this.usuario = usuario.toLowerCase();
        this.contraseña = contraseña.toLowerCase();
        this.tipoBaseDatos = tipoBaseDatos;
        this.host = host;

        switch (tipoBaseDatos.toLowerCase()) {
            case "postgresql":
                this.url = "jdbc:postgresql://" + host + ":5432/" + nombreBaseDatos;
                break;
            case "mysql":
                this.url = "jdbc:mysql://" + host + ":3306/" + nombreBaseDatos + "?useSSL=false";
                break;
            case "sql server":
                this.url = "jdbc:sqlserver://" + host + ":1433;databaseName=" + nombreBaseDatos + ";encrypt=true;trustServerCertificate=true;";
                break;
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + tipoBaseDatos);
        }
    }
    
    public void setQuery(String Query){
        this.query = Query;
    }
    public int queryStatus(){
        return queryStatus;
    }
    
    private boolean hacerPing() {
        try {
            InetAddress servidorInet = InetAddress.getByName(host);
            System.out.println("Haciendo ping a " + host + "...");

            // Verifica si el servidor es alcanzable dentro del tiempo límite
            return servidorInet.isReachable(5000);

        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            return false;
        }
    }
    
    private void crearConneccion(){
            try {
                if (!hacerPing()) {
                    MensajesPantalla.TareaconFallo("Error al conectarse con el servidor");
                    return;
                }
            this.conn = DriverManager.getConnection(url, usuario, contraseña);
            if (conn != null) {
                conn.setAutoCommit(false); // Desactivar auto commit para manejar las transacciones manualmente
                System.out.println("Conexión establecida con éxito a la base de datos: " + url);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            MensajesPantalla.TareaconFallo("Error al conectarse con el Fragmento");
        }
}

    public DefaultTableModel select(String query, DefaultTableModel tableModel) {
        ResultSet rs = null;

        try {
            crearConneccion();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            // Obtener los nombres de las columnas del ResultSet
            ResultSetMetaData metaData;
            metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Si el DefaultTableModel es null, lo inicializamos con las columnas de la consulta
            if (tableModel == null) {
                tableModel = new DefaultTableModel();

                for (int i = 1; i <= columnCount; i++) {
                    tableModel.addColumn(metaData.getColumnLabel(i));
                }
            }

            // Añadir los nombres de las columnas al modelo
            // Agregar las filas al modelo
            if (tipoBaseDatos.equals("postgresql")) {
                while (rs.next()) {
                    Object[] fila = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        Object valor = rs.getObject(i + 1);
                        // Verificar si el valor es de tipo Money
                        if (valor instanceof java.math.BigDecimal) {
                            // Convierte BigDecimal a String o double
                            fila[i] = ((java.math.BigDecimal) valor).doubleValue(); // o valor.toString();
                        } else {
                            fila[i] = valor;
                        }
                        System.out.print(i + " ");
                    }
                    tableModel.addRow(fila);
                }
            } else {
                while (rs.next()) {
                    Object[] fila = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        fila[i] = rs.getObject(i + 1);
                        System.out.println("bddback.Fragmentos.select()"+ i);
                    }
                    tableModel.addRow(fila);
                }
            }
            commit();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            rollback();
            MensajesPantalla.TareaconFallo("Instruccion no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }

        return tableModel;
    }

    @Override
    public void run() {
        try {
            crearConneccion();
            Statement stmt = conn.createStatement();
            int filasAfectadas = stmt.executeUpdate(query);
            if (filasAfectadas > 0) {     // Si se afectó al menos una fila, la transaccion fue exitosa.
                //la transaccion fue con exito
                queryStatus = 1;
            } else {
                //fallo la transaccion
                queryStatus = 2;
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la transaccion: " + e.getMessage());
            MensajesPantalla.TareaconFallo("Error al ejecutar la insercion verifique la consulta");
        }
    }

    //implementar metodo is readytocommit
    public void commit() {
        try {
            if (conn != null) {
                conn.commit();
                System.out.println("Transacción confirmada con éxito.");
                closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el commit: " + e.getMessage());
        }
    }

    public void rollback() {
        try {
            if (conn != null) {
                conn.rollback();
                System.out.println("Transacción revertida.");
                closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el rollback: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Fragmentos{"
                + "url='" + url + '\''
                + ", usuario='" + usuario + '\''
                + ", contraseña='" + contraseña + '\''
                + '}';
    }

}
