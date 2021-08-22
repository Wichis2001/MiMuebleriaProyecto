<%-- 
    Document   : piezas-update
    Created on : 18/08/2021, 19:38:20
    Author     : luis
--%>

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
                        <li><a class="dropdown-item" href="#">Ensablar Mueble</a></li>
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
                        <h1 class="text-center">Listado de Piezas Disponibles</h1>
                        <a class="btn btn-success" href="Servlet-Pieza?accion=add">Agregar nuevo</a>
                        <br>
                        <br>
                        <table class="table table-bordered"border="1">
                        <thead>
                    <tr>
                        <th class="text-center">TIPO</th>
                        <th class="text-center">COSTO</th>
                        <th class="text-center">CANTIDAD</th>
                        <th class="text-center">ACCIONES</th>
                        </tr>
                    </thead>
                    <%
                        PiezaDAO dao= new PiezaDAO();
                        List<Pieza>list=dao.listar();
                        Iterator<Pieza>iter=list.iterator();
                        Pieza pieza=null;
                        while(iter.hasNext()){
                            pieza=iter.next();
                    %>
                    <tbody>
                    <tr>
                        <th class="text-center" class="text-black-50"><%=pieza.getTipo()%> </th>
                        <th class="text-center" class="text-black-50"><%=pieza.getCosto()%></th>
                        <th class="text-center" class="text-black-50"><%=pieza.getCantidad()%></th>
                        <td class="text-center">
                            <input type="hidden" id="tipo" value="<%=pieza.getTipo()%>">
                            <input type="hidden" id="costo" value="<%=pieza.getCosto()%>">
                            <a id="updatePieza"class="btn btn-warning" href="Servlet-Pieza?accion=editar&tipo=<%=pieza.getTipo()%>&costo=<%=pieza.getCosto()%>">Editar</a>
                            <a id="deletePieza" class="btn btn-danger">Eliminar</a>
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
        <script src="./../resources/js/funcionesPieza.js"></script>
    </body>
</html>
