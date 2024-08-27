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
    static final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private Connection conexion = null;
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
    }
    
    public void MakeConexion(){
        
    }
    
    
}
