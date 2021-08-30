<%-- 
    Document   : consulta-muebles-menor
    Created on : 30/08/2021, 03:36:55 PM
    Author     : luis
--%>

<%@page import="Mueble.MuebleEnsamblado"%>
<%@page import="java.util.Iterator"%>
<%@page import="Mueble.Pieza"%>
<%@page import="Mysql.modelos.*"%>
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
        <title>Consulta de Muebles</title>
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
                        <li><a class="dropdown-item" href="Servlet-Mueble?accion=listar2">Registrar Mueble</a></li>
                      </ul>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Consultas
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="Servlet-Consulta?accion=listar">Consulta de Piezas</a></li>
                        <li><a class="dropdown-item" href="Servlet-Consulta?accion=listar4">Consulta de Muebles</a></li>
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
                        <h1 class="text-center">Consulta de Piezas Disponibles</h1>
                        <a class="btn btn-success" href="Servlet-Consulta?accion=listar4">Regresar</a>
                        <a class="btn btn-success" href="Servlet-Consulta?accion=listar5">Ordenar mayor a menor</a>
                        <br>
                        <br>
                        <table class="table table-bordered"border="1">
                        <thead>
                    <tr>
                        <th class="text-center">ID MUEBLE</th>
                        <th class="text-center">NOMBRE MUEBLE</th>
                        <th class="text-center">USUARIO CONSTRUCTOR</th>
                        <th class="text-center">PRECIO</th>
                        <th class="text-center">FECHA ENSAMBLAJE</th>
                    </tr>
                    </thead>
                    <%
                        
                        ConsultasFabricaDAO dao= new ConsultasFabricaDAO();
                        List<MuebleEnsamblado>list=dao.listarMueble();
                        Iterator<MuebleEnsamblado>iter=list.iterator();
                        MuebleEnsamblado mueble=null;
                        while(iter.hasNext()){
                            mueble=iter.next();
                    %>
                    <tbody>
                    <tr>
                        <th class="text-center" class="text-black-50"><%=mueble.getIdentificador_mueble()%> </th>
                        <th class="text-center" class="text-black-50"><%=mueble.getNombre_mueble_ensamblado()%></th>
                        <th class="text-center" class="text-black-50"><%=mueble.getUsuario_constructor()%></th>
                        <th class="text-center" class="text-danger"><%=mueble.getPrecio()%></th>
                        <th class="text-center" class="text-danger"><%=mueble.getFecha_ensamblaje()%></th>
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

