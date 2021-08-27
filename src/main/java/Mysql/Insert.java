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
public class Insert {
    public static final String insertPieza="INSERT INTO pieza (tipo, costo, cantidad) values (?,?,?)";
    public static final String INSERTMUEBLEENSAMBLADO="INSERT INTO mueble_ensamblado (identificador_mueble, nombre_mueble_ensamble, usuario_constructor, fecha_ensamblaje, costo_construccion, estado, precio) VALUES (?,?,?,?,?,?,?)";
}
