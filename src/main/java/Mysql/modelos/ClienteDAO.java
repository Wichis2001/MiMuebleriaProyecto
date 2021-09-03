/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;

import CompraVenta.Cliente;
import Mueble.Pieza;
import Mysql.Conexion;
import Mysql.Insert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este DAO me permite la manipulacion de un objeto cliente como comunicador entre mi Servlet y mi pagina web
 * @author luis
 */
public class ClienteDAO{
    //Establecemos variables
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Cliente cliente=new Cliente();
    int r;
    
    /**
     * Este metodo me devuelve un cliente a travez de un nit
     * @param nit
     * @return
     */
    public Cliente buscar(String nit){
        Cliente c=new Cliente();
        //Establecemos un select para que me devuelva un cliente
        String sql="SELECT * FROM cliente WHERE UPPER(nit)=UPPER("+nit+")";
        try{
            //Nos conectamos a la base de datos y enviamos como parametro la conexion
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                //Agregamos un nuevo cliente
                c.setNit(rs.getString(1));
                c.setNombre(rs.getString(5));
                c.setMunicipio(rs.getString(3));
                c.setDepartamento(rs.getString(4));
                c.setDireccion(rs.getString(2));
            }
            //Error SQL
        } catch(SQLException e){
            System.err.print(e);
        }
        return c;
    }
    
    /**
     * Establecemos un metodo booleano para determinar si este cliente fue realmente encontrado, ya que si esto no es asi iremos a crear otro cliente
     * @param nit
     * @return
     */
    public Boolean encontrado(String nit){
        boolean esta=false;
        Cliente c=new Cliente();
        //Establecemos un select para que me devuelva un cliente a travez de un nit
        String sql="SELECT * FROM cliente WHERE UPPER(nit)=UPPER("+nit+")";
        try{
            //Nos conectamos a la base
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                esta=true;
            }
        } catch(SQLException e){
            System.err.print(e);
        }
        return esta;
    }
    
    /**
     * Este metodo me permite agregar un nuevo cliente desde mi Servlet
     * @param cliente
     * @return
     */
    public boolean add(Cliente cliente) {
        try{
            //Establecemos una conexin la base de datos
            con=conexion.getConnection();
            con.setAutoCommit(false);
            //Realizamos un insert de Cliente pieza a travez de enviar un parametro
            ps=con.prepareStatement(Insert.INSERTCLIENTE);
            ps.setString(1, String.valueOf(cliente.getNit()));
            ps.setString(2, String.valueOf(cliente.getDireccion()));
            ps.setString(3, String.valueOf(cliente.getMunicipio()));
            ps.setString(4, String.valueOf(cliente.getDepartamento()));
            ps.setString(5, String.valueOf(cliente.getNombre()));
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
}

