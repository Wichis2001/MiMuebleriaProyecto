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
 * Este Servlet me permite el manejo de mi jsp para la creacion de usuarios y poder enviar estos datos a mis DAO para poder hacer su manejo en la base de datos
 * @author luis
 */
@WebServlet(name = "ServletUsuariosF", urlPatterns = {"/areaFinanciera/Servlet-Usuarios"})
public class ServletUsuariosF extends HttpServlet {
    //Esablecemos nuestas constantes con los paths a los cuales nos redigiremos
    UsuarioFDAO dao= new UsuarioFDAO(); 
    String listar="usuarios/manejoUsuario.jsp";
    String error="usuarios/error.jsp";
    String gracias="usuarios/gracias.jsp";
    String crear="usuarios/crearUsuario.jsp";
    Usuario usuario=new Usuario();
    
    /**
     * Este metodo get me permite extraer mis datos de mis formularios de mi jsp y poder enviarlo a su respectivo DAO
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        //Establecemos la palabre clave accion a la cual usaremos para conducirnos en el jsp
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            //Nos redirigimos al path que esta denominado como listar
            acceso=listar;
        } if(action.equalsIgnoreCase("eliminar")){
            //Procedemos a eliminar un usuario de la base de datos
            try{
                String nombre=request.getParameter("nombre");
                dao.delete(nombre);
                acceso=listar;
            } catch (Exception e){
                request.setAttribute("msje", "Error al eliminar usuario" + e.getMessage());
            } 
        } else if(action.equalsIgnoreCase("inicio")){
            //Hacemos una desconexino de la base de datos
            Conexion conexion=new Conexion();
            try {
                conexion.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(ServletPieza.class.getName()).log(Level.SEVERE, null, ex);
            }
            acceso=gracias;
        }else if(action.equalsIgnoreCase("Crear Usuario")){
            //Creamos un nuevo usuario
            try {
                //Visualizamos las areas disponibles para el usuario
                if(request.getParameter("area").equals("1")||request.getParameter("area").equals("2")||request.getParameter("area").equals("3")){
                    //Asignamos los parametros
                    usuario.setNombre_usuario(request.getParameter("nombre"));
                    usuario.setContraseña(request.getParameter("contraseña"));
                    usuario.setTipo_usuario(Integer.parseInt(request.getParameter("area")));
                    dao.add(usuario);
                    acceso=listar;
                } else {
                    acceso=error;
                }
                //Error numerico
            } catch (NumberFormatException e) {
                request.setAttribute("msje", "Error al Crear usuario" + e.getMessage());
            } catch (NullPointerException e) {
                //Error de punto nulo
                request.setAttribute("msje", "Error al Crear usuario" + e.getMessage());
            } catch(Exception e){
                //Error general
                request.setAttribute("msje", "Error al Crear usuario" + e.getMessage());
            }
        }else if(action.equalsIgnoreCase("crear")){
            //Nos redirigimos al path que esta especificado en crear 
            acceso=crear;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    

}
