/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Mysql.Conexion;
import Mysql.modelos.UsuarioFDAO;
import Trabajadores.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletUsuariosF", urlPatterns = {"/areaFinanciera/Servlet-Usuarios"})
public class ServletUsuariosF extends HttpServlet {
    UsuarioFDAO dao= new UsuarioFDAO(); 
    String listar="usuarios/manejoUsuario.jsp";
    String error="usuarios/error.jsp";
    String gracias="usuarios/gracias.jsp";
    String crear="usuarios/crearUsuario.jsp";
    Usuario usuario=new Usuario();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } if(action.equalsIgnoreCase("eliminar")){
            try{
                String nombre=request.getParameter("nombre");
                dao.delete(nombre);
                acceso=listar;
            } catch (Exception e){
                request.setAttribute("msje", "Error al eliminar usuario" + e.getMessage());
            } 
        } else if(action.equalsIgnoreCase("inicio")){
            Conexion conexion=new Conexion();
            try {
                conexion.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(ServletPieza.class.getName()).log(Level.SEVERE, null, ex);
            }
            acceso=gracias;
        }else if(action.equalsIgnoreCase("Crear Usuario")){
            try {
                if(request.getParameter("area").equals("1")||request.getParameter("area").equals("2")||request.getParameter("area").equals("3")){
                    usuario.setNombre_usuario(request.getParameter("nombre"));
                    usuario.setContraseña(request.getParameter("contraseña"));
                    usuario.setTipo_usuario(Integer.parseInt(request.getParameter("area")));
                    dao.add(usuario);
                    acceso=listar;
                } else {
                    acceso=error;
                }
                
            } catch (NumberFormatException e) {
                request.setAttribute("msje", "Error al Crear usuario" + e.getMessage());
            } catch (NullPointerException e) {
                request.setAttribute("msje", "Error al Crear usuario" + e.getMessage());
            } catch(Exception e){
                request.setAttribute("msje", "Error al Crear usuario" + e.getMessage());
            }
        }else if(action.equalsIgnoreCase("crear")){
            acceso=crear;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    

}
