

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaCategoria extends Conexion implements Consulta<Categoria> {
    @Override
    public boolean registrar(Categoria cat){
        
        String sql = "INSERT INTO categoria (nombre,descripcion) VALUES (?,?)";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setString(1, cat.getNombre());
            ps.setString(3, cat.getDescripcion());
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
        String sql = "INSERT INTO productos nombre=?,descripcion=? WHERE idCategoria=?";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setString(1, cat.getNombre());
            ps.setString(3, cat.getDescripcion());
            ps.setInt(8, cat.getIdCategoria());
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
            ps.setInt(8, cat.getIdCategoria());
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
        String sql="SELECT *FROM categoria WHERE nombre=?"; 
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cat.getNombre());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cat.setIdCategoria(rs.getInt("idCateogoria"));
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
}