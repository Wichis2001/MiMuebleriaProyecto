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
 *
 * @author luis
 */
@WebServlet(name = "ServletPieza", urlPatterns = {"/areaFabrica/Servlet-Pieza"})
public class ServletPieza extends HttpServlet {

    String listar="piezas/piezas-listar.jsp";
    String add="piezas/piezas-add.jsp";
    String edit="piezas/piezas-edit.jsp";
    Pieza pieza =new Pieza();
    PiezaDAO dao= new PiezaDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } else if(action.equalsIgnoreCase("add")){
            acceso=add;
        } else if(action.equalsIgnoreCase("Agregar")){
            try{
                String tipo=request.getParameter("txtTipo");
                Double precio=Double.parseDouble(request.getParameter("txtCosto"));
                int cantidad=Integer.parseInt(request.getParameter("txtCantidad"));
                if(dao.verificar(tipo,precio,cantidad)==false){
                    pieza.setTipo(tipo);
                    pieza.setCosto(precio);
                    pieza.setCantidad(cantidad);
                    dao.add(pieza);
                    acceso=listar;
                } else {
                    dao.update(tipo, precio, cantidad);
                    acceso=listar;
                }  
            } catch (NumberFormatException e){
                System.err.print(e);
                request.setAttribute("msje", "Error al agregar pieza" + e.getMessage());
            }   
        } else if(action.equalsIgnoreCase("editar")){
            request.setAttribute(("tipopieza"), request.getParameter(("tipo")));
            request.setAttribute(("costopieza"), request.getParameter(("costo")));
            acceso=edit; 
        } else if(action.equalsIgnoreCase("Actualizar")){
            try {
                String tipo=request.getParameter("txtTipo");
                Double precio=Double.parseDouble(request.getParameter("txtCosto"));
                int cantidad=Integer.parseInt(request.getParameter("txtCantidad"));
                pieza.setTipo(tipo);
                pieza.setCosto(precio);
                pieza.setCantidad(cantidad);
                dao.edit(pieza);
                acceso=listar;
            } catch (Exception e) {
                request.setAttribute("msje", "Error al actualizar pieza" + e.getMessage());
            }
            
        } else if(action.equalsIgnoreCase("eliminar")){      
            try{
                String tipo=request.getParameter("tipo");
                Double precio=Double.parseDouble(request.getParameter("costo"));
                dao.delete(tipo, precio);
                acceso=listar;
            } catch (Exception e){
                request.setAttribute("msje", "Error al eliminar pieza" + e.getMessage());
            }        
        }
        
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    
}
