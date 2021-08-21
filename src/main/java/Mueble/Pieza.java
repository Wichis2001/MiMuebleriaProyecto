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
public class Pieza implements Serializable{
    private String tipo;
    private Double costo;
    private int cantidad;

    public Pieza() {
        
    }

    public Pieza(String tipo, Double costo, int cantidad) {
        this.tipo = tipo;
        this.costo = costo;
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }   
}
