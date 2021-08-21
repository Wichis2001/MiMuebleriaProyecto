/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Mueble.Pieza;
import java.util.List;

/**
 *
 * @author luis
 */
public interface CRUDPieza {
    public List listar();
    public Pieza list(String tipo, double costo);
    public boolean add(Pieza pieza);
    public boolean edit(Pieza pieza);
    public boolean delete(String tipo, double costo);
}
