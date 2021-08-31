/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Codigos.GeneradorCodigos;
import Mueble.MuebleEnsamblado;
import Mueble.Pieza;
import Mysql.modelos.ConstruirMuebleDAO;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este servlet me permite establecer una comunicacion entre mi jsp de creacion de muebles y mis clases Java
 * @author luis
 */
@WebServlet(name = "ServletMueble", urlPatterns = {"/areaFabrica/Servlet-Mueble"})
public class ServletMueble extends HttpServlet {
    String listar="muebles/muebles-listar.jsp";
    String listar2="muebles/muebles-ventas.jsp";
    ConstruirMuebleDAO dao= new ConstruirMuebleDAO();
    
    /**
     * El metodo do get me permite establecer un comunicador entre mi pagina web y el usuario recogiendo la accion que tomara el usuario
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Establecemos las acciones que tomara el usuario y enviamos las paginas a donde redirigemos al usuario
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } else if (action.equalsIgnoreCase("construir")){
            try{
                //Establecemos los parabetros para poder agregar un nuevo mueble ensamblado
                String tipo=request.getParameter("tipo");
                Double precio=Double.parseDouble(request.getParameter("precio"));
                MuebleEnsamblado mueble=new MuebleEnsamblado(LocalDate.now(),precio,2,ServletUsuario.nombreRecurrente,tipo);
                dao.add(mueble);
                acceso=listar;
                //Error al momento de crear una pieza
            } catch (Exception e){ 
                request.setAttribute("msje", "Error al crear mueble" + e.getMessage());
            } 
        } else if(action.equalsIgnoreCase("listar2")){
            acceso=listar2;
        } else if (action.equalsIgnoreCase("update")){
            try{
                //Asignamos el parametro de Id para poder hacer la actualizacion del mueble ensamblado
                String identificador=request.getParameter("id");
                dao.upgrade(identificador);
                acceso=listar2;
            } catch (Exception e){ 
                request.setAttribute("msje", "Error al actualizar mueble" + e.getMessage());
            } 
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }


}
