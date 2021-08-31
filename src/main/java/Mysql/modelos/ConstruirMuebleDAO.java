/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql.modelos;
import Mueble.EnsamblePiezas;
import Mueble.Mueble;
import Mueble.MuebleEnsamblado;
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

/**
 * Esta clase me permite establecer un DAO de un mueble para poder construir un muebley asignarle las funciones
 * @author luis
 */
public class ConstruirMuebleDAO implements Interfaces.CRUDMUEBLES{
    //Establecemos variables
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar() {
        ArrayList<Mueble>listMueble=new ArrayList<>();
        try{
           //Establecemos una conexion con la base de datos
           con=conexion.getConnection();
           //Establecemos la Query
           ps =con.prepareStatement(Querys.queryMueble);
           rs =ps.executeQuery();
           while(rs.next()){
               //Asignamos un nuevo mueble con los parametros
               Mueble mueble=new Mueble();
               mueble.setNombre_mueble_ensamble(rs.getString("nombre_mueble"));
               mueble.setPrecio(rs.getDouble("precio"));
               listMueble.add(mueble);
           }
           //Error una excepcion SQL
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listMueble;
    }

    @Override
    public boolean add(MuebleEnsamblado mueble) throws SQLException{
        try {
            //Estrablecemos una conexion con la base de datos y enviamos los parametros
            String sql="SELECT * FROM ensamble_piezas WHERE UPPER(mueble_nombre) = UPPER('"+ mueble.getNombre_mueble_ensamblado() +"')";
            con=conexion.getConnection();
            ArrayList<EnsamblePiezas>piezasNecesarias=new ArrayList<>();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            boolean permitirEnsamble = true;
            double costo_construccion = 0;
            //Agregamos todas las piezas que son necesarias para poder construir un mueble
            while(rs.next()){
                EnsamblePiezas pieza=new EnsamblePiezas(rs.getString("mueble_nombre"),rs.getString("pieza_tipo"),rs.getInt("cantidad"));
                piezasNecesarias.add(pieza);
                System.err.print("prebando");    
            }
            //Verificamos que nuestro Array de piezas necesarias no se encuentre vacio y tenga instrucciones
            if(piezasNecesarias.size() > 0){
                System.err.print("Si se puede");
                ArrayList<ResultSet> resultadoPiezasaEnsamblar = new ArrayList<>();
                ArrayList<Pieza> piezas = new ArrayList<>();

                //Asignamos todas las piezas necesariasr para el ensamble
                for(EnsamblePiezas piezasEnsamble: piezasNecesarias){
                    con=conexion.getConnection();
                    ps=con.prepareStatement(Mysql.Querys.querySelectPiezasNecesarias);
                    ps.setString(1, piezasEnsamble.getPieza_tipo());
                    rs=ps.executeQuery();
                    resultadoPiezasaEnsamblar.add(rs);
                }
                
                //Asignamos todas las piezas que tengamos en el sistema
                for(int i = 0; i < resultadoPiezasaEnsamblar.size(); i++){ 
                    while(resultadoPiezasaEnsamblar.get(i).next()){ 
                        piezas.add(new Pieza(resultadoPiezasaEnsamblar.get(i).getString("tipo"), resultadoPiezasaEnsamblar.get(i).getDouble("costo"), resultadoPiezasaEnsamblar.get(i).getInt("cantidad")));
                    }
                }
                //Si existe esta pieza en el sistema, por lo cual necesitamos verificar si podemos tomar estas piezas para podder construir el mueble
                if(piezas.size() > 0){
                int piezasEncontradas = 0;
                for(EnsamblePiezas receta: piezasNecesarias){
                    int piezasFaltantes = receta.getCantidad();
                    //Recorremos las piezas que son necesarias
                    for(int i = 0; i < piezas.size(); i++){ 
                        //Verificamos la comparacion de las piezas a travez de su nombre
                        if(receta.getPieza_tipo().equalsIgnoreCase(piezas.get(i).getTipo())){ 
                            int piezasUtilizadas = 0;
                            //Verificamos cuantos registros de piezas son necesarios para poder ensamblar el mueble
                            if(piezas.get(i).getCantidad() - piezasFaltantes >= 0){ 
                                piezasUtilizadas = piezasFaltantes; 
                                piezas.get(i).setCantidad(piezas.get(i).getCantidad()-piezasFaltantes); 
                                piezasFaltantes = 0;
                            //En caso contrario necesaritamos almacenar la cantidad de piezas faltantes para hacer verificaciones
                            } else if(piezasFaltantes - piezas.get(i).getCantidad() > 0 && piezas.get(i).getCantidad() > 0) { 
                                piezasUtilizadas = piezas.get(i).getCantidad();
                                piezas.get(i).setCantidad(0); 
                                piezasFaltantes = piezasFaltantes - piezasUtilizadas; 
                            } else if(piezas.get(i).getCantidad() == 0){
                                break;
                            }
                            //Verificamos si ya se han encontrado todas las piezas necesarias
                            if(piezasFaltantes == 0 && permitirEnsamble == true){
                                piezasEncontradas++; 
                               // Obtenemos el costo de fabricacion que va tenerel mueble
                                costo_construccion+=piezas.get(i).getCosto().doubleValue()*piezasUtilizadas;
                                break;
                               // Si en dado caso no se han encontrado todas las piezas necesarias debemos tomar el costo de estas peizas
                            } else if(piezasFaltantes > piezasUtilizadas && piezasUtilizadas > 0){ 
                                costo_construccion+=piezas.get(i).getCosto().doubleValue()*piezasUtilizadas;
                                continue; 
                                //No se han encontrado las piezas necesarias para poder armar el producto
                            } else if(piezasFaltantes > 0 && permitirEnsamble == true && i == piezas.size()){
                                permitirEnsamble = false;
                            }
                        }
                    } 
                    if(permitirEnsamble == false){
                        break;
                    }
                }
                //Se encontraron con todas las peizas necesarias y podemos progresar con el ensamblamiento en la base de datos
                if(permitirEnsamble == true && piezasEncontradas == piezasNecesarias.size()){ 
                    System.err.print("Si se puede");
                    //Actualizamos las piezas
                    for(Pieza piezasActualizadas: piezas){
                        String sqlPieza="UPDATE pieza SET cantidad='"+ piezasActualizadas.getCantidad() + "' WHERE UPPER(tipo)=UPPER('"+ piezasActualizadas.getTipo() +"') AND costo='"+ piezasActualizadas.getCosto() +"'"; 
                        con=conexion.getConnection();
                        ps=con.prepareStatement(sqlPieza);
                        ps.executeUpdate();
                    }
                    //Insertamos los muebles ensamblados creados en el sismtema
                    con=conexion.getConnection();
                    ps=con.prepareStatement(Insert.INSERTMUEBLEENSAMBLADO);           
                    ps.setString(1, mueble.getIdentificador_mueble());
                    ps.setString(2, mueble.getNombre_mueble_ensamblado());
                    ps.setString(3, mueble.getUsuario_constructor());
                    ps.setDate(4, java.sql.Date.valueOf(mueble.getFecha_ensamblaje()));
                    ps.setDouble(5, costo_construccion);
                    ps.setInt(6, mueble.getEstado());
                    ps.setDouble(7, mueble.getPrecio());
                    ps.executeUpdate();
                } else {
                    System.err.println("No posee todas las piezas para elaborar el mueble");
                }
            }else {
                System.err.println("No se poseen las piezas necesarias par apoder elaborar el mueble");
            }
            } else {
                System.err.print("error");
            }
        } catch (Exception e) {
            System.err.print(e);
        }
        return false;    
    }  

    @Override
    public List listarMueble() {
        ArrayList<MuebleEnsamblado>listMuebleEnsamblado=new ArrayList<>();
        try{
            //Establecemos una conexion con la base de datos y enviamos como parametro la query
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.queryMuebleEnsamblado);
           rs =ps.executeQuery();
           while(rs.next()){
               //Asignamos un nuevo mueble ensamblado y le asignamos los parametros
               MuebleEnsamblado mueble=new MuebleEnsamblado();
               mueble.setIdentificador_mueble(rs.getString("identificador_mueble"));
               mueble.setNombre_mueble_ensamblado(rs.getString("nombre_mueble_ensamble"));
               mueble.setUsuario_constructor(rs.getString("usuario_constructor"));
               mueble.setFecha_ensamblaje(rs.getDate(("fecha_ensamblaje")).toLocalDate());
               listMuebleEnsamblado.add(mueble);
           }
           //Error en excepcion de SQL
        }catch(SQLException e){
            System.err.print(e);
        } 
            return listMuebleEnsamblado;
    }

    @Override
    public boolean upgrade(String identificador) {
       //Actualizamos el mueble ensamblado y le ponemos en estado 3 el cual significa eque puede ser vendido
       String sql="UPDATE mueble_ensamblado SET estado='3' WHERE (identificador_mueble)='"+ identificador +"'"; 
         try {
            //Establecemos conexion con la base de datos y enviamos la Query
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.print(e);
        }
         return false;
    }

}
