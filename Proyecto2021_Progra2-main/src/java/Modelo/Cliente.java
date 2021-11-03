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
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo FX
 */
public class Cliente extends Persona {
    private String nit,correo;
    private Conexion cn;

    public Cliente(){}
    public Cliente(String nit, String correo, int id, String nombres, String apellidos, String telefono, String genero) {
        super(id, nombres, apellidos, telefono, genero);
        this.nit = nit;
        this.correo = correo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public ArrayList<Cliente> getCliente() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try{
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "SELECT * FROM clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            
            while(consulta.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(consulta.getInt("idClientes"));
                cliente.setNombres(consulta.getString("nombres"));
                cliente.setApellidos(consulta.getString("apellidos"));
                cliente.setNit(consulta.getString("nit"));
                listaClientes.add(cliente);
            }
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return listaClientes;
    }
    
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "SELECT idClientes as id,nombres,apellidos,nit,CASE genero WHEN 1 THEN 'M' WHEN 0 THEN 'F' END,telefono,correo_electronico,fechaIngreso FROM clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Nombres","Apellidos","Nit","Género","Telefono","Email","F. Ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[8];
            while(consulta.next()) {
                datos[0] = consulta.getString("id");                
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("nit");
                datos[4] = consulta.getString("CASE genero WHEN 1 THEN 'M' WHEN 0 THEN 'F' END");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("correo_electronico");
                datos[7] = consulta.getString("fechaIngreso");
                tabla.addRow(datos);
            }
            
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return tabla;
    }
    
    @Override
    public int agregar(){
        int retorno = 0;
        
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "INSERT INTO clientes(nombres,apellidos,nit,genero,telefono,correo_electronico,fechaIngreso) VALUES(?,?,?,b?,?,?,CURRENT_TIMESTAMP);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getNit());
            parametro.setString(4, getGenero());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getCorreo());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
                    
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return retorno;
    }
    @Override
    public int modificar() {
        int retorno = 0;
        
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "UPDATE clientes SET nombres=?,apellidos=?,nit=?,genero=b?,telefono=?,correo_electronico=?,fechaIngreso=CURRENT_TIMESTAMP WHERE idClientes = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getNit());
            parametro.setString(4, getGenero());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getCorreo());
            parametro.setInt(7,getId());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
                    
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return retorno;
    }
    @Override
    public int eliminar() {
        int retorno = 0;
        
         try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "DELETE from clientes WHERE idClientes = ?;";
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