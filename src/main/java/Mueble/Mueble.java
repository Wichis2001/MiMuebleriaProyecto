/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mueble;
import java.io.Serializable;
import java.sql.Date;
import Codigos.GeneradorCodigos;
/**
 *
 * @author luis
 */
public class Mueble implements Serializable{

    private String nombre_mueble_ensamble;
    private Double precio;

    public Mueble() {
    }

    public Mueble(String nombre_mueble_ensamble, Double precio) {
        this.nombre_mueble_ensamble = nombre_mueble_ensamble;
        this.precio = precio;
    }

    public String getNombre_mueble_ensamble() {
        return nombre_mueble_ensamble;
    }

    public void setNombre_mueble_ensamble(String nombre_mueble_ensamble) {
        this.nombre_mueble_ensamble = nombre_mueble_ensamble;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
}
