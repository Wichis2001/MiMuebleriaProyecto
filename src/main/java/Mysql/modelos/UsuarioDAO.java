/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;
import Interfaces.Validar;
import Mysql.Conexion;
import Mysql.Querys;
import Trabajadores.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author luis
 */
public class UsuarioDAO implements Validar{
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public int validar(Usuario usuario) {
        int r=0;
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(Querys.queryUsuarios);
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getContraseña());
            rs=ps.executeQuery();
            while(rs.next()){
                r=r+1;
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setTipo_usuario((rs.getInt("tipo_usuario")));
            }
            if(r==1){
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int correspondencia(Usuario usuario) {
        int respuesta=0;
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(Querys.queryUsuarios);
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getContraseña());
            rs=ps.executeQuery();
            while(rs.next()){
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setTipo_usuario((rs.getInt("tipo_usuario")));
            }
            respuesta=usuario.getTipo_usuario();
            if(respuesta==1){
                return 1;
            }else if(respuesta==2){
                return 2;
            } else{
                return 3;
            } 
        } catch (Exception e) {
            return 0;
        }
    }
}
