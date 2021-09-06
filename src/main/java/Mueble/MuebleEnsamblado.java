/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mueble;
import Codigos.GeneradorCodigos;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Esta clase me permite la estabilizacion de un objeto de mueble ensamblado el cual me puede permitir su utilizacion a travez de la base de datos y la pagiana web
 * @author luis
 */
public class MuebleEnsamblado implements Serializable{
    private String identificador_mueble;
    private LocalDate fecha_ensamblaje;
    private Double precio;
    private Double costo_construccion;
    private int estado;
    private String usuario_constructor;
    private String nombre_mueble_ensamblado;

    /**
     * Este es un metodo vacio el cual me peude permitir la creacion de un mueble ensamblado sin parametros a lo largo del programa
     */
    public MuebleEnsamblado() {
    }
    
    {
        // Este metodo anonimo establecemos al identificador de un mueble como ""
        this.identificador_mueble="";
    }

    /**
     * Este constructor me permite la creacion de un mueble ensamblado a travez de la carga de datos
     * @param fecha_ensamblaje
     * @param precio
     * @param usuario_constructor
     * @param nombre_mueble_ensamblado
     */
    public MuebleEnsamblado(LocalDate fecha_ensamblaje, Double precio, String usuario_constructor, String nombre_mueble_ensamblado) {
        this.fecha_ensamblaje = fecha_ensamblaje;
        this.precio = precio;
        this.costo_construccion=0.0;
        this.estado = 3;
        this.usuario_constructor = usuario_constructor;
        this.nombre_mueble_ensamblado = nombre_mueble_ensamblado;
        this.identificador_mueble= GeneradorCodigos.generarCodigo(this.identificador_mueble, 6, true);
    }

    /**
     * Este metodo me permite la creacion de un mueble ensamblado para su transferencia a la base de datos
     * @param fecha_ensamblaje
     * @param precio
     * @param estado
     * @param usuario_constructor
     * @param nombre_mueble_ensamblado
     */
    public MuebleEnsamblado(LocalDate fecha_ensamblaje, Double precio, int estado, String usuario_constructor, String nombre_mueble_ensamblado) {
        this.fecha_ensamblaje = fecha_ensamblaje;
        this.precio=precio;
        this.estado = estado;
        this.usuario_constructor = usuario_constructor;
        this.nombre_mueble_ensamblado = nombre_mueble_ensamblado;
        this.identificador_mueble= GeneradorCodigos.generarCodigo(this.identificador_mueble, 6, true);
    }
    
    /**
     * Este metodo me devuelve el costo de construccion de un mueble
     * @return
     */
    public Double getCosto_construccion() {
        return costo_construccion;
    }

    /**
     * Este metodo me permite cambiar el costo de construccion de un mueble
     * @param costo_construccion
     */
    public void setCosto_construccion(Double costo_construccion) {
        this.costo_construccion = costo_construccion;
    }
    
    /**
     * Este metodo me devuelve el identificador de un mueble
     * @return
     */
    public String getIdentificador_mueble() {
        return identificador_mueble;
    }

    /**
     * Este metodo me permite cambir el identificador de un mueble
     * @param identificador_mueble
     */
    public void setIdentificador_mueble(String identificador_mueble) {
        this.identificador_mueble = identificador_mueble;
    }

    /**
     * Este metodo me devuelve la fecha de ensamblaje de un mueble
     * @return
     */
    public LocalDate getFecha_ensamblaje() {
        return fecha_ensamblaje;
    }

    /**
     * Este metodo me permite cambiar la fecha de ensamblaje de un mueble
     * @param fecha_ensamblaje
     */
    public void setFecha_ensamblaje(LocalDate fecha_ensamblaje) {
        this.fecha_ensamblaje = fecha_ensamblaje;
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

    /**
     * Este metodo me devuelve el estado en el que se encuentra un mueble ensamblado
     * @return
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Este metodo me permite cambiar el estado en el que se encuentra un mueble ensamblado
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Este metodo me devuelve el usuario constructor de un mueble
     * @return
     */
    public String getUsuario_constructor() {
        return usuario_constructor;
    }

    /**
     * Este metodo me permite cambiar el usuario constructor de un mueble
     * @param usuario_constructor
     */
    public void setUsuario_constructor(String usuario_constructor) {
        this.usuario_constructor = usuario_constructor;
    }

    /**
     * Este metodo me devuelve el nombre de un mueble ensamblado
     * @return
     */
    public String getNombre_mueble_ensamblado() {
        return nombre_mueble_ensamblado;
    }

    /**
     * Este metodo me permite cambiar el nombre de un mueble ensamblado
     * @param nombre_mueble_ensamblado
     */
    public void setNombre_mueble_ensamblado(String nombre_mueble_ensamblado) {
        this.nombre_mueble_ensamblado = nombre_mueble_ensamblado;
    }
}
