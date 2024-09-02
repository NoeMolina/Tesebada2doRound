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
        
        // Lee las líneas restantes (datos)
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(separador);
            
            // Asigna los valores a la consulta según la tabla
            if (table) {
                pstmt.setString(1, datos[0]); // FOLIO
                pstmt.setDate(2, java.sql.Date.valueOf(datos[1])); // FECHA
                pstmt.setInt(3, Integer.parseInt(datos[2])); // IDESTADO
                pstmt.setInt(4, Integer.parseInt(datos[3])); // IDCIUDAD
                pstmt.setInt(5, Integer.parseInt(datos[4])); // IDTIENDA
                pstmt.setInt(6, Integer.parseInt(datos[5])); // IDEMPLEADO
            } else {
                pstmt.setString(1, datos[0]); // TICKET
                pstmt.setInt(2, Integer.parseInt(datos[1])); // IDPRODUCTO
                pstmt.setInt(3, Integer.parseInt(datos[2])); // UNIDADES
                pstmt.setInt(4, Integer.parseInt(datos[3])); // PRECIO
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
    
    
}
