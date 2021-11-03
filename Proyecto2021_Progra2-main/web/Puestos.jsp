<%-- 
    Document   : Puestos
    Created on : 8/10/2021, 23:03:09
    Author     : Lenovo FX
--%>

<%@page import="Modelo.Puesto"%>
<%@page import="java.util.HashMap"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <title>Puestos DB</title>
    </head>
    <body>
        <h1 align="center">Formulario Puestos</h1>
        <div class="container">
            <form action="sr_puesto" method="post" class="form-group" enctype="multipart/form-data">
                <label for="lbl_id"><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>                
                <label for="lbl_puesto"><b>Nombre del Puesto</b></label>
                <input type="text" name="txt_puesto" id="txt_puesto" class="form-control" placeholder="NombrePuesto" required pattern="[A-Z]{1}[a-z]{2,9}([ ][A-Z]{1}[a-z]{2,9})?" oninvalid="this.setCustomValidity('Los nombres deben iniciar con mayúsculas')" oninput="this.setCustomValidity('')">
                
                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>                   
             </form>
                <br>
      
            <table class="table table-hover table-dark" style="width:500px">
                <thead>
                  <tr>
                      <th scope="col">#</th>                    
                    <th scope="col">Puesto</th>
                  </tr>
                </thead>
                <tbody id="tbl_puestos">
                    <%
                      Puesto puesto = new Puesto();
                      DefaultTableModel tabla = new DefaultTableModel();
                      tabla = puesto.leer();
                      for (int t = 0; t < tabla.getRowCount(); t++) {
                        out.println("<tr data-id=" + tabla.getValueAt(t, 0) +">");
                            out.println("<td scope='row' width='50%'>" + t + "</td>");
                            out.println("<td width='50%'>" + tabla.getValueAt(t, 1) + "</td>");
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
        $('#tbl_puestos').on('click','tr td',function(evt){
            var target,id,puesto;
            target = $(event.target);
            id = target.parent().data('id');
            puesto = target.parent("tr").find("td").eq(1).html();
            
            $("#txt_id").val(id);
            $("#txt_puesto").val(puesto);
        });
    </script>
    
    </body>
</html>
