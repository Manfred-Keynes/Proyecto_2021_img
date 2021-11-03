<%-- 
    Document   : Ventas
    Created on : 26/10/2021, 00:14:16
    Author     : Lenovo FX
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Venta"%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Empleado"%>
<%@page import="java.util.HashMap"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <title>Empleados DB</title>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="http://localhost:8080/Proyecto2021">Home</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="http://localhost:8080/Proyecto2021/Empleados.jsp">Empleados</a></li>
                    <li class="nav-item"><a class="nav-link" href="http://localhost:8080/Proyecto2021/Clientes.jsp">Clientes</a></li>
                    <li class="nav-item"><a class="nav-link" href="http://localhost:8080/Proyecto2021/Proveedores.jsp">Proveedores</a></li>
                    <li class="nav-item"><a class="nav-link" href="http://localhost:8080/Proyecto2021/Puestos.jsp">Puestos</a></li>
                    <li class="nav-item"><a class="nav-link" href="http://localhost:8080/Proyecto2021/Marcas.jsp">Marcas</a></li>
                    <li class="nav-item"><a class="nav-link" href="http://localhost:8080/Proyecto2021/Productos.jsp">Productos</a></li>
                    <li class="nav-item"><a class="nav-link" href="http://localhost:8080/Proyecto2021/Ventas.jsp">Ventas</a></li>
                </ul>
            </div>
        </div>
    </nav>
</head>
<body>
    <h1 align="center">Maestro Detalle Ventas</h1>
    <div class="container">
        <form action="sr_ventas" method="post" class="form-group">
            <label for="lbl_id"><b>ID</b></label>
            <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
            <label for="lbl_factura"><b>No. Factura</b></label>
            <input type="number" name="txt_factura" id="txt_factura" class="form-control" placeholder="123">
            <label for="lbl_serie"><b>Serie</b></label>
            <input type="text" name="txt_serie" id="txt_serie" class="form-control" value="" readonly>
            <label for="lbl_ff"><b>Fecha de Factura</b></label>
            <input type="text" name="txt_ff" id="txt_ff" class="form-control" placeholder="YYYY-MM-DD" readonly>

            <label for="lbl_cliente"><b>Cliente</b></label>
            <select name="drop_cliente" id="drop_cliente" class="form-select">
                <option value="" disabled selected>Seleccione un cliente...</option>
                <%
                    Cliente obj_cliente = new Cliente();
                    ArrayList<Cliente> listaClientes = obj_cliente.getCliente();

                    for (int i = 0; i < listaClientes.size(); i++) {
                        out.println(" <option value='" + listaClientes.get(i).getId() + "'>" + listaClientes.get(i).getNombres() + " " + listaClientes.get(i).getApellidos() + " - " + listaClientes.get(i).getNit() + "</option>");
                    }
                %>
            </select>

            <label for="lbl_empleado"><b>Empleado</b></label>
            <select name="drop_empleado" id="drop_empleado" class="form-select">
                <option value="" disabled selected>Seleccione un empleado...</option>
                <%
                    Empleado obj_empleado = new Empleado();
                    ArrayList<Empleado> listaEmpleados = obj_empleado.getEmpleado();

                    for (int i = 0; i < listaEmpleados.size(); i++) {
                        out.println(" <option value='" + listaEmpleados.get(i).getId() + "'>" + listaEmpleados.get(i).getNombres() + " " + listaEmpleados.get(i).getApellidos() + "</option>");
                    }
                %>
            </select>

            <br>
            <div class="col-md-8">
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if (!confirm('¿Desea Eliminar?'))return false">Eliminar</button>
            </div>
            <br>
            <div class="col-md-4">
                <a href="http://localhost:8080/Proyecto2021/Clientes.jsp" name="btn_clts" id="btn_clts" class="btn btn-warning">Clientes</a>
                <a href="http://localhost:8080/Proyecto2021/Empleados.jsp" name="btn_epls" id="btn_epls" class="btn btn-info">Empleados</a>
            </div>
        </form>
        <br>
        <table class="table table-hover table-dark">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">No. Factura</th>
                    <th scope="col">Serie</th>
                    <th scope="col">F. Factura</th>
                    <th scope="col">Nit</th>
                    <th scope="col">Nombres Cliente</th>
                    <th scope="col">Apellidos Cliente</th>
                    <th scope="col">Nombres Empleado</th>
                    <th scope="col">Apellidos Empledado</th>
                </tr>
            </thead>
            <tbody id="tbl_ventas">
                <%  Venta venta = new Venta();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla = venta.leer();
                    for (int t = 0; t < tabla.getRowCount(); t++) {
                        out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-idc=" + tabla.getValueAt(t, 9) + " data-ide=" + tabla.getValueAt(t, 10) + ">");
                        out.println("<td>" + t + "</td>");
                        out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                        out.println("<td>" + tabla.getValueAt(t, 8) + "</td>");
                        out.println("</tr>");
                    }
                %>

            </tbody>
        </table>
        
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
    <script type="text/javascript">
                    $('#tbl_ventas').on('click', 'tr td', function (evt) {
                        var target, id, id_e, id_c, nFac, serie, fFac, nit;
                        target = $(event.target);
                        id = target.parent().data('id');
                        id_c = target.parent().data('idc');
                        id_e = target.parent().data('ide');
                        nFac = target.parent("tr").find("td").eq(1).html();
                        serie = target.parent("tr").find("td").eq(2).html();
                        fFac = target.parent("tr").find("td").eq(3).html();

                        $("#txt_id").val(id);
                        $("#drop_cliente").val(id_c);
                        $("#drop_empleado").val(id_e);
                        $("#txt_factura").val(nFac);
                        $("#txt_serie").val(serie);
                        $("#txt_ff").val(fFac);

                    });
    </script>

</body>
</html>
