/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompraVenta;
import java.io.Serializable;
import java.sql.Date;

/**
 * Esta clase me permite  la creacion y manejamiento de un objeto Devolucion, el cual me permitira manejarlo desde la pagina de web y la base de datos
 * @author luis
 */
public class Devolucion implements Serializable {
    int no_devolucion;
    Date fecha_devolucion;
    Double perdida;
    int no_factura;

    /**
     * Este es un costructor de la clase devolución que me puede llegar a ser de utilidad en el uso del programa
     */
    public Devolucion() {
    }
    
    /**
     * Este constructor me permite enviar un objeto devolucion a mi base de datos
     * @param fecha_devolucion
     * @param perdida
     * @param no_factura
     */
    public Devolucion(Date fecha_devolucion, Double perdida, int no_factura) {
        this.fecha_devolucion = fecha_devolucion;
        this.perdida = perdida;
        this.no_factura = no_factura;
    }

    /**
     * Este metodo me devuelve el numero de devolucion
     * @return
     */
    public int getNo_devolucion() {
        return no_devolucion;
    }

    /**
     * Este metodo me permite cambiar el numero de devolucion
     * @param no_devolucion
     */
    public void setNo_devolucion(int no_devolucion) {
        this.no_devolucion = no_devolucion;
    }

    /**
     * Este metodo me devuelve la fecha de devolucion
     * @return
     */
    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    /**
     * Este metodo me permite cambiar la fecha de devolucion 
     * @param fecha_devolucion
     */
    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Este metodo me devuelve la perdida que hay al momemnto de una devolucion
     * @return
     */
    public Double getPerdida() {
        return perdida;
    }

    /**
     * Este metodo me permite cambiar la perdida que hay al momento de una devolcion
     * @param perdida
     */
    public void setPerdida(Double perdida) {
        this.perdida = perdida;
    }

    /**
     * Este metodo me devuelve el numero de factura que esta asociada con la devoluciono
     * @return
     */
    public int getNo_factura() {
        return no_factura;
    }

    /**
     * Este metodo me permite cambiar el numero de factura que esta asociada con la devolucion
     * @param no_factura
     */
    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }
    
}
