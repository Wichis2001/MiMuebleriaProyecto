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
        <title>JSP Page</title>
    </head>
    <body>      
        <h1>Agregar Piezas</h1>
        <form>
            Tipo: <br>
            <input type="text" name="txtTipo" required><br>
            Costo: <br>
            <input type="text" name="txtCosto"required><br>
            Cantidad: <br>
            <input type="number" name="txtCantidad"required><br>
            <input type="submit" name="accion" value="Agregar"><br>
        </form>
    </body>
</html>
