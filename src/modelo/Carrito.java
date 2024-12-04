

package modelo;

public class Carrito {
    private int id_carrito, id_usuario, id_producto, cantidad;

    public Carrito() {
    }

    public Carrito(int id_carrito, int id_usuario, int id_producto, int cantidad) {
        this.id_carrito = id_carrito;
        this.id_usuario = id_usuario;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public int getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
