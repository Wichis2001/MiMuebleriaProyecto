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
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Piezas</h1>
            <a href="ServletPieza?accion=add">Agregar nuevo</a>
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
                    PiezaDAO dao=new PiezaDAO();
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
                            <a>Editar</a>
                            <a>Eliminar</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

        </div>
        
    </body>
</html>
