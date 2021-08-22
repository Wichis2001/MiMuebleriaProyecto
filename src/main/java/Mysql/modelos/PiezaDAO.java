/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;

import Mueble.Pieza;
import Mysql.Conexion;
import Mysql.Insert;
import Mysql.Querys;
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
public class PiezaDAO implements Interfaces.CRUDPieza{
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Pieza piezas = new Pieza();
    Pieza piezaauxiliar= new Pieza();

    @Override
    public List listar() {
        ArrayList<Pieza>listPiezas=new ArrayList<>();
        try{
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.queryPieza);
           rs =ps.executeQuery();
           while(rs.next()){
               Pieza pieza=new Pieza();
               pieza.setTipo(rs.getString("tipo"));
               pieza.setCosto(rs.getDouble("costo"));
               pieza.setCantidad(rs.getInt("cantidad"));
               listPiezas.add(pieza);
           }
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listPiezas;
    }

    @Override
    public Pieza list(String tipo, double costo) {
        String sql = "SELECT * FROM pieza WHERE tipo='"+ tipo +"' AND costo='"+ costo +"'";
        try{
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               piezas.setTipo(rs.getString("tipo"));
               piezas.setCosto(rs.getDouble("costo"));
               piezas.setCantidad(rs.getInt("cantidad"));
           }
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return piezas;
    }

    @Override
    public boolean add(Pieza pieza) {
        try{
            con=conexion.getConnection();
            con.setAutoCommit(false);
            ps=con.prepareStatement(Insert.insertPieza);
            ps.setString(1, pieza.getTipo());
            ps.setString(2, String.valueOf(pieza.getCosto()));
            ps.setString(3, String.valueOf(pieza.getCantidad()));
            ps.executeUpdate();
            con.commit();
        } catch(SQLException e){
            System.err.print(e);
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;   
    }

    @Override
    public boolean edit(Pieza pieza) {
         String sql="UPDATE pieza SET cantidad='"+ pieza.getCantidad() + "' WHERE (tipo)='"+ pieza.getTipo() +"' AND costo='"+ pieza.getCosto() +"'"; 
         try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.print(e);
        }
         return false;
    }

    @Override
    public boolean delete(String tipo, double costo) {
        String sql="DELETE FROM pieza WHERE (tipo)='"+ tipo +"' AND costo='"+ costo +"'";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.print(e);
        }
        return false;
    }
    
    public boolean verificar(String tipo, double costo, int cantidad){
        boolean existencia=false;
        try{
           con=conexion.getConnection();
           ps=con.prepareStatement("SELECT tipo, costo FROM pieza WHERE UPPER(tipo)=UPPER('"+ tipo +"') AND costo='"+ costo +"'");
           rs=ps.executeQuery();
           while(rs.next()){
               existencia=true;
               piezaauxiliar.setTipo(rs.getString("tipo"));
               piezaauxiliar.setCosto(rs.getDouble("costo"));
               piezaauxiliar.setCantidad(rs.getInt("cantidad"));
           }
        }catch(SQLException e){
            System.err.print(e);
           
        } 
            return existencia;
    }
    
    public boolean update(String tipo, double costo, int cantidad){
        int total=piezaauxiliar.getCantidad()+cantidad;
        String sql="UPDATE pieza SET cantidad='"+ total + "' WHERE UPPER(tipo)=UPPER('"+ tipo +"') AND costo='"+ costo +"'"; 
        try{
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(Exception e){
            
        }
        System.out.print("Dato actualizado");
        return false;
    }
    
}
