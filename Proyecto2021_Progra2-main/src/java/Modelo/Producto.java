
package Modelo;


import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Producto {
    private String producto,descripcion;
    public String imagen;
    private int id,id_marca,existencia;
    private float precioCosto,precioVenta;
    
    Conexion cn;

    public Producto(){}
    public Producto(String producto, String descripcion, String imagen, int id, int id_marca, int existencia, float precioCosto, float precioVenta) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.id = id;
        this.id_marca = id_marca;
        this.existencia = existencia;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public float getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(float precioCosto) {
        this.precioCosto = precioCosto;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    
   //public List<Producto> listar() {
       // List<Producto>lista=new ArrayList<>();
        //String sql = "SELECT p.id_producto,p.nombre,p.ruta,p.descripcion,pr.presentacion,p.id_presentacion FROM producto as p inner join presentacion as pr on p.id_presentacion = pr.id_presentacion;";
        //String sql = "select * from producto";
        //Conexion cn = new Conexion();
        
       // try {
            /*
            cn = new Conexion();
            cn.abrir_conexion();
            //cn = new Conexion();
            ps = cn.conexionBD.prepareStatement(sql);
            rs=ps.executeQuery();*/
            //rs = cn.conexionBD.createStatement().executeQuery(sql);
         /*   
            String query = "SELECT p.id_producto,p.nombre,p.ruta,p.descripcion,pr.presentacion,p.id_presentacion FROM producto as p inner join presentacion as pr on p.id_presentacion = pr.id_presentacion;";
            cn = new Conexion();
            cn.abrir_conexion();
            rs = cn.conexionBD.createStatement().executeQuery(query);
            
            
            while (rs.next()) {
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setRuta(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPresentacion(rs.getString(5));
                p.setId_presentacion(rs.getInt(6));
                lista.add(p);
                
            }
            cn.cerrar_conexion();

        } catch (Exception e) {
        }
        return lista;
    }
   /**
    public Producto leer_id (int id) {
        
        String sql = "SELECT p.id_producto,p.nombre,p.ruta,p.descripcion,pr.presentacion,p.id_presentacion FROM producto as p inner join presentacion as pr on p.id_presentacion = pr.id_presentacion where id_producto =?;";
        Conexion cn = new Conexion();
        Producto producto =new Producto();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()) {
                
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setRuta(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPresentacion(rs.getString(5));
                producto.setId_presentacion(rs.getInt(6));

            }
        } catch (Exception e) {
        }
        return producto;
    }*/
    
   public int agregar() {
        int retorno = 0;
        
        try{
            PreparedStatement parametro;
            
            String query;
            cn = new Conexion();
            cn.abrir_conexion();
            query = "INSERT INTO productos(producto,idMarca,Descripcion,Imagen,precio_costo,precio_venta,existencia,fecha_ingreso) VALUES(?,?,?,?,?,?,?,CURRENT_TIMESTAMP);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setInt(2, getId_marca());
            parametro.setString(3, getDescripcion());
            parametro.setString(4, getImagen());
            parametro.setFloat(5, getPrecioCosto());
            parametro.setFloat(6, getPrecioVenta());
            parametro.setInt(7, getExistencia());
            
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return retorno;
    }
   
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        
        try{
            cn = new Conexion();
            String query = "SELECT idProducto as id,producto,marca,Descripcion,Imagen,precio_costo,precio_venta,existencia,fecha_ingreso,idMarca FROM productos inner join marcas USING(idMarca);";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Producto","Marca","Descripción","Imagen","Precio-Costo","Precio-Venta","Existencias","fecha Ingreso","idMarca"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[12];
            while(consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("producto");
                datos[2] = consulta.getString("marca");
                datos[3] = consulta.getString("Descripcion");
                datos[4] = consulta.getString("Imagen");
                datos[5] = consulta.getString("precio_costo");
                datos[6] = consulta.getString("precio_venta");
                datos[7] = consulta.getString("existencia");  
                datos[8] = consulta.getString("fecha_ingreso");
                datos[9] = consulta.getString("idMarca");
                tabla.addRow(datos);

                
            }
            
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return tabla;
    }
    
    public int modificar() {
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE productos set producto=?,idMarca=?,Descripcion=?,Imagen=?,precio_costo=?,precio_venta=?,existencia=?,fecha_ingreso=CURRENT_TIMESTAMP where idProducto=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            
            parametro.setString(1, getProducto());
            parametro.setInt(2, getId_marca());
            parametro.setString(3, getDescripcion());
            parametro.setString(4, getImagen());
            parametro.setFloat(5, getPrecioCosto());
            parametro.setFloat(6, getPrecioVenta());          
            parametro.setInt(7, getExistencia());
            parametro.setInt(8, getId());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
    
    
    public int eliminar() {
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE from productos  where idProducto = ?;";
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
   /*
    public boolean  eliminar (int id) {
        String query = "DELETE FROM producto WHERE id_producto = "+id;
        cn = new Conexion();
        try{
            cn.abrir_conexion();
            ps = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            ps.executeUpdate();
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return false;
    }

   
*/
  public List<Producto> listar() {
        ResultSet rs;
        List<Producto>lista=new ArrayList<>();
        //String sql = "SELECT p.id_producto,p.nombre,p.ruta,p.descripcion,pr.presentacion,p.id_presentacion FROM producto as p inner join presentacion as pr on p.id_presentacion = pr.id_presentacion;";
        //String sql = "select * from producto";
        //Conexion cn = new Conexion();
        
        try {
            /*
            cn = new Conexion();
            cn.abrir_conexion();
            //cn = new Conexion();
            ps = cn.conexionBD.prepareStatement(sql);
            rs=ps.executeQuery();*/
            //rs = cn.conexionBD.createStatement().executeQuery(sql);
            
            String query = "Select idProducto,producto,idMarca,Descripcion,Imagen,precio_costo,precio_venta,existencia from productos;";
            cn = new Conexion();
            cn.abrir_conexion();
            rs = cn.conexionBD.createStatement().executeQuery(query);
            
            
            while (rs.next()) {
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setProducto(rs.getString(2));
                p.setId_marca(rs.getInt(3));
                p.setImagen(rs.getString(4));
                p.setDescripcion(rs.getString(5));
                p.setPrecioCosto(rs.getFloat(6));
                p.setPrecioVenta(rs.getFloat(7));
                p.setExistencia(rs.getInt(8));
                lista.add(p);
                
            }
            cn.cerrar_conexion();

        } catch (Exception e) {
        }
        return lista;
    }
    
   
    
}
