/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql;

/**
 * Esta clase me permite establecer las querys que me serviran para poder hacer llamadas de esteas en mis DAO
 * @author luis
 */
public class Querys {

    /**
     * Esta constante me permite hacer una consulta de todas mis piezas
     */
    public static final String  queryPieza="SELECT * FROM pieza";

    /**
     * Esta constante me permite hacer una consulta de todos los muebles
     */
    public static final String  queryMueble="SELECT * FROM mueble";

    /**
     * Esta consutla me permite hacer un llamado de todos los muebles que han sido ensamblados
     */
    public static final String  queryMuebleEnsamblado="SELECT identificador_mueble, nombre_mueble_ensamble, usuario_constructor, fecha_ensamblaje  FROM mueble_ensamblado WHERE estado=2";

    /**
     * Esta consulta me permite hacer una consulta de un usuario cuyos parabetros son su nombre y la contraseña
     */
    public static final String queryUsuarios="SELECT * FROM usuario WHERE nombre_usuario=? AND contraseña=?";

    /**
     * Esta consulta selecciona una pieza poniendo como parametro el tipo de la pieza
     */
    public static final String querySelectPiezasNecesarias="SELECT * FROM pieza WHERE UPPER(tipo) = UPPER(?)";

    /**
     * Esta constante me permite establecer el formato de fecha que tiene Java SQL
     */
    public static final String FORMATO_FECHA_SQL = "yyyy-MM-dd";

    /**
     * Esta consulta me devuelve todas las pieza y ordenar estas de manera descientente en base a la cantidad de piezas
     */ 
    public static final String querypiezaMayoraMenor="SELECT tipo, costo, cantidad FROM pieza ORDER BY cantidad DESC";

    /**
     *Esta consulta me devuelve todas las pieza y ordenar estas de manera ascendente en base a la cantidad de piezas
     */
    public static final String querypiezaMenoraMayor="SELECT tipo, costo, cantidad FROM pieza ORDER BY cantidad ASC";

    /**
     * Esta consulta me devuelve todos los muebles que han sido ensamblados
     */
    public static final String querymuebleconsulta="SELECT identificador_mueble, nombre_mueble_ensamble, usuario_constructor, precio, fecha_ensamblaje FROM mueble_ensamblado";

    /** 
     * Esta consulta me devuelve todos los muebles que han sido ensamblados, y los ordena de manera descendiente en base a la fecha de construccion
     */
    public static final String querymuebleconsultamenor="SELECT identificador_mueble, nombre_mueble_ensamble, usuario_constructor, precio, fecha_ensamblaje FROM mueble_ensamblado ORDER BY fecha_ensamblaje DESC";

    /**
     * Esta consulta me devuelve todos los muebles que han sido ensamblados, y los ordena de manera ascendente en base a la fecha de construccion
     */
    public static final String querymuebleconsultamayor="SELECT identificador_mueble, nombre_mueble_ensamble, usuario_constructor, precio, fecha_ensamblaje FROM mueble_ensamblado ORDER BY fecha_ensamblaje ASC";
    
}
