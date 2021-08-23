/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompraVenta;
import java.io.Serializable;
import java.sql.Date;

/**
 *
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

    public Factura() {
    }

    public Factura(Double total, Date fecha_compra, long nit_cliente, String direccion_cliente, String usuario_vendedor, String mueble_identificador_mueble) {
        this.total = total;
        this.fecha_compra = fecha_compra;
        this.nit_cliente = nit_cliente;
        this.direccion_cliente = direccion_cliente;
        this.usuario_vendedor = usuario_vendedor;
        this.mueble_identificador_mueble = mueble_identificador_mueble;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public long getNit_cliente() {
        return nit_cliente;
    }

    public void setNit_cliente(long nit_cliente) {
        this.nit_cliente = nit_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getUsuario_vendedor() {
        return usuario_vendedor;
    }

    public void setUsuario_vendedor(String usuario_vendedor) {
        this.usuario_vendedor = usuario_vendedor;
    }

    public String getMueble_identificador_mueble() {
        return mueble_identificador_mueble;
    }

    public void setMueble_identificador_mueble(String mueble_identificador_mueble) {
        this.mueble_identificador_mueble = mueble_identificador_mueble;
    }
}
