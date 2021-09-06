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
 * Esta clase me ayuda a las verificaciones que se pueden realizar al hacer una carga de datos, con la finalidad de almacenar estos datos corectamente
 * @author luis
 */
public class VerificadorDatos {
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Esta clase me ayuda a verificar el tamaño que esta registrado en los caracteres de la entrada de un usuario
     * @param nombre
     * @param contraseña
     * @return
     */
    public boolean verificarTamañoEntradaUsuario(String nombre, String contraseña){
       boolean retornar=false;
       //Asignamos los parametros y verificamos su longitud
       if(nombre.length()<=45&&contraseña.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar que el usuario no haya sido creado previamente
     * @param nombre
     * @return
     */
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
    
    /**
     * Este metodo me ayuda a verificar si el ensamblaje de la pieza no ha sido registrado previamente
     * @param nombre
     * @param pieza
     * @return
     */
    public boolean verificadorEnsamblajePieza(String nombre, String pieza){
        boolean retornar=true;
        //Establecemos la query a buscar
        String sql="SELECT * FROM ensamble_piezas WHERE UPPER(mueble_nombre)=UPPER('"+nombre+"') AND UPPER(pieza_tipo)=UPPER('"+pieza+"');";
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
    
    /**
     * Este metodo me ayuda a verificar que la contraseña designada en la carga de datos tiene más de 6 caracteres
     * @param contraseña
     * @return
     */
    public boolean verificadorContraseña(String contraseña){
        boolean retornar=false;
        //Verificamos la longitud de la contraseña
        if(contraseña.length()>=6){
            retornar=true;
        }
        return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar si el lugar donde fue designado el usuario es correcto
     * @param cantidad
     * @return
     */
    public boolean verificar(int cantidad){
        boolean retornar=false;
        //Verificamos si tiene el valor númerico de alguna de las areas designadas
        if(cantidad==1||cantidad==2||cantidad==3){
            retornar=true;
        }
        return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar el tamaño de la entrada de la pieza con la finalidad de no corromper la base de datos
     * @param nombre
     * @return
     */
    public boolean verificarTamañoEntradaPieza(String nombre){
       boolean retornar=false;
       //Verificamos el tamaño del nombre
       if(nombre.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar si la entrada de un nuevo mueble es valido
     * @param nombre
     * @return
     */
    public boolean verificarTamañoEntradaMueble(String nombre){
       boolean retornar=false;
       //Verificamos si el nombre ingresado es valido
       if(nombre.length()<=75){
           retornar=true;
       }
       return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar si este mueble no esta asignado previamente en la base de datos
     * @param nombre
     * @return
     */
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
    
    /**
     * Este metodo me ayuda a verificar el tamaño de entrada para el ensamblaej de un mueble
     * @param mueble
     * @param pieza
     * @return
     */
    public boolean verificarTamañoEntradaEnsamblaje(String mueble, String pieza){
       boolean retornar=false;
       //Verificamos el tamaño de un mueble y de la pieza
       if(mueble.length()<=75&&pieza.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar si la pieza existe en la base de datos
     * @param nombre
     * @return
     */
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
    
    /**
     * Este metodo me ayuda a verificar si existe un mueble en la base de datos
     * @param nombre
     * @return
     */
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
    
    /**
     * Este metodo me ayuda a verificar el numoero de caracteres que tiene una entrada de ensamblaje de un mueble
     * @param mueble
     * @param usuario
     * @return
     */
    public boolean verificarTamañoEntradaEnsamblarMueble(String mueble, String usuario){
       boolean retornar=false;
       //Verificamos la cantidad de caracteres que tienen el mueble y el usuario
       if(mueble.length()<=75&&usuario.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar si existe previametne un usuario
     * @param nombre
     * @return
     */
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
    
    /**
     * Este metodo me devuelve el precio que tiene un mueble
     * @param nombre
     * @return
     */
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
    
    /**
     * Este metodo me permite asignar un nuevo mueble ensamblado
     * @param mueble
     * @return
     * @throws SQLException
     */
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
   
    /**
     * Este metodo me permite verificar el numero de caracteres que tiene la entrada de clientes
     * @param nit
     * @param direccion
     * @param municipio
     * @param departamento
     * @param nombre
     * @return
     */
    public boolean verificarTamañoEntradaCliente(String nit, String direccion, String municipio, String departamento, String nombre){
       boolean retornar=false;
       //Verificamos el tamaño de las cadenas String
       if(nit.length()<=12&&direccion.length()<=100&&municipio.length()<=100&&departamento.length()<=100&&nombre.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    /**
     * Este metodo me permite verificar el numero de caracteres que posee la entrada de un nuevo cliente
     * @param nit
     * @param direccion
     * @param nombre
     * @return
     */
    public boolean verificarTamañoEntradaClientePequeño(String nit, String direccion,String nombre){
       boolean retornar=false;
       //Verificamos el tamaño de las cadenas de texto
       if(nit.length()<=12&&direccion.length()<=100&&nombre.length()<=45){
           retornar=true;
       }
       return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar si el nit de un cliente posee un guion
     * @param nit
     * @return
     */
    public boolean verificarGuiones(String nit){
        char caracter;
        boolean retornar=true;
        //Recorremos la cadena
        for (int i = 0; i < nit.length(); i++) {
            caracter=nit.charAt(i);
            //Verificamos si este posee un guion
            if(Character.compare(caracter, '-')==0){
                retornar=false;
            }
        }
       return retornar;
    }
    
    /**
     * Este metodo me ayuda a verificar si un cliente ya existe previamente en la base de datos
     * @param nit
     * @return
     */
    public boolean verificadorCliente(String nit){
        boolean retornar=true;
        //Establecemos la query a buscar
        String sql="SELECT * FROM cliente WHERE UPPER(nit)=UPPER('"+nit+"')";
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
}
