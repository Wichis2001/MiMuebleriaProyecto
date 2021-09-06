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
 * Este Servlet me ayuda a poder designar cada uno de los metodos que tendra el manejo de mis reportes para su empleacion en el jsp de repotes
 * @author luis
 */
@WebServlet(name = "ServletReportes", urlPatterns = {"/areaFinanciera/Servlet-Reportes"})
public class ServletReportes extends HttpServlet {
//Establecemos nuestros paths
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

    /**
     * Definimos esta variable estatica que tendra el campo del nombre de usuario
     */
    public static String nombreUsuario;

    /**
     *Definimos esta variable fecha inicio para definir los parametros en los que se movera la fecha
     */
    public static Date fechaInicio;
    
    /**
     *Definimos esta variable fecha final para definir los parametros en los que se movera la fecha
     */
    public static Date fechaFinal;    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String fecha1;
    String fecha2;

    /**
     * El metodo get me ayudara a extraaer cada uno de los atributos necesarios para poder ejecutar cualquier metodo a travez de las acciones que se realicen en los jsp
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
        if(action.equalsIgnoreCase("venta")){
            //Nos redirigimos al path de ventas
            acceso=ventas;
        } else if(action.equalsIgnoreCase("Consultar Venta")){
            //Establecemos los parametros
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            //Definimos si las fechas son nulas para no tomarlas en cuenta
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                //Le damos un formato a las fechas
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //Nos redirigimos al path de ventas reporte
            acceso=ventasreporte;
        } else if(action.equalsIgnoreCase("devolucion")){
            //Nos redirigimo al path de devolucion
            acceso=devolucion;
        } else if(action.equalsIgnoreCase("Consultar Devolucion")){
            //Extraemos los parametros de los campos
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            //Verificamos si estos son nulos para no tomarlos en cuenta
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                //Establecemos un formato a las fechas
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
            //Nos redirigimos al path de ganancias
            acceso=ganancia;
        } else if(action.equalsIgnoreCase("Consultar Ganancia")){
            //Extraemos los parametros de los campos
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                //Verificamos si estos son nulos para no tomarlos en cuenta
                fechaInicio=null;
                fechaFinal=null;
            } else{
                //Establecemos un formato a las fechas
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //Nos rederigimos al path de reporte de ganancia
            acceso=gananciaReporte;
        } else if(action.equalsIgnoreCase("usuariov")){
            //Nos rederigimos al path de usuariosv 
            acceso=usuarioventa;
            //Extraemos los parametros de los campos
        } else if(action.equalsIgnoreCase("Consultar Usuario")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
             //Verificamos si estos son nulos para no tomarlos en cuenta
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                 //Establecemos un formato a las fechas
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //Nos rederigimos al reporte de usaurioganancia
            acceso=usuarioventaReporte;
        } else if(action.equalsIgnoreCase("usuariog")){
            //nos rederigimos al path que esta establecido en ganancia
            acceso=ganancias;
        } else if(action.equalsIgnoreCase("Consultar Usuario Ganancia")){
            //Extraemos los parametros de los campos
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            //Verificamos si estos son nulos para no tomarlos en cuenta
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                //Establecemos un formato a las fechas
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //Nos redirigimos al path de ganancia reporte
            acceso=gananciasReporte;
        } else if(action.equalsIgnoreCase("mmas")){
            //Nos rederigimos al path establecido en mumas
            acceso=mumas;
        } else if(action.equalsIgnoreCase("Consultar Mueble")){
            //Extraemos los parametros de los campos
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            //Verificamos si estos son nulos para no tomarlos en cuenta
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                //Establecemos un formato a las fechas
                try {
                    //Extraemos los parametros de los campos
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //Nos rederigimos al path de mumasReporte
            acceso=mumasReporte;
        } else if(action.equalsIgnoreCase("mmenos")){
            // Nos redirigimos al path de mume
            acceso=mume;
        } else if(action.equalsIgnoreCase("Consultar Mueble Vendido")){
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                fechaInicio=null;
                fechaFinal=null;
            } else{
                //Establecemos un formato a las fechas
                try {
                    java.util.Date nfecha= formatter.parse(fecha1);
                    java.util.Date nfecha2= formatter.parse(fecha2);
                    fechaInicio= new java.sql.Date(nfecha.getTime());
                    fechaFinal=new java.sql.Date(nfecha2.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ServletCounstaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //Nos rederigimos al path de mumeReporte 
            acceso=mumeReporte;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

  

}
