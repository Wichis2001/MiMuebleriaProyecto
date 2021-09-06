<%-- 
    Document   : carga_datos
    Created on : 19/08/2021, 08:48:02 PM
    Author     : luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
    <h1> Carga del archivo de prueba </ h1>
        <form action="Carga-Datos" method="post" enctype="multipart/form-data" >
            <input type='file' name='fileName' />
            <input type = "submit" value = "upload" />
        </form>
    </body>
</html>
