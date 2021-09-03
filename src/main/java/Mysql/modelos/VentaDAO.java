/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;
import CompraVenta.Venta;
import Mysql.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class VentaDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    /**
     *
     * @return
     */
    public String GenerarSerie(){
        String numeroSerie="";
        String sql="SELECT MAX(numeros_serie) FROM venta";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                numeroSerie=rs.getString(1);     
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return numeroSerie;
    }
    
    /**
     *
     * @return
     */
    public String idVentas(){
        String idventas="";
        String sql="SELECT MAX(id_venta) FROM venta";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                idventas=rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return idventas;
    }
    
    /**
     *
     * @param venta
     * @return
     */
    public int guardarVenta(Venta venta){
        String sql="INSERT INTO venta(total,fecha_compra,nit_cliente,usuario_vendedor,numeros_serie)VALUES(?,?,?,?,?)";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setDouble(1, venta.getMonto());
            ps.setDate(2, java.sql.Date.valueOf(venta.getFecha_compra()));
            ps.setString(3, venta.getNit_cliente());
            ps.setString(4, venta.getUsuario_vendedor());
            ps.setString(5, venta.getNumero_serie());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print(e);
        }
        return r;
    }
    
    /**
     *
     * @param venta
     * @return
     */
    public int guardarDetalledelaVenta(Venta venta){
        String sql="INSERT INTO detalle_venta(venta_id,mueble_identificador_mueble,precio_venta)VALUES(?,?,?)";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, venta.getId_venta());
            ps.setString(2, venta.getIdmueble());
            ps.setDouble(3, venta.getTotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print(e);
        }
        return r;
    }
    

}

