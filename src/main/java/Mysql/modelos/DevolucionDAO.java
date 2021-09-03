/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;

import CompraVenta.Devolucion;
import CompraVenta.Venta;
import Mueble.MuebleEnsamblado;
import Mueble.Pieza;
import Mysql.Conexion;
import Mysql.Insert;
import Mysql.Querys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class DevolucionDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Venta ventas = new Venta();
    MuebleEnsamblado mueble= new MuebleEnsamblado();
    /**
     *
     * @param noFactura
     * @return
     */
    public int venta(String noFactura) {
        int r=0;
        String sql="SELECT id_venta FROM venta WHERE numeros_serie="+noFactura+"";
        try{            
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery(); 
           while(rs.next()){
               //Creamos un objeto de pieza y le asignamos los parametros
               ventas.setId_venta(rs.getInt(1));
               r=1;
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            System.err.print(e);
        } 
            return r;
    }
    
    /**
     *
     * @param noFactura
     * @return
     */
    public Venta ventaValor(String noFactura) {
        Venta venta= new Venta();
        String sql="SELECT id_venta FROM venta WHERE numeros_serie="+noFactura+"";
        try{            
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery(); 
           while(rs.next()){
               //Creamos un objeto de pieza y le asignamos los parametros
               venta.setId_venta(rs.getInt(1));
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            System.err.print(e);
        } 
            return venta;
    }
    
    /**
     *
     * @param noFactura
     * @return
     */
    public boolean corroborarFecha(String noFactura) {
        Venta venta=new Venta();
        Boolean posible=false;
        Long dias;
        String sql="SELECT fecha_compra FROM venta WHERE numeros_serie="+noFactura+"";
        try{            
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery(); 
           while(rs.next()){
               venta.setFecha_compra(rs.getDate(1).toLocalDate());
           }
           if(venta.getFecha_compra()!=null){
               LocalDate fechaactual=LocalDate.now();
               dias=ChronoUnit.DAYS.between(venta.getFecha_compra(),fechaactual);
               if(dias>7){
                   posible=false;
               } else{
                   posible=true;
               }
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            System.err.print(e);
            
        } catch(Exception e){
           System.err.print(e);
        }
        return posible;
    }
    
    /**
     *
     * @param noVenta
     * @return
     */
    public List listVenta(int noVenta) {
        ArrayList<Venta>listVenta=new ArrayList<>();
        String sql="SELECT mueble_identificador_mueble, precio_venta FROM detalle_venta WHERE venta_id="+noVenta+"";
        try{            
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery(); 
           while(rs.next()){
               //Creamos un objeto de pieza y le asignamos los parametros
               Venta venta=new Venta();
               venta.setIdmueble(rs.getString(1));
               venta.setTotal(rs.getDouble(2));
               listVenta.add(venta);
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            System.err.print(e);
        } 
        return listVenta;
    }
    
    /**
     *
     * @param idMueble
     * @return
     */
    public MuebleEnsamblado verificarEstadoMueble(String idMueble) {
        String sql = "SELECT * FROM mueble_ensamblado WHERE identificador_mueble='"+ idMueble +"'";
        try{
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
                mueble.setIdentificador_mueble(rs.getString(1));
                mueble.setFecha_ensamblaje(rs.getDate(2).toLocalDate());
                mueble.setPrecio(rs.getDouble(3));
                mueble.setCosto_construccion(rs.getDouble(4));
                mueble.setEstado(rs.getInt(5));
                mueble.setUsuario_constructor(rs.getString(6));
                mueble.setNombre_mueble_ensamblado(rs.getString(7));
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            System.err.print(e);
        }
        return mueble;
    }
    
    /**
     *
     * @param devolucion
     * @return
     */
    public boolean add(Devolucion devolucion){
        try{
            //Establecemos una conexin la base de datos
            con=conexion.getConnection();
            con.setAutoCommit(false);
            //Realizamos un insert de una pieza a travez de enviar un parametro
            ps=con.prepareStatement(Insert.INSERTDEVOLUCION);
            ps.setDate(1, java.sql.Date.valueOf(devolucion.getFecha_devolucion()));
            ps.setDouble(2, devolucion.getPerdida());
            ps.setString(3, devolucion.getNo_factura());
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
