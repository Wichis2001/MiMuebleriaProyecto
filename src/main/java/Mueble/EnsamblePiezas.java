/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mueble;
import java.io.Serializable;

/**
 * Esta clase me permite el manejo del objeto de ensamble de piezas para su uso en la pagina web y la base de datos
 * @author luis
 */
public class EnsamblePiezas implements Serializable{
    private String mueble_nombre;
    private int cantidad;
    private String pieza_tipo;

    /**
     * Este constructor vacio me permite la utilizacion de un objeto de ensamble de piezas desde la base de datos
     */
    public EnsamblePiezas() {
    }

    /**
     *  Este metodo me permite ensamblar piezas a travez del programa y poder ser cargadas desde la base de datos
     * @param mueble_nombre
     * @param pieza_tipo
     * @param cantidad
     */
    public EnsamblePiezas(String mueble_nombre,String pieza_tipo,int cantidad) {
        this.mueble_nombre = mueble_nombre;
        this.cantidad = cantidad;
        this.pieza_tipo = pieza_tipo;
    }
    
    /**
     * Este metodo me devuelve el nombre de un mueble
     * @return
     */
    public String getMueble_nombre() {
        return mueble_nombre;
    }

    /**
     * Este metodo me permite cambiar el nombre de un mueble
     * @param mueble_nombre
     */
    public void setMueble_nombre(String mueble_nombre) {
        this.mueble_nombre = mueble_nombre;
    }

    /**
     * Este metodo me devuelve la cantidad de piezas necesarias
     * @return
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Este metodo me permite cambiar la cantidad de piezas necesarias
     * @param cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Este metodo me devuelve el tipo de pieza que utilizara el mueble
     * @return
     */
    public String getPieza_tipo() {
        return pieza_tipo;
    }

    /**
     * Este metodo me permite cambiar el tipo de pieza que empleara el mueble
     * @param pieza_tipo
     */
    public void setPieza_tipo(String pieza_tipo) {
        this.pieza_tipo = pieza_tipo;
    }

}
