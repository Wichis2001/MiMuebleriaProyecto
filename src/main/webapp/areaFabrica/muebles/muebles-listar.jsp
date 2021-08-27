<%-- 
    Document   : muebles-listar
    Created on : 26/08/2021, 05:50:10 PM
    Author     : luis
--%>

<%@page import="Mueble.Mueble"%>
<%@page import="Mysql.modelos.ConstruirMuebleDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="Mueble.Pieza"%>
<%@page import="Mueble.Pieza"%>
<%@page import="Mysql.modelos.PiezaDAO"%>
<%@page import="Mysql.modelos.PiezaDAO"%>
<%@page import="Mysql.modelos.PiezaDAO"%>
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
        <title>Lista Piezas</title>
    </head>
    <body>
        <header>
            <nav class="ewk_navbar navbar navbar-expand-lg navbar-light bg-light fixed-top">
              <div class="container-fluid">
                <a class="navbar-brand" href="pagina-area-fabrica.jsp">Área de Fábrica</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="pagina-area-fabrica.jsp" href="../areaFabrica/pagina-area-fabrica.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="pagina-area-fabrica.jsp" href="Servlet-Pieza?accion=listar">Piezas</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Muebles
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="Servlet-Mueble?accion=listar">Ensablar Mueble</a></li>
                        <li><a class="dropdown-item" href="#">Registrar Mueble</a></li>
                      </ul>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Consultas
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Consulta de Piezas</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Muebles</a></li>
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
                        <h1 class="text-center">Listado de Muebles Disponibles</h1>
                        <br>
                        <br>
                        <table class="table table-bordered"border="1">
                        <thead>
                    <tr>
                        <th class="text-center">NOMBRE MUEBLE</th>
                        <th class="text-center">COSTO</th>
                        <th class="text-center">ACCIONES</th>
                        </tr>
                    </thead>
                    <%
                        ConstruirMuebleDAO dao= new ConstruirMuebleDAO();
                        List<Mueble>list=dao.listar();
                        Iterator<Mueble>iter=list.iterator();
                        Mueble mueble=null;
                        while(iter.hasNext()){
                            mueble=iter.next();
                    %>
                    <tbody>
                    <tr>
                        <th class="text-center" class="text-black-50"><%=mueble.getNombre_mueble_ensamble()%> </th>
                        <th class="text-center" class="text-black-50"><%=mueble.getPrecio()%></th>
                        <td class="text-center">
                            <input type="hidden" id="tipo" value="<%=mueble.getNombre_mueble_ensamble()%>">
                            <input type="hidden" id="precio" value="<%=mueble.getPrecio()%>">  
                            <a class="btn btn-warning" href="Servlet-Mueble?accion=construir&tipo=<%=mueble.getNombre_mueble_ensamble()%>&precio=<%=mueble.getPrecio()%>"">Construir Mueble</a>
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
