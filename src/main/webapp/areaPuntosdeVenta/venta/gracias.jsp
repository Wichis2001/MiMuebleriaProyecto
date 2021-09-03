<%-- 
    Document   : gracias
    Created on : 3/09/2021, 05:37:22 PM
    Author     : luis
--%>

<%@page import="Servlets.ServletUsuario"%>
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
            <section>
                <div class="ewk_cont_banner">
                    <div class="ewk_sombra">
                        <h1>AREA DE VENTAS</h1>
                        <p>Gracias por Acceder al sistema <%=ServletUsuario.nombreRecurrente%>!</p>
                        <hr/>
                        <div class="ewk_cont_banner_link">
                            <a class="ewk_banner_link" href="../index.html">Salir</a>
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