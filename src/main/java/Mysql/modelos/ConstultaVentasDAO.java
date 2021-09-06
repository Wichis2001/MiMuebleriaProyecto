/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;
import CompraVenta.Reporte;
import Mueble.MuebleEnsamblado;
import Mysql.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Esta clase me ayuda a manejar mis consultas de mi area de ventas y poder servir como puente entre mi base de datos y mi servlet
 * @author luis
 */
public class ConstultaVentasDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
      
    /**
     * Este metodo me permite listar compras realizadas por un cliente
     * @param nombre
     * @param inicio
     * @param finalfecha

     * @return
     */
        public List Compras(String nombre, Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT c.nombre, c.direccion, c.nit, v.fecha_compra ,dv.mueble_identificador_mueble, m.nombre_mueble_ensamble FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('"+nombre+"')";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setDireccion(rs.getString("direccion"));
                    reporte.setNit(rs.getString("nit"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        } else {
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT c.nombre, c.direccion, c.nit, v.fecha_compra ,dv.mueble_identificador_mueble, m.nombre_mueble_ensamble FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('"+nombre+"') AND v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporotes y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setDireccion(rs.getString("direccion"));
                    reporte.setNit(rs.getString("nit"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }
        
    }

    /**
     * Este metodo me ayuda a determinar las devoluciones realizadas por un cliente hecas en un intervalo de itempo
     * @param nombre
     * @param inicio
     * @param finalfecha
     * @return
     */
    public List Devolucion(String nombre, Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT v.numeros_serie, d.fecha_devolucion, c.nombre, c.nit, m.nombre_mueble_ensamble, d.perdida FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN devolucion d ON (d.numeros_serie=v.numeros_serie) JOIN detalle_venta dv ON (dv.venta_id=v.id_venta) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('"+nombre+"')";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNumeroSerie(rs.getString("numeros_serie"));
                    reporte.setFechaDevolucion(rs.getDate("fecha_devolucion").toLocalDate());
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setNit(rs.getString("nit"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPerdida(rs.getDouble("perdida"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT v.numeros_serie, d.fecha_devolucion, c.nombre, c.nit, m.nombre_mueble_ensamble, d.perdida FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN devolucion d ON (d.numeros_serie=v.numeros_serie) JOIN detalle_venta dv ON (dv.venta_id=v.id_venta) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('"+nombre+"') AND d.fecha_devolucion BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reportes y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNumeroSerie(rs.getString("numeros_serie"));
                    reporte.setFechaDevolucion(rs.getDate("fecha_devolucion").toLocalDate());
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setNit(rs.getString("nit"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPerdida(rs.getDouble("perdida"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }
        
    }

    /**
     * Este metodo me ayuda a listar los muebles disponibles en la sala de ventas
     * @return
     */
    public List Muebles() {
        ArrayList<MuebleEnsamblado>listMuebles=new ArrayList<>();
        //Establecemos la Query requerida
        String sql="SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM mueble_ensamblado m WHERE m.estado=3";
        try{
            //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto mueble ensablado y lo asignamos a la lista
                MuebleEnsamblado mueble= new MuebleEnsamblado();
                mueble.setIdentificador_mueble(rs.getString("identificador_mueble"));
                mueble.setNombre_mueble_ensamblado(rs.getString("nombre_mueble_ensamble"));
                mueble.setPrecio(rs.getDouble("precio"));
                listMuebles.add(mueble);
           }
           // Error SQL al momento de listar mis muebles ensamblados
        }catch(SQLException e){
            System.err.print(e);

        } 
            return listMuebles;   
    }
    
    /**
     * Este metodo me ayuda a determinar los datos obtenidos a travez de los datos por una factura 
     * @param noFactura
     * @return
     */
    public List Factira(String noFactura) {
        ArrayList<Reporte>listReporte=new ArrayList<>();
        //Establecemos la Query solicitado
        String sql="SELECT v.numeros_serie, v.fecha_compra, c.nombre, c.direccion, c.nit, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, v.total FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.numeros_serie LIKE '" + noFactura +"'";
        try{
            //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto reporte y lo asignamos a la lista
                Reporte reporte = new Reporte();
                reporte.setNumeroSerie(rs.getString("numeros_serie"));
                reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                reporte.setNombreClientre(rs.getString("nombre"));
                reporte.setDireccion(rs.getString("direccion"));
                reporte.setNit(rs.getString("nit"));
                reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                reporte.setPerdida(rs.getDouble("total"));
                listReporte.add(reporte);
           }
           // Error SQL al momento de listar mis reportes
        }catch(SQLException e){
            System.err.print(e);

        } 
            return listReporte;   
    }
    
    /**
     * Este metodo me ayuda a determinar las ventas realizadasa en el día actual
     * @return
     */
    public List ventasDia(){
        Date corroborar= java.sql.Date.valueOf(LocalDate.now());
        //Establecemos nuesetra query con nuestros requerimientos
        ArrayList<Reporte>listReporte=new ArrayList<>();
        String sql="SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, v.fecha_compra FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.fecha_compra='"+corroborar+"'";
        try{
            //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto reporte y lo asignamos a la lista
                Reporte reporte= new Reporte();
                reporte.setMueble_identificador_mueble(rs.getString("identificador_mueble"));
                reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                reporte.setPerdida(rs.getDouble("precio"));
                reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                listReporte.add(reporte);
           }
           // Error SQL al momento de listar mis reportes
        }catch(SQLException e){
            System.err.print(e);

        } 
            return listReporte;   
    }
}
