/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;

import CompraVenta.Cliente;
import CompraVenta.Reporte;
import CompraVenta.Venta;
import Mueble.MuebleEnsamblado;
import Mueble.Pieza;
import Mysql.Conexion;
import Mysql.Querys;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author luis
 */
public class ConstultaVentasDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
      
    /**
     * Este metodo me permite listar las piezas disponibles para poder exportarlas a JSP
     * @param nombre
     * @param inicio

     * @return
     */
        public List Compras(String nombre, Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT c.nombre, c.direccion, c.nit, v.fecha_compra ,dv.mueble_identificador_mueble, m.nombre_mueble_ensamble FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('"+nombre+"')";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto pieza y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setDireccion(rs.getString("direccion"));
                    reporte.setNit(rs.getString("nit"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis piezas
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        } else {
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT c.nombre, c.direccion, c.nit, v.fecha_compra ,dv.mueble_identificador_mueble, m.nombre_mueble_ensamble FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('"+nombre+"') AND v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto pieza y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setDireccion(rs.getString("direccion"));
                    reporte.setNit(rs.getString("nit"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis piezas
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }
        
    }

}
