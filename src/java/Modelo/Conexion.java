/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lsfer
 */
public class Conexion {
    protected Connection con=null;
    protected Statement stmt;
    private final String userName = "root";
    private final String password = ""; 
    private String errString;
    
    public Conexion(){
      }
    
    public Connection Conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/pureza?useUnicode=true&characterEncoding=utf-8",userName, password);
            stmt=con.createStatement();
            System.out.println("Conectado");
         }catch(ClassNotFoundException | SQLException e){
             errString= "Error Mientras se conectaba a la Base de Datos";
             System.out.println(errString);
             return null;
         }
          return con;
     }
    
     public Statement getStmt()
       {
          return this.stmt;
       }
}
