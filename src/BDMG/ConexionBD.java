/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDMG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

//para realizar la conexion a la base de datos
public class ConexionBD {
    private String url;
    private Connection connection = null;
    private String ip;
    private String nameBD;
    private String user;
    private String password;
    private boolean WC;
    
    public ConexionBD(String ip, String nameBD, String user, String  password, boolean WC){
        this.ip = ip;
        this.nameBD = nameBD;
        this.user = user;
        this.password = password;
        this.WC = WC;   
        url = "jdbc:sqlserver://"+this.ip+":1433;database="+this.nameBD+";encrypt=true;trustServerCertificate=true";
        
    }
    
    public boolean MakeConexion(){
        boolean itsconnected = true;
        try {
        connection = DriverManager.getConnection(url, user, password);
        
            if (connection != null) {
                System.out.println("Conexion exitosa");
                itsconnected = true;
            }
 
        } catch (SQLException  e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            itsconnected = false;
        }
        return itsconnected;
    }
    
    public void Insert(String archivo, boolean table){
        String linea;
        String separador = ",";
        
        String sqlTICKETSH = "INSERT INTO TICKETSH (FOLIO, FECHA, IDESTADO, IDCIUDAD, IDTIENDA, IDEMPLEADO) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlTICKETSD = "INSERT INTO TICKETSD (TICKET, IDPRODUCTO, UNIDADES, PRECIO) VALUES (?, ?, ?, ?)";
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        PreparedStatement pstmt = null;
        
        // Selecciona la consulta basada en el valor de `table`
        if (table) {
            pstmt = connection.prepareStatement(sqlTICKETSH);
        } else {
            pstmt = connection.prepareStatement(sqlTICKETSD);
        }
        
        // Lee la primera línea (encabezados) y la ignora
        br.readLine();
        
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(separador);
            
            
            if (table) {
                pstmt.setString(1, datos[0]); 
                pstmt.setDate(2, java.sql.Date.valueOf(datos[1]));
                pstmt.setInt(3, Integer.parseInt(datos[2]));
                pstmt.setInt(4, Integer.parseInt(datos[3]));
                pstmt.setInt(5, Integer.parseInt(datos[4]));
                pstmt.setInt(6, Integer.parseInt(datos[5]));
            } else {
                pstmt.setString(1, datos[0]);
                pstmt.setInt(2, Integer.parseInt(datos[1])); 
                pstmt.setInt(3, Integer.parseInt(datos[2])); 
                pstmt.setInt(4, Integer.parseInt(datos[3])); 
            }
            
            // Ejecuta la consulta
            pstmt.executeUpdate();
        }
        
        // Cierra el PreparedStatement
        if (pstmt != null) pstmt.close();
        System.out.println("Datos insertados correctamente.");
        
    } catch (IOException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
public void Procesar(int id, String criterio) {
    ResultSet conjuntoResultados = null;
    
    String queryTickets = "BEGIN TRANSACTION " +
        "SELECT S.TICKET, S.IDPRODUCTO, S.PRECIO"+
        "FROM TICKETSD S"+
        "INNER JOIN TICKETSH H ON H.FOLIO = S.TICKET"+
        "WHERE H."+criterio+" = ? AND S.TICKET IN ("+
        "    SELECT TICKET"+
        "    FROM TICKETSD"+
        "    GROUP BY TICKET"+
        "    HAVING COUNT(DISTINCT IDPRODUCTO) >= 3)";


    String queryActualizar = "UPDATE TICKETSD SET PRECIO = ? " +
                             "WHERE TICKET = ? AND IDPRODUCTO = ?";
    
    try {
        
        PreparedStatement ConsultarTickets = connection.prepareStatement(queryTickets);
        ConsultarTickets.setInt(1, id);
        ConsultarTickets.setInt(2, id);

        
        PreparedStatement ActualizarPrecios = connection.prepareStatement(queryActualizar);
        connection.setAutoCommit(false);  
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        conjuntoResultados = ConsultarTickets.executeQuery();
        System.out.println("Se realizo el select");
        
        int c = 0;
        while (conjuntoResultados.next()) {
            String ticket = conjuntoResultados.getString("FOLIO");
            int idProducto = conjuntoResultados.getInt("IDPRODUCTO");
            int precio = conjuntoResultados.getInt("PRECIO");
            int nuevoPrecio = precio + 1;  
            
            
            ActualizarPrecios.setInt(1, nuevoPrecio);
            ActualizarPrecios.setString(2, ticket);
            ActualizarPrecios.setInt(3, idProducto);
            
            ActualizarPrecios.executeUpdate();  
            System.out.println("Registro #" + c + " actualizado. Ticket: " + ticket + ", Producto: " + idProducto);
            c++;
        }
        
        connection.commit();  
        System.out.println("Transacción completada con éxito.");
    } catch (SQLException excepcionSql) {
        excepcionSql.printStackTrace();
        try {
            if (connection != null) {
                connection.rollback();  
                System.out.println("Transacción revertida.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al realizar el rollback: " + ex.getMessage());
        }
    } finally {
        try {
            if (conjuntoResultados != null) conjuntoResultados.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    public void close(){
	try {
		connection.close();
	}catch ( SQLException excepcionSql ){
		excepcionSql.printStackTrace();
	}
    }   
    
    
}
