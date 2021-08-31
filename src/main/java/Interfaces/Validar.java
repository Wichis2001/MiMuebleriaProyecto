/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Trabajadores.Usuario;

/**
 * Esta interfaz me permite establecer los metodos que me permitiran validad un usuario a travez del inicio de sesion 
 * @author luis
 */
public interface Validar {

    /**
     * Este metodo me permite validar un usuario a travez de un usuario
     * @param usuario
     * @return
     */
    public int validar(Usuario usuario);
}
