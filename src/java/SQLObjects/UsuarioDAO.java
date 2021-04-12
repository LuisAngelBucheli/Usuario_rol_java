/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLObjects;

import DBOjects.Usuario;
import java.sql.*;
import conection.Conn;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANGEL
 */
public class UsuarioDAO {
    
    Connection connection;
    PreparedStatement ps;
    Conn con = new Conn();
    ResultSet rs;
    String sql;
    
    public UsuarioDAO(){
    }
    
    public boolean insert(Usuario user){
        boolean success;
        con.Connect();
        connection = con.getConnection();
        try{
            String sql = "INSERT INTO usuario(id_rol,nombre,activo) VALUES (" + user.getIdRol() + ", '" + user.getNombre() + "', '" + user.getActivo().charAt(0) + "');";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            success = true;
        } catch (SQLException e){
            success = false;
        }
        return success;
    }
    public boolean update(Usuario user){
        boolean success;
        con.Connect();
        connection = con.getConnection();
        try{
            String sql = "UPDATE usuario SET nombre = '" + user.getNombre() + "', id_rol = " + user.getIdRol() + " WHERE id_usuario = " + user.getIdUsuario() + ";";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            success = true;
        } catch (SQLException e){
            success = false;
        }
        return success;
    }
    
    public boolean delete(Integer id){
        boolean success;
        con.Connect();
        connection = con.getConnection();
        try{
            String sql = "DELETE FROM usuario WHERE id_usuario = " + id + ";";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            success = true;
        } catch (SQLException e){
            success = false;
        }
        return success;
    }
    
    public List<Usuario> selectUsers(String nombre){
        con.Connect();
        connection = con.getConnection();
        List<Usuario> users;
        users = new ArrayList<>();
        sql = "SELECT u.*, r.nombre AS rol_name FROM usuario u INNER JOIN rol r ON u.id_rol=r.id_rol";
        if (!nombre.equals("")) {
            sql += " WHERE u.nombre ILIKE '%"+nombre+"%'";
        }
        sql += " ORDER BY u.id_usuario ASC;";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setIdRol(rs.getInt("id_rol"));
                user.setNombre(rs.getString("nombre"));
                user.setActivo(rs.getString("activo"));
                user.setRol(rs.getString("rol_name"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}
