/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajadores;
import java.io.Serializable;

/**
 * Esta clase me permite la creación de un nuevo usuario, esto con la finalidad de poder emplearlo tanto en la creacion en la base de datos, como su empleacion en la pagina web 
 * @author luis
 */
public class Usuario implements Serializable{
    private String nombre_usuario;
    private String contraseña;
    private int tipo_usuario;

    /**
     * Constructor vacio que me permite la creacion de un nuevo usuario sin la necesidad de parametros
     */
    public Usuario() {
    }

    /**
     * Este constructor me permite la creación de un nuevo usuario a travez de la carga de datos
     * @param nombre_usuario
     * @param contraseña
     * @param tipo_usuario
     */
    public Usuario(String nombre_usuario, String contraseña, int tipo_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.contraseña = contraseña;
        this.tipo_usuario = tipo_usuario;
    }

    /**
     * Este metodo me devuelve el nombre del usuario
     * @return
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * Este metodo me permite cambiar el nombre del usuario
     * @param nombre_usuario
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * Este metodo me devuelve la contraseña del usuario
     * @return
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Este metodo me permite cambiar la contraseña de un usuario
     * @param contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Este metodo me devuelve el tipo del usuario
     * @return
     */
    public int getTipo_usuario() {
        return tipo_usuario;
    }

    /**
     * Este metodo me permite cambiar el tipo de usuario
     * @param tipo_usuario
     */
    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
}
