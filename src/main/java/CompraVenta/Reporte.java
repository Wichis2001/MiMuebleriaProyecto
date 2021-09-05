/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompraVenta;
import java.time.LocalDate;

/**
 *
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
    public Reporte(String nombreClientre, String direccion, String nit, LocalDate fecha_compra, String mueble_identificador_mueble, String nombre_mueble_ensamble) {
        this.nombreClientre = nombreClientre;
        this.direccion = direccion;
        this.nit = nit;
        this.fecha_compra = fecha_compra;
        this.mueble_identificador_mueble = mueble_identificador_mueble;
        this.nombre_mueble_ensamble = nombre_mueble_ensamble;
    }

    public Reporte() {
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
    public Double getPerdida() {
        return perdida;
    }

    public void setPerdida(Double perdida) {
        this.perdida = perdida;
    }

    
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    
    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
    
    

    public String getNombreClientre() {
        return nombreClientre;
    }

    public void setNombreClientre(String nombreClientre) {
        this.nombreClientre = nombreClientre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public LocalDate getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDate fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public String getMueble_identificador_mueble() {
        return mueble_identificador_mueble;
    }

    public void setMueble_identificador_mueble(String mueble_identificador_mueble) {
        this.mueble_identificador_mueble = mueble_identificador_mueble;
    }

    public String getNombre_mueble_ensamble() {
        return nombre_mueble_ensamble;
    }

    public void setNombre_mueble_ensamble(String nombre_mueble_ensamble) {
        this.nombre_mueble_ensamble = nombre_mueble_ensamble;
    }
    
    
}
