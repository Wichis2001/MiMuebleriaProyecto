<%-- 
    Document   : piezas
    Created on : 18/08/2021, 19:19:37
    Author     : luis
--%>

<%@page import="Mysql.modelos.PiezaDAO"%>
<%@page import="Mueble.Pieza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond" rel="stylesheet">
        <link rel="stylesheet" href="../resources/libraries/aos/aos.css"/>
        <link rel="stylesheet" href="../resources/libraries/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../resources/css/banner-area-fabrica.css"/>
        <link rel="stylesheet" href="../resources/css/keyframes.css"/>
        <link rel="stylesheet" href="../resources/css/style-area-fabrica.css"/>
        <link href="./../resources/libraries/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body> 
        <div class="container">
            <div class="col-lg-6">
                <h1 class="text-center">Formulario de Asignacion de Piezas</h1>
                <form>
                Tipo: <br>
                <input class="form-control" type="text" name="txtTipo" required><br>
                Costo: <br>
                <input class="form-control" type="text" name="txtCosto"required><br>
                Cantidad: <br>
                <input class="form-control" type="number" name="txtCantidad"required><br>
                <input class="btn-primary" type="submit" name="accion" value="Agregar"><br>
                </form> 
            </div>    
        </div>
        
    </body>
</html>
