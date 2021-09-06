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
 * Esta clase me permite establecer una conexion con mi base de datos para poder hacer uso de ella en mi sistema
 * @author luis
 */
public final class Conexion {
    static Connection conexion=null;
    
    /**
     * Este constructor de mi clase conexion invoca a mi metodo conectar para que al momento de establecer mi conexion pueda conectarse a la base de datos
     */
    public Conexion(){
        conectar();
    }
    
    /**
     * Este metodo me permite conectarme a mi base de datos
     */
    public static void conectar(){
        try{
            // Verificamos que no haya una conexion vigente con la base de datos para no tener mas de una conexion corriendo
            if (conexion != null) {
                System.err.print("Ya hay una conexion vigente con la base de datos");
                return;
            }
            //Establecemos y enviamos nuestros parametros
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/MiMuebleria";
            String user = "root";
            String password = "Wichis6661";
            conexion = DriverManager.getConnection(url, user, password);
            System.out.print("Conexio exitosa");
        }catch (SQLException ex) {
            //Error de conexion fallida
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.err.print("Conexion Fallida"+ex);
            //Excepcion de clase no encontrada
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e){
            System.err.print(e);
        }
    }
    
    /**
     * Este metodo me permite desconectarme de mi base de datos
     * @throws java.sql.SQLException
     */
    public void desconectar() throws SQLException{
        conexion.close();
        conexion = null;
        if (conexion!=null) {
            JOptionPane.showMessageDialog(null, "NO se pudo desconectar de la base de datos, ya que hay una conexion vigente");
        }
    }
    
    /**
     * Este metodo me devuelve mi conexion
     * @return
     */
    public static Connection getConnection(){
        conectar();
        return conexion;
    }
}
