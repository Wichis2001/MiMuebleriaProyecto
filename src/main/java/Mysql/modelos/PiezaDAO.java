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
 * Esta clase me permite establecer los metodos DAO que tendra una pieza al momento de utilizarlos a travez de un Servlet
 * @author luis
 */
public class PiezaDAO implements Interfaces.CRUDPieza{
    //Establecemos nuestras constantes
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
            //Establecemos una conexion con la bese de datos y le enviamos el parametro de mi Query
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.queryPieza);
           rs =ps.executeQuery();
           while(rs.next()){
               //Creamos un objeto de pieza y le asignamos los parametros
               Pieza pieza=new Pieza();
               pieza.setTipo(rs.getString("tipo"));
               pieza.setCosto(rs.getDouble("costo"));
               pieza.setCantidad(rs.getInt("cantidad"));
               listPiezas.add(pieza);
           }
           //Error SQL al momento de listar las piezas
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listPiezas;
    }

    @Override
    public Pieza list(String tipo, double costo) {
        String sql = "SELECT * FROM pieza WHERE tipo='"+ tipo +"' AND costo='"+ costo +"'";
        try{
            //Establecemos la conexion con la base de datos y le enviamos la quey
           con=conexion.getConnection();
           ps =con.prepareStatement(sql);
           rs =ps.executeQuery();
           while(rs.next()){
               //Seteamos el objeto pieza que tenemos en este parametro
               piezas.setTipo(rs.getString("tipo"));
               piezas.setCosto(rs.getDouble("costo"));
               piezas.setCantidad(rs.getInt("cantidad"));
           }
           //Error SQL al momento de listar la pieza
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return piezas;
    }

    @Override
    public boolean add(Pieza pieza) {
        try{
            //Establecemos una conexin la base de datos
            con=conexion.getConnection();
            con.setAutoCommit(false);
            //Realizamos un insert de una pieza a travez de enviar un parametro
            ps=con.prepareStatement(Insert.insertPieza);
            ps.setString(1, pieza.getTipo());
            ps.setString(2, String.valueOf(pieza.getCosto()));
            ps.setString(3, String.valueOf(pieza.getCantidad()));
            ps.executeUpdate();
            con.commit();
            //Error SQL al momento de agregar una pieza
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

    @Override
    public boolean edit(Pieza pieza) {
         String sql="UPDATE pieza SET cantidad='"+ pieza.getCantidad() + "' WHERE (tipo)='"+ pieza.getTipo() +"' AND costo='"+ pieza.getCosto() +"'"; 
         try {
             //Establecemos una conexion y enviamos la instruccion string para poder actualizar la pieza
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
            //Errpr general exception al momento de editar la pieza
        } catch (Exception e) {
            System.err.print(e);
        }
         return false;
    }

    @Override
    public boolean delete(String tipo, double costo) {
        String sql="DELETE FROM pieza WHERE (tipo)='"+ tipo +"' AND costo='"+ costo +"'";
        try {
            //Establecemos una conexion con la base de datos y enviamos una instruccion delete
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            //Error general exception al momento de hacer un delet
            System.err.print(e);
        }
        return false;
    }
    
    /**
     * Este metodo me permite verifiar la existencia de una pieza con la finalidad de que al moomento de agregarla si esta existe unicamente se actualiza
     * @param tipo
     * @param costo
     * @param cantidad
     * @return
     */
    public boolean verificar(String tipo, double costo, int cantidad){
        boolean existencia=false;
        try{
            //Establecemos una conexin con la base de datos y mandamos la Query
           con=conexion.getConnection();
           ps=con.prepareStatement("SELECT tipo, costo, cantidad FROM pieza WHERE UPPER(tipo)=UPPER('"+ tipo +"') AND costo='"+ costo +"'");
           rs=ps.executeQuery();
           while(rs.next()){
               //Si la pieza existe podemos agregarla
               existencia=true;
               piezaauxiliar.setTipo(rs.getString("tipo"));
               piezaauxiliar.setCosto(rs.getDouble("costo"));
               piezaauxiliar.setCantidad(rs.getInt("cantidad"));
           }
           //Error general sql al momento de hacer una verificacion
        }catch(SQLException e){
            System.err.print(e);
           
        } 
            return existencia;
    }
    
    /**
     * Este metodo me permite hacer un update de una pieza mientras esta se haya verifado que existe previamente en la base de datos
     * @param tipo
     * @param costo
     * @param cantidad
     * @return
     */
    public boolean update(String tipo, double costo, int cantidad){
        int total=piezaauxiliar.getCantidad()+cantidad;
        String sql="UPDATE pieza SET cantidad='"+ total + "' WHERE UPPER(tipo)=UPPER('"+ tipo +"') AND costo='"+ costo +"'"; 
        try{
            //Establecemos una conexion con la base de datos y enviamos la Query
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
            //Error general sql al momento de hacer una verificacion
        } catch(Exception e){
            System.err.print(e);
        }
        System.out.print("Dato actualizado");
        return false;
    }
    
    /**
     * Este metodo me ayuda a verificar la cantidad de piezas que hay a travez de un mensaje responsivo
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
            //Establecemos que la pieza estas disponible
        } else if(pieza.getCantidad()>5){
            palabra="Disponible";
        }
        return palabra;
    }  
}
