<%-- 
    Document   : tabla
    Created on : 2/09/2021, 05:20:02 PM
    Author     : luis
--%>

<%@page import="Servlets.ServletFactura"%>
<%@page import="CompraVenta.Venta"%>
<%@page import="Mysql.modelos.DevolucionDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="Mueble.Pieza"%>
<%@page import="Mysql.modelos.PiezaDAO"%>
<%@page import="Mueble.Mueble"%>
<%@page import="java.util.List"%>
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
        <title>Devolucion Mueble</title>
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
                        <h1 class="text-center">Listado de Productos Comprados</h1>
                        <a id="salir" class="btn btn-danger" href="Servlet-Pieza?accion=add">Finalizar Devolucion</a>
                        <br>
                        <br>
                        <table class="table table-bordered"border="1">
                        <thead>
                    <tr>
                        <th class="text-center">Número</th>
                        <th class="text-center">IDENTIFICADOR MUEBLE</th>
                        <th class="text-center">PRECIO VENTA</th>
                        <th class="text-center">ACCIONES</th>
                        </tr>
                    </thead>
                    <%
                        //Creamos un nuevo dao de listar los muebles adquiridos por el usuario en la factura
                        int i=0;
                        DevolucionDAO dao= new DevolucionDAO();
                        List<Venta>list=dao.listVenta(ServletFactura.noVenta);
                        Iterator<Venta>iter=list.iterator();
                        Venta venta=null;
                        while(iter.hasNext()){
                            i=i+1;
                            venta=iter.next();
                    %>
                    <tbody>
                    <tr>
                        <th class="text-center" class="text-black-50"><%=i%> </th>
                        <th class="text-center" class="text-black-50"><%=venta.getIdmueble()%></th>
                        <th class="text-center" class="text-black-50"><%=venta.getTotal()%></th>
                        <td class="text-center">
                            <a class="btn btn-warning" id="devolver" href="Servlet-Factura?accion=devolucion&id=<%=venta.getIdmueble()%>">Devolver Mueble</a>
                        </td>
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
        <script src="./../resources/js/index.js"></script>
        <script src="./../resources/libraries/sweetalert/sweetalert.js" type="text/javascript"></script>
        
    </body>
</html>
