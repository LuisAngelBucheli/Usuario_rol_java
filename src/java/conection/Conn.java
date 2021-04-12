/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
       
package conection;


import java.sql.*;
/**
 *
 * @author ANGEL
 */

public class Conn {
    
  Connection conn;
  
  public Conn(){
  }
  
  public Conn(Connection conn){
    this.conn = conn; 
  }
  

  public void Connect() {
      String conection = "jdbc:postgresql://localhost:5433/prueba";
      String user = "postgres";
      String pass = "123456";
      try {
        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection(conection, user, pass);
      } catch (SQLException | ClassNotFoundException ex) {        
      }
  }
  
  public Connection getConnection() {
      return this.conn;
  }
}
