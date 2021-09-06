/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Mueble.EnsamblePiezas;
import Mueble.Mueble;
import Mysql.modelos.CrearMuebleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
@WebServlet(name = "ServletMuebleF", urlPatterns = {"/areaFinanciera/Servlet-Mueble"})
public class ServletMuebleF extends HttpServlet {
    public static String nombreMueble="";
    String listar="muebles/listado.jsp";
    String crear="muebles/crear-mueble.jsp";
    String crearp="muebles/crear-piezas.jsp";
    String errormr="muebles/error-mueble-repetido.jsp";
    String errormn="muebles/error-mueble-numero.jsp";
    String errop="muebles/error-pieza-repetida.jsp";
    String errorpn="muebles/error-pieza-numero.jsp";
    CrearMuebleDAO dao= new CrearMuebleDAO();
    Mueble mueble= new Mueble();
    EnsamblePiezas ensamblaje= new EnsamblePiezas();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } else if(action.equalsIgnoreCase("crearm")){
            acceso=crear;
        } else if(action.equalsIgnoreCase("Crear Mueble")){
            try{
                String nombre=request.getParameter("nombre");
                Double precio=Double.parseDouble(request.getParameter("precio"));
                if(dao.verificar(nombre)==true){
                    acceso=errormr;
                } else {
                    mueble.setNombre_mueble_ensamble(nombre);
                    mueble.setPrecio(precio);
                    dao.addMueble(mueble);
                    nombreMueble= nombre;
                    acceso=crearp;
                }
            } catch(NumberFormatException e){
                acceso=errormn;
            } catch(Exception e){
                System.err.print(e);
            }
        } else if(action.equalsIgnoreCase("crearem")){
            acceso=crearp;
        } else if(action.equalsIgnoreCase("Establecer Ensamblaje")){
            try{
                String tipo=request.getParameter("tipo");
                int cantidad=Integer.parseInt(request.getParameter("cantidad"));
                if(dao.verificarPieza(tipo)==true){
                    acceso=errop;
                } else {
                    ensamblaje.setPieza_tipo(tipo);
                    ensamblaje.setCantidad(cantidad);
                    dao.addPieza(ensamblaje);
                    acceso= crearp;
                }
            } catch(NumberFormatException e){
                acceso=errorpn;
            } catch(Exception e){
                System.err.print(e);
            }
        } else if(action.equalsIgnoreCase("Terminar Ensamblaje")){
            acceso=listar;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }  
}
