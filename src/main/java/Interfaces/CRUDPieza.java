/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Mueble.Pieza;
import java.util.List;

/**
 * Esta interfaz me permite establecer los CRUD que me permitira crear una nueva pieza
 * @author luis
 */
public interface CRUDPieza {

    /**
     * Este metodo me permite listar las piezas que esten disponibles en la base de datos
     * @return
     */
    public List listar();

    /**
     * Este metodo me permite listar la pieza necesaria para una actualización a travez de su tipo y de su costo
     * @param tipo
     * @param costo
     * @return
     */
    public Pieza list(String tipo, double costo);

    /**
     * Este metodo me permite agregar una pieza a la base de datos
     * @param pieza
     * @return
     */
    public boolean add(Pieza pieza);

    /**
     * Este metodo me permite actualizar una pieza a la base de datos
     * @param pieza
     * @return
     */
    public boolean edit(Pieza pieza);

    /**
     * Este metodo me permite eliminar una pieza de la base de datos
     * @param tipo
     * @param costo
     * @return
     */
    public boolean delete(String tipo, double costo);
}
