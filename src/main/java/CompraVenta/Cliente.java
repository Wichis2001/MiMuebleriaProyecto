/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompraVenta;
import java.io.Serializable;

/**
 * Esta clase me permite poder establecer los atributos que debe de tener un cliente para poder emplearlo desde mi base de datos y la págna web
 * @author luis
 */
public class Cliente implements Serializable{
    private String nombre;
    private long nit;
    private String direccion;
    private String municipio;
    private String departamento;

    /**
     * Constructor vacio que puedo llegar a emplear en el uso del programa
     */
    public Cliente() {
    }
    
    /**
     * Este constructor de cliente me permite crear un objeto cliente desde la base de datos
     * @param nombre
     * @param nit
     * @param direccion
     */
    public Cliente(String nombre, long nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
    }

    /**
     * Este constructor me permite 
     * @param nombre
     * @param nit
     * @param direccion
     * @param municipio
     * @param departamento
     */
    public Cliente(String nombre, long nit, String direccion, String municipio, String departamento) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.municipio = municipio;
        this.departamento = departamento;
    }

    /**
     *  Este metodo me devuelve el nombre de un cliente
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Este metodo me permite cambiar el nombre de un cliente
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Este metodo me devuelve el nit que tiene un cliente
     * @return
     */
    public long getNit() {
        return nit;
    }

    /**
     * Este metodo me permite cambiar el nit que tiene un cliente
     * @param nit
     */
    public void setNit(long nit) {
        this.nit = nit;
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
     * Este metodo me devuelve el municipio de un cliente
     * @return
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Este metodo me permite cambiar el municipio de un cliente
     * @param municipio
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * Este metodo me devueve el departamento al que pertence un cliente
     * @return
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Este metodo me permite cambiar el departmento de cliente
     * @param departamento
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
