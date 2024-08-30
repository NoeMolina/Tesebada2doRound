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
        }finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return itsconnected;
    }
    
    
}
