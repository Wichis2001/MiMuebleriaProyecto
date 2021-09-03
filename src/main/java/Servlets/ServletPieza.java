/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Mueble.Pieza;
import Mysql.Conexion;
import Mysql.modelos.PiezaDAO;
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
 * Esta Servlet me permite establecer una conexion entre mis jsp de piezas y poder establecer sus parametros con mis clases javas 
 * @author luis
 */
@WebServlet(name = "ServletPieza", urlPatterns = {"/areaFabrica/Servlet-Pieza"})
public class ServletPieza extends HttpServlet {
    //Establacemos constantes
    String listar="piezas/piezas-listar.jsp";
    String add="piezas/piezas-add.jsp";
    String edit="piezas/piezas-edit.jsp";
    String inicio="piezas/gracias.jsp";
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
        //Establecemos nuestras acciones y nuestra respuesta hacia donde se rediccionara la pagina al momento de ralizar la accion
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } else if(action.equalsIgnoreCase("add")){
            acceso=add;
        } else if(action.equalsIgnoreCase("Agregar")){
            try{
                //Recogemos los parametros para poder asignar una pieza
                String tipo=request.getParameter("txtTipo");
                Double precio=Double.parseDouble(request.getParameter("txtCosto"));
                int cantidad=Integer.parseInt(request.getParameter("txtCantidad"));
                //Verificamos que esta piesa no exista para poder crear una nueva
                if(dao.verificar(tipo,precio,cantidad)==false){
                    pieza.setTipo(tipo);
                    pieza.setCosto(precio);
                    pieza.setCantidad(cantidad);
                    dao.add(pieza);
                    acceso=listar;
                } else {
                    //Si esta pieza exista procedemos a actualizr la cantidad de esta pieza
                    dao.update(tipo, precio, cantidad);
                    acceso=listar;
                }  
                //Error format exceptcion al crear la pieza
            } catch (NumberFormatException e){
                System.err.print(e);
                request.setAttribute("msje", "Error al agregar pieza" + e.getMessage());
            }   
        } else if(action.equalsIgnoreCase("editar")){
            //Asignamos los parametros para poder actualizasr
            request.setAttribute(("tipopieza"), request.getParameter(("tipo")));
            request.setAttribute(("costopieza"), request.getParameter(("costo")));
            acceso=edit; 
        } else if(action.equalsIgnoreCase("Actualizar")){
            try {
                //Recogemos los parametros para poder establecer los datos consizos para poder actualizar
                String tipo=request.getParameter("txtTipo");
                Double precio=Double.parseDouble(request.getParameter("txtCosto"));
                int cantidad=Integer.parseInt(request.getParameter("txtCantidad"));
                //Construimos una nueva pieza y procedemos a recargar la pagina
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
        } else if(action.equalsIgnoreCase("inicio")){
            Conexion conexion=new Conexion();
            try {
                conexion.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(ServletPieza.class.getName()).log(Level.SEVERE, null, ex);
            }
            acceso=inicio;
        }
        
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    
}
