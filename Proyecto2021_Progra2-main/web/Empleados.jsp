<%-- 
    Document   : Empleados
    Created on : 28/09/2021, 22:19:06
    Author     : Lenovo FX
--%>

<%@page import="Modelo.Puesto"%>
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
                    </ul>
                </div>
            </div>
        </nav>
    </head>
    <body>
        <h1 align="center">Formulario Empleados</h1>
        <div class="container">
            <form action="sr_empleado" method="post" class="form-group">
                <label for="lbl_id"><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                <label for="lbl_nombres"><b>Nombres</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Nombre1 Nombre2">
                <label for="lbl_apellidos"><b>Apellidos</b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Apellido1 Apellido2">
                <label for="lbl_direccion"><b>Dirección</b></label>
                <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Av. 123, Adress Anywhere, Street Wherever">
                <label for="lbl_telefono"><b>Telefono</b></label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ej: 30518836">
                <label for="lbl_dpi"><b>DPI</b></label>
                <input type="text" name="txt_dpi" id="txt_dpi" class="form-control" placeholder="Ej: 1234000000101">
                
                <label for="lbl_genero"><b>Género</b></label>
                <select name="drop_genero" id="drop_genero" class="form-select">
                    <option value="" disabled selected>Elija su genero...</option>
                    <option value="0">Femenino</option>
                    <option value="1">Masculino</option>
                </select>
                
                <label for="lbl_fn"><b>F. Nacimiento</b></label>
                <input type="date" name="txt_fn" id="txt_fn" class="form-control" placeholder="YYYY-MM-DD">
                
                <label for="lbl_puestos"><b>Puesto</b></label>
                <select name="drop_puesto" id="drop_puesto" class="form-select">
                    <option value="" disabled selected>Elija un puesto...</option>
                    <%
                        Puesto puesto = new Puesto();
                        HashMap<String,String> drop = puesto.drop_sangre();
                        for (String i:drop.keySet()) {
                            out.println(" <option value='"+ i +"'>"+ drop.get(i) +"</option>");
                        }
                    %>
                </select>
                
                <label for="lbl_IniLab"><b>Inicio de Labores</b></label>
                <input type="date" name="txt_IniLab" id="txt_IniLab" class="form-control" placeholder="YYYY-MM-DD">
                <label for="lbl_fi"><b>Fecha de Ingreso</b></label>
                <input type="text" name="txt_fi" id="txt_fi" class="form-control" placeholder="YYYY-MM-DD" readonly>
                
                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>
            </form>
            <br>
            <table class="table table-hover table-dark">
                <thead>
                  <tr>
                      <th scope="col">#</th>
                    <th scope="col">Nombres</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col">Dirección</th>
                    <th scope="col">Teléfono</th>
                    <th scope="col">DPI</th>
                    <th scope="col">Género</th>
                    <th scope="col">Nacimiento</th>
                    <th scope="col">Puesto</th>
                    <th scope="col">Inicio de Labores</th>
                    <th scope="col">Fecha de Ingreso</th>
                  </tr>
                </thead>
                <tbody id="tbl_empleados">
                    <%
                      Empleado empleado = new Empleado();
                      DefaultTableModel tabla = new DefaultTableModel();
                      tabla = empleado.leer();
                      for (int t = 0; t < tabla.getRowCount(); t++) {
                        out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-gen=" + tabla.getValueAt(t, 6) + " data-idp=" + tabla.getValueAt(t, 9)+">");
                            out.println("<td>" + t + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 8) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 10) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 11) + "</td>");
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
        $('#tbl_empleados').on('click','tr td',function(evt){
            var target,id,id_p,codigo,nombres,apellidos,direccion,telefono,dpi,genero,labores,ingreso,nacimiento;
            target = $(event.target);
            id = target.parent().data('id');
            id_p = target.parent().data('idp');
            genero = target.parent().data('gen');
            codigo = target.parent("tr").find("td").eq(0).html();
            nombres = target.parent("tr").find("td").eq(1).html();
            apellidos = target.parent("tr").find("td").eq(2).html();
            direccion = target.parent("tr").find("td").eq(3).html();
            telefono = target.parent("tr").find("td").eq(4).html();
            dpi = target.parent("tr").find("td").eq(5).html();
            //genero = target.parent("tr").find("td").eq(6).html();
            nacimiento = target.parent("tr").find("td").eq(7).html();
            labores = target.parent("tr").find("td").eq(9).html();
            ingreso = target.parent("tr").find("td").eq(10).html();
            
            
            
            
            $("#txt_id").val(id);
            $("#drop_puesto").val(id_p);
            $("#txt_codigo").val(codigo);
            $("#txt_nombres").val(nombres);
            $("#txt_apellidos").val(apellidos);
            $("#txt_direccion").val(direccion);
            $("#txt_telefono").val(telefono);
            $("#txt_fn").val(nacimiento);
            $("#txt_dpi").val(dpi);
            
            //valida el genero para insertar dentro del drop
            if(genero==="M"){
                $("#drop_genero").val('1');
            }
            if(genero==="F"){
                $("#drop_genero").val('0');
            }
            $("#txt_IniLab").val(labores);
            $("#txt_fi").val(ingreso);

        });
    </script>
    
    </body>
</html>
