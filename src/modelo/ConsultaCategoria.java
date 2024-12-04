

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaCategoria extends Conexion implements Consulta<Categoria> {
    @Override
    public boolean registrar(Categoria cat){
        
        String sql = "INSERT INTO categoria (nombre,descripcion) VALUES (?,?)";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setString(1, cat.getNombre());
            ps.setString(2, cat.getDescripcion());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }              
    }
    
    @Override
    public boolean modificar(Categoria cat){
        String sql = "UPDATE categoria SET nombre=?,descripcion=? WHERE idCategoria=?";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setString(1, cat.getNombre());
            ps.setString(2, cat.getDescripcion());
            ps.setInt(3, cat.getIdCategoria());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        } 
    }
    
    @Override
    public boolean eliminar(Categoria cat){
        String sql="DELETE FROM categoria WHERE idCategoria=?"; 
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setInt(1, cat.getIdCategoria());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        } 
    }
    
    @Override
    public boolean buscar(Categoria cat){
        String sql="SELECT *FROM categoria WHERE idCategoria=?"; 
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cat.getIdCategoria());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cat.setIdCategoria(rs.getInt("idCategoria"));
                    cat.setNombre(rs.getString("nombre"));
                    cat.setDescripcion(rs.getString("descripcion"));
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setNombre(rs.getString("nombre"));
                cat.setDescripcion(rs.getString("descripcion"));
                categorias.add(cat);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener categorías: " + e.getMessage());
        }

        return categorias;
    }
}
