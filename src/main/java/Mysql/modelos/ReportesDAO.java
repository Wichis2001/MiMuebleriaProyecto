/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;

import CompraVenta.Reporte;
import Mysql.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class ReportesDAO {
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
        public List Compras(Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, v.fecha_compra FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble)";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto pieza y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setMueble_identificador_mueble(rs.getString("identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis piezas
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        } else {
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, v.fecha_compra FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.fecha_compra BETWEEN '"+inicio+"' AND '" + finalfecha + "'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto pieza y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setMueble_identificador_mueble(rs.getString("identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
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
