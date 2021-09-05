<%-- 
    Document   : manejoUsuario
    Created on : 5/09/2021, 02:13:56 PM
    Author     : luis
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Trabajadores.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="Mysql.modelos.UsuarioFDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="Mysql.modelos.PiezaDAO"%>
<%@page import="Mueble.Pieza"%>
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
        <title>MANEJO USUARIOS</title>
    </head>
    
    <body>
        <header>
            <nav class="ewk_navbar navbar navbar-expand-lg navbar-light bg-light fixed-top">
              <div class="container-fluid">
                  <a class="navbar-brand" href="../areaFinanciera/ventana-principal.jsp">Área Financiera</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="ventana-principal.jsp" href="../areaFinanciera/ventana-principal.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="ventana-principal.jsp" href="#">Crear Mueble</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="ventana-principal.jsp" href="">Creación/Cancelacion Usuarios</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reportes
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=vender">Reporte de Ventas</a></li>
                        <li><a class="dropdown-item" href="#">Reporte de Devoluciones</a></li>
                        <li><a class="dropdown-item" href="#">Reporte de Ganancias</a></li>
                        <li><a class="dropdown-item" href="#">Reporte de Usuario con Ventas</a></li>
                        <li><a class="dropdown-item" href="#">Reporte de Usuario Ganancias</a></li>
                        <li><a class="dropdown-item" href="#">Reporte de Mueble más Vendido</a></li>
                        <li><a class="dropdown-item" href="#">Reporte de Mueble menos Vendido</a></li>
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
                        <h1 class="text-center">Listado de Usuarios Disponibles</h1>
                        <a class="btn btn-success" href="Servlet-Usuarios?accion=crear">Agregar nuevo</a>
                        <br>
                        <br>
                        <table class="table table-bordered"border="1">
                        <thead>
                    <tr>
                        <th class="text-center">NOMBRE USUARIO</th>
                        <th class="text-center">ÁREA LABORAL</th>
                        <th class="text-center">ACCIONES</th>
                        </tr>
                    </thead>
                    <%
                        //Creamos un nuevo dao de listar pieza para poder agregar todos nuestros objetos de  piezas dentro de nuestra tabla
                        UsuarioFDAO dao= new UsuarioFDAO();
                        List<Usuario>list=dao.listar();
                        Iterator<Usuario>iter=list.iterator();
                        Usuario usuario=null;
                        while(iter.hasNext()){
                            usuario=iter.next();
                    %>
                    <tbody>
                        <tr>
                            <th class="text-center" class="text-black-50"><%=usuario.getNombre_usuario()%> </th>
                            <th class="text-center" class="text-black-50"><%=usuario.getTipo_usuario_S()%></th>
                            <td class="text-center">
                                <input type="hidden" id="nombre" value="<%=usuario.getNombre_usuario()%>">
                                <a id="suspender" class="btn btn-danger">Suspender</a>
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
        <script src="./../resources/js/funcionesUsuarios.js"></script>
    </body>
</html>
