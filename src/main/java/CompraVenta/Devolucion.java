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
public class Devolucion implements Serializable {
    int no_devolucion;
    Date fecha_devolucion;
    Double perdida;
    int no_factura;

    public Devolucion() {
    }
    
    
    public Devolucion(Date fecha_devolucion, Double perdida, int no_factura) {
        this.fecha_devolucion = fecha_devolucion;
        this.perdida = perdida;
        this.no_factura = no_factura;
    }

    public int getNo_devolucion() {
        return no_devolucion;
    }

    public void setNo_devolucion(int no_devolucion) {
        this.no_devolucion = no_devolucion;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public Double getPerdida() {
        return perdida;
    }

    public void setPerdida(Double perdida) {
        this.perdida = perdida;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }
    
}
