package Controlador;
import Modelo.Producto;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class sr_producto extends HttpServlet {

    //private Interfaseporducto dao;
    Producto p;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        
        
        
        String accion = request.getParameter("accion");
       
        

        //Producto p = new Producto();
        
        
        
        //ProductoDAO pdao = new ProductoDAO();
        //presentacion pr = new presentacion();
        
        
         List<Producto> productos = null;
        int id;
        String ruta ="";
        String rutaMod ="";
        String boton ="";
        
        //ArrayList<String> lista = new ArrayList<>();
        switch (accion) {
           
            
            case "Guardar":
                
                ArrayList<String> lista = new ArrayList<>();
                int idp;
                String nombre;
                int id_marca;
                String descripcion;
                float costo;
                float presio;
                int existencia;
                String boton1;
                try {
                    FileItemFactory file = new DiskFileItemFactory();
                    ServletFileUpload fileUpload = new ServletFileUpload(file);
                    List items = fileUpload.parseRequest(request);
                    for (int i = 0; i < items.size(); i++) {
                        FileItem fileItem = (FileItem) items.get(i);
                        if (!fileItem.isFormField()) {
                            //File f = new File("C:\\Users\\gerar\\Documents\\NetBeansProjects\\SistemaImagenes\\web\\img\\" + fileItem.getName());
                            File f = new File("C:\\Users\\mfer_\\Documents\\NetBeansProjects\\SistemaImagenes\\web\\img\\" + fileItem.getName());
                            
                           
                            //File f = new File("C:\\imagenes\\" + fileItem.getName());
                            fileItem.write(f);
                            //p.setRuta("http://localhost:8081/Imagenes/img/" + fileItem.getName());
                           //          http://localhost:8080/Imagenes/img/
                            /*p.setRuta("imagenes/2.jpg");*/
                            /*p.setRuta(f.getAbsolutePath());*/
                            
                            //ruta = f.getAbsolutePath();
                            ruta =  "http://localhost:8081/Imagenes/img/" + fileItem.getName();

                        } else {
                            lista.add(fileItem.getString());     
                       } 
                       
                    }
                     
                     idp = Integer.valueOf(lista.get(0));
                     nombre = lista.get(1);
                     id_marca = Integer.valueOf(lista.get(2));
                     descripcion = lista.get(3);
                     costo = Float.valueOf(lista.get(4));
                     presio = Float.valueOf(lista.get(5));
                     existencia = Integer.valueOf(lista.get(6));
                     boton1 = lista.get(7);
                     
                     boton = lista.get(4);
                     
                     
                    if("Actualizar".equals(boton1)){
                        p = new Producto(nombre, descripcion, ruta, idp, id_marca, existencia, costo, presio);
                        //public Producto(String producto, String descripcion, String imagen, int id, int id_marca, int existencia, float precioCosto, float precioVenta) {
                        p.modificar();
                    }if("Guardar".equals(boton1)){
                        p = new Producto(nombre, descripcion, ruta, 0, id_marca, existencia, costo, presio);
                        //public Producto(String producto, String descripcion, String imagen, int id, int id_marca, int existencia, float precioCosto, float precioVenta) {
                        p.agregar();
                    }          
                    
                 
                   // p.setNombre(lista.get(0));
                    //p.setDescripcion(lista.get(1));
                   // p.setId_presentacion(Integer.valueOf(lista.get(2)));
                    //p.agregar(p);
                    
               request.getRequestDispatcher("Productos.jsp").forward(request, response);     
                    
                } catch (Exception e) {
                        idp = Integer.valueOf(lista.get(0));
                        p = new Producto("", "", "", idp, 0, 0, 0, 0);
                        //public Producto(String producto, String descripcion, String imagen, int id, int id_marca, int existencia, float precioCosto, float precioVenta) {
                        p.eliminar();
                request.getRequestDispatcher("Productos.jsp").forward(request, response);       
                }
                    
                //request.getRequestDispatcher("sr_producto?accion=Listar").forward(request, response);

                break;
            case "modificar":
                  //productos= p.listar();
                  //request.setAttribute("productos", productos);
                  //request.getRequestDispatcher("index.jsp").forward(request, response);
                
                
                try ( PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet sr_cliente</title>");
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<p>" + request.getParameter("txt_id") + "</p>");
                    out.println("<p>" + request.getParameter("txt_nombre") + "</p>");
                    out.println("<p>" + request.getParameter("txt_descripcion") + "</p>");
                    out.println("<p>" + request.getParameter("drop_presentacion") + "</p>");
                    out.println("<p>" + request.getParameter("fileImagen") + "</p>");
                   

                    out.println("</body>");
                    out.println("</html>");
                }
                
                
                
                
                
                

                
                //List<Producto> productos = p.listar();
                //request.setAttribute("productos", productos);
                 request.getRequestDispatcher("Productos.jsp").forward(request, response);
                break;
             case "Listar":
                 
                request.getRequestDispatcher("Productos.jsp").forward(request, response);
                 
                 
                  //productos= p.listar();
                  //request.setAttribute("productos", productos);
                  //request.getRequestDispatcher("index.jsp").forward(request, response);
                

                
                  
                  
                  
                  
                //List<Producto> productos = p.listar();
                //request.setAttribute("productos", productos);
                //request.getRequestDispatcher("index.jsp").forward(request, response);
               
               
            
                break;

            case "Eliminar":
                //ProductoDAO productoDAO = new ProductoDAO();
                //ProductoDAO pdao = new ProductoDAO();
                
                
                
                
                
                
                
                
                  //id = Integer.parseInt(request.getParameter("id"));
                  //p.setId(id);
                  //p.eliminar(id);
                  //request.getRequestDispatcher("index.jsp").forward(request, response);
                  
                  
                  
                  
                  
                  
                //int res = p.eliminar(id_prod);
                /**
                if(res !=0){
                    request.setAttribute("config","alert alert warning");
                    request.setAttribute("mensaje", "Se elimino el registro");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);  
                }else{
                    request.setAttribute("config","alert alert warning");
                    request.setAttribute("mensaje", "Error al eliminar");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response); 
                }
               */
                
                //productoDAO.eliminar(id);
                //dispatcher = request.getDispatcherType("Producto/index.jsp");
                //List<Producto> productos = pdao.listar();
                //request.setAttribute("productos", productos);
                //request.getRequestDispatcher("index.jsp").forward(request, response);
                
                try ( PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet sr_cliente</title>");
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<p>" + request.getParameter("id") + "</p>");
                    
                    

                    out.println("</body>");
                    out.println("</html>");
                }

                break;
            default:
                request.getRequestDispatcher("Productos.jsp").forward(request, response); 
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
