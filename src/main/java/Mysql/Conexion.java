/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public final class Conexion {
    static Connection conexion=null;
    
    public Conexion(){
        conectar();
    }
    
    public void conectar(){
        try{
            if (conexion != null) {
                System.err.print("Ya hay una conexion vigente con la base de datos");
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/MiMuebleria";
            String user = "root";
            String password = "Wichis6661";
            conexion = DriverManager.getConnection(url, user, password);
            System.out.print("Conexio exitosa");
        }catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.err.print("Conexion Fallida"+ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e){
            System.err.print(e);
        }
    }
    
    
    public void desconectar(){
        conexion = null;
        if (conexion!=null) {
            JOptionPane.showMessageDialog(null, "NO se pudo desconectar de la base de datos, ya que hay una conexion vigente");
        }
    }
    
    public static Connection getConnection(){
        return conexion;
    }
}
