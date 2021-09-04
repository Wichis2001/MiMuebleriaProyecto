<%-- 
    Document   : tabla-devoluciones-cliente
    Created on : 4/09/2021, 03:34:23 PM
    Author     : luis
--%>

<%@page import="CompraVenta.Reporte"%>
<%@page import="CompraVenta.Cliente"%>
<%@page import="CompraVenta.Venta"%>
<%@page import="Mueble.MuebleEnsamblado"%>
<%@page import="java.util.Iterator"%>
<%@page import="Mueble.Pieza"%>
<%@page import="Mysql.modelos.*"%>
<%@page import="Mueble.Mueble"%>
<%@page import="Mysql.modelos.*"%>
<%@page import="java.util.List"%>
<%@page import="Servlets.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond" rel="stylesheet">
        <link rel="stylesheet" href="./../resources/libraries/aos/aos.css"/>
        <link rel="stylesheet" href="./../resources/libraries/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="./../resources/css/banner-general.css"/>
        <link rel="stylesheet" href="./../resources/css/keyframes.css"/>
        <link href="./../resources/libraries/sweetalert/sweetalert.css" rel="stylesheet" type="text/css"/>
        <link href="./../resources/libraries/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="./../resources/css/style-general.css"/>
        <title>Consultas Ventas</title>
    </head>
    
    <body>
        <header>
            <nav class="ewk_navbar navbar navbar-expand-lg navbar-light bg-light fixed-top">
              <div class="container-fluid">
                <a class="navbar-brand" href="../areaPuntosdeVenta/ventana-principal.jsp">Área de Punto de Venta</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="../areaPuntosdeVenta/ventana-principal.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="Servlet-Venta?accion=vender">Ventas</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="Servlet-Factura?accion=devolver">Devoluciones</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Consultas
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Consulta de Compras</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Devoluciones</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Muebles</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Facturas</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Ventas</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </nav>
        </header>
            <section>
                <div class="ewk_cont_banner">
                    <div class="ewk_sombra">
                        <h1 class="text-center">Consulta de Devoluciones de un Cliente</h1>
                        <a class="btn btn-success" href="Servlet-CounstaVenta?accion=DevolucionC">Regresar</a>
                        <br>
                        <br>
                        <table class="table table-bordered"border="1">
                        <thead>
                    <tr>
                        <th class="text-center">No.</th>
                        <th class="text-center">NÚMERO FACTURA</th>
                        <th class="text-center">FECHA DEVOLUCION</th>
                        <th class="text-center">NOMBRE</th>
                        <th class="text-center">NIT</th>
                        <th class="text-center">NOMBRE MUEBLE</th>
                        <th class="text-center">PERDIDA</th>
                    </tr>
                    </thead>
                    <% 
                        //Creamos un nuevo dao de listar mueble para poder agregar todos nuestros objetos de mueble dentro de nuestra tabla y poder ordenarlos de mayor a menor
                        int i=0;
                        ConstultaVentasDAO dao= new ConstultaVentasDAO();
                        List<Reporte>list=dao.Devolucion(ServletCounstaVenta.nombreCliente,ServletCounstaVenta.fechaInicio,ServletCounstaVenta.fechaFinal);
                        Iterator<Reporte>iter=list.iterator();
                        Reporte reporte=null;
                        while(iter.hasNext()){
                            i++;
                            reporte=iter.next();
                    %>
                    <tbody>
                    <tr>
                        <th class="text-center" class="text-black-50"><%=i%> </th>
                        <th class="text-center" class="text-black-50"><%=reporte.getNumeroSerie()%></th>
                        <th class="text-center" class="text-black-50"><%=reporte.getFechaDevolucion()%></th>
                        <th class="text-center" class="text-black-50"><%=reporte.getNombreClientre()%></th>
                        <th class="text-center" class="text-black-50"><%=reporte.getNit()%></th>
                        <th class="text-center" class="text-black-50"><%=reporte.getNombre_mueble_ensamble()%></th>
                        <th class="text-center" class="text-black-50"><%="Q. "+reporte.getPerdida()%></th>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            </div>
        </div>
    </section>
        <script src="./../resources/libraries/bootstrap/js/jquery.js"></script>
        <script src="./../resources/libraries/bootstrap/js/bootstrap.min.js"></script>
        <script src="./../resources/libraries/aos/aos.js"></script>
    </body>
</html>
