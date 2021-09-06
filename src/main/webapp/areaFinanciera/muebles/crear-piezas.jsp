<%-- 
    Document   : crear-piezas
    Created on : 5/09/2021, 04:27:33 PM
    Author     : luis
--%>

<%@page import="Mueble.Pieza"%>
<%@page import="Mysql.modelos.CrearMuebleDAO"%>
<%@page import="Servlets.ServletMuebleF"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="Mueble.Mueble"%>
<%@page import="Mysql.modelos.ConstruirMuebleDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="Trabajadores.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="Mysql.modelos.UsuarioFDAO"%>
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
        <title>CREAR MUEBLES</title>
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
                      <a class="nav-link active" aria-current="ventana-principal.jsp" href="Servlet-Mueble?accion=listar">Crear Mueble</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="ventana-principal.jsp" href="Servlet-Usuarios?accion=listar">Creación/Cancelacion Usuarios</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reportes
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=venta">Reporte de Ventas</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=devolucion">Reporte de Devoluciones</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=ganancia">Reporte de Ganancias</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=usuariov">Reporte de Usuario con Ventas</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=usuariog">Reporte de Usuario Ganancias</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=mmas">Reporte de Mueble más Vendido</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=mmenos">Reporte de Mueble menos Vendido</a></li>
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
                        <h1 class="text-center">Asignar Instrucciones del Mueble</h1>
                        <form>
                            <input type="hidden" name="txtTipo" value ="<%=ServletMuebleF.nombreMueble%>"required><br>
                            Tipo Pieza: <br>
                            <select onchange="this.form['valor'].value=this.value" name="tipo" class="form-control" required> 
                               <option >Desplegar Opciones</option>
                                <%
                                        //Creamos un nuevo dao de mueble para poder agregar todos nuestros objetos de muebles que pueden llegar a ser fabricados dentro de nuestra tabla
                                        CrearMuebleDAO dao= new CrearMuebleDAO();
                                        List<Pieza>list=dao.listar();
                                        Iterator<Pieza>iter=list.iterator();
                                        Pieza pieza=null;
                                        while(iter.hasNext()){
                                            pieza=iter.next();
                                %>
                                <option><%=pieza.getTipo()%></option>
                                <%}%>      
                            </select>
                            Cantidad: <br>
                            <input class="form-control" class="text-center" type="number" name="cantidad" required><br>
                            <input id="agregar" class="btn btn-primary" class="text-center" type="submit" name="accion" value="Establecer Ensamblaje"><br>
                            <input id="agregar" class="btn btn-warning" class="text-center" type="submit" name="accion" value="Terminar Ensamblaje"><br>
                        </form>
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
