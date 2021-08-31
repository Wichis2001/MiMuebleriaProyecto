/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql;

/**
 * Esta clase me permite establecer mis INSERTS para mi base de datos a travez de constantes
 * @author luis
 */
public class Insert {

    /**
     * Esta constante me permite hacer un insert de una pieza
     */
    public static final String insertPieza="INSERT INTO pieza (tipo, costo, cantidad) values (?,?,?)";

    /**
     * Esta constante me permite hacer un insert de un mueble ensamblado para mi base de datos
     */
    public static final String INSERTMUEBLEENSAMBLADO="INSERT INTO mueble_ensamblado (identificador_mueble, nombre_mueble_ensamble, usuario_constructor, fecha_ensamblaje, costo_construccion, estado, precio) VALUES (?,?,?,?,?,?,?)";
}
