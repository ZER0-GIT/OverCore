

package modelo;

public class Usuario {
    private String usuario, contrasena, correo, nom_ap;
    private int idUsuario, telf, dni;
    private boolean staff;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasena, String correo, String nom_ap, int idUsuario, int telf, int dni, boolean staff) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.nom_ap = nom_ap;
        this.idUsuario = idUsuario;
        this.telf = telf;
        this.dni = dni;
        this.staff = staff;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNom_ap() {
        return nom_ap;
    }

    public void setNom_ap(String nom_ap) {
        this.nom_ap = nom_ap;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public boolean isStaff() {
        return staff;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }
    

}
