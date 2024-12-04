package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaCarrito extends Conexion implements Consulta<Carrito>{
    
    @Override
    public boolean registrar(Carrito carrito){
        
        String sql = "INSERT INTO carrito (id_usuario,id_producto,cantidad) VALUES (?,?,?)";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setInt(1, carrito.getId_usuario());
            ps.setInt(2, carrito.getId_producto());
            ps.setInt(3, carrito.getCantidad());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }              
    }
    
    @Override
    public boolean modificar(Carrito carrito){
        String sql = "UPDATE carrito SET id_usuario=?,id_producto=?,cantidad=? WHERE id_carrito=?";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setInt(1, carrito.getId_usuario());
            ps.setInt(2, carrito.getId_producto());
            ps.setInt(3, carrito.getCantidad());
            ps.setInt(4, carrito.getId_carrito());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        } 
    }
    
    @Override
    public boolean eliminar(Carrito carrito){
        String sql="DELETE FROM carrito WHERE id_carrito=?"; 
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setInt(1, carrito.getId_carrito());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        } 
    }
    
    @Override
    public boolean buscar(Carrito carrito){
        String sql="SELECT *FROM carrito WHERE id_carrito=?"; 
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, carrito.getId_carrito());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    carrito.setId_carrito(rs.getInt("id_carrito"));
                    carrito.setId_usuario(rs.getInt("id_usuario"));
                    carrito.setId_producto(rs.getInt("id_producto"));
                    carrito.setCantidad(rs.getInt("cantidad"));
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public List<Object[]> listar() {
        List<Object[]> productos = new ArrayList<>();
        String sql = """
            SELECT c.id_carrito, c.id_usuario, c.id_producto, c.cantidad, 
                   p.nombre, p.modelo, p.precio
            FROM carrito c
            JOIN productos p ON c.id_producto = p.idProducto
            WHERE c.id_usuario = ?;
            """;

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int idUsuarioSesion = Sesion.getInstancia().getUsuario().getIdUsuario();
            ps.setInt(1, idUsuarioSesion);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[7]; // Cambiamos el tamaño a 7
                    fila[0] = rs.getInt("id_carrito");        // ID del carrito
                    fila[1] = rs.getInt("id_producto");       // ID del producto
                    fila[2] = rs.getString("nombre");         // Nombre del producto
                    fila[3] = rs.getString("modelo");         // Modelo del producto
                    fila[4] = rs.getInt("cantidad");          // Cantidad
                    fila[5] = rs.getDouble("precio");         // Precio unitario
                    fila[6] = rs.getInt("cantidad") * rs.getDouble("precio"); // Precio total
                    productos.add(fila);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        return productos;
    }
}
