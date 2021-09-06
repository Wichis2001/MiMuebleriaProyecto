<%-- 
    Document   : subir
    Created on : 5/09/2021, 09:34:21 PM
    Author     : luis
--%>

<%@page import="CargaDatos.HiloCargadeDatos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MI MUEBLERIA</title>
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond" rel="stylesheet">
        <link rel="stylesheet" href="resources/libraries/aos/aos.css"/>
        <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/banner.css"/>
        <link rel="stylesheet" href="resources/css/keyframes.css"/>
        <link rel="stylesheet" href="resources/css/style.css"/>
    </head>
    <body>
        <header>
            <section>
                <div class="ewk_cont_banner">
                    <div class="ewk_sombra">
                        <h1>Lista de errores</h1>
                        <hr/>
                        <div class="ewk_cont_banner_link">
                            <%
                                //Recorremos el array de errores y los asignamos a un parrafo
                                for (int i = 0; i < HiloCargadeDatos.errores.size(); i++) {
                                    String error="";
                                    error=HiloCargadeDatos.errores.get(i);
                            %>
                            <h2><%="Error: "+ error%></h2>                    
                            <%}%>
                            <a class="ewk_banner_link" href="inicio-sesion.jsp">Iniciar Sesión</a>
                        </div>
                    </div>
                </div>
            </section>
        </header>
        <script src="resources/libraries/bootstrap/js/jquery.js"></script>
        <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
        <script src="resources/libraries/aos/aos.js"></script>
        <script src="resources/js/index.js"></script>
    </body> 
</html>
