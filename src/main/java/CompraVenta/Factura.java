/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompraVenta;
import java.io.Serializable;
import java.sql.Date;

/**
 * Esta clase me permite establecer un objeto factura el cual podre usar para poder emplearlo en la página web
 * @author luis
 */
public class Factura implements Serializable{
    int no_factura;
    Double total;
    Date fecha_compra;
    long nit_cliente;
    String direccion_cliente;
    String usuario_vendedor;
    String mueble_identificador_mueble;

    /**
     * Constructor vacio de la clase factura, el cual me puede ser de utilidad en uso del programa
     */
    public Factura() {
    }

    /**
     * Este constructor me permite construir un objeto factura con la finalidad de poder transferirlo a la base de datos
     * @param total
     * @param fecha_compra
     * @param nit_cliente
     * @param direccion_cliente
     * @param usuario_vendedor
     * @param mueble_identificador_mueble
     */
    public Factura(Double total, Date fecha_compra, long nit_cliente, String direccion_cliente, String usuario_vendedor, String mueble_identificador_mueble) {
        this.total = total;
        this.fecha_compra = fecha_compra;
        this.nit_cliente = nit_cliente;
        this.direccion_cliente = direccion_cliente;
        this.usuario_vendedor = usuario_vendedor;
        this.mueble_identificador_mueble = mueble_identificador_mueble;
    }

    /**
     * Este metodo me devuelve el numero de la factura
     * @return
     */
    public int getNo_factura() {
        return no_factura;
    }

    /**
     * Este metodo me permite cambiar el numero de la factura
     * @param no_factura
     */
    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    /**
     * Este metodo me devuelve el total de la factura
     * @return
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Este metodo me permite cambiar el total de la factura
     * @param total
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Este metodo me devuelve la fecha de compra que esta establecida en la factura
     * @return
     */
    public Date getFecha_compra() {
        return fecha_compra;
    }

    /**
     * Este metodo me permite cambiar la fecha de compra del cliente
     * @param fecha_compra
     */
    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    /**
     * Este metodo me devuelve el nit del cliente
     * @return
     */
    public long getNit_cliente() {
        return nit_cliente;
    }

    /**
     * Este metodo me permite cambiar el nit del cliente
     * @param nit_cliente
     */
    public void setNit_cliente(long nit_cliente) {
        this.nit_cliente = nit_cliente;
    }

    /**
     * Este metodo me devuelve la direccion de la que procede el cliente
     * @return
     */
    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    /**
     * Este metodo me permite cambiar la direccion de la que procede el cliente
     * @param direccion_cliente
     */
    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    /**
     * Este metodo me devuelve el nombre del usuario vendedor
     * @return
     */
    public String getUsuario_vendedor() {
        return usuario_vendedor;
    }

    /**
     * Este metodo me permite cambiar el nombre del usuario vendedor
     * @param usuario_vendedor
     */
    public void setUsuario_vendedor(String usuario_vendedor) {
        this.usuario_vendedor = usuario_vendedor;
    }

    /**
     * Este metodo me devuelve identificador del mueble
     * @return
     */
    public String getMueble_identificador_mueble() {
        return mueble_identificador_mueble;
    }

    /**
     * Este metodo me permite cambiar el identificador del mueble
     * @param mueble_identificador_mueble
     */
    public void setMueble_identificador_mueble(String mueble_identificador_mueble) {
        this.mueble_identificador_mueble = mueble_identificador_mueble;
    }
}
