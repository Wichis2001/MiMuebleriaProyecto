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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class PiezaDAO implements Interfaces.CRUDPieza{
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Pieza pieza = new Pieza();
    
    @Override
    public List listar() {
        ArrayList<Pieza>listPiezas=new ArrayList<>();
        try{
           con=conexion.getConnection();
           ps=con.prepareStatement(Querys.queryPieza);
           rs=ps.executeQuery();
           while(rs.next()){
               Pieza pieza=new Pieza();
               pieza.setTipo(rs.getString("tipo"));
               pieza.setCosto(rs.getDouble("costo"));
               pieza.setCantidad(rs.getInt("cantidad"));
               listPiezas.add(pieza);
           }
        }catch(Exception e){
            System.err.print(e);
        }
            return listPiezas;
    }

    @Override
    public Pieza list(String tipo, double costo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Pieza pieza) {
        try{
            con=conexion.getConnection();
            ps=con.prepareStatement(Insert.insertPieza);
            ps.setString(1, pieza.getTipo());
            ps.setString(2, String.valueOf(pieza.getCosto()));
            ps.setString(3, String.valueOf(pieza.getCantidad()));
            ps.executeUpdate();
        } catch(Exception e){
            System.err.print(e);
        }
        return false;
        
    }

    @Override
    public boolean edit(Pieza pieza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String tipo, double costo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
