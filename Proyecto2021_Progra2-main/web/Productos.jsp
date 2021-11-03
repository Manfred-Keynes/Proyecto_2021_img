<%@page import="Modelo.Marca"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
           body{
           background: url(https://cdn.osxdaily.com/wp-content/uploads/2020/12/macos-big-sur-updated-wallpapers-1.jpg) no-repeat center center fixed;
           background-size: cover;
           
            }
            
            
            .table{
            opacity: 0.9;
            
            .modal-body{
            background: url(https://cdn.osxdaily.com/wp-content/uploads/2020/12/macos-big-sur-updated-wallpapers-2.jpg) no-repeat center center fixed;
            background-size: cover;
   
}
            
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <title>Productos</title>
        
    </head>
   <body>
       
       
       <div class="container mt-4">
           <style>
               h1{
                   color: #FFFFFF;
               }
           </style>
           <h1 class="text-center">Mantenimiento de  Porductos</h1>
           <button type="button"  class="btn btn-primary table-hover" data-toggle="modal" data-target="#modal_productos" onclick="limpiar()">Nuevo Mantenimiento</button>
           <a class="btn btn-success" href="sr_productos?accion=Listar">Refrescar</a>
           <!-- Modal -->
           <div id="modal_productos" class="modal fade" role="dialog">
               <div class="modal-dialog">

                   <!-- Modal content-->
                   <div class="modal-content">

                       <div class="modal-body">
                           <form action="sr_productos?accion=Guardar" id="form_productos" class="form-group" method="POST" enctype="multipart/form-data">
                               <label>Id</label>
                               <input type="text" name="txt_id" id="txt_id" value="0" readonly class="form-control"  >

                               <label>Nombre del Producto</label>
                               <input type="text" name="txt_nombre" id="txt_nombre" value="" class="form-control" >

                               <label>Marca</label>
                               <select name="drop_marcas" id="drop_marcas"class="form-control" >
                                   <option value="0" disabled selected>--Seleccione--</option>
                                   <%

                                       Marca marca = new Marca();
                                       HashMap<String, String> drop = marca.drop_sangre();
                                       for (String i : drop.keySet()) {
                                           out.println(" <option value='" + i + "'>" + drop.get(i) + "</option>");
                                       }
                                   %>
                               </select>

                               <label>Descripcion</label>
                               <input type="text" name="txt_descripcion" id="txt_descripcion" value="" class="form-control">

                               <label>Costo</label>
                               <input type="number" name="txt_costo" id="txt_costo" value="" class="form-control" >

                               <label>Precio</label>
                               <input type="number" name="txt_precio" id="txt_precio" value="" class="form-control" >

                               <label>Existencia</label>
                               <input type="number" name="txt_existencia" id="txt_existencia" value="" class="form-control" >
                               <div class="mb-3">
                                   <label class="form-label"><b>Selecciona una imagen del producto.</b></label>

                                   <input type="file" class="form-control" name="fileImagen" id="fileImagen" >
                               </div>

                               <button class="btn btn-primary" name="accion" value="Guardar">Guardar</button>
                               <button class="btn btn-secondary" name="accion" value="Actualizar">Actualizar</button>
                               <button  value="eliminar"class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar el registro?'))return false">Eliminar</button>
                               <button type="button" class="btn btn-dark" data-dismiss="modal">Cancelar</button>
                           </form>
                       </div>
                   </div>
              </div>
            </div>
            <hr>
               
            <table class="table table-hover table-dark text-center">
                        
                                <tr class="text-center">
                                    <!--th>#</th-->
                                    <th>NOMBRE</th>
                                    <th>MARCA</th>
                                    <th>DESCRIPCION</th>
                                    <th>IMAGEN</th>
                                    <th>COSTO</th>
                                    <th>PRECIO</th>
                                    <th>EXISTENCIA</th>
                                    <!--th>FECHA DE INGRESO</th-->
                                </tr>
                       

                            <tbody  id="tbl_productos" >
                                <tr >
                                    <%
                                        Producto producto = new Producto();
                                        DefaultTableModel tabla = new DefaultTableModel();
                                        tabla = producto.leer();
                                        for (int t = 0; t < tabla.getRowCount(); t++) {
                                            out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-id_m=" + tabla.getValueAt(t, 9) + ">");
                                            //out.println("<td>" + t + "</td>");
                                            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                                            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                                            out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                                            out.println("<td><img src=" + tabla.getValueAt(t, 4) + " height='100' width='100' ></td>");
                                            out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                                            out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                                            out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                                            //out.println("<td>" + tabla.getValueAt(t, 8) + "</td>");
                                            out.println("</tr>");
                                        }
                                    %>
                                </tr>                              
                        </tbody>        
                    </table>
                    
                </div>       
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script type="text/javascript">
            
            function limpiar(){
            $("#txt_id").val(0);
            $("#drop_marcas").val(0); 
            $("#txt_nombre").val('');
            $("#txt_descripcion").val('');
            $("#txt_costo").val(0.00); 
            $("#txt_precio").val(0.00);
            $("#txt_existencia").val(0);
            }
            $('#tbl_productos').on('click','tr td',function(evt){
               var target,id,id_m,nombre,descripcion,costo,precio,existencia;     
               target = $(event.target);
               id = target.parent().data('id');
               id_m = target.parent().data('id_m');
               nombre = target.parent("tr").find("td").eq(0).html();
               descripcion = target.parent("tr").find("td").eq(2).html();
               costo = target.parent("tr").find("td").eq(4).html(); 
               precio = target.parent("tr").find("td").eq(5).html();
               existencia = target.parent("tr").find("td").eq(6).html();
               
               
               
            $("#txt_id").val(id);
            $("#drop_marcas").val(id_m); 
            $("#txt_nombre").val(nombre);
            $("#txt_descripcion").val(descripcion);
            $("#txt_costo").val(costo); 
            $("#txt_precio").val(precio);
            $("#txt_existencia").val(existencia);
            $("#modal_productos").modal('show');
            
            
            
           
            
            });
            
            
        </script>
    </body>
</html>
