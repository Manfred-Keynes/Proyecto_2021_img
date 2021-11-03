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
public class Marca {
    private int id_marca;
    private String marca;
    Conexion cn;

    public Marca(){}
    public Marca(int id_marca, String marca) {
        this.id_marca = id_marca;
        this.marca = marca;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public HashMap drop_sangre(){
        HashMap<String,String> drop = new HashMap();
        try{
            String query = "SELECT idMarca as id,marca FROM marcas;";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            while(consulta.next()) {
                drop.put(consulta.getString("id"), consulta.getString("marca"));
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
            query = "SELECT idMarca as id,marca FROM marcas;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Marca"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[2];
            while(consulta.next()) {
                datos[0] = consulta.getString("id");                
                datos[1] = consulta.getString("marca");
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
            query = "INSERT INTO marcas(marca) VALUES(?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getMarca());
            
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
            query = "UPDATE marcas SET marca=? WHERE idMarca = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getMarca());
            parametro.setInt(2,getId_marca());
            
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
            query = "DELETE from marcas WHERE idMarca = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,getId_marca());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
                    
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
         
        return retorno;
    }
}
