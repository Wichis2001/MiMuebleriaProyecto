/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;
import Codigos.GeneradorSerie;
import CompraVenta.Cliente;
import CompraVenta.Venta;
import Mueble.MuebleEnsamblado;
import Mysql.Conexion;
import Mysql.modelos.ClienteDAO;
import Mysql.modelos.ConstruirMuebleDAO;
import Mysql.modelos.VentaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este servlet me permite establecer una comunicacion entre mi jsp de creacion de muebles y mis clases Java
 * @author luis
 */
@WebServlet(name = "ServletVenta", urlPatterns = {"/areaPuntosdeVenta/Servlet-Venta"})
public class ServletVenta extends HttpServlet {
    //Definimos nuestras variables y constantes
    String vender="venta/venta.jsp";
    String crearCliente="venta/cliente.jsp";
    ClienteDAO cdao= new ClienteDAO();
    ConstruirMuebleDAO mdao=new ConstruirMuebleDAO();
    Cliente c=new Cliente();
    MuebleEnsamblado mueble= new MuebleEnsamblado();
    ArrayList<Venta>listVenta=new ArrayList<>();
    int item;
    String idMueble;
    String descripcion;
    double precio;
    double subtotal;
    double totalaPagar;
    String idenuso;
    boolean agregar=false;
    Venta venta=new Venta();
    String numeroSerie;
    String inicio="venta/gracias.jsp";
    VentaDAO vdao= new VentaDAO();
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
        //Establecemos las acciones que tomara el usuario y enviamos las paginas a donde redirigemos al usuario
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("vender")){
            //Nos dirigimos a la pestaña de vender y le asignamos un número de serie
            numeroSerie=vdao.GenerarSerie();
            if(numeroSerie==null){
                //Si no existe el dato le asignamos un numero de serie
                numeroSerie="00000001";
                request.setAttribute("nserie", numeroSerie);
            } else {
                //Asignamos numero de serie a travez de nuestra clase generador de serie
                int incrementar=Integer.parseInt(numeroSerie);
                GeneradorSerie gs= new GeneradorSerie();
                numeroSerie=gs.numeroSerie(incrementar);
                request.setAttribute("nserie", numeroSerie);
            }
            acceso=vender;
        }else if(action.equalsIgnoreCase("Eliminar")){
            //Eliminamos una opcion de nuestro carrito de compras
            item=item-1;
            String lugar=request.getParameter("lugar");
            //Ubicamos el lugar donde tenemos almacenado el mueble a eliminar
            for (int i = 0; i < listVenta.size(); i++) {
                if(listVenta.get(i).getIdmueble().equals(lugar)){
                    listVenta.remove(i);
                    //Reorganizamos nuestra lista de compras
                    for (int j =i; j < listVenta.size(); j++) {
                        listVenta.get(j).setItem(listVenta.get(j).getItem()-1);
                    }
                }
            }
            //Asignamos nuestras constantes a las variables de texto dentro de nuestra pagina web
            request.setAttribute("c", c);
            request.setAttribute("lista", listVenta);
            totalaPagar=0.0;
            for (int i = 0; i < listVenta.size(); i++) {
                totalaPagar=totalaPagar+listVenta.get(i).getSubtotal();
            }
            request.setAttribute("totalpagar", totalaPagar);
            numeroSerie=vdao.GenerarSerie();
            if(numeroSerie==null){
                numeroSerie="00000001";
                request.setAttribute("nserie", numeroSerie);
            } else {
                int incrementar=Integer.parseInt(numeroSerie);
                GeneradorSerie gs= new GeneradorSerie();
                numeroSerie=gs.numeroSerie(incrementar);
                request.setAttribute("nserie", numeroSerie);
            }
            acceso=vender;
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
    
    /**
     * El metodo Post se encargara del manejo de mi JSP de ventas y la creacion de un nuevo cliente
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu=request.getParameter("menu");
        String accion=request.getParameter("accion");
        if(menu.equalsIgnoreCase("NuevaVenta")){
            switch(accion){
            case "BuscarCliente":
                try{
                    //Recogemos el parametro de nit de cliente y procedemos a encontrarlo
                    String nit=request.getParameter("nitcliente");
                    //Si este existe procedemos a hacer la busqueda
                    if(cdao.encontrado(nit)==true){
                        c.setNit(nit);
                        c=cdao.buscar(nit);
                        request.setAttribute("c", c);
                        numeroSerie=vdao.GenerarSerie();
                        //Asignamos un número de serie
                        if(numeroSerie==null){
                            numeroSerie="00000001";
                            request.setAttribute("nserie", numeroSerie);
                        } else {
                            int incrementar=Integer.parseInt(numeroSerie);
                            GeneradorSerie gs= new GeneradorSerie();
                            numeroSerie=gs.numeroSerie(incrementar);
                            request.setAttribute("nserie", numeroSerie);
                        }
                        //Volvemos a recargar la pagina
                        accion=vender;
                    } else{
                        //Si en caso no se encuentra el cliente procedemos a crear un cliente nuevo
                        accion=crearCliente;
                    }
                }  catch(Exception e){
                    System.err.print(e);
                }
                break;
            case "BuscarMueble":
                try{
                    //Procedemos a recoger el mueble y a listar dicho mueble
                    String id=request.getParameter("valor");
                    mueble=mdao.listarID(id);
                    request.setAttribute("c", c);
                    request.setAttribute("m", mueble);
                    request.setAttribute("lista", listVenta);
                    request.setAttribute("totalpagar", totalaPagar);
                    //Volvemos a llenar nuestro frontend con las variables que ya tenemos definidas
                    numeroSerie=vdao.GenerarSerie();
                    if(numeroSerie==null){
                        numeroSerie="00000001";
                        request.setAttribute("nserie", numeroSerie);
                    } else {
                        int incrementar=Integer.parseInt(numeroSerie);
                        GeneradorSerie gs= new GeneradorSerie();
                        numeroSerie=gs.numeroSerie(incrementar);
                        request.setAttribute("nserie", numeroSerie);
                    }
                    //Recargamos la página
                    accion=vender;
                } catch(Exception e){
                    System.err.print(e);
                }
                break;
            case "Agregar":
                //Esto nos permite agregar una nueva venta a travez de los parametros
                try{
                    request.setAttribute("c", c);
                    totalaPagar=0.0;
                    idMueble=mueble.getIdentificador_mueble();
                    //Asinamos las constantes
                    descripcion=request.getParameter("nombreMueble");
                    precio=Double.parseDouble(request.getParameter("precio"));
                    subtotal=precio;
                    for (int x = 0; x < listVenta.size(); x++) {
                        if(idMueble.equalsIgnoreCase(listVenta.get(x).getIdmueble())){
                            agregar=true;
                        }
                    }
                } catch(Exception e){
                    
                }
                //Verificamos que el objeto no haya sido agregado al carrito
                if(agregar==false){
                    try{
                        //Agregamos parametros para la venta
                        item=item+1;
                        venta=new Venta();
                        venta.setItem(item);
                        venta.setIdmueble(idMueble);
                        venta.setDescripcion(descripcion);
                        venta.setTotal(precio);
                        venta.setSubtotal(subtotal);
                        listVenta.add(venta);
                        //Modificamos el total a pagar
                        for (int i = 0; i < listVenta.size(); i++) {
                            totalaPagar=totalaPagar+listVenta.get(i).getSubtotal();
                        }
                        //Agregamos estos valores al formulario de la pagina web
                        request.setAttribute("totalpagar", totalaPagar);
                        request.setAttribute("lista", listVenta);
                        numeroSerie=vdao.GenerarSerie();
                        //Asignamos el numero de serie
                        if(numeroSerie==null){
                            numeroSerie="00000001";
                            request.setAttribute("nserie", numeroSerie);
                        } else {
                            int incrementar=Integer.parseInt(numeroSerie);
                            GeneradorSerie gs= new GeneradorSerie();
                            numeroSerie=gs.numeroSerie(incrementar);
                            request.setAttribute("nserie", numeroSerie);
                        }
                        //Recargamos la pagina
                        accion=vender; 
                    } catch(Exception e){
                        System.err.print(e);
                    }
                } else {
                    //Si en caso este llega a existe unicamente volvemos a asignar los parametros
                    try{
                        for (int i = 0; i < listVenta.size(); i++) {
                        totalaPagar=totalaPagar+listVenta.get(i).getSubtotal();
                        }
                        request.setAttribute("lista", listVenta);
                        request.setAttribute("totalpagar", totalaPagar);
                        numeroSerie=vdao.GenerarSerie();
                        if(numeroSerie==null){
                            numeroSerie="00000001";
                            request.setAttribute("nserie", numeroSerie);
                        } else {
                            int incrementar=Integer.parseInt(numeroSerie);
                            GeneradorSerie gs= new GeneradorSerie();
                            numeroSerie=gs.numeroSerie(incrementar);
                            request.setAttribute("nserie", numeroSerie);
                        }
                        //Volmemos a recargar la pagina
                        accion=vender;
                    } catch (Exception e){
                        System.err.print(e);
                    }
                } 
                break;
            case "Generar Venta":
                //Recorremos nuestra lista y actualizamos sus estados a vendidos
                try{
                    for (int i = 0; i < listVenta.size(); i++) {
                        String idmueble=listVenta.get(i).getIdmueble();
                        mdao.actualizarEstadoMueble(idmueble);
                    }
                    //Agregamos los parametros de la venta
                    venta.setMonto(totalaPagar);
                    venta.setFecha_compra(LocalDate.now());
                    venta.setNit_cliente(c.getNit());
                    venta.setUsuario_vendedor(ServletUsuario.nombreRecurrente);
                    venta.setNumero_serie(numeroSerie);
                    //Guardamos la venta
                    vdao.guardarVenta(venta);
                    int idv=Integer.parseInt(vdao.idVentas());
                    for (int i = 0; i < listVenta.size(); i++) {
                        //Asignamos parametros y volvemos a guardar el detalle de la venta a travez de los muebles
                        venta=new Venta();
                        venta.setId_venta(idv);
                        venta.setIdmueble(listVenta.get(i).getIdmueble());
                        venta.setTotal(listVenta.get(i).getTotal());
                        vdao.guardarDetalledelaVenta(venta);
                    }
                    //Volvemos a recargar la pagina
                    accion=vender;
                } catch(Exception e){
                    request.setAttribute("msje", "Error al vender" + e.getMessage());
                }
                break;

            case "Cancelar":
                //Reiniciamos las variables y recargamos la pagina
                totalaPagar=0.0;
                listVenta.removeAll(listVenta);
                item=0;
                accion=vender;
                break;
            default:
            }  
        } else if(menu.equalsIgnoreCase("NuevoCliente")){
            switch(accion){
                case "Agregar":
                try{
                    //Recogemos los parametros para poder asignar un cliente
                    String nit=request.getParameter("txtNIT");
                    String nombre=request.getParameter("txtNombre");
                    String direccion=request.getParameter("txtDireccion");
                    String municipio=request.getParameter("txtMunicipio");
                    String departamento=request.getParameter("txtDepartamento");
                    //Verificamos que esta piesa no exista para poder crear una nueva
                    c.setNit(nit);
                    c.setNombre(nombre);
                    c.setDireccion(direccion);
                    c.setMunicipio(municipio);
                    c.setDepartamento(departamento);
                    cdao.add(c);
                    numeroSerie=vdao.GenerarSerie();
                    if(numeroSerie==null){
                        numeroSerie="00000001";
                        request.setAttribute("nserie", numeroSerie);
                    } else {
                        int incrementar=Integer.parseInt(numeroSerie);
                        GeneradorSerie gs= new GeneradorSerie();
                        numeroSerie=gs.numeroSerie(incrementar);
                        request.setAttribute("nserie", numeroSerie);
                    }
                    accion=vender;                     
                    //Error format exceptcion al crear la pieza
                } catch (NumberFormatException e){
                    System.err.print(e);
                    request.setAttribute("msje", "Error al agregar Cliente" + e.getMessage());
                }   
                break;
            default:
                
            }  
        }
        RequestDispatcher vista=request.getRequestDispatcher(accion);
        vista.forward(request, response);
    }
}