/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Codigos.GeneradorSerie;
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
 * Esta clase me ayuda a manejar todas las consultas que se podran extraer desde la ventana de area de puntos de venta y poder representarlos en un jsp
 * @author luis
 */
@WebServlet(name = "ServletCounstaVenta", urlPatterns = {"/areaPuntosdeVenta/Servlet-CounstaVenta"})
public class ServletCounstaVenta extends HttpServlet {
    String ingresoCompras="consultas/consulta-comopras-cliente-datos.jsp";
    String reporteCompras="consultas/tabla-consulta-compras-cliente.jsp";
    String ingresoDevoluciones="consultas/consulta-devoluciones-cliente.jsp";
    String reporteDevoluciones="consultas/tabla-devoluciones-cliente.jsp";
    String mueble="consultas/tablaMueble.jsp";
    String factura="consultas/consulta-factura.jsp";
    String tablaFactura="consultas/tablaFactura.jsp";
    String ventasInicio="consultas/tabla-ventas-dia.jsp";

    /**
     * Este variable me define el nombre de un cliente
     */
    public static String nombreCliente;

    /**
     * Esta variable me permite establecer la fecha de inicio
     */
    public static Date fechaInicio;

    /**
     * Esta variable me permite establecer la fecha final
     */
    public static Date fechaFinal;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String fecha1;
    String fecha2;
    int numeroFactura;

    /**
     * Este netodo me ayuda a determinar cual sera el numero de factura final que sera el que en realidad tomaremos en cuenta
     */
    public static String numeroFacturaFinal;

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
        //Definimos los puntos de acceso y mandamos el acceso al cual se redigira
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("ingresoaCompra")){
            //Nos redirigimos al path establecido en ingreso compra
            acceso=ingresoCompras;
        } else if(action.equalsIgnoreCase("Buscar ClienteC")){
            //Extraemos los parametros de las fechas
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            if(fecha1.equals("")||fecha2.equals("")){
                //Verificamos si las fechas no son nulas para no no tomarlas en cuenta
                nombreCliente=request.getParameter("txtNombre");
                fechaInicio=null;
                fechaFinal=null;
            } else{
                //Le damos formato a las fechas
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
            //Nos redirigimos al reporte de las comparas
            acceso=reporteCompras;
        } else if(action.equalsIgnoreCase("DevolucionC")){
            //Nos redirigimos al ingreso de las devouciones
            acceso=ingresoDevoluciones;
        } else if(action.equalsIgnoreCase("Consultar Devolucion")){
            //Extraemos los parametros de las fechas
            fecha1=request.getParameter("fechaI");
            fecha2=request.getParameter("fechaF");
            //Verificamos si las fechas no son nulas para no no tomarlas en cuenta
            if(fecha1.equals("")||fecha2.equals("")){
                nombreCliente=request.getParameter("txtNombre");
                fechaInicio=null;
                fechaFinal=null;
            } else{
                //Le damos formato a las fechas
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
            //Nos redirigimos al reporte de devoluciones
            acceso=reporteDevoluciones;
        } else if(action.equalsIgnoreCase("mueble")){
            //Nos redirigimos al path establecido en mueble
            acceso=mueble;
        } else if(action.equalsIgnoreCase("factura")){
            //Nos redirigimos al path establecido en factura
            acceso=factura;
        } else if(action.equalsIgnoreCase("Buscar Factura")){
            //Extraemos los campos y asignamos un nuevo numero de factura
            numeroFactura=Integer.parseInt(request.getParameter("txtFactura"));
            GeneradorSerie generador= new GeneradorSerie();
            numeroFacturaFinal=generador.numeroSerieFactura(numeroFactura);
            //Nos redirigmos a la tabla factura
            acceso=tablaFactura;
        } else if(action.equalsIgnoreCase("ventas")){
            //Nos redirigomos a la tabla de ventana de inicio
            acceso=ventasInicio;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}