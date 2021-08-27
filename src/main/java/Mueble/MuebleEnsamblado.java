/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mueble;
import Codigos.GeneradorCodigos;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author luis
 */
public class MuebleEnsamblado implements Serializable{
    private String identificador_mueble;
    private Date fecha_ensamblaje;
    private Double precio;
    private Double costo_construccion;
    private int estado;
    private String usuario_constructor;
    private String nombre_mueble_ensamblado;

    public MuebleEnsamblado() {
    }

    public MuebleEnsamblado(Date fecha_ensamblaje, Double precio, String usuario_constructor, String nombre_mueble_ensamblado) {
        this.identificador_mueble = GeneradorCodigos.generarCodigo(this.identificador_mueble, 6, true);
        this.fecha_ensamblaje = fecha_ensamblaje;
        this.precio = precio;
        this.costo_construccion=0.0;
        this.estado = 3;
        this.usuario_constructor = usuario_constructor;
        this.nombre_mueble_ensamblado = nombre_mueble_ensamblado;
    }

    public MuebleEnsamblado(Date fecha_ensamblaje, Double precio, Double costo_construccion, int estado, String usuario_constructor, String nombre_mueble_ensamblado) {
        this.identificador_mueble = GeneradorCodigos.generarCodigo(this.identificador_mueble, 6, true);
        this.fecha_ensamblaje = fecha_ensamblaje;
        this.precio = precio;
        this.costo_construccion=costo_construccion;
        this.estado = estado;
        this.usuario_constructor = usuario_constructor;
        this.nombre_mueble_ensamblado = nombre_mueble_ensamblado;
    }

    public Double getCosto_construccion() {
        return costo_construccion;
    }

    public void setCosto_construccion(Double costo_construccion) {
        this.costo_construccion = costo_construccion;
    }
    
    

    public String getIdentificador_mueble() {
        return identificador_mueble;
    }

    public void setIdentificador_mueble(String identificador_mueble) {
        this.identificador_mueble = identificador_mueble;
    }

    public Date getFecha_ensamblaje() {
        return fecha_ensamblaje;
    }

    public void setFecha_ensamblaje(Date fecha_ensamblaje) {
        this.fecha_ensamblaje = fecha_ensamblaje;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getUsuario_constructor() {
        return usuario_constructor;
    }

    public void setUsuario_constructor(String usuario_constructor) {
        this.usuario_constructor = usuario_constructor;
    }

    public String getNombre_mueble_ensamblado() {
        return nombre_mueble_ensamblado;
    }

    public void setNombre_mueble_ensamblado(String nombre_mueble_ensamblado) {
        this.nombre_mueble_ensamblado = nombre_mueble_ensamblado;
    }
}
