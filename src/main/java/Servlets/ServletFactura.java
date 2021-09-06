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
 * La clase de servletcactura se encargara de manejar las devoluciones de un cliente y asignarles todos los parametros de un dao y asignarlo a la base de datos
 * @author luis
 */
@WebServlet(name = "ServletFactura", urlPatterns = {"/areaPuntosdeVenta/Servlet-Factura"})
public class ServletFactura extends HttpServlet {

    /**
     * Esta variable me devuleve el numero de venta de una factura
     */
    public static int noVenta;

    /**
     * Esta variable me devuelve el numero de factura de una vafiable
     */
    public static String factura;

    /**
     * Esta variable me devuelve el identificador de un mueble 
     */
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
        //Establecemos nuestras acciones y nuestra respuesta hacia donde se rediccionara la pagina al momento de ralizar la accion
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("devolver")){
            //Nos redirigimos al path establecido en devolver
            acceso=devolver;
        } else if(action.equalsIgnoreCase("Optar Devolución")){
            //Recolectamos los valoor
            int noFactura=Integer.parseInt(request.getParameter("txtfactura"));
            factura=generador.numeroSerieFactura(noFactura);
            valorFactura=generador.numeroSerieFactura(noFactura);
            if(dao.venta(valorFactura)==0){
                //Nos redirigimos a error dato dado que el valor es inexistente
                acceso=errordato;
            } else{
                //Corroboramos que la fecha en la que se reallizo la compra pueda optar por la devolucion
                if(dao.corroborarFecha(valorFactura)!=false){
                    //Extraemos los parametros para obtener el numero de venta
                    venta=dao.ventaValor(valorFactura);
                    noVenta=venta.getId_venta();
                    //Nos redirigmos a listar
                    acceso=listar;
                } else{
                    //Nos redirigimos al path de error fecha
                    acceso=errorfecha;
                }
            }   
        } else if(action.equalsIgnoreCase("devolucion")){
            //Extraemos los parametros necesaios
            valorMueble=request.getParameter("id");
            noMueble=valorMueble;   
            mueble=dao.verificarEstadoMueble(noMueble);
            //Verificamos que el estado del objeto sea 4
            if(mueble.getEstado()==4){
                try{
                    //Asignamos los parametros
                    devolucion.setFecha_devolucion(LocalDate.now());
                    devolucion.setPerdida(mueble.getCosto_construccion()/3);
                    devolucion.setNo_factura(factura);
                    dao.add(devolucion);
                    mdao.actualizarEstadoMuebleDevuelto(noMueble);
                    //Nos redirigimos a listar
                    acceso=listar;
                } catch(Exception e){
                    System.err.print(e);
                }
            } else{
                //Nos redirigimos a error mueble
                acceso=errormueble;
            }     
        } else if(action.equalsIgnoreCase("listar")){
            //Nos redirigimos a listar
            acceso=listar;
        } 
        
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}
