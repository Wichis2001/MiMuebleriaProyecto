/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mueble;
import java.io.Serializable;

/**
 *
 * @author luis
 */
public class EnsamblePiezas implements Serializable{
    private String mueble_nombre;
    private int cantidad;
    private String pieza_tipo;

    public EnsamblePiezas() {
    }

    public EnsamblePiezas(String mueble_nombre,String pieza_tipo,int cantidad) {
        this.mueble_nombre = mueble_nombre;
        this.cantidad = cantidad;
        this.pieza_tipo = pieza_tipo;
    }
    
    public String getMueble_nombre() {
        return mueble_nombre;
    }

    public void setMueble_nombre(String mueble_nombre) {
        this.mueble_nombre = mueble_nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPieza_tipo() {
        return pieza_tipo;
    }

    public void setPieza_tipo(String pieza_tipo) {
        this.pieza_tipo = pieza_tipo;
    }

}
