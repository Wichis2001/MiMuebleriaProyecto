/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Mueble.Pieza;
import Mysql.modelos.PiezaDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ServletConsultasFabrica", urlPatterns = {"/areaFabrica/Servlet-Consulta"})
public class ServletConsultasFabrica extends HttpServlet {

    String listar="consultas/consulta-piezas.jsp";
    String listar2="consultas/consulta-piezas-mayor.jsp";
    String listar3="consultas/consulta-piezas-menor.jsp";
    Pieza pieza =new Pieza();
    PiezaDAO dao= new PiezaDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } else if(action.equalsIgnoreCase("listar2")){
            acceso=listar2;
        } else if(action.equalsIgnoreCase("listar3")){
            acceso=listar3;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    
}