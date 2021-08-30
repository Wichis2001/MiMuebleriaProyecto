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
 *
 * @author luis
 */
public interface CRUDMUEBLES {
    public List listar();
    public boolean add(MuebleEnsamblado mueble) throws SQLException;
    public List listarMueble();
    public boolean upgrade(String identificador);
}
