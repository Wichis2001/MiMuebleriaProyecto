<%-- 
    Document   : venta
    Created on : 3/09/2021, 05:15:39 AM
    Author     : luis
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Iterator"%>
<%@page import="Mueble.MuebleEnsamblado"%>
<%@page import="java.util.List"%>
<%@page import="Mysql.modelos.ConstruirMuebleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
       <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MI MUEBLERIA</title>
        <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond" rel="stylesheet">
        <link rel="stylesheet" href="../resources/libraries/aos/aos.css"/>
        <link rel="stylesheet" href="../resources/libraries/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../resources/css/banner-area-punto-venta.css"/>
        <link rel="stylesheet" href="../resources/css/keyframes.css"/>
        <link rel="stylesheet" href="../resources/css/style-area-ventas.css"/>

    </head>
    <body>
        <header>
            <nav class="ewk_navbar navbar navbar-expand-lg navbar-light bg-light fixed-top">
              <div class="container-fluid">
                <a class="navbar-brand" href="../areaPuntosdeVenta/ventana-principal.jsp">Área de Punto de Venta</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="../areaPuntosdeVenta/ventana-principal.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="">Ventas</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="pagina-principal.jsp" href="#">Devoluciones</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Consultas
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Consulta de Compras</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Devoluciones</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Muebles</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Facturas</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de Ventas</a></li>
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
                        <h1>Registro de Venta</h1>
                        <div class="ewk_cont_banner_link">
                            <div class="d-flex">
                                <div class="col-sm-4">
                                    <div class="card">
                                        <form action="Servlet-Venta?menu=NuevaVenta" method="POST">
                                        <div class="card-body">
                                            <div class="form-group">
                                                <label>Datos del Cliente</label>
                                            </div>
                                            <div class="form-group d-flex">
                                                <div class="col-sm-6 d-flex">
                                                    <input type="text" name="nitcliente" value="${c.getNit()}" class="form-control" placeholder="NIT" required>
                                                    <input type="submit" name="accion" value="BuscarCliente" class="btn-outline-info">   
                                                </div>
                                                <div class="col-sm-6">
                                                    <input type="text" name="nombreCliente" value="${c.getNombre()}"class="form-control" placeholder="Nombre Cliente">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>Datos Muebles</label>
                                            </div>
                                            <div class="form-group d-flex">
                                                <div class="col-sm-6 d-flex">
                                                    <select onchange="this.form['valor'].value=this.value" name="idMueble" class="form-control" required> 
                                                       <option >Desplegar Opciones</option>
                                                        <%
                                                                //Creamos un nuevo dao de mueble para poder agregar todos nuestros objetos de muebles que pueden llegar a ser fabricados dentro de nuestra tabla
                                                                ConstruirMuebleDAO dao= new ConstruirMuebleDAO();
                                                                List<MuebleEnsamblado>list=dao.listarMuebleEnsamblado();
                                                                Iterator<MuebleEnsamblado>iter=list.iterator();
                                                                MuebleEnsamblado mueble=null;
                                                                while(iter.hasNext()){
                                                                    mueble=iter.next();
                                                        %>
                                                        <option ><%=mueble.getIdentificador_mueble()%></option>
                                                        <%}%>
                                                    </select>
                                                    <input type="hidden" name="valor">
                                                    <button type="submit" name="accion" value="BuscarMueble" class="btn-outline-info">Buscar</button>
                                                </div>
                                                <div class="col-sm-6">
                                                    <input type="text" name="nombreMueble" value="${m.getNombre_mueble_ensamblado()}" class="form-control" placeholder="Nombre Mueble">
                                                </div>
                                            </div>
                                            <div class="form-group d-flex">
                                                <div class="col-sm-12 d-flex">
                                                    <input type="text" name="precio" value="${m.getPrecio()}" class="form-control" placeholder="Q/.0.00">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <button type="submit" name="accion" value="Agregar" class="btn-outline-info">Agregar Producto</button>
                                            </div>
                                            </form>
                                        </div>
                                            
                                    </div>
                                        
                                </div>
                                <div class="col-sm-8">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="d-flex col-sm-5 ml-auto">
                                                <label class="text-black">Número Serie:</label>
                                                <input type="text" name="No. Factura" value="${nserie}" class="form-control">
                                            </div>
                                            <br>
                                            <table border="1" class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>No.</th>
                                                        <th>Codigo</th>
                                                        <th>Descripecion</th>
                                                        <th>Precio</th>
                                                        <th>Subtotal</th>
                                                        <th>Acciones</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="list" items="${lista}">
                                                    <tr>
                                                        <td>${list.getItem()}</td>
                                                        <td>${list.getIdmueble()}</td>
                                                        <td>${list.getDescripcion()}</td>
                                                        <td>${list.getTotal()}</td>
                                                        <td>${list.getSubtotal()}</td>
                                                        <td class="text-center">
                                                            <a class="btn btn-danger" href="Servlet-Venta?accion=Eliminar&lugar=${list.getIdmueble()}">Eliminar</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>

                                        </div>
                                        <form action="Servlet-Venta?menu=NuevaVenta" method="POST">
                                        <div class="card-footer d-flex">
                                            <div class="col-sm-6">
                                                <input type="submit" name="accion" value="Generar Venta" class="btn btn-success">  
                                                <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                                            </div>
                                            <div class="col-sm-4 ml-auto">
                                                <input type="text" name="txtTotal" value="Q/. ${totalpagar}" class="form-control">
                                            </div>
                                        </div>
                                        </form>   
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        <script src="../resources/libraries/bootstrap/js/jquery.js"></script>
        <script src="../resources/libraries/bootstrap/js/bootstrap.min.js"></script>
        <script src="../resources/libraries/aos/aos.js"></script>
        <script src="../resources/js/index.js"></script>
    </body>
</html>

