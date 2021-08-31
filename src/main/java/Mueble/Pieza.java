package Mueble;
import java.io.Serializable;

/**
 * Este clase me permite establecer un objeto pieza para su utilizacion y manejo a travez de la base de datos o la página web
 * @author luis
 */
public class Pieza implements Serializable{
    private String tipo;
    private Double costo;
    private int cantidad;

    /**
     * Este constructor vacio me permite establecer una pieza sin necesidad de parametros, esto con la finalidad de que puede ser de utilidad en el desarrollo del software 
     */
    public Pieza() {
        
    }

    /**
     * Este constructor me permite la creacion de una pieza a travez de la base de datos
     * @param tipo
     * @param costo
     * @param cantidad
     */
    public Pieza(String tipo, Double costo, int cantidad) {
        this.tipo = tipo;
        this.costo = costo;
        this.cantidad = cantidad;
    }

    /**
     * Este metodo me devuelve el tipo de una pieza
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Este metodo me permite cambiar el tipo de una pieza
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Este metodo me devuelve el costo de una pieza
     * @return
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * Este metdoo me permite cambiar el costo de una pieza
     * @param costo
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * Este metodo me devuelve la cantiad de piezas que hay en existencia
     * @return
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Este metodo me permite cambiar la cantidad de piezas que hay en existencia
     * @param cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }   
}
