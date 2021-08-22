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
        <link href="../../resources/libraries/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Piezas</h1>
            <a class="btn btn-success" href="Servlet-Pieza?accion=add">Agregar nuevo</a>
            <table border="1">
                <thead>
                    <tr>
                        <th>TIPO</th>
                        <th>COSTO</th>
                        <th>CANTIDAD</th>
                        <th>ACCIONES</th>
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
                        <td><%=pieza.getTipo()%></td>
                        <td><%=pieza.getCosto()%></td>
                        <td><%=pieza.getCantidad()%></td>
                        <td>
                            <a href="Servlet-Pieza?accion=editar&tipo=<%=pieza.getTipo()%>&costo=<%=pieza.getCosto()%>">Editar</a>
                            <a href="Servlet-Pieza?accion=eliminar&tipo=<%=pieza.getTipo()%>&costo=<%=pieza.getCosto()%>">Eliminar</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

        </div>
        
    </body>
</html>
