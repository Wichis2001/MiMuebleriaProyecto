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
 * Esta clase me ayuda a establecer los metodos que va tener una venta, a la vez que servira como puente entre la base de datos y el servlet
 * @author luis
 */
public class VentaDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    /**
     * Este metodo me ayuda a generar el numero de factura para una nueva venta
     * @return
     */
    public String GenerarSerie(){
        String numeroSerie="";
        //Establecemos la consulta que nos devueva el maximo numero de serie
        String sql="SELECT MAX(numeros_serie) FROM venta";
        try {
            //Establecemos una conexion con la base de datos
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                //Asitnamos la respuesta
                numeroSerie=rs.getString(1);     
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return numeroSerie;
    }
    
    /**
     * Este metodo me ayuda a determinar el numero de ventas maximo para el manejo de las ventas
     * @return
     */
    public String idVentas(){
        String idventas="";
        //Esta consulta me devuelve el id de ventas maximo
        String sql="SELECT MAX(id_venta) FROM venta";
        try {
            //Establecemos una conexion con la base de datos
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                //Asignamos el id de la venta
                idventas=rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return idventas;
    }
    
    /**
     * Este metodo me ayuda a guardar una nueva venta
     * @param venta
     * @return
     */
    public int guardarVenta(Venta venta){
        //Asignamos el insert para poder almacenar una nueva venta en la base de datos
        String sql="INSERT INTO venta(total,fecha_compra,nit_cliente,usuario_vendedor,numeros_serie)VALUES(?,?,?,?,?)";
        try {
            //Establecemos una conexion con la base de datos
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            //Establecemos los parametros para el Insert
            ps.setDouble(1, venta.getMonto());
            ps.setDate(2, java.sql.Date.valueOf(venta.getFecha_compra()));
            ps.setString(3, venta.getNit_cliente());
            ps.setString(4, venta.getUsuario_vendedor());
            ps.setString(5, venta.getNumero_serie());
            ps.executeUpdate();
            //Excepcion de SQL
        } catch (SQLException e) {
            System.err.print(e);
        }
        return r;
    }
    
    /**
     * Este metodo me ayuda a guardar a guardar los detalles de una venta a una base de datos
     * @param venta
     * @return
     */
    public int guardarDetalledelaVenta(Venta venta){
        //Establecemos el insert para poder almacenar una nueva venta
        String sql="INSERT INTO detalle_venta(venta_id,mueble_identificador_mueble,precio_venta)VALUES(?,?,?)";
        try {
            //Establecemos una conexion con la base de datos
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            //Asignamos los parametor para poder asignar una nueva venta
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

