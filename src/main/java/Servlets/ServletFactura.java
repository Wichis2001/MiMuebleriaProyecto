/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;
import Mysql.modelos.*;
import Codigos.GeneradorSerie;
import CompraVenta.Devolucion;
import CompraVenta.Venta;
import Mueble.MuebleEnsamblado;
import java.io.IOException;
import java.time.LocalDate;
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
@WebServlet(name = "ServletFactura", urlPatterns = {"/areaPuntosdeVenta/Servlet-Factura"})
public class ServletFactura extends HttpServlet {
    public static int noVenta;
    public static String factura;
    public static String noMueble;
    String devolver="devolucion/devolucion.jsp";
    String listar="devolucion/tabla.jsp";
    String errordato="devolucion/error-dato.jsp";
    String errorfecha="devolucion/error-fecha.jsp";
    String errormueble="devolucion/error-mueble.jsp";
    Venta venta= new Venta();
    Devolucion devolucion= new Devolucion();
    DevolucionDAO dao= new DevolucionDAO();
    ConstruirMuebleDAO mdao=new ConstruirMuebleDAO();
    GeneradorSerie generador= new GeneradorSerie();
    String valorFactura;
    MuebleEnsamblado mueble= new MuebleEnsamblado();
    String valorMueble="";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Establecemos nuestras acciones y nuestra respuesta hacia donde se rediccionara la pagina al momento de ralizar la accion
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("devolver")){
            acceso=devolver;
        } else if(action.equalsIgnoreCase("Optar Devolución")){
            int noFactura=Integer.parseInt(request.getParameter("txtfactura"));
            factura=generador.numeroSerieFactura(noFactura);
            valorFactura=generador.numeroSerieFactura(noFactura);
            if(dao.venta(valorFactura)==0){
                acceso=errordato;
            } else{
                if(dao.corroborarFecha(valorFactura)!=false){
                    venta=dao.ventaValor(valorFactura);
                    noVenta=venta.getId_venta();
                    acceso=listar;
                } else{
                    acceso=errorfecha;
                }
            }   
        } else if(action.equalsIgnoreCase("devolucion")){
            valorMueble=request.getParameter("id");
            noMueble=valorMueble;   
            mueble=dao.verificarEstadoMueble(noMueble);
            if(mueble.getEstado()==4){
                try{
                    devolucion.setFecha_devolucion(LocalDate.now());
                    devolucion.setPerdida(mueble.getCosto_construccion()/3);
                    devolucion.setNo_factura(factura);
                    dao.add(devolucion);
                    mdao.actualizarEstadoMuebleDevuelto(noMueble);
                    acceso=listar;
                } catch(Exception e){
                    System.err.print(e);
                }
            } else{
                acceso=errormueble;
            }     
        } else if(action.equalsIgnoreCase("listar")){
            acceso=listar;
        } 
        
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}
