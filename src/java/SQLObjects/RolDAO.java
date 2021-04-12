/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLObjects;

import DBOjects.Rol;
import conection.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANGEL
 */
public class RolDAO {
    
    Connection connection;
    PreparedStatement ps;
    Conn con = new Conn();
    ResultSet rs;
    String sql;
    
    public RolDAO(){
    }
    
    public void insert(Rol rol){
        con.Connect();
        connection = con.getConnection();
        try{
            sql = "INSERT INTO rol(id_rol, nombre) VALUES (" + rol.getIdRol() + ", '" + rol.getNombre() + "');";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e){
        }
    }
    public void update(Rol rol){
        con.Connect();
        connection = con.getConnection();
        try{
            sql = "UPDATE rol SET nombre = '" + rol.getNombre() + "' WHERE id_rol = " + rol.getIdRol() + ";";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e){
        }
    }
    public void delete(Integer id){
        con.Connect();
        connection = con.getConnection();
        try{
            sql = "DELETE FROM rol WHERE id_rol = " + id + ";";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e){
        }
    }
    public List<Rol> listAll(){
        con.Connect();
        connection = con.getConnection();
        List<Rol> roles;
        roles = new ArrayList<>();
        sql = "SELECT * FROM rol";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre"));
                roles.add(rol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }
}
