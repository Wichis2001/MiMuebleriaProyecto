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
 * Esta clase me ayuda con la creacion de un mueble y las piezas necesarias par poder ser ensamblado
 * @author luis
 */
public class CrearMuebleDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Este metodo me ayuda verificar el nombre de un mueble con la finalidad de que este no se repita
     * @param nombre
     * @return
     */
    public boolean verificar(String nombre){
        boolean verificador=false;
        //Establecemos la Query sollicitada
        String sql="SELECT nombre_mueble FROM mueble WHERE UPPER(mueble.nombre_mueble)=UPPER('"+nombre+"')";
        try{
            //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               verificador=true;
           }
           //Error SQL al momento de ejecutar mi Query
        }catch(SQLException e){
            verificador=false;
            System.err.print(e);
        }
        return verificador;
    }

    /**
     * Este metodo me asigna un objeto mueble a mi base de datos
     * @param mueble
     * @return
     */
    public boolean addMueble(Mueble mueble) {
        //Establecemos la Query sollicitada
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
            //Error SQL al momento de agregar un mueble
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
    
    /**
     * Este metodo me ayuda a listar las piezas para verificar que estas existan en mi base de datos
     * @return
     */
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
    
    /**
     * Este metodo  me ayuda a verificar de que no se repita mas de una vez la sentencia de emsamblaje de piezas
     * @param nombre
     * @return
     */
    public boolean verificarPieza(String nombre){
        boolean verificador=false;
        //Establecemos la Query sollicitada
        String sql="SELECT * FROM ensamble_piezas WHERE upper(ensamble_piezas.pieza_tipo)=UPPER('"+nombre+"') AND UPPER(ensamble_piezas.mueble_nombre)=Upper('"+ServletMuebleF.nombreMueble+"')";
        try{
            //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto de verificador y le asignamos los parametros
               verificador=true;
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            verificador=false;
            System.err.print(e);
        }
        return verificador;
    }

    /**
     * Este metodo me ayuda a asignar una nueva instrucion para ensamblar piezas
     * @param ensamblaje
     * @return
     */
    public boolean addPieza(EnsamblePiezas ensamblaje) {
        //Establecemos el incert conjunto a sus parametros
        String sql="INSERT INTO ensamble_piezas (mueble_nombre,pieza_tipo,cantidad) VALUES(?,?,?)";
        try{
            //Establecemos una conexin la base de datos
            con=conexion.getConnection();
            con.setAutoCommit(false);
            //Realizamos un insert de un ensamblaje de piezas a travez de enviar un parametro
            ps=con.prepareStatement(sql);
            ps.setString(1, ServletMuebleF.nombreMueble);
            ps.setString(2, ensamblaje.getPieza_tipo());
            ps.setInt(3, ensamblaje.getCantidad());
            ps.executeUpdate();
            con.commit();
            //Error SQL al momento de agregar un ensamblaje de parametros
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
