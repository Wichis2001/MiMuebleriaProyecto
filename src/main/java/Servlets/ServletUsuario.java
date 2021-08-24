/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Mysql.modelos.UsuarioDAO;
import Trabajadores.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
public class ServletUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    UsuarioDAO dao= new UsuarioDAO();
    Usuario usuario= new Usuario();
    int resultado=0;
    int lugar=0;
    String fabrica="areaFabrica/pagina-area-fabrica.jsp";
    String financiera="areaFinanciera/ventana-principal.jsp";
    String venta="areaPuntosdeVenta/ventana-principal.jsp";
    String inicio="inicio-sesion.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("Ingresar")){
            String nom=request.getParameter("txtnom");
            String contraseña=request.getParameter("txtcontraseña");
            usuario.setNombre_usuario(nom);
            usuario.setContraseña(contraseña);
            resultado=dao.validar(usuario);
            if(resultado==1){
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
                }
                
            } else{
                response.sendRedirect(inicio);
            }
        }
    }


}
