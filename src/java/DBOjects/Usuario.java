/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOjects;

/**
 *
 * @author ANGEL
 */
public class Usuario {
    Integer idUsuario;
    Integer idRol;
    String nombre;
    String activo;
    String rol;
    
    public Usuario(){
    }
    
    public Usuario(Integer idUsuario, Integer idRol, String nombre, String activo) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getActivo() {
        return activo;
    }
    
    public String getRol() {
        return rol;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
}
