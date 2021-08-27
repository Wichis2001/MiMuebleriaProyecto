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
 *
 * @author luis
 */
@WebServlet(name = "ServletMueble", urlPatterns = {"/areaFabrica/Servlet-Mueble"})
public class ServletMueble extends HttpServlet {
    String listar="muebles/muebles-listar.jsp";
    ConstruirMuebleDAO dao= new ConstruirMuebleDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } else if (action.equalsIgnoreCase("construir")){
            try{
                String tipo=request.getParameter("tipo");
                Double precio=Double.parseDouble(request.getParameter("precio"));
                MuebleEnsamblado mueble=new MuebleEnsamblado(LocalDate.now(),precio,2,ServletUsuario.nombreRecurrente,tipo);
                dao.add(mueble);
                acceso=listar;
            } catch (Exception e){ 
                request.setAttribute("msje", "Error al crear pieza" + e.getMessage());
            } 
        }
        
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }


}
