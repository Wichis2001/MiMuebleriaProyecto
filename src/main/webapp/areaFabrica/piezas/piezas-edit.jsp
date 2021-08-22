<%-- 
    Document   : piezas-delete
    Created on : 18/08/2021, 19:38:07
    Author     : luis
--%>

<%@page import="Mueble.Pieza"%>
<%@page import="Mysql.modelos.PiezaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Modificar Piezas</h1>
        <div>
            <% 
            PiezaDAO dao=new PiezaDAO();
            String tipo=(String)request.getAttribute("tipopieza");
            Double precio=Double.parseDouble((String)request.getAttribute("costopieza"));
            Pieza p=(Pieza)dao.list(tipo, precio);
            %>
            <form>
            Cantidad: <br>
            <input type="number" name="txtCantidad" value ="<%=p.getCantidad()%>" required><br>
            <input type="hidden" name="txtTipo" value ="<%=p.getTipo()%>"required><br>
            <input type="hidden" name="txtCosto" value ="<%=p.getCosto()%>"required><br>
            <input type="submit" name="accion" value="Actualizar"><br>
            </form>
        </div>
        
    </body>
</html>
