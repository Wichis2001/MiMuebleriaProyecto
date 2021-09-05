<%-- 
    Document   : consulta-devoluciones-cliente
    Created on : 4/09/2021, 03:33:29 PM
    Author     : luis
--%>

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
        <title>Consultas Devoluciones Cliente</title>
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
                        <li><a class="dropdown-item" href="Servlet-CounstaVenta?accion=ingresoaCompra">Consulta de Compras</a></li>
                        <li><a class="dropdown-item" href="Servlet-CounstaVenta?accion=DevolucionC">Consulta de Devoluciones</a></li>
                        <li><a class="dropdown-item" href="Servlet-CounstaVenta?accion=mueble">Consulta de Muebles</a></li>
                        <li><a class="dropdown-item" href="Servlet-CounstaVenta?accion=factura">Consulta de Facturas</a></li>
                        <li><a class="dropdown-item" href="Servlet-CounstaVenta?accion=ventas">Consulta de Ventas</a></li>
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
                        <h1 class="text-center">CONSULTA DE DEVOLUCIONES DE UN NUEVO CLIENTE</h1>
                        <form action="Servlet-CounstaVenta?accion=reporteCompra">
                            Nombre Cliente: <br>
                            <input class="form-control" class="text-center" type="text" name="txtNombre" placeholder="Nombre" required><br>
                            FechaInicial: <br>
                            <input class="form-control" class="text-center" type="date" name="fechaI"><br>
                            FechaFinal: <br>
                            <input class="form-control" class="text-center" type="date" name="fechaF"><br>
                            <input id="agregar" class="btn btn-primary" class="text-center" type="submit" name="accion" value="Consultar Devolucion"><br>
                        </form>
                    </div>
                </div>
            </section>
        <script src="./../resources/libraries/bootstrap/js/jquery.js"></script>
        <script src="./../resources/libraries/bootstrap/js/bootstrap.min.js"></script>
        <script src="./../resources/libraries/aos/aos.js"></script>
        <script src="./../resources/js/index.js"></script>
        <script src="./../resources/libraries/sweetalert/sweetalert.js" type="text/javascript"></script>
        <script src="./../resources/js/piezasAgregar.js"></script>
    </body>
</html>
