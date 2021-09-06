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

    /**
     *El metodo get me permite establecer un comunicador entre mi pagina web y el usuario recogiendo la accion que tomara el usuario
     */
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
    
    /**
     * El metodo get me permite establecer un comunicador entre mi pagina web y el usuario recogiendo la accion que tomara el usuario
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        //Dependiendo de la accion nos redirigiremos a ese path y establecemos los parametros a tomar en cuenta
        if(action.equalsIgnoreCase("listar")){
            //Nos redirigimos al path de listar
            acceso=listar;
        } else if(action.equalsIgnoreCase("crearm")){
            //Nos redirigimos al path esblecido en crear
            acceso=crear;
        } else if(action.equalsIgnoreCase("Crear Mueble")){
            try{
                //Extraemos los parametros
                String nombre=request.getParameter("nombre");
                Double precio=Double.parseDouble(request.getParameter("precio"));
                //Verificamos que el mueble no se haya creado previamente
                if(dao.verificar(nombre)==true){
                    //Nos redirigimos al path de errorm
                    acceso=errormr;
                } else {
                    //Creamos un nuevo mueble y lo enviamos al dao
                    mueble.setNombre_mueble_ensamble(nombre);
                    mueble.setPrecio(precio);
                    dao.addMueble(mueble);
                    nombreMueble= nombre;
                    //Nos redirigimos a crear ensamblaje de piezas;
                    acceso=crearp;
                }
            } catch(NumberFormatException e){
                //error numerico por lo cual nos redirigimos al path establecido en errormn
                acceso=errormn;
            } catch(Exception e){
                System.err.print(e);
            }
        } else if(action.equalsIgnoreCase("crearem")){
            //Nos redirigimos al path de crear p
            acceso=crearp;
        } else if(action.equalsIgnoreCase("Establecer Ensamblaje")){
            try{
                //Extraemos los parametros
                String tipo=request.getParameter("tipo");
                int cantidad=Integer.parseInt(request.getParameter("cantidad"));
                //Verificamos que la instruccion no se haya creado previamente
                if(dao.verificarPieza(tipo)==true){
                    acceso=errop;
                } else {
                    //Creamos un nuevo objeto de ensamblaje pieza y lo agregamos al dao
                    ensamblaje.setPieza_tipo(tipo);
                    ensamblaje.setCantidad(cantidad);
                    dao.addPieza(ensamblaje);
                    //Nos redirigimos a crearp
                    acceso= crearp;
                }
            } catch(NumberFormatException e){
                //error numerico por lo cual nos redirigimos al path establecido en errorpn
                acceso=errorpn;
            } catch(Exception e){
                System.err.print(e);
            }
        } else if(action.equalsIgnoreCase("Terminar Ensamblaje")){
            //Nos redirigimos a listar
            acceso=listar;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }  
}
