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
import Mysql.Querys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class ConstruirMuebleDAO implements Interfaces.CRUDMUEBLES{
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Mueble muebles = new Mueble();
    Pieza piezaauxiliar= new Pieza();
    
    @Override
    public List listar() {
        ArrayList<Mueble>listMueble=new ArrayList<>();
        try{
           con=conexion.getConnection();
           ps =con.prepareStatement(Querys.queryMueble);
           rs =ps.executeQuery();
           while(rs.next()){
               Mueble mueble=new Mueble();
               mueble.setNombre_mueble_ensamble(rs.getString("mueble"));
               mueble.setPrecio(rs.getDouble("precio"));;
               listMueble.add(mueble);
           }
        }catch(SQLException e){
            System.err.print(e);
            
        } 
            return listMueble;
    }

    @Override
    public boolean add(MuebleEnsamblado mueble) throws SQLException{
        try {
            String sql="SELECT mueble_nombre, pieza_tipo, cantidad FROM ensamble_piezas WHERE UPPER(nombre_mueble) = UPPER('"+ mueble.getNombre_mueble_ensamblado() + "')";
            con=conexion.getConnection();
            ArrayList<EnsamblePiezas>piezasNecesarias=new ArrayList<>();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            boolean permitirEnsamble = true;
            double costo_construccion = 0;
            //Agregamos todas las piezas que son necesarias para poder construir un mueble
            while(rs.next()){
                EnsamblePiezas pieza=new EnsamblePiezas();
                pieza.setCantidad(rs.getInt("cantidad"));
                pieza.setMueble_nombre(rs.getString("mueble_nombre"));
                pieza.setPieza_tipo(rs.getString("pieza_tipo"));
                piezasNecesarias.add(pieza);
            }
            //Verificamos que nuestro Array de piezas necesarias no se encuentre vacio y tenga instrucciones
            if(piezasNecesarias.size() > 0){
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
                                //Obtenemos el costo de fabricacion que va tenerel mueble
                                costo_construccion+=piezas.get(i).getCosto().doubleValue()*piezasUtilizadas;
                                break;
                                //Si en dado caso no se han encontrado todas las piezas necesarias debemos tomar el costo de estas peizas
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
                    //Actualizamos las piezas
                    for(Pieza piezasActualizadas: piezas){
                        String sqlPieza="UPDATE pieza SET cantidad='"+ piezasActualizadas.getCantidad() + "' WHERE UPPER(tipo)=UPPER('"+ piezasActualizadas.getTipo() +"') AND UPPER(costo)=UPPER('"+ piezasActualizadas.getCosto() +"')"; 
                        con=conexion.getConnection();
                        ps=con.prepareStatement(sqlPieza);
                        ps.executeUpdate();
                    }
                    con=conexion.getConnection();
                    ps=con.prepareStatement(Mysql.Querys.querySelectPiezasNecesarias);
                    resultadoPiezasaEnsamblar.add(rs);
                    ps.setString(1, mueble.getIdentificador_mueble());
                    ps.setString(7, mueble.getNombre_mueble_ensamblado());
                    ps.setString(6, mueble.getUsuario_constructor());
                    ps.setDate(2, java.sql.Date.valueOf(new SimpleDateFormat(Mysql.Querys.FORMATO_FECHA_SQL).format(mueble.getFecha_ensamblaje())));
                    ps.setDouble(4, costo_construccion);
                    ps.setInt(5, mueble.getEstado());
                    
                } else {
                    System.err.println("No posee todas las piezas para elaborar el mueble");
                }
            }else {
                System.err.println("No se poseen las piezas necesarias par apoder elaborar el mueble");
            }
            }
        } catch (Exception e) {
            System.err.print(e);
        }
        return false;    
    }  
    

}
