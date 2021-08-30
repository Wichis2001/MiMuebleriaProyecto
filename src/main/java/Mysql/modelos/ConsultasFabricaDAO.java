/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;

import Mueble.MuebleEnsamblado;
import Mueble.Pieza;
import Mysql.Conexion;
import Mysql.Querys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class ConsultasFabricaDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
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
    public List listarMayoraMenor() {
        ArrayList<Pieza>listPiezas=new ArrayList<>();
        try{
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querypiezaMayoraMenor);
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
    public List listarMenoraMayor() {
        ArrayList<Pieza>listPiezas=new ArrayList<>();
        try{
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querypiezaMenoraMayor);
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
    public List listarMueble() {
        ArrayList<MuebleEnsamblado>listMuelble=new ArrayList<>();
        try{
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querymuebleconsulta);
           rs =ps.executeQuery();
           while(rs.next()){
               MuebleEnsamblado mueble=new MuebleEnsamblado();
               mueble.setIdentificador_mueble(rs.getString("identificador_mueble"));
               mueble.setNombre_mueble_ensamblado(rs.getString("nombre_mueble_ensamble"));
               mueble.setUsuario_constructor(rs.getString("usuario_constructor"));
               mueble.setPrecio(rs.getDouble("precio"));
               mueble.setFecha_ensamblaje(rs.getDate("fecha_ensamblaje").toLocalDate());
               listMuelble.add(mueble);
           }
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listMuelble;
    }
    public List listarMayoraMenorMueble() {
        ArrayList<MuebleEnsamblado>listMuelble=new ArrayList<>();
        try{
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querymuebleconsultamenor);
           rs =ps.executeQuery();
           while(rs.next()){
               MuebleEnsamblado mueble=new MuebleEnsamblado();
               mueble.setIdentificador_mueble(rs.getString("identificador_mueble"));
               mueble.setNombre_mueble_ensamblado(rs.getString("nombre_mueble_ensamble"));
               mueble.setUsuario_constructor(rs.getString("usuario_constructor"));
               mueble.setPrecio(rs.getDouble("precio"));
               mueble.setFecha_ensamblaje(rs.getDate("fecha_ensamblaje").toLocalDate());
               listMuelble.add(mueble);
           }
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listMuelble;
    }
    public List listarMenoraMayorMueble() {
        ArrayList<MuebleEnsamblado>listMuelble=new ArrayList<>();
        try{
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querymuebleconsultamayor);
           rs =ps.executeQuery();
           while(rs.next()){
               MuebleEnsamblado mueble=new MuebleEnsamblado();
               mueble.setIdentificador_mueble(rs.getString("identificador_mueble"));
               mueble.setNombre_mueble_ensamblado(rs.getString("nombre_mueble_ensamble"));
               mueble.setUsuario_constructor(rs.getString("usuario_constructor"));
               mueble.setPrecio(rs.getDouble("precio"));
               mueble.setFecha_ensamblaje(rs.getDate("fecha_ensamblaje").toLocalDate());
               listMuelble.add(mueble);
           }
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listMuelble;
    }
    public String verificadorCantidad(Pieza pieza){
        String palabra="";
        if(pieza.getCantidad()==0){
            palabra="Agotada";
        } else if(pieza.getCantidad()<=5){
            palabra="A punto de agotarse";
        } else if(pieza.getCantidad()>5){
            palabra="Disponible";
        }
        return palabra;
    }
}
