/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajadores;
import java.io.Serializable;

/**
 *
 * @author luis
 */
public class Usuario implements Serializable{
    private String nombre_usuario;
    private String contraseña;
    private int tipo_usuario;

    public Usuario() {
    }

    public Usuario(String nombre_usuario, String contraseña, int tipo_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.contraseña = contraseña;
        this.tipo_usuario = tipo_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
}
