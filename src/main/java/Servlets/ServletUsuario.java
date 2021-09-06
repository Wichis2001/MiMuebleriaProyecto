/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Mysql.modelos.UsuarioDAO;
import Trabajadores.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este servlet me permite establecer una comunicacion entre la ventana de inicio sesion y mis metodos y clases java
 * @author luis
 */
public class ServletUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.El metodo do get me permite establecer un comunicador entre mi pagina web y el usuario recogiendo la accion que tomara el usuario
     * 
     */
    //Establecemos las constantes
    public static String nombreRecurrente;
    UsuarioDAO dao= new UsuarioDAO();
    Usuario usuario= new Usuario();
    int resultado=0;
    int lugar=0;
    String fabrica="areaFabrica/pagina-area-fabrica.jsp";
    String financiera="areaFinanciera/ventana-principal.jsp";
    String venta="areaPuntosdeVenta/ventana-principal.jsp";
    String inicio="inicio-sesion.jsp";

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Establecemos los parametros de accion para recoger la accion que ingresa el usuaio y poder rederigirlo a otra ventana
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("Ingresar")){
            //Recogemos los parametros del nombre y la contraseña para poder establecer el usuario a examinar
            String nom=request.getParameter("txtnom");
            String contraseña=request.getParameter("txtcontraseña");
            usuario.setNombre_usuario(nom);
            usuario.setContraseña(contraseña);
            resultado=dao.validar(usuario);
            //Determinamos el area a la que pertenece el usuario
            if(resultado==1){
                //Guardamos el parametro del usuario que esta laborando
                request.getSession().setAttribute("nom", nom);
                nombreRecurrente=nom;
                lugar=dao.correspondencia(usuario);
                if(lugar==1){
                    response.sendRedirect(fabrica);
                    lugar=0;
                } else if(lugar==2){
                    response.sendRedirect(venta);
                    lugar=0;
                }else if(lugar==3){
                    response.sendRedirect(financiera);
                    lugar=0;
                }else if(lugar==4){
                    response.sendRedirect(inicio);
                    lugar=0;
                }
            } else{
                //Si la contraseña o el usuario son incorrectos regresamos a la pestaña de inicio
                response.sendRedirect(inicio);
            }
        }
    }


}
