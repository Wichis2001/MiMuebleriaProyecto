/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
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
@WebServlet(name = "ServletReportes", urlPatterns = {"/areaFinanciera/Servlet-Reportes"})
public class ServletReportes extends HttpServlet {
String ventas="reportes/reporte-venta.jsp";
String ventasreporte="reportes/reporte-venta-tabla.jsp";
String devolucion="reportes/reporte-devoluciones.jsp";
String devolucionreporte="reportes/reporte-devoluciones-tabla.jsp";
String ganancia="reportes/reporte-ganancias.jsp";
String gananciaReporte="reportes/reporte-ganancias-tabla.jsp";
String usuarioventa="reportes/reporte-usuarios-ventas.jsp";
String usuarioventaReporte="reportes/reporte-usuarios-ventas-tabla.jsp";
String ganancias="reportes/reporte-usuarios-ganancias.jsp";
String gananciasReporte="reportes/reporte-usuario-ganancias-tabla.jsp";
String mumas="reportes/reporte-mueble-mas.jsp";
String mumasReporte="reportes/reporte-mueble-mas-tabla.jsp";
String mume="reportes/reporte-mueble-menos.jsp";
String mumeReporte="reportes/reporte-mueble-menos-tabla.jsp";
public static String nombreUsuario;
public static Date fechaInicio;
public static Date fechaFinal;    
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String fecha1;
String fecha2;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("venta")){
            acceso=ventas;
        } else if(action.equalsIgnoreCase("Consultar Venta")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            acceso=ventasreporte;
        } else if(action.equalsIgnoreCase("devolucion")){
            acceso=devolucion;
        } else if(action.equalsIgnoreCase("Consultar Devolucion")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            acceso=devolucionreporte;
        } else if(action.equalsIgnoreCase("ganancia")){
            acceso=ganancia;
        } else if(action.equalsIgnoreCase("Consultar Ganancia")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            acceso=gananciaReporte;
        } else if(action.equalsIgnoreCase("usuariov")){
            acceso=usuarioventa;
        } else if(action.equalsIgnoreCase("Consultar Usuario")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            acceso=usuarioventaReporte;
        } else if(action.equalsIgnoreCase("usuariog")){
            acceso=ganancias;
        } else if(action.equalsIgnoreCase("Consultar Usuario Ganancia")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            acceso=gananciasReporte;
        } else if(action.equalsIgnoreCase("mmas")){
            acceso=mumas;
        } else if(action.equalsIgnoreCase("Consultar Mueble")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            acceso=mumasReporte;
        } else if(action.equalsIgnoreCase("mmenos")){
            acceso=mume;
        } else if(action.equalsIgnoreCase("Consultar Mueble Vendido")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            acceso=mumeReporte;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

  

}
