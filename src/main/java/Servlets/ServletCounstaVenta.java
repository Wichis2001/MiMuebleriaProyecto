/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ServletCounstaVenta", urlPatterns = {"/areaPuntosdeVenta/Servlet-CounstaVenta"})
public class ServletCounstaVenta extends HttpServlet {
    String ingresoCompras="consultas/consulta-comopras-cliente-datos.jsp";
    String reporteCompras="consultas/tabla-consulta-compras-cliente.jsp";
    public static String nombreCliente;
    public static Date fechaInicio;
    public static Date fechaFinal;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String fecha1;
    String fecha2;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        //Definimos los puntos de acceso y mandamos el acceso al cual se redigira
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("ingresoaCompra")){
            acceso=ingresoCompras;
        } else if(action.equalsIgnoreCase("Buscar ClienteC")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                nombreCliente=request.getParameter("txtNombre");
                fechaInicio=null;
                fechaFinal=null;
            } else{
                try {
                    nombreCliente=request.getParameter("txtNombre");
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            acceso=reporteCompras;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}