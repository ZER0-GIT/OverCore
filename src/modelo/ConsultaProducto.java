

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaProducto extends Conexion implements Consulta<Producto>{
    
    @Override
    public boolean registrar(Producto prodc){
        
        String sql = "INSERT INTO productos (nombre,id_categoria,marca,modelo,descripcion,precio,stock) VALUES (?,?,?,?,?,?,?)";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setString(1, prodc.getNombre());
            ps.setInt(2, prodc.getIdCategoria());
            ps.setString(3, prodc.getMarca());
            ps.setString(4, prodc.getModelo());
            ps.setString(5, prodc.getDescripcion());
            ps.setDouble(6, prodc.getPrecio());
            ps.setInt(7, prodc.getStock());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }              
    }
    
    @Override
    public boolean modificar(Producto prodc){
        String sql = "INSERT INTO productos nombre=?,id_categoria=?,marca=?,modelo=?,descripcion=?,precio=?,stock=? WHERE idProducto=?";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setString(1, prodc.getNombre());
            ps.setInt(2, prodc.getIdCategoria());
            ps.setString(3, prodc.getMarca());
            ps.setString(4, prodc.getModelo());
            ps.setString(5, prodc.getDescripcion());
            ps.setDouble(6, prodc.getPrecio());
            ps.setInt(7,prodc.getStock());
            ps.setInt(8, prodc.getIdProducto());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        } 
    }
    
    @Override
    public boolean eliminar(Producto prodc){
        String sql="DELETE FROM productos WHERE idProducto=?"; 
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setInt(8, prodc.getIdProducto());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        } 
    }
    
    @Override
    public boolean buscar(Producto prodc){
        String sql="SELECT *FROM productos WHERE nombre=?"; 
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, prodc.getNombre());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prodc.setIdProducto(rs.getInt("idProducto"));
                    prodc.setNombre(rs.getString("nombre"));
                    prodc.setIdCategoria(rs.getInt("id_categoria"));
                    prodc.setMarca(rs.getString("marca"));
                    prodc.setModelo(rs.getString("modelo"));
                    prodc.setDescripcion(rs.getString("descripcion"));
                    prodc.setPrecio(rs.getDouble("precio"));
                    prodc.setStock(rs.getInt("stock"));
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public List<Object[]> obtenerProductos() {
        List<Object[]> productos = new ArrayList<>();
        String sql = "SELECT idProducto, nombre, id_categoria, marca, modelo, descripcion, precio, stock FROM productos";
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("idProducto");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getInt("id_categoria");
                fila[3] = rs.getString("marca");
                fila[4] = rs.getString("modelo");
                fila[5] = rs.getString("descripcion");
                fila[6] = rs.getDouble("precio");
                fila[7] = rs.getInt("stock");
                productos.add(fila);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return productos;
    }
    
}
