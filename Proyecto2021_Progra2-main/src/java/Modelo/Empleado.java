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
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo FX
 */
public class Empleado extends Persona {
    private int id_puesto;
    private String direccion,dpi,f_inicio,f_nac;
    private Conexion cn;

    public Empleado(){}

    public Empleado(int id_puesto, String direccion, String dpi, String f_inicio, String f_nac, int id, String nombres, String apellidos, String telefono, String genero) {
        super(id, nombres, apellidos, telefono, genero);
        this.id_puesto = id_puesto;
        this.direccion = direccion;
        this.dpi = dpi;
        this.f_inicio = f_inicio;
        this.f_nac = f_nac;
    }
    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getF_inicio() {
        return f_inicio;
    }

    public void setF_inicio(String f_inicio) {
        this.f_inicio = f_inicio;
    }

    public String getF_nac() {
        return f_nac;
    }

    public void setF_nac(String f_nac) {
        this.f_nac = f_nac;
    }
    
    public ArrayList<Empleado> getEmpleado() {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        
        try{
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "SELECT * FROM empleados;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            
            while(consulta.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(consulta.getInt("idempleados"));
                empleado.setNombres(consulta.getString("nombres"));
                empleado.setApellidos(consulta.getString("apellidos"));
                listaEmpleados.add(empleado);
            }
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return listaEmpleados;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        
        try{
            cn = new Conexion();
            String query = "SELECT e.idempleados as id,e.nombres,e.apellidos,e.direccion,e.telefono,e.DPI,CASE genero WHEN 1 THEN 'M' WHEN 0 THEN 'F' END,e.fecha_nacimiento,p.puesto,e.idPuesto,e.fecha_inicio_labores,e.fechaIngreso FROM empleados as e inner join puestos as p on e.idPuesto = p.idPuesto;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Nombres","Apellidos","Direccion","Telefono","DPI","Genero","F. nacimiento","Puesto","idPuesto","Inicio de Labores","Fecha de Ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[12];
            while(consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                datos[5] = consulta.getString("DPI");
                datos[6] = consulta.getString("CASE genero WHEN 1 THEN 'M' WHEN 0 THEN 'F' END");
                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getString("puesto");
                datos[9] = consulta.getString("idPuesto");
                datos[10] = consulta.getString("fecha_inicio_labores");
                datos[11] = consulta.getString("fechaIngreso");
                
                tabla.addRow(datos);
            }
            
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return tabla;
    }
    
    @Override
    public int agregar() {
        int retorno = 0;
        
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "INSERT INTO empleados(nombres,apellidos,direccion,telefono,DPI,genero,fecha_nacimiento,idPuesto,fecha_inicio_labores,fechaIngreso) VALUES(?,?,?,?,?,b?,?,?,?,CURRENT_TIMESTAMP);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDpi());          
            parametro.setString(6, getGenero());
            parametro.setString(7, getF_nac());
            parametro.setInt(8, getId_puesto());
            parametro.setString(9, getF_inicio());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return retorno;
    }
    
    @Override
    public int modificar() {
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE empleados set nombres=?,apellidos=?,direccion=?,telefono=?,DPI=?,genero=b?,fecha_nacimiento=?,idPuesto=?,fecha_inicio_labores=?,fechaIngreso=CURRENT_TIMESTAMP where idempleados=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDpi());          
            parametro.setString(6, getGenero());
            parametro.setString(7, getF_nac());
            parametro.setInt(8, getId_puesto());
            parametro.setString(9, getF_inicio());
            parametro.setInt(10, getId());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    @Override
    public int eliminar() {
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE from empleados  where idempleados = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }

    
}
