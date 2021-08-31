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
 * Este clase DAO me permite establecer los metodos que tendra la clase para que puede ser empleada por el Servlet
 * @author luis
 */
public class ConsultasFabricaDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Este metodo me permite listar las piezas disponibles para poder exportarlas a JSP
     * @return
     */
    public List listar() {
        ArrayList<Pieza>listPiezas=new ArrayList<>();
        try{
            //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.queryPieza);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto pieza y lo asignamos a la lista
               Pieza pieza=new Pieza();
               pieza.setTipo(rs.getString("tipo"));
               pieza.setCosto(rs.getDouble("costo"));
               pieza.setCantidad(rs.getInt("cantidad"));
               listPiezas.add(pieza);
           }
           // Error SQL al momento de listar mis piezas
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listPiezas;
    }

    /**
     * Este metodo me devuelve una lista de piezas que estan ordenadas de mayor a menor
     * @return
     */
    public List listarMayoraMenor() {
        ArrayList<Pieza>listPiezas=new ArrayList<>();
        try{
           //Establecemos una nueva conexion y enviamos la Query par aque pueda ser examinada
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querypiezaMayoraMenor);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos una nueva pieza y la asignamos a la lista
               Pieza pieza=new Pieza();
               pieza.setTipo(rs.getString("tipo"));
               pieza.setCosto(rs.getDouble("costo"));
               pieza.setCantidad(rs.getInt("cantidad"));
               listPiezas.add(pieza);
           }
           // Error SQL al momento de listar mis piezas
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listPiezas;
    }

    /**
     *Este metodo me devuelve una lista de piezas que estan ordenadas de menor a mayor
     * @return
     */
    public List listarMenoraMayor() {
        ArrayList<Pieza>listPiezas=new ArrayList<>();
        try{
           //Establecemos una nueva conexion y enviamos la Query par aque pueda ser examinada
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querypiezaMenoraMayor);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos una nueva pieza y la asignamos a la lista
               Pieza pieza=new Pieza();
               pieza.setTipo(rs.getString("tipo"));
               pieza.setCosto(rs.getDouble("costo"));
               pieza.setCantidad(rs.getInt("cantidad"));
               listPiezas.add(pieza);
           }
           // Error SQL al momento de listar mis piezas
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listPiezas;
    }

    /**
     * Este metodo me permite listar los muebles construidos disponibles para poder exportarlas a JSP
     * @return
     */
    public List listarMueble() {
        ArrayList<MuebleEnsamblado>listMuelble=new ArrayList<>();
        try{
            //Establecemos una nueva conexion y enviamos la Query par aque pueda ser examinada
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querymuebleconsulta);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un nuevo mueble y la asignamos a la lista
               MuebleEnsamblado mueble=new MuebleEnsamblado();
               mueble.setIdentificador_mueble(rs.getString("identificador_mueble"));
               mueble.setNombre_mueble_ensamblado(rs.getString("nombre_mueble_ensamble"));
               mueble.setUsuario_constructor(rs.getString("usuario_constructor"));
               mueble.setPrecio(rs.getDouble("precio"));
               mueble.setFecha_ensamblaje(rs.getDate("fecha_ensamblaje").toLocalDate());
               listMuelble.add(mueble);
           }
           // Error SQL al momento de listar mis muebles
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listMuelble;
    }

    /**
     * Este metodo me devuelve una lista de muebles que estan ordenadas de mayor a menor
     * @return
     */
    public List listarMayoraMenorMueble() {
        ArrayList<MuebleEnsamblado>listMuelble=new ArrayList<>();
        try{
            //Establecemos una nueva conexion y enviamos la Query para que pueda ser examinada
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querymuebleconsultamenor);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un nuevo mueble y la asignamos a la lista
               MuebleEnsamblado mueble=new MuebleEnsamblado();
               mueble.setIdentificador_mueble(rs.getString("identificador_mueble"));
               mueble.setNombre_mueble_ensamblado(rs.getString("nombre_mueble_ensamble"));
               mueble.setUsuario_constructor(rs.getString("usuario_constructor"));
               mueble.setPrecio(rs.getDouble("precio"));
               mueble.setFecha_ensamblaje(rs.getDate("fecha_ensamblaje").toLocalDate());
               listMuelble.add(mueble);
           }
           // Error SQL al momento de listar mis muebles
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listMuelble;
    }

    /**
     * Este metodo me devuelve una lista de muebles que estan ordenadas de menor a mayor
     * @return
     */
    public List listarMenoraMayorMueble() {
        ArrayList<MuebleEnsamblado>listMuelble=new ArrayList<>();
        try{
            //Establecemos una nueva conexion y enviamos la Query para que pueda ser examinada
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.querymuebleconsultamayor);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un nuevo mueble y la asignamos a la lista
               MuebleEnsamblado mueble=new MuebleEnsamblado();
               mueble.setIdentificador_mueble(rs.getString("identificador_mueble"));
               mueble.setNombre_mueble_ensamblado(rs.getString("nombre_mueble_ensamble"));
               mueble.setUsuario_constructor(rs.getString("usuario_constructor"));
               mueble.setPrecio(rs.getDouble("precio"));
               mueble.setFecha_ensamblaje(rs.getDate("fecha_ensamblaje").toLocalDate());
               listMuelble.add(mueble);
           }
           // Error SQL al momento de listar mis muebles
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listMuelble;
    }

    /**
     * Este metodo me permite analizar la cantidad de piezas que hay disponible en el sistema
     * @param pieza
     * @return
     */
    public String verificadorCantidad(Pieza pieza){
        String palabra="";
        //Establecemos que la pieza esta agotada
        if(pieza.getCantidad()==0){
            palabra="Agotada";
            //Establecemos que la pieza esta a punto de agotarse
        } else if(pieza.getCantidad()<=5){
            palabra="A punto de agotarse";
            //Establecemos que la pieza esta disponible
        } else if(pieza.getCantidad()>5){
            palabra="Disponible";
        }
        return palabra;
    }
}
