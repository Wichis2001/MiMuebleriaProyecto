/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;
import Mysql.Conexion;
import Trabajadores.Usuario;
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
 * @author luis
 */
public class UsuarioFDAO {
    //Establecemos nuestras constantes
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario usuarios = new Usuario();
    
    public List listar() {
        ArrayList<Usuario>listUsuario=new ArrayList<>();
        try{
           String sql="SELECT * FROM usuario WHERE tipo_usuario=1 OR tipo_usuario=2 OR tipo_usuario=3;";
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto de usuario y le asignamos los parametros
               Usuario usuario=new Usuario();
               usuario.setNombre_usuario(rs.getString("nombre_usuario"));
               if((rs.getInt("tipo_usuario"))==1){
                   usuario.setTipo_usuario_S("Área Fabrica");
               } else if((rs.getInt("tipo_usuario"))==2){
                   usuario.setTipo_usuario_S("Área Puntos de Venta");
               } else if((rs.getInt("tipo_usuario"))==3){
                   usuario.setTipo_usuario_S("Área Financiera");
               }
               listUsuario.add(usuario);
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listUsuario;
    }
    
    public boolean delete(String nombre) {
        String sql="UPDATE usuario SET tipo_usuario='4' WHERE (nombre_usuario)='"+ nombre +"'"; 
        try {
            //Establecemos una conexion con la base de datos y enviamos una instruccion delete
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            //Error general exception al momento de hacer un delet
            System.err.print(e);
        }
        return false;
    }
    
    public boolean add(Usuario usuario) {
        try{
            String sql="INSERT INTO usuario (nombre_usuario,contraseña,tipo_usuario) VALUES(?,?,?)";
            //Establecemos una conexin la base de datos
            con=conexion.getConnection();
            con.setAutoCommit(false);
            //Realizamos un insert de una pieza a travez de enviar un parametro
            ps=con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getContraseña());
            ps.setInt(3, usuario.getTipo_usuario());
            ps.executeUpdate();
            con.commit();
            //Error SQL al momento de agregar una pieza
        } catch(SQLException e){
            System.err.print(e);
            try {
                //Regresamos el rollback
                con.rollback();
            } catch (SQLException ex) {
                //Error SQL al momento de hacer un roll back
                Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;   
    }
}
