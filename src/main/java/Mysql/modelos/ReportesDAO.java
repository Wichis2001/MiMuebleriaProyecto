/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;

import CompraVenta.Reporte;
import Mysql.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase me ayuda a manejar mis reportes de manera correcta, esto con la finalidad de servir como puente entre mi pagina web y mi servelet
 * @author luis
 */
public class ReportesDAO {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;    
      
    /**
     * Este metodo me permite listar las compras realizados en un intervalo de tiempo
     * @param finalfecha
     * @param inicio
     * @return
     */
    public List Compras(Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si la fecha tanto de inicio como de final son nulas establecemos la query sin estos parametro
            String sql="SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, v.fecha_compra FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble)";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista con sus parametros
                    Reporte reporte=new Reporte();
                    reporte.setMueble_identificador_mueble(rs.getString("identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);
            } 
                return listReporte;
        } else {
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si tanto la fecha de inicio como de final no son iguales a nulas procedemos a establecer la Query con estos parametros
            String sql="SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, v.fecha_compra FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.fecha_compra BETWEEN '"+inicio+"' AND '" + finalfecha + "'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setMueble_identificador_mueble(rs.getString("identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }  
    }
    
    /**
     * Este metodo me ayuda a listar las devoluciones que fueron realizadas en un intervalo de tiempo
     * @param inicio
     * @param finalfecha
     * @return
     */
    public List Devolucion(Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si la fecha tanto de inicio y la de ifnal son nulas procedemos a establecer la Query sin estos parametros
            String sql="SELECT v.numeros_serie, d.fecha_devolucion, c.nombre, c.nit, m.nombre_mueble_ensamble, d.perdida FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN devolucion d ON (d.numeros_serie=v.numeros_serie) JOIN detalle_venta dv ON (dv.venta_id=v.id_venta) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble);";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNumeroSerie(rs.getString("numeros_serie"));
                    reporte.setFechaDevolucion(rs.getDate("fecha_devolucion").toLocalDate());
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setNit(rs.getString("nit"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPerdida(rs.getDouble("perdida"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);
            } 
                return listReporte;
        } else {
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si la fecha de inicio como de final no son iguales a nulas le asignamos estos parametros a la Query
            String sql="SELECT v.numeros_serie, d.fecha_devolucion, c.nombre, c.nit, m.nombre_mueble_ensamble, d.perdida FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN devolucion d ON (d.numeros_serie=v.numeros_serie) JOIN detalle_venta dv ON (dv.venta_id=v.id_venta) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE d.fecha_devolucion BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNumeroSerie(rs.getString("numeros_serie"));
                    reporte.setFechaDevolucion(rs.getDate("fecha_devolucion").toLocalDate());
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setNit(rs.getString("nit"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPerdida(rs.getDouble("perdida"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reoortes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }  
    }
    
    /**
     * Este metodo me ayuda a calcular las ganancias obtenidas por la empresa en un intervalo de tiempo
     * @param inicio
     * @param finalfecha
     * @return
     */
    public Double ganancia(Date inicio, Date finalfecha) {
        Double valor=0.0;
        Double valor2=0.0;
        Double ganancia=0.0;
        if(inicio==null||finalfecha==null){
            //Definimos la query con la suma de todas las ventas si en dado caso las fechas son nulas para no ingresar dichos parametros
            String sql="SELECT SUM(v.total) AS 'TOTAL' FROM venta v";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto para guardar dicho atributo
                    valor=(rs.getDouble("TOTAL"));
               }
               // Error SQL al momento de listar mis piezas
            }catch(SQLException e){
                System.err.print(e);

            }
            //Si en caso la fecha de inicio como de final son nulas procedemos a calcular el costo de construccion sin estos parametros
            String sql2="SELECT SUM(m.costo_construccion) AS 'TOTAL' FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble)";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql2);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valo2 y lo asignamos a la lista
                    valor2=(rs.getDouble("TOTAL"));
               }
               // Error SQL al ejecutar las Querys
            }catch(SQLException e){
                System.err.print(e);

            }
            //Calculamos la ganancia como la resta de estos dos valores
            ganancia=valor-valor2;
            return ganancia;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT SUM(v.total) AS 'TOTAL' FROM venta v WHERE v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getDouble("TOTAL"));
               }
               // Error SQL al ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            }
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql2="SELECT SUM(m.costo_construccion) AS 'TOTAL' FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql2);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor2 y lo asignamos a la lista
                    valor2=(rs.getDouble("TOTAL"));
               }
               // Error ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            }
            ganancia=valor-valor2;
            return ganancia;
        }  
    }
    
    /**
     * Este metodo me ayuda a calcular mi ganancia atravez de mis atributos ya dispuestos en mibase de datos
     * @param inicio
     * @param finalfecha
     * @return
     */
    public List gananciaAtributos(Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, m.costo_construccion FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble)";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setMueble_identificador_mueble(rs.getString("identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio")-rs.getDouble("costo_construccion"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        } else {
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si la fecha de incio y final  son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, m.costo_construccion FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setMueble_identificador_mueble(rs.getString("identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio")-rs.getDouble("costo_construccion"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis Reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }  
    }
    
    /**
     * Este metodo me devuelve el usuario que registra mas ventas
     * @param inicio
     * @param finalfecha
     * @return
     */
    public String clienteV(Date inicio, Date finalfecha) {
        String valor="";
        if(inicio==null||finalfecha==null){
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT v.usuario_vendedor FROM venta v GROUP BY v.usuario_vendedor ORDER BY COUNT(v.usuario_vendedor) DESC LIMIT 1";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getString("usuario_vendedor"));
               }
               // Error SQL al momento de ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            } 
                return valor;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT v.usuario_vendedor FROM venta v GROUP BY v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"', v.usuario_vendedor ORDER BY COUNT(v.usuario_vendedor) DESC LIMIT 1";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getString("usuario_vendedor"));
               }
               // Error SQL al momento de ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            } 
                return valor;
        }  
    }
    
    /**
     * Este metodo me ayuda a listar las ventas realizadas por un usuario
     * @param nombre
     * @param inicio
     * @param finalfecha
     * @return
     */
    public List clientesVtabla(String nombre,Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT v.usuario_vendedor, v.fecha_compra, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.usuario_vendedor LIKE '"+nombre+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNombreClientre(rs.getString("usuario_vendedor"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT v.usuario_vendedor, v.fecha_compra, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.usuario_vendedor LIKE '"+nombre+"' AND v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNombreClientre(rs.getString("usuario_vendedor"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }  
    }
    
    /**
     * Este metodo me aydua a determinar mi usuario con mas ganancias en mi base de datos
     * @param inicio
     * @param finalfecha
     * @return
     */
    public String usuarioG(Date inicio, Date finalfecha) {
        String valor="";
        if(inicio==null||finalfecha==null){
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT v.usuario_vendedor FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) GROUP BY v.usuario_vendedor ORDER BY SUM(m.precio-m.costo_construccion) DESC LIMIT 1";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getString("usuario_vendedor"));
               }
               // Error SQL al momento de ejecutar la Query
            }catch(SQLException e){
                System.err.print(e);

            } 
                return valor;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT v.usuario_vendedor FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) GROUP BY v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"', v.usuario_vendedor ORDER BY SUM(m.precio-m.costo_construccion) DESC LIMIT 1;";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getString("usuario_vendedor"));
               }
               // Error SQL al momento de ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            } 
                return valor;
        }  
    }
    
    /**
     * Este metodo me ayuda a determinar todos los atributos del usuario que registra mas ventas
     * @param nombre
     * @param inicio
     * @param finalfecha
     * @return
     */
    public List usuarioGTabla(String nombre,Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            ArrayList<Reporte>listReporte=new ArrayList<>();
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT v.usuario_vendedor, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio, m.costo_construccion,v.fecha_compra FROM  venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.usuario_vendedor LIKE '"+nombre+"';";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto pieza y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNombreClientre(rs.getString("usuario_vendedor"));
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio")-rs.getDouble("costo_construccion"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT v.usuario_vendedor, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio, m.costo_construccion, v.fecha_compra FROM  venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.usuario_vendedor LIKE '"+nombre+"' AND v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNombreClientre(rs.getString("usuario_vendedor"));
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio")-rs.getDouble("costo_construccion"));
                    reporte.setFecha_compra(rs.getDate("fecha_compra").toLocalDate());
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }  
    }
    
    /**
     * Este metodo me ayuda a determinar las ganancias establecidas por un usuario en un intervalo de tiempo
     * @param nombre
     * @param inicio
     * @param finalfecha
     * @return
     */
    public Double gananciaUsuario(String nombre, Date inicio, Date finalfecha) {
        Double valor=0.0;
        Double valor2=0.0;
        Double ganancia=0.0;
        if(inicio==null||finalfecha==null){
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT SUM(v.total) AS 'TOTAL' FROM venta v WHERE v.usuario_vendedor='"+nombre+"'";
            try{
               //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getDouble("TOTAL"));
               }
               // Error SQL al momento de ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            }
            //Si la fecha de incio y final  son nulas procedemos a mandarle estos parametros a mi Query
            String sql2="SELECT SUM(m.costo_construccion) AS 'TOTAL' FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.usuario_vendedor='"+nombre+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql2);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor2 y lo asignamos a la lista
                    valor2=(rs.getDouble("TOTAL"));
               }
               // Error SQL al momento de ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            }
            //Establecemos la ganancia como la resta de estos dos valores
            ganancia=valor-valor2;
            return ganancia;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT SUM(v.total) AS 'TOTAL' FROM venta v WHERE v.usuario_vendedor='"+nombre+"' AND v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getDouble("TOTAL"));
               }
               // Error SQL al momento de ejecutar mi consulta
            }catch(SQLException e){
                System.err.print(e);

            }
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql2="SELECT SUM(m.costo_construccion) AS 'TOTAL' FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.usuario_vendedor='"+nombre+"' AND v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql2);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor2 y lo asignamos a la lista
                    valor2=(rs.getDouble("TOTAL"));
               }
               // Error SQL al momento de ejecutar mi consulta
            }catch(SQLException e){
                System.err.print(e);

            }
            //Establecemos la gananica como la resta de estos dos valores
            ganancia=valor-valor2;
            return ganancia;
        }  
    }
    
    /**
     * Este metodo ayuda a establecer el nombre del mueble mas vendido en mi base de datos
     * @param inicio
     * @param finalfecha
     * @return
     */
    public String nombreMuebleMas(Date inicio, Date finalfecha) {
        String valor="";
        if(inicio==null||finalfecha==null){
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT m.nombre_mueble_ensamble FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) GROUP BY dv.mueble_identificador_mueble ORDER BY COUNT(dv.mueble_identificador_mueble) DESC LIMIT 1";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getString("nombre_mueble_ensamble"));
               }
               // Error SQL al momento de ejecutar mi consulta
            }catch(SQLException e){
                System.err.print(e);

            } 
                return valor;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT m.nombre_mueble_ensamble FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) GROUP BY dv.mueble_identificador_mueble, v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"' ORDER BY COUNT(dv.mueble_identificador_mueble) DESC LIMIT 1;";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getString("nombre_mueble_ensamble"));
               }
               // Error SQL al momento de ejecutar mi consulta
            }catch(SQLException e){
                System.err.print(e);

            } 
                return valor;
        }  
    }
    
    /**
     * Este  metodo me ayuda a listar todos los muebles obtenidos por mi consulta de mueble mas y menos vendido
     * @param nombre
     * @param inicio
     * @param finalfecha
     * @return
     */
    public List mueble(String nombre,Date inicio, Date finalfecha) {
        if(inicio==null||finalfecha==null){
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT v.numeros_serie, c.nombre, c.direccion, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE m.nombre_mueble_ensamble LIKE '"+nombre+"';";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNumeroSerie(rs.getString("numeros_serie"));
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setDireccion(rs.getString("direccion"));
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            ArrayList<Reporte>listReporte=new ArrayList<>();
            String sql="SELECT v.numeros_serie, c.nombre, c.direccion, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE m.nombre_mueble_ensamble LIKE '"+nombre+"' AND v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"'";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto reporte y lo asignamos a la lista
                    Reporte reporte=new Reporte();
                    reporte.setNumeroSerie(rs.getString("numeros_serie"));
                    reporte.setNombreClientre(rs.getString("nombre"));
                    reporte.setDireccion(rs.getString("direccion"));
                    reporte.setMueble_identificador_mueble(rs.getString("mueble_identificador_mueble"));
                    reporte.setNombre_mueble_ensamble(rs.getString("nombre_mueble_ensamble"));
                    reporte.setPrecio(rs.getDouble("precio"));
                    listReporte.add(reporte);
               }
               // Error SQL al momento de listar mis reportes
            }catch(SQLException e){
                System.err.print(e);

            } 
                return listReporte;
        }  
    }
    
    /**
     * Este metodo me ayuda a determinar cual fue mi mueble menos vendido
     * @param inicio
     * @param finalfecha
     * @return
     */
    public String nombreMuebleMe(Date inicio, Date finalfecha) {
        String valor="";
        if(inicio==null||finalfecha==null){
            //Si la fecha de incio y final son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT m.nombre_mueble_ensamble FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE m.identificador_mueble=(SELECT MIN(m.identificador_mueble)  FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble))";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getString("nombre_mueble_ensamble"));
               }
               // Error SQL al momento ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            } 
                return valor;
        } else {
            //Si la fecha de incio y final no son nulas procedemos a mandarle estos parametros a mi Query
            String sql="SELECT m.nombre_mueble_ensamble FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE (v.fecha_compra BETWEEN '"+inicio+"' AND '"+finalfecha+"') AND (m.identificador_mueble=(SELECT MIN(m.identificador_mueble)  FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble)))";
            try{
                //Establecemos una conexion con la base de datos y enviamos los parametros de las Querys
               con=conexion.getConnection();
               ps =con.prepareStatement(sql);
               rs =ps.executeQuery();
               while(rs.next()){
                   //Creamos un objeto valor y lo asignamos a la lista
                    valor=(rs.getString("nombre_mueble_ensamble"));
               }
               // Error SQL al momento de ejecutar mi Query
            }catch(SQLException e){
                System.err.print(e);

            } 
                return valor;
        }  
    }
}
