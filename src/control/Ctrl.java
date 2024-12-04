package control;

import javax.swing.JTable;

public interface Ctrl {
    public abstract void registrar();
    public abstract void modificar();
    public abstract void eliminar();
    public abstract void buscar();
    public abstract void buscar(JTable tabla);
}
