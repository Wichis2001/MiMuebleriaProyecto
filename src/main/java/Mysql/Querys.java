/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql;

/**
 *
 * @author luis
 */
public class Querys {
    public static final String  queryPieza="SELECT * FROM pieza";
    public static final String  queryMueble="SELECT * FROM mueble";
    public static final String  queryMuebleEnsamblado="SELECT identificador_mueble, nombre_mueble_ensamble, usuario_constructor, fecha_ensamblaje  FROM mueble_ensamblado WHERE estado=2";
    public static final String queryUsuarios="SELECT * FROM usuario WHERE nombre_usuario=? AND contraseña=?";
    public static final String querySelectPiezasNecesarias="SELECT * FROM pieza WHERE UPPER(tipo) = UPPER(?)";
    public static final String FORMATO_FECHA_SQL = "yyyy-MM-dd";
    
}
