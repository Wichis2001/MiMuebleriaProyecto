/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Mueble.MuebleEnsamblado;
import java.sql.SQLException;
import java.util.List;

/**
 * Esta interfaz me termite establecer los metodos CRUD que tendran los muebles
 * @author luis
 */
public interface CRUDMUEBLES {

    /**
     * Este metodo me permite establecer una lista de clientes
     * @return
     */
    public List listar();

    /**
     * Este metdo me permite agregar un mueble ensamblado a la base de datos
     * @param mueble
     * @return
     * @throws SQLException
     */
    public boolean add(MuebleEnsamblado mueble) throws SQLException;

    /**
     * Este metodo me permite listar los muebles disponibles que han sido ensamblados
     * @return
     */
    public List listarMueble();

    /**
     * Este metodo me permite hacer una actualizacion de un mueble ensablado para poder cambiarle su estado a travez del identificador
     * @param identificador
     * @return
     */
    public boolean upgrade(String identificador);
}
