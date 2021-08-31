/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;
import Mueble.Pieza;
import Mysql.modelos.PiezaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este Servlet me permite establecer un comunicador entre java y la pagina web esperando acciones ue haga el usuario
 * @author luis
 */
@WebServlet(name = "ServletConsultasFabrica", urlPatterns = {"/areaFabrica/Servlet-Consulta"})
public class ServletConsultasFabrica extends HttpServlet {
    //Establecemos constantes
    String listar="consultas/consulta-piezas.jsp";
    String listar2="consultas/consulta-piezas-mayor.jsp";
    String listar3="consultas/consulta-piezas-menor.jsp";
    String listar4="consultas/consulta-muebles.jsp";
    String listar5="consultas/consulta-muebles-mayor.jsp";
    String listar6="consultas/consulta-muebles-menor.jsp";
    Pieza pieza =new Pieza();
    PiezaDAO dao= new PiezaDAO();

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
        String acceso="";
        //Definimos los puntos de acceso y mandamos el acceso al cual se redigira
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } else if(action.equalsIgnoreCase("listar2")){
            acceso=listar2;
        } else if(action.equalsIgnoreCase("listar3")){
            acceso=listar3;
        } else if(action.equalsIgnoreCase("listar4")){
            acceso=listar4;
        } else if(action.equalsIgnoreCase("listar5")){
            acceso=listar5;
        } else if(action.equalsIgnoreCase("listar6")){
            acceso=listar6;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}