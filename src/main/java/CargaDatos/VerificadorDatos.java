/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaDatos;

import Mueble.EnsamblePiezas;
import Mueble.MuebleEnsamblado;
import Mueble.Pieza;
import Mysql.Conexion;
import Mysql.Insert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class VerificadorDatos {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public boolean verificarTamañoEntradaUsuario(String nombre, String contraseña){
       boolean retornar=false;
       if(nombre.length()<=45&&contraseña.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    public boolean verificadorUsuario(String nombre){
        boolean retornar=true;
        //Establecemos la query a buscar
        String sql="SELECT * FROM usuario WHERE UPPER(nombre_usuario)=UPPER('"+nombre+"')";
        try{
            //Nos conectamos a la base
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                retornar=false;
            }
        } catch(SQLException e){
            System.err.print(e);
        }
        return retornar;
    }
    
    public boolean verificadorContraseña(String contraseña){
        boolean retornar=false;
        if(contraseña.length()>=6){
            retornar=true;
        }
        return retornar;
    }
    
    public boolean verificar(int cantidad){
        boolean retornar=false;
        if(cantidad==1||cantidad==2||cantidad==3){
            retornar=true;
        }
        return retornar;
    }
    
    public boolean verificarTamañoEntradaPieza(String nombre){
       boolean retornar=false;
       if(nombre.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    public boolean verificarTamañoEntradaMueble(String nombre){
       boolean retornar=false;
       if(nombre.length()<=75){
           retornar=true;
       }
       return retornar;
    }
    
    public boolean verificadorMueble(String nombre){
        boolean retornar=true;
        //Establecemos la query a buscar
        String sql="SELECT * FROM mueble WHERE UPPER(mueble.nombre_mueble)=UPPER('"+nombre+"')";
        try{
            //Nos conectamos a la base
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                retornar=false;
            }
        } catch(SQLException e){
            System.err.print(e);
        }
        return retornar;
    }
    
    public boolean verificarTamañoEntradaEnsamblaje(String mueble, String pieza){
       boolean retornar=false;
       if(mueble.length()<=75&&pieza.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    public boolean verificadorPieza(String nombre){
        boolean retornar=false;
        //Establecemos la query a buscar
        String sql="SELECT * FROM pieza WHERE UPPER(tipo)=UPPER('"+nombre+"');";
        try{
            //Nos conectamos a la base
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                retornar=true;
            }
        } catch(SQLException e){
            System.err.print(e);
        }
        return retornar;
    }
    
    public boolean verificadorMueble2(String nombre){
        boolean retornar=false;
        //Establecemos la query a buscar
        String sql="SELECT * FROM mueble WHERE UPPER(mueble.nombre_mueble)=UPPER('"+nombre+"')";
        try{
            //Nos conectamos a la base
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                retornar=true;
            }
        } catch(SQLException e){
            System.err.print(e);
        }
        return retornar;
    }
    
    public boolean verificarTamañoEntradaEnsamblarMueble(String mueble, String usuario){
       boolean retornar=false;
       if(mueble.length()<=75&&usuario.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    public boolean verificadorUsuario2(String nombre){
        boolean retornar=false;
        //Establecemos la query a buscar
        String sql="SELECT * FROM usuario WHERE UPPER(nombre_usuario)=UPPER('"+nombre+"')";
        try{
            //Nos conectamos a la base
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                retornar=true;
            }
        } catch(SQLException e){
            System.err.print(e);
        }
        return retornar;
    }
    
    public Double precioMueble(String nombre){
        double precio=0.0;
        //Establecemos la query a buscar
        String sql="SELECT * FROM mueble WHERE UPPER(mueble.nombre_mueble)=UPPER('"+nombre+"')";
        try{
            //Nos conectamos a la base
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                precio=rs.getDouble("precio");
            }
        } catch(SQLException e){
            System.err.print(e);
        }
        return precio;
    }
    
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
                    HiloCargadeDatos.errores.add("No posees las piezas necesasrias para construir el mueble");
                }
            }else {
                HiloCargadeDatos.errores.add("No se poseen las piezas necesarias par apoder elaborar el mueble");
            }
            } else {
                System.err.print("error");
            }
        } catch (Exception e) {
            System.err.print(e);
        }
        return false;    
    }
   
    public boolean verificarTamañoEntradaCliente(String nit, String direccion, String municipio, String departamento, String nombre){
       boolean retornar=false;
       if(nit.length()<=12&&direccion.length()<=100&&municipio.length()<=100&&departamento.length()<=100&&nombre.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    public boolean verificarGuiones(String nit){
        char caracter;
        boolean retornar=true;
        for (int i = 0; i < nit.length(); i++) {
            caracter=nit.charAt(i);
            if(Character.compare(caracter, '-')==0){
                retornar=false;
            }
        }
       return retornar;
    }
}
