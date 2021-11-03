/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo FX
 */
public class VentaDetalle {
    private int id,id_venta,id_producto;
    private String cantidad;
    private float precioUnit;
    Conexion cn;

    public VentaDetalle(){}
    public VentaDetalle(int id, int id_venta, int id_producto, String cantidad, float precioUnit) {
        this.id = id;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(float precioUnit) {
        this.precioUnit = precioUnit;
    }
    
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "SELECT idVentas as id,v.noFactura, v.serie, v.fechaFactura, c.nit, c.nombres, c.apellidos, e.nombres, e.apellidos, c.idClientes, e.idempleados FROM ventas AS v INNER JOIN empleados AS e ON e.idempleados = v.idempleado INNER JOIN clientes AS c ON c.idClientes = v.idcliente;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","N. Factura","Serie","f. Factura","Nit","Nombres Cliente","Apellidos Cliente","Nombres Empleado","Apellidos Empledado","idClientes","idempleados"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[11];
            while(consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("v.noFactura");
                datos[2] = consulta.getString("v.serie");
                datos[3] = consulta.getString("v.fechaFactura");
                datos[4] = consulta.getString("c.nit");
                datos[5] = consulta.getString("c.nombres");
                datos[6] = consulta.getString("c.apellidos");
                datos[7] = consulta.getString("e.nombres");
                datos[8] = consulta.getString("e.apellidos");
                datos[9] = consulta.getString("c.idClientes");
                datos[10] = consulta.getString("e.idempleados");
                tabla.addRow(datos);
            }
            
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return tabla;
    }
    
    /*
    public int agregar(){
        int retorno = 0;
        
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "INSERT INTO ventas(noFactura,serie,fechaFactura,idcliente,idempleado,fechaIngreso) VALUES(?,'f',CURRENT_TIMESTAMP,?,?,CURRENT_TIMESTAMP);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getnFactura());
            parametro.setInt(2, getId_cliente());
            parametro.setInt(3, getId_empleado());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
                    
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return retorno;
    }
    public int modificar() {
        int retorno = 0;
        
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "UPDATE ventas SET noFactura=?,idcliente=?,idempleado=? WHERE idVentas = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getnFactura());
            parametro.setInt(2, getId_cliente());
            parametro.setInt(3, getId_empleado());
            parametro.setInt(4, getId());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
                    
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return retorno;
    }*/
    public int eliminar() {
        int retorno = 0;
        
         try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "DELETE from ventas WHERE idVentas = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,getId());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
                    
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
         
        return retorno;
    }
}
