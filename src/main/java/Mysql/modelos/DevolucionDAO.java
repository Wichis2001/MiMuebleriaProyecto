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
 * Esta calse me ayuda a menejar mis devoluciones colocondo toda la logica necesaria, a la vez que me sirve como punte entre mi base datos y mi servlet
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
     * Esta clase me devuelve la venta registrada a travez de un numero de factura
     * @param noFactura
     * @return
     */
    public int venta(String noFactura) {
        int r=0;
        //Establecemos la Query sollicitada
        String sql="SELECT id_venta FROM venta WHERE numeros_serie="+noFactura+"";
        try{            
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery(); 
           while(rs.next()){
               //Creamos un objeto de ventas y le asignamos los parametros
               ventas.setId_venta(rs.getInt(1));
               r=1;
           }
           //Error SQL al momento de listar las ventas
        }catch(SQLException e){
            System.err.print(e);
        } 
            return r;
    }
    
    /**
     * Este metodo me ayuda a determinar el numero de venta ala que esta asociada mi numero de factura
     * @param noFactura
     * @return
     */
    public Venta ventaValor(String noFactura) {
        Venta venta= new Venta();
        //Establecemos la Query sollicitada
        String sql="SELECT id_venta FROM venta WHERE numeros_serie="+noFactura+"";
        try{            
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery(); 
           while(rs.next()){
               //Creamos un objeto de venta y le asignamos los parametros
               venta.setId_venta(rs.getInt(1));
           }
           //Error SQL al momento de listar las ventas
        }catch(SQLException e){
            System.err.print(e);
        } 
            return venta;
    }
    
    /**
     * Este metodo me ayuda a corroborar la fecha en la que se quiere realizar la devolucion para garantizar que no se hayan pasado mas de 7 dias
     * @param noFactura
     * @return
     */
    public boolean corroborarFecha(String noFactura) {
        Venta venta=new Venta();
        Boolean posible=false;
        Long dias;
        //Establecemos la Query sollicitada
        String sql="SELECT fecha_compra FROM venta WHERE numeros_serie="+noFactura+"";
        try{            
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery(); 
           while(rs.next()){
               //Establecemos un objeto venta el cual analizaremos su fecha
               venta.setFecha_compra(rs.getDate(1).toLocalDate());
           }
           //Hacer una comparacion de fechas con la actual par ver la cantidad de dias que ha transcurrido
           if(venta.getFecha_compra()!=null){
               LocalDate fechaactual=LocalDate.now();
               dias=ChronoUnit.DAYS.between(venta.getFecha_compra(),fechaactual);
               if(dias>7){
                   posible=false;
               } else{
                   posible=true;
               }
           }
           //Error SQL al momento de listar las facturas
        }catch(SQLException e){
            System.err.print(e);
            
        } catch(Exception e){
           System.err.print(e);
        }
        return posible;
    }
    
    /**
     * Este metodo me ayuda a listar mis ventas a travez de un numero de venta
     * @param noVenta
     * @return
     */
    public List listVenta(int noVenta) {
        ArrayList<Venta>listVenta=new ArrayList<>();
        //Establecemos la Query sollicitada
        String sql="SELECT mueble_identificador_mueble, precio_venta FROM detalle_venta WHERE venta_id="+noVenta+"";
        try{            
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery(); 
           while(rs.next()){
               //Creamos un objeto de venta y le asignamos los parametros
               Venta venta=new Venta();
               venta.setIdmueble(rs.getString(1));
               venta.setTotal(rs.getDouble(2));
               listVenta.add(venta);
           }
           //Error SQL al momento de listar las ventas
        }catch(SQLException e){
            System.err.print(e);
        } 
        return listVenta;
    }
    
    /**
     * Ese metodo me ayuda a verificar el estado de un mueble con la finalidad de no registrr un meuble que haya sido vendido previamente
     * @param idMueble
     * @return
     */
    public MuebleEnsamblado verificarEstadoMueble(String idMueble) {
        //Establecemos la Query sollicitada
        String sql = "SELECT * FROM mueble_ensamblado WHERE identificador_mueble='"+ idMueble +"'";
        try{
           //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto mueble, le asignamos sus respectivos atributos y lo listamos
                mueble.setIdentificador_mueble(rs.getString(1));
                mueble.setFecha_ensamblaje(rs.getDate(2).toLocalDate());
                mueble.setPrecio(rs.getDouble(3));
                mueble.setCosto_construccion(rs.getDouble(4));
                mueble.setEstado(rs.getInt(5));
                mueble.setUsuario_constructor(rs.getString(6));
                mueble.setNombre_mueble_ensamblado(rs.getString(7));
           }
           //Error SQL al momento de listar los muebles
        }catch(SQLException e){
            System.err.print(e);
        }
        return mueble;
    }
    
    /**
     * Este metodo me ayuda a asignar una nueva devolucion
     * @param devolucion
     * @return
     */
    public boolean add(Devolucion devolucion){
        try{
            //Establecemos una conexin la base de datos
            con=conexion.getConnection();
            con.setAutoCommit(false);
            //Realizamos un insert de una devoluciohn a travez de enviar un parametro
            ps=con.prepareStatement(Insert.INSERTDEVOLUCION);
            ps.setDate(1, java.sql.Date.valueOf(devolucion.getFecha_devolucion()));
            ps.setDouble(2, devolucion.getPerdida());
            ps.setString(3, devolucion.getNo_factura());
            ps.executeUpdate();
            con.commit();
            //Error SQL al momento de agregar una devolucion
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
