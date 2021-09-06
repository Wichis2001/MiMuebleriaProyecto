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
            <section>
                <div class="container"> 
                    <div class="row ewk_cont_sec_1">
                        <div class="col-md-12">
                            <h2 data-aos="fade-up-right"><b>Presentación</b></h2>
                            <p data-aos="fade-right"> <i> El mejor lugar para fabricar y comprar tus muebles</i></p>
                            <hr/>
                            <p data-aos="flip-left">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus faucibus tincidunt sapien in fringilla. Nulla facilisi. Fusce libero magna, placerat id tempus sit amet, tincidunt in leo. Fusce ut rhoncus nisl. Maecenas id velit ante. Sed fermentum vitae tortor at facilisis. Ut sit amet feugiat enim, ut interdum quam. Suspendisse potenti. Aliquam et tristique sem. Quisque nec eros massa. Curabitur ornare quis erat et consectetur. Praesent sed nisi consectetur, fringilla enim quis, lobortis ante. Integer varius mauris condimentum, dapibus tortor sed, suscipit magna. Praesent et elit sollicitudin, hendrerit est ac, ornare neque. Mauris vel est eu odio sagittis volutpat. Lorem.</p>
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
