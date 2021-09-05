<%-- 
    Document   : reporte-mueble-menos-tabla
    Created on : 5/09/2021, 04:59:03 AM
    Author     : luis
--%>

<%@page import="CompraVenta.Reporte"%>
<%@page import="CompraVenta.Cliente"%>
<%@page import="CompraVenta.Venta"%>
<%@page import="Mueble.MuebleEnsamblado"%>
<%@page import="java.util.Iterator"%>
<%@page import="Mueble.Pieza"%>
<%@page import="Mysql.modelos.*"%>
<%@page import="Mueble.Mueble"%>
<%@page import="Mysql.modelos.*"%>
<%@page import="java.util.List"%>
<%@page import="Servlets.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond" rel="stylesheet">
        <link rel="stylesheet" href="./../resources/libraries/aos/aos.css"/>
        <link rel="stylesheet" href="./../resources/libraries/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="./../resources/css/banner-general.css"/>
        <link rel="stylesheet" href="./../resources/css/keyframes.css"/>
        <link href="./../resources/libraries/sweetalert/sweetalert.css" rel="stylesheet" type="text/css"/>
        <link href="./../resources/libraries/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="./../resources/css/style-general.css"/>
        <title>Reportes Financieros</title>
    </head>
    
    <body>
        <header>
            <nav class="ewk_navbar navbar navbar-expand-lg navbar-light bg-light fixed-top">
              <div class="container-fluid">
                  <a class="navbar-brand" href="../areaFinanciera/ventana-principal.jsp">Área Financiera</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="ventana-principal.jsp" href="../areaFinanciera/ventana-principal.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="ventana-principal.jsp" href="Servlet-Mueble?accion=listar">Crear Mueble</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="ventana-principal.jsp" href="Servlet-Usuarios?accion=listar">Creación/Cancelacion Usuarios</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reportes
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=venta">Reporte de Ventas</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=devolucion">Reporte de Devoluciones</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=ganancia">Reporte de Ganancias</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=usuariov">Reporte de Usuario con Ventas</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=usuariog">Reporte de Usuario Ganancias</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=mmas">Reporte de Mueble más Vendido</a></li>
                        <li><a class="dropdown-item" href="Servlet-Reportes?accion=mmenos">Reporte de Mueble menos Vendido</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </nav>
        </header>
            <section>
                <div class="ewk_cont_banner">
                    <div class="ewk_sombra">
                        <h1 class="text-center">REPORTE MUEBLE MENOS VENDIDO</h1>
                        <a class="btn btn-success" href="Servlet-Reportes?accion=mmenos">Regresar</a>
                        <br>
                        <br>
                        <table id="dataTable" class="table table-bordered"border="1">
                        <thead>
                    <tr>
                        <th class="text-center">No.</th>
                        <th class="text-center">No. FACTURA</th>
                        <th class="text-center">NOMBRE CLIENTE</th>
                        <th class="text-center">DIRECCION</th>
                        <th class="text-center">IDENTIFICADOR MUEBLE</th>
                        <th class="text-center">NOMBRE MUEBLE</th>
                        <th class="text-center">PRECIO</th>
                    </tr>
                    </thead>
                    <% 
                        //Creamos un nuevo dao de listar mueble para poder agregar todos nuestros objetos de mueble dentro de nuestra tabla y poder ordenarlos de mayor a menor
                        int i=0;
                        ReportesDAO dao= new ReportesDAO();
                        List<Reporte>list=dao.mueble(dao.nombreMuebleMe(ServletReportes.fechaInicio, ServletReportes.fechaFinal),ServletReportes.fechaInicio, ServletReportes.fechaFinal);
                        Iterator<Reporte>iter=list.iterator();
                        Reporte reporte=null;
                        while(iter.hasNext()){
                            i++;
                            reporte=iter.next();
                    %>
                    <tbody>
                    <tr>
                        <th class="text-center" class="text-black-50"><%=i%> </th>
                        <th class="text-center" class="text-black-50"><%=reporte.getNumeroSerie()%></th>
                        <th class="text-center" class="text-black-50"><%=reporte.getNombreClientre()%></th>
                        <th class="text-center" class="text-black-50"><%=reporte.getDireccion()%></th>
                        <th class="text-center" class="text-black-50"><%=reporte.getMueble_identificador_mueble()%></th>
                        <th class="text-center" class="text-black-50"><%=reporte.getNombre_mueble_ensamble()%></th>
                        <th class="text-center" class="text-black-50"><%="Q. "+reporte.getPrecio()%></th>
                    </tr>
                    <%}%>
                </tbody>
            </table>
                <br>
                        <form class="form-group d-flex">
                            <label class="text-black-50">Nombre Mueble:</label>
                            <input class="col-sm-2 d-flex" class="text-center" type="text" value="<%=dao.nombreMuebleMe(ServletReportes.fechaInicio, ServletReportes.fechaFinal)%>" disabled="disabled"><br>
                        </form>
                <br>
                <button id="btnExportToCsv" type="button" class="btn btn-success">Exportar a CSV</button>
                <script>
                    const dataTable = document.getElementById("dataTable");
                    const btnExportToCsv = document.getElementById("btnExportToCsv");

                    btnExportToCsv.addEventListener("click", () => {
                        const exporter = new TableCSVExporter(dataTable);
                        const csvOutput = exporter.convertToCSV();
                        const csvBlob = new Blob([csvOutput], { type: "text/csv" });
                        const blobUrl = URL.createObjectURL(csvBlob);
                        const anchorElement = document.createElement("a");

                        anchorElement.href = blobUrl;
                        anchorElement.download = "reporte-mueble-menos-vendido.csv";
                        anchorElement.click();

                        setTimeout(() => {
                            URL.revokeObjectURL(blobUrl);
                        }, 500);
                    });
                </script>
            </div>
        </div>
    </section>
        <script src="./../resources/libraries/bootstrap/js/jquery.js"></script>
        <script src="./../resources/libraries/bootstrap/js/bootstrap.min.js"></script>
        <script src="./../resources/libraries/aos/aos.js"></script>
        <script src="./../resources/js/TableCSVExporter.js"></script>
    </body>
</html>
