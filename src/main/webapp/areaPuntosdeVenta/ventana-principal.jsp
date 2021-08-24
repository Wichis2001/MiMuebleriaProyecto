<%-- 
    Document   : ventana-principal
    Created on : 19/08/2021, 11:07:57 PM
    Author     : luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
       <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MI MUEBLERIA</title>
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond" rel="stylesheet">
        <link rel="stylesheet" href="../resources/libraries/aos/aos.css"/>
        <link rel="stylesheet" href="../resources/libraries/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../resources/css/banner-area-punto-venta.css"/>
        <link rel="stylesheet" href="../resources/css/keyframes.css"/>
        <link rel="stylesheet" href="../resources/css/style-area-ventas.css"/>

    </head>
    <body>
        <header>
            <nav class="ewk_navbar navbar navbar-expand-lg navbar-light bg-light fixed-top">
              <div class="container-fluid">
                <a class="navbar-brand" href="pagina-area-fabrica.jsp">Área de Punto de Venta</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="#">Ventas</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="#">Devoluciones</a>
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
                        <h1>AREA DE PUNTO DE VENTA</h1>
                        <p>Bienvenid@ al sistema ${nom}!</p>
                        <hr/>
                        <div class="ewk_cont_banner_link">
                            <a class="ewk_banner_link" href="../index.html">Cerrar Sesión</a>
                        </div>
                    </div>
                </div>
            </section>
        <script src="../resources/libraries/bootstrap/js/jquery.js"></script>
        <script src="../resources/libraries/bootstrap/js/bootstrap.min.js"></script>
        <script src="../resources/libraries/aos/aos.js"></script>
        <script src="../resources/js/index.js"></script>
    </body>
</html>

