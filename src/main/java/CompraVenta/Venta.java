/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompraVenta;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Esta clase me permite la creacion y manejo de ventas a travez de la pagina web y la base de datos
 * @author luis
 */
public class Venta implements Serializable{
    private int id_venta;
    private double total;
    private LocalDate fecha_compra;
    private String nit_cliente;
    private String direccion_cliente;
    private String usuario_vendedor;
    private String numero_serie;
    private int item;
    private Double subtotal;
    private String idmueble;
    private String descripcion;
    private Double monto;
    /**
     * Este constructor vacio me puede ayudar a crear un objeto venta para su uso en la interfaz grafica
     */
    public Venta() {
    }

    /**
     * Este constructor me permite establecer un objeto venta para poder ser ingresado a la base de datos
     * @param id_venta
     * @param total
     * @param fecha_compra
     * @param nit_cliente
     * @param direccion_cliente
     * @param usuario_vendedor
     * @param numero_serie
     * @param item
     * @param subtotal
     * @param idmueble
     * @param descripcion
     * @param monto
     */
    public Venta(int id_venta, double total, LocalDate fecha_compra, String nit_cliente, String direccion_cliente, String usuario_vendedor, String numero_serie, int item, Double subtotal, String idmueble, String descripcion, Double monto) {
        this.id_venta = id_venta;
        this.total = total;
        this.fecha_compra = fecha_compra;
        this.nit_cliente = nit_cliente;
        this.direccion_cliente = direccion_cliente;
        this.usuario_vendedor = usuario_vendedor;
        this.numero_serie = numero_serie;
        this.item=item;
        this.subtotal=subtotal;
        this.idmueble=idmueble;
        this.descripcion=descripcion;
        this.monto=monto;
    }

    /**
     * Este metodo me devuelve el monto de una venta
     * @return
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Este metodo me permite cambiar el monto de una venta
     * @param monto
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Este metodo me devuelve la descripcion de un producto en una venta
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Este metodo me permite cambiar la descripcion de un producto de una venta
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Este metodo me devuelve el id de un mueble
     * @return
     */
    public String getIdmueble() {
        return idmueble;
    }

    /**
     * Este metodo me permite cambiar el id de un mueble
     * @param idmueble
     */
    public void setIdmueble(String idmueble) {
        this.idmueble = idmueble;
    }
    

    /**
     * Este metodo me devuelve el subtotal de una compra
     * @return
     */
    public Double getSubtotal() {
        return subtotal;
    }

    /**
     * Este metodo me permite cambiar el subtotal de una compra
     * @param subtotal
     */
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    

    /**
     * Este metodo me permite cambiar el numero de item
     * @return
     */
    public int getItem() {
        return item;
    }

    /**
     * Este metodo me permite cambiar el numero de item
     * @param item
     */
    public void setItem(int item) {
        this.item = item;
    }
    
    /**
     * Este metod me devuelve el id de venta 
     * @return
     */
    public int getId_venta() {
        return id_venta;
    }

    /**
     * Este metodo me permite cambiar el id de una venta
     * @param id_venta
     */
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    /**
     * Este metodo me devuelve el total de una venta
     * @return
     */
    public double getTotal() {
        return total;
    }

    /**
     * Este metodo me permite cambiar el total de una venta
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Este metodo me devuelve la fecha de una compra
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
     * Este metodo me devuelve el nit de un cliente 
     * @return
     */
    public String getNit_cliente() {
        return nit_cliente;
    }

    /**
     * Este metodo me permite cambiar el nit de un cliente
     * @param nit_cliente
     */
    public void setNit_cliente(String nit_cliente) {
        this.nit_cliente = nit_cliente;
    }

    /**
     * Este metodo me devuelve la direccin de un cliente
     * @return
     */
    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    /**
     * Este metodo me permite cambiar la direccion de un ciente
     * @param direccion_cliente
     */
    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    /**
     * Este metodo me devuelve al usuario vendedor
     * @return
     */
    public String getUsuario_vendedor() {
        return usuario_vendedor;
    }

    /**
     * Este metodo permite cambiar al usuario vendedor
     * @param usuario_vendedor
     */
    public void setUsuario_vendedor(String usuario_vendedor) {
        this.usuario_vendedor = usuario_vendedor;
    }

    /**
     * Este metodo me devuevle el numero de serie
     * @return
     */
    public String getNumero_serie() {
        return numero_serie;
    }

    /**
     * Este metodo me permite cambiar un numero de serie
     * @param numero_serie
     */
    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }
  
}
