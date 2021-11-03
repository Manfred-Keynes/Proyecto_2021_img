<%-- 
    Document   : Empleados
    Created on : 28/09/2021, 22:19:06
    Author     : Lenovo FX
--%>

<%@page import="Modelo.Cliente"%>
<%@page import="java.util.HashMap"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <title>Clientes DB</title>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
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
        <h1 align="center">Formulario Clientes</h1>
        <div class="container">
            <form action="sr_cliente" method="post" class="form-group">
                <label for="lbl_id"><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>                
                <label for="lbl_nombres"><b>Nombres</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Nombre1 Nombre2" required pattern="[A-Z]{1}[a-z]{2,9}([ ][A-Z]{1}[a-z]{2,9})?" oninvalid="this.setCustomValidity('Los nombres deben iniciar con mayúsculas y no contener números.')" oninput="this.setCustomValidity('')">
                <label for="lbl_apellidos"><b>Apellido</b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Apellido1 Apellido2" required pattern="[A-Z]{1}[a-z]{2,9}([ ][A-Z]{1}[a-z]{2,9})?" oninvalid="this.setCustomValidity('Los apellidos deben iniciar con mayúsculas y no contener números.')" oninput="this.setCustomValidity('')">
                <label for="lbl_nit"><b>Nit</b></label>
                <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="Ej: 12345678 ó C/F" maxlength="8" minlength="3" required pattern="^([C,c]+\/?+[F,f])|[1-9][0-9]{7,8}?" oninvalid="this.setCustomValidity('Debe contener almenos 7 numeros y no debe contener letras, o en su defecto C/F.')" oninput="this.setCustomValidity('')">
                
                <label for="lbl_genero"><b>Género</b></label>
                <select name="drop_genero" id="drop_genero" class="form-select" required oninvalid="this.setCustomValidity('Selecciona un género porfavor.')" oninput="this.setCustomValidity('')">
                    <option value="" disabled selected>Elija su genero...</option>
                    <option value="0">Femenino</option>
                    <option value="1">Masculino</option>
                </select>
                
                <label for="lbl_telefono"><b>Telefono</b></label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ej: 30518836" required>
                <label for="lbl_email"><b>Email</b></label>
                <input type="email" name="txt_email" id="txt_email" class="form-control" placeholder="Ej: user@gmail.com" required pattern="^[^@]+@[^@]+\.[a-zA-Z]{2,3}$">
                <label for="lbl_fi"><b>F. Ingreso</b></label>
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
                    <th scope="col">Nit</th>
                    <th scope="col">Género</th>
                    <th scope="col">Teléfono</th>
                    <th scope="col">Email</th>
                    <th scope="col">F. Ingreso</th>
                  </tr>
                </thead>
                <tbody id="tbl_clientes">
                    <%
                      Cliente cliente = new Cliente();
                      DefaultTableModel tabla = new DefaultTableModel();
                      tabla = cliente.leer();
                      for (int t = 0; t < tabla.getRowCount(); t++) {
                        out.println("<tr data-gen=" + tabla.getValueAt(t, 4) + " data-id=" + tabla.getValueAt(t, 0) +">");
                            out.println("<td>" + t + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
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
        $('#tbl_clientes').on('click','tr td',function(evt){
            var target,id,nombres,apellidos,nit,genero,telefono,correo,Fingreso;
            target = $(event.target);
            id = target.parent().data('id');
            genero = target.parent().data('gen');
            nombres = target.parent("tr").find("td").eq(1).html();
            apellidos = target.parent("tr").find("td").eq(2).html();
            nit = target.parent("tr").find("td").eq(3).html();
            telefono = target.parent("tr").find("td").eq(5).html();
            correo = target.parent("tr").find("td").eq(6).html();
            Fingreso = target.parent("tr").find("td").eq(7).html();
            
            
            
            
            $("#txt_id").val(id);
            $("#txt_nombres").val(nombres);
            $("#txt_apellidos").val(apellidos);
            $("#txt_nit").val(nit);
            $("#txt_telefono").val(telefono);
            
            //valida el genero para insertar dentro del drop
            if(genero==="M"){
                $("#drop_genero").val('1');
            }
            if(genero==="F"){
                $("#drop_genero").val('0');
            }
            $("#txt_email").val(correo);
            $("#txt_fi").val(Fingreso);

        });
    </script>
    
    </body>
</html>
