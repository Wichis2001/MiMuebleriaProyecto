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
 * Este DAO me permite establecer los metodos que emplementara mi servlet de iinicio de sesion
 * @author luis
 */
public class UsuarioDAO implements Validar{
    //Establecemos nuestras variables y constantes
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public int validar(Usuario usuario) {
        int r=0;
        try {
            //Establsecemos una conexion y establecemos la Query a realizar
            con=conexion.getConnection();
            ps=con.prepareStatement(Querys.queryUsuarios);
            //Asignamos los parametros
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getContraseña());
            rs=ps.executeQuery();
            while(rs.next()){
                //El usuario fue encontrado y devolvemos los parametros
                r=r+1;
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setTipo_usuario((rs.getInt("tipo_usuario")));
            }
            //Usuario encontrado
            if(r==1){
                return 1;
                //Usuario no encontrado
            }else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * Este metodo me permite establecer hacia que ventana se redirigira mi usuario
     * @param usuario
     * @return
     */
    public int correspondencia(Usuario usuario) {
        int respuesta=0;
        try {
            //Establecemos una conexion y enviamos la Query
            con=conexion.getConnection();
            ps=con.prepareStatement(Querys.queryUsuarios);
            //Asignamos los parametros
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getContraseña());
            rs=ps.executeQuery();
            while(rs.next()){
                //Usuario fue encontrado cambiamos los parametros del usuario encontrado
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setTipo_usuario((rs.getInt("tipo_usuario")));
            }
            respuesta=usuario.getTipo_usuario();
            //Usuario del area de fabrica
            if(respuesta==1){
                return 1;
                //Usuario del area de Venta
            }else if(respuesta==2){
                return 2;
                //Usuario del area de administracion
            } else if(respuesta==3){
                return 3;
            } else {
                //Usuario que fue renistringido de la base de datos
                return 4;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
