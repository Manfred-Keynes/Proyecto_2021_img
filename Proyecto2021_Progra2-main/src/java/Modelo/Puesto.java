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
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo FX
 */
public class Puesto {
    private int id_puesto;
    private String puesto;
    Conexion cn;

    public Puesto(){}
    public Puesto(int id_puesto, String puesto) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    public HashMap drop_sangre(){
        HashMap<String,String> drop = new HashMap();
        try{
            String query = "SELECT idPuesto as id,puesto FROM puestos;";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            while(consulta.next()) {
                drop.put(consulta.getString("id"), consulta.getString("puesto"));
            }
            
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return drop;
    }
    

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "SELECT idPuesto as id,puesto FROM puestos;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Puesto"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[2];
            while(consulta.next()) {
                datos[0] = consulta.getString("id");                
                datos[1] = consulta.getString("puesto");
                tabla.addRow(datos);
            }
            
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return tabla;
    }
    
    public int agregar(){
        int retorno = 0;
        
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "INSERT INTO puestos(puesto) VALUES(?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getPuesto());
            
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
            query = "UPDATE puestos SET puesto=? WHERE idPuesto = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getPuesto());
            parametro.setInt(5,getId_puesto());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
                    
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return retorno;
    }
    public int eliminar() {
        int retorno = 0;
        
         try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "DELETE from puestos WHERE idPuesto = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,getId_puesto());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
                    
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
         
        return retorno;
    }
}
