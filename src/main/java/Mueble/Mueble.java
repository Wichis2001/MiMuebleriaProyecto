/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mueble;
import java.io.Serializable;

/**
 * Esta clase me permite la creación de un mueble que puede ser emmpleado desde la pagina web o la base de datos
 * @author luis
 */
public class Mueble implements Serializable{

    private String nombre_mueble_ensamble;
    private Double precio;

    /**
     * Este es un metodo vacio que me permite la utilizacion del mueble a lo largo del programa
     */
    public Mueble() {
    }

    /**
     * Este metodo me servira para la creacion de un nuevo mueble a travez de la carga de archivos
     * @param nombre_mueble_ensamble
     * @param precio
     */
    public Mueble(String nombre_mueble_ensamble, Double precio) {
        this.nombre_mueble_ensamble = nombre_mueble_ensamble;
        this.precio = precio;
    }

    /**
     * Este metodo me permite cambiar el nombre del mueble ensamblado
     * @return
     */
    public String getNombre_mueble_ensamble() {
        return nombre_mueble_ensamble;
    }

    /**
     * Este metodo me permite cambiar el nombre del mueble ensamblado
     * @param nombre_mueble_ensamble
     */
    public void setNombre_mueble_ensamble(String nombre_mueble_ensamble) {
        this.nombre_mueble_ensamble = nombre_mueble_ensamble;
    }

    /**
     * Este metodo me devuelve el precio de un mueble
     * @return
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Este metodo me permite cambiar el precio de un mueble
     * @param precio
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
