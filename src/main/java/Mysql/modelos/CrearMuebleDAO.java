/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;
import Mueble.Mueble;
import Mueble.Pieza;
import Mysql.Conexion;
import Mueble.*;
import Mysql.Querys;
import Servlets.ServletMuebleF;
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
public class CrearMuebleDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public boolean verificar(String nombre){
        boolean verificador=false;
        String sql="SELECT nombre_mueble FROM mueble WHERE UPPER(mueble.nombre_mueble)=UPPER('"+nombre+"')";
        try{
            //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               verificador=true;
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            verificador=false;
            System.err.print(e);
        }
        return verificador;
    }

    public boolean addMueble(Mueble mueble) {
        String sql="INSERT INTO mueble (nombre_mueble,precio) VALUES(?,?)";
        try{
            //Establecemos una conexin la base de datos
            con=conexion.getConnection();
            con.setAutoCommit(false);
            //Realizamos un insert de un mueble a travez de enviar un parametro
            ps=con.prepareStatement(sql);
            ps.setString(1, mueble.getNombre_mueble_ensamble());
            ps.setDouble(2, mueble.getPrecio());
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
    
    public List listar() {
        ArrayList<Pieza>listPiezas=new ArrayList<>();
        try{
            //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.queryPieza);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto de pieza y le asignamos los parametros
               Pieza pieza=new Pieza();
               pieza.setTipo(rs.getString("tipo"));
               pieza.setCosto(rs.getDouble("costo"));
               pieza.setCantidad(rs.getInt("cantidad"));
               listPiezas.add(pieza);
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listPiezas;
    }
    
    public boolean verificarPieza(String nombre){
        boolean verificador=false;
        String sql="SELECT * FROM ensamble_piezas WHERE upper(ensamble_piezas.pieza_tipo)=UPPER('"+nombre+"') AND UPPER(ensamble_piezas.mueble_nombre)=Upper('"+ServletMuebleF.nombreMueble+"')";
        try{
            //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto de pieza y le asignamos los parametros
               verificador=true;
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            verificador=false;
            System.err.print(e);
        }
        return verificador;
    }

    public boolean addPieza(EnsamblePiezas ensamblaje) {
        String sql="INSERT INTO ensamble_piezas (mueble_nombre,pieza_tipo,cantidad) VALUES(?,?,?)";
        try{
            //Establecemos una conexin la base de datos
            con=conexion.getConnection();
            con.setAutoCommit(false);
            //Realizamos un insert de un mueble a travez de enviar un parametro
            ps=con.prepareStatement(sql);
            ps.setString(1, ServletMuebleF.nombreMueble);
            ps.setString(2, ensamblaje.getPieza_tipo());
            ps.setInt(3, ensamblaje.getCantidad());
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
