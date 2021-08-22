<%-- 
    Document   : piezas-update
    Created on : 18/08/2021, 19:38:20
    Author     : luis
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Mueble.Pieza"%>
<%@page import="Mysql.modelos.PiezaDAO"%>
<%@page import="Mueble.Mueble"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond" rel="stylesheet">
        <link rel="stylesheet" href="../../resources/libraries/aos/aos.css"/>
        <link rel="stylesheet" href="../../resources/libraries/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../../resources/css/banner-area-fabrica.css"/>
        <link rel="stylesheet" href="../../resources/css/keyframes.css"/>
        <link rel="stylesheet" href="../../resources/css/style-area-fabrica.css"/>
        <link href="./../resources/libraries/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Lista Piezas</title>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Listado de Piezas Disponibles</h1>
            <a class="btn btn-success" href="Servlet-Pieza?accion=add">Agregar nuevo</a>
            <br>
            <br>
            <table class="table table-bordered"border="1">
                <thead>
                    <tr>
                        <th class="text-center">TIPO</th>
                        <th class="text-center">COSTO</th>
                        <th class="text-center">CANTIDAD</th>
                        <th class="text-center">ACCIONES</th>
                    </tr>
                </thead>
                <%
                    PiezaDAO dao= new PiezaDAO();
                    List<Pieza>list=dao.listar();
                    Iterator<Pieza>iter=list.iterator();
                    Pieza pieza=null;
                    while(iter.hasNext()){
                        pieza=iter.next();
                %>
                <tbody>
                    <tr>
                        <td class="text-center"><%=pieza.getTipo()%></td>
                        <td class="text-center"><%=pieza.getCosto()%></td>
                        <td class="text-center"><%=pieza.getCantidad()%></td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="Servlet-Pieza?accion=editar&tipo=<%=pieza.getTipo()%>&costo=<%=pieza.getCosto()%>">Editar</a>
                            <a class="btn btn-danger" href="Servlet-Pieza?accion=eliminar&tipo=<%=pieza.getTipo()%>&costo=<%=pieza.getCosto()%>">Eliminar</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

        </div>
        <script src="../../resources/libraries/bootstrap/js/jquery.js"></script>
        <script src="../resources/libraries/bootstrap/js/bootstrap.min.js"></script>
        <script src="../resources/libraries/aos/aos.js"></script>
        <script src="../resources/js/index.js"></script>
    </body>
</html>
