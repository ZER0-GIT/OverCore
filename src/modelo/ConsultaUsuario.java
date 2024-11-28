

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaUsuario extends Conexion implements  Consulta<Usuario>{

    public ConsultaUsuario() {
    }
    
    @Override
    public boolean registrar(Usuario user){
        
        String sql = "INSERT INTO usuario (usuario,contrasena,correo,telf,nom_ap,dni) VALUES (?,?,?,?,?,?)";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getContrasena());
            ps.setString(3, user.getCorreo());
            ps.setInt(4, user.getTelf());
            ps.setString(5, user.getNom_ap());
            ps.setInt(6, user.getDni());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }              
    }
    
    @Override
    public boolean modificar(Usuario user){
        String sql = "INSERT INTO usuario usuario=?,contrasena=?,correo=?,telf=?,nom_ap=?,dni=?,staff=? WHERE idusuario=?";
        
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getContrasena());
            ps.setString(3, user.getCorreo());
            ps.setInt(4, user.getTelf());
            ps.setString(5, user.getNom_ap());
            ps.setInt(6, user.getDni());
            ps.setBoolean(7,user.isStaff());
            ps.setInt(8, user.getIdUsuario());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        } 
    }
    
    @Override
    public boolean eliminar(Usuario user){
        String sql="DELETE FROM usuario WHERE idusuario=?"; 
        try(Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)){        
            ps.setInt(8, user.getIdUsuario());
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            System.err.println(e);
            return false;
        } 
    }
    
    @Override
    public boolean buscar(Usuario user){
        String sql="SELECT *FROM usuario WHERE usuario=?"; 
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getUsuario());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setIdUsuario(rs.getInt("idUsuario"));
                    user.setUsuario(rs.getString("usuario"));
                    user.setContrasena(rs.getString("contrasena"));
                    user.setCorreo(rs.getString("correo"));
                    user.setTelf(rs.getInt("telf"));
                    user.setNom_ap(rs.getString("nom_ap"));
                    user.setDni(rs.getInt("dni"));
                    user.setStaff(rs.getBoolean("staff"));
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
