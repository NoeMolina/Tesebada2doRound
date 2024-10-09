/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bddback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;

/**
 *
 * @author soule
 */
public class ConfigBDD {

    static String stringConnection = "jdbc:sqlserver://localhost:1433;database=BBDCONFIG;encrypt=true;trustServerCertificate=true;";
    ;
     static Connection connection = null;
    static String user = "sa";
    static String password = "sa";
    static String prstmInsertTable = "INSERT INTO TBD (NOM) VALUES (?)";
    static String prstmInsertAtributos = "INSERT INTO TBDATRIBUTOS (NOMTD, NOMATRIBUTO, TIPODATO) VALUES (?, ?, ?)";
    static String prstmInsertarFragmento = "INSERT INTO FRAGMENTO (NOMTD, NOMFRAGMENTO, NOMBDD,TIPOBDD, DIRECCIONIP, USUARIO, CONTRASEÑA, ESTADO, ZONADISTRIBUCION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    static String prstmObtenerFragmentoNOT = "SELECT * FROM FRAGMENTO WHERE ZONADISTRIBUCION != ?";
    static String prstmObtenerFragmentoIN = "SELECT * FROM FRAGMENTO WHERE ZONADISTRIBUCION = ?";
    static String prstmObtenerFragmento = "SELECT * FROM FRAGMENTO";

    public static boolean connectBDD() {
        boolean itsconnected = true;

        try {
            connection = DriverManager.getConnection(stringConnection, user, password);

            if (connection != null) {
                System.out.println("Conexion exitosa");
                itsconnected = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            itsconnected = false;
        }
        return itsconnected;
    }

    public static void closeConection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar cerrar conexion con la BDD: " + e.getMessage());
        }
    }

    public static boolean createTable(String nomTabla, String[] NomAtributos, String[] TipoDatos) {
        if (!connectBDD()) {
            return false;
        }
        try {
            //insertar la tabla
            PreparedStatement pstmt = connection.prepareStatement(prstmInsertTable);
            pstmt.setString(1, nomTabla);
            pstmt.execute();
            //insertar atributos
            pstmt = connection.prepareStatement(prstmInsertAtributos);
            for (int i = 0; i < NomAtributos.length; i++) {
                pstmt.setString(1, nomTabla);
                pstmt.setString(2, NomAtributos[i]);
                pstmt.setString(3, TipoDatos[i]);
                pstmt.execute();
            }
            closeConection();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            closeConection();
            return false;
        }

    }

    public static void llenarComboTabla(JComboBox<String> comboBox) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT NOM FROM TBD";
        try {
            connectBDD();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nomTabla = rs.getString("NOM");
                comboBox.addItem(nomTabla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void llenarComboFragmento(JComboBox<String> comboBox, String NOMTD) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT NOMFRAGMENTO FROM FRAGMENTO WHERE NOMTD = ?";
        try {
            connectBDD();
            stmt = connection.prepareStatement(sql);
            stmt.setString(0, NOMTD);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nomFragmento = rs.getString("NOMFRAGMENTO");
                comboBox.addItem(nomFragmento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean InsertarFragmento(String NOMTD, String NOMFRAGMENTO, String NOMBDD, String TIPOBDD, String DIRECCIONIP, String USUSARIO, String CONTRASEÑA, String ESTADO, String ZONADISTRIBUCION) {
        connectBDD();
        PreparedStatement stmt = null;
        boolean isInserted = false;
        try {
            stmt = connection.prepareStatement(prstmInsertarFragmento);
            //se establecen los valores por parametros
            stmt.setString(1, NOMTD);
            stmt.setString(2, NOMFRAGMENTO);
            stmt.setString(3, NOMBDD);
            stmt.setString(4, TIPOBDD);
            stmt.setString(5, DIRECCIONIP);
            stmt.setString(6, USUSARIO);
            stmt.setString(7, CONTRASEÑA);
            stmt.setString(8, ESTADO);
            stmt.setString(9, ZONADISTRIBUCION);

            // Ejecutar la inserción
            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa en la tabla FragmentosDistribuidos.");
                isInserted = true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el PreparedStatement y la conexión
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return isInserted;
    }

    public static List<Fragmentos> obtenerFragmentosZona(String zona, boolean flag) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fragmentos> fragmentosList = new ArrayList<>();

        try {
            connectBDD();
            if (flag) {
                stmt = connection.prepareStatement(prstmObtenerFragmentoNOT);
            } else {
                stmt = connection.prepareStatement(prstmObtenerFragmentoIN);
            }

            stmt.setString(1, zona);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nomBDD = rs.getString("NOMBDD");
                String tipoBDD = rs.getString("TIPOBDD");
                String direccionIp = rs.getString("DIRECCIONIP");
                String usuario = rs.getString("USUARIO");
                String constraseña = rs.getString("CONTRASEÑA");

                System.out.println(nomBDD + " " + tipoBDD + " " + direccionIp + " " + usuario + " " + constraseña);

                Fragmentos fragmento = new Fragmentos(nomBDD, direccionIp, tipoBDD, usuario, constraseña);
                fragmentosList.add(fragmento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Retornar la lista de fragmentos fuera del bloque finally
        return fragmentosList;
    }

    public static List<Fragmentos> obtenerFragmentos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fragmentos> fragmentosList = new ArrayList<>();

        try {
            connectBDD();
            stmt = connection.prepareStatement(prstmObtenerFragmento);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nomBDD = rs.getString("NOMBDD");
                String tipoBDD = rs.getString("TIPOBDD");
                String direccionIp = rs.getString("DIRECCIONIP");
                String usuario = rs.getString("USUARIO");
                String constraseña = rs.getString("CONTRASEÑA");

                System.out.println(nomBDD + " " + tipoBDD + " " + direccionIp + " " + usuario + " " + constraseña);

                Fragmentos fragmento = new Fragmentos(nomBDD, direccionIp, tipoBDD, usuario, constraseña);
                fragmentosList.add(fragmento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Retornar la lista de fragmentos fuera del bloque finally
        return fragmentosList;
    }

    public static List<Fragmentos> obtenerFragmentosEstado(String estado) {
        String query = "SELECT ZONAGEOGRAFICA FROM CRITERIODISTRIBUCION WHERE ESTADO = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fragmentos> fragmentosList = new ArrayList<>();

        try {
            connectBDD();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, estado);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String zona = rs.getString("ZONAGEOGRAFICA");
                System.out.println(zona);
                fragmentosList = obtenerFragmentosZona(zona, false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Retornar la lista de fragmentos
        return fragmentosList;
    }
}
