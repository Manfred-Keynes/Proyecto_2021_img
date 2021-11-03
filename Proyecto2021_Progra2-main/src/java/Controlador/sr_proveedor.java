/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.Proveedor;

/**
 *
 * @author Lenovo FX
 */
public class sr_proveedor extends HttpServlet {

    
    Proveedor proveedor;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cliente</title>");  
            out.println("</head>");
            out.println("<body>");
            
           /* out.println("<p>" + Integer.valueOf(request.getParameter("txt_id")) + "</p>");
            out.println("<p>" + request.getParameter("txt_proveedor") + "</p>");
            out.println("<p>" + request.getParameter("txt_nit") + "</p>");
            out.println("<p>" + request.getParameter("txt_direccion") + "</p>");
            out.println("<p>" + request.getParameter("txt_telefono") + "</p>");*/
            
            proveedor = new Proveedor(Integer.valueOf(request.getParameter("txt_id")),request.getParameter("txt_proveedor"),request.getParameter("txt_nit"),request.getParameter("txt_direccion"),request.getParameter("txt_telefono"));
            
        // Boton agregar 
            if ("agregar".equals(request.getParameter("btn_agregar"))){
             if (proveedor.agregar()>0){
             response.sendRedirect("http://localhost:8080/Proyecto2021/Proveedores.jsp");
             }else{
             out.println("<h1> xxxxxxx No se Ingreso xxxxxxxxxxxx </h1>");
             out.println("<a href='index.jsp'>Regresar...</a>");
             }
             }
            
            // Boton modificar 
            if ("modificar".equals(request.getParameter("btn_modificar"))){
             if (proveedor.modificar()>0){
             response.sendRedirect("http://localhost:8080/Proyecto2021/Proveedores.jsp");
             }else{
             out.println("<h1> xxxxxxx No se Modifico xxxxxxxxxxxx </h1>");
             out.println("<a href='index.jsp'>Regresar...</a>");
             }
             }
            
            // Boton eliminar 
            if ("eliminar".equals(request.getParameter("btn_eliminar"))){
             if (proveedor.eliminar()>0){
             response.sendRedirect("http://localhost:8080/Proyecto2021/Proveedores.jsp");
             }else{
             out.println("<h1> xxxxxxx No se Elimino xxxxxxxxxxxx </h1>");
             out.println("<a href='index.jsp'>Regresar...</a>");
             }
             }
            
            
            out.println("</body>");
            out.println("</html>");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
