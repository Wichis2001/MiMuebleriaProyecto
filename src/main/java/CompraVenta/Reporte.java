/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompraVenta;
import java.time.LocalDate;

/**
 * Esta clase me permite generar un objeto reporte con la finallidad de que sirva como puenta para extraer los datos brindados por una Query
 * @author luis
 */
public class Reporte {
    private String nombreClientre;
    private String direccion;
    private String nit;
    private LocalDate fecha_compra;
    private String mueble_identificador_mueble;
    private String nombre_mueble_ensamble;
    private String numeroSerie;
    private LocalDate fechaDevolucion;
    private Double perdida;
    private Double precio;
    private Double total;
    
    /**
     * Este constructor me permite establecer un reporte general a travez de los parametros definidos en la misma
     * @param nombreClientre
     * @param direccion
     * @param nit
     * @param fecha_compra
     * @param mueble_identificador_mueble
     * @param nombre_mueble_ensamble
     */
    public Reporte(String nombreClientre, String direccion, String nit, LocalDate fecha_compra, String mueble_identificador_mueble, String nombre_mueble_ensamble) {
        this.nombreClientre = nombreClientre;
        this.direccion = direccion;
        this.nit = nit;
        this.fecha_compra = fecha_compra;
        this.mueble_identificador_mueble = mueble_identificador_mueble;
        this.nombre_mueble_ensamble = nombre_mueble_ensamble;
    }

    /**
     * Este metodo vacio me puede ayudar a lo largo del programa
     */
    public Reporte() {
    }

    /**
     * Este metodo me devuelve el total de un reporte
     * @return
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Este metodo me permite cambiar el total de un reporte
     * @param total
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Este metodo me devuelve el precio de un reporte
     * @return
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Este metodo me permite cambiar el precio de un reporte
     * @param precio
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    /**
     * Este metodo me devuelve la perdida de un reporte
     * @return
     */
    public Double getPerdida() {
        return perdida;
    }

    /**
     * Este metodo me permite cambiar la perdida de un reporte
     * @param perdida
     */
    public void setPerdida(Double perdida) {
        this.perdida = perdida;
    }

    /**
     * Este metodo me devuelve la fecha de una devolucion
     * @return
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Este metodo me permite cambiar la fecha de una devolucion
     * @param fechaDevolucion
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Este metodo me devuelve una factura
     * @return
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Este metodo me permite cambiar el numero de una factura
     * @param numeroSerie
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
    
    /** 
     * Este metodo me devuelve el nombre de un cliente
     * @return
     */
    public String getNombreClientre() {
        return nombreClientre;
    }

    /**
     * Este metodo me permite cambiar el nombre de un cliente
     * @param nombreClientre
     */
    public void setNombreClientre(String nombreClientre) {
        this.nombreClientre = nombreClientre;
    }

    /**
     * Este metodo me devuelve la direccion de un cliente
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Este metodo me permite cambiar la direccion de un cliente
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Este metodo me devuelve el nit de un cliente
     * @return
     */
    public String getNit() {
        return nit;
    }

    /**
     * Este metodo me permite cambiar el nit de un cliente
     * @param nit
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /** 
     * Este metodo me devuevle la fecha de una compra
     * @return
     */
    public LocalDate getFecha_compra() {
        return fecha_compra;
    }

    /**
     * Este metodo me permite cambiar la fecha de una compra
     * @param fecha_compra
     */
    public void setFecha_compra(LocalDate fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    /**
     * Este metodo me devuelve el identificador de un mueble
     * @return
     */
    public String getMueble_identificador_mueble() {
        return mueble_identificador_mueble;
    }

    /**
     * Este metodo me permite cambiar el identificador de un mueble
     * @param mueble_identificador_mueble
     */
    public void setMueble_identificador_mueble(String mueble_identificador_mueble) {
        this.mueble_identificador_mueble = mueble_identificador_mueble;
    }

    /**
     * Este metodo me devuelve el nombre de un mueble
     * @return
     */
    public String getNombre_mueble_ensamble() {
        return nombre_mueble_ensamble;
    }

    /**
     * Este metodo me permite cambiar el nombre de un mueble
     * @param nombre_mueble_ensamble
     */
    public void setNombre_mueble_ensamble(String nombre_mueble_ensamble) {
        this.nombre_mueble_ensamble = nombre_mueble_ensamble;
    }
    
    
}
