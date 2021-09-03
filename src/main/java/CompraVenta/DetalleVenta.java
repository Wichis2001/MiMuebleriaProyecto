/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompraVenta;

import java.io.Serializable;

/**
 * Esta clase me permite establer un detalle de una venta con la finalidad de poder ser un intermediario entre mi base de datos y la página web
 * @author luis
 */
public class DetalleVenta implements Serializable{
    private int id_detalle_venta;
    private int venta_id;
    private String mueble_identificador_mueble;
    private Double precio_venta;

    /**
     *  Este constructor vacio me permite construir un objeto detalle de venta sin necesidad de parametros
     */
    public DetalleVenta() {
    }

    /**
     * Este constructor me permite crear un objeto de detalle de venta para poder asignarlo a la base de datos
     * @param id_detalle_venta
     * @param venta_id
     * @param mueble_identificador_mueble
     * @param precio_venta
     */
    public DetalleVenta(int id_detalle_venta, int venta_id, String mueble_identificador_mueble, Double precio_venta) {
        this.id_detalle_venta = id_detalle_venta;
        this.venta_id = venta_id;
        this.mueble_identificador_mueble = mueble_identificador_mueble;
        this.precio_venta = precio_venta;
    }

    /**
     * Este metodo me devuelve el id del detalle de una venta
     * @return
     */
    public int getId_detalle_venta() {
        return id_detalle_venta;
    }

    /**
     * Este metodo me permite cambiar el detalle de una venta
     * @param id_detalle_venta
     */
    public void setId_detalle_venta(int id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }

    /**
     * este metodo me devuelve el id de una venta
     * @return
     */
    public int getVenta_id() {
        return venta_id;
    }

    /**
     * Este metodo me permite cambiar el id de una venta
     * @param venta_id
     */
    public void setVenta_id(int venta_id) {
        this.venta_id = venta_id;
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
     * Este metodo me devuelve el precio de una venta
     * @return
     */
    public Double getPrecio_venta() {
        return precio_venta;
    }

    /**
     * Este metodo me permite cambiar el precio de una venta
     * @param precio_venta
     */
    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }
}

