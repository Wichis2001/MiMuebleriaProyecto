<%-- 
    Document   : carga_datos
    Created on : 19/08/2021, 08:48:02 PM
    Author     : luis
--%>

<%@page import="Mysql.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INICIO SESION</title>
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond" rel="stylesheet">
        <link rel="stylesheet" href="resources/libraries/aos/aos.css"/>
        <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/banner.css"/>
        <link rel="stylesheet" href="resources/css/keyframes.css"/>
        <link rel="stylesheet" href="resources/css/style.css"/>
        <link rel="stylesheet" href="resources/css/inicio-sesion-estilo.css"/>
        <title>Carga de Datos</title>
    </head>
    <body>
        <header>
            <section>
                <div class="ewk_cont_banner">
                    <div class="ewk_sombra">
                        <p><strong>CARGA DE DATOS</strong></p>
                        <img src="resources/img/carpeta.png" height="80" width="80"/>
                        <hr/>
                        <div class="ewk_cont_banner_link">
                            <form action="Carga-Datos" method="post" enctype="multipart/form-data">
                                <div class="form-group text-center">
                                    <div class="form-group">
                                        <input class="btn btn-success" type='file' name='fileName'/>
                                        <input class="btn btn-success" type = "submit" value = "upload"/>
                                </div>
                            </form>
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
