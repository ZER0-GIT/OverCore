

package control;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Carrito;
import modelo.ConsultaCarrito;
import modelo.Sesion;

public class CtrlCarrito{
    ConsultaCarrito consulta = new ConsultaCarrito();
    
    public void actualizarTabla(JTable tabla, JLabel lblTotal){
        List<Object[]> carrito = consulta.listar();
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : carrito) {
        modelo.addRow(fila);
        }
        
        double total = calcularTotal(modelo);
        lblTotal.setText("Total a pagar: S/ " + String.format("%.2f", total));
    }
    private double calcularTotal(DefaultTableModel modelo) {
        double total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            total += (double) modelo.getValueAt(i, 6);
        }
        return total;
    }
    public void eliminar(JTable tabla){
        try{
            Carrito carrito = new Carrito();
            carrito.setId_carrito((int) tabla.getValueAt(tabla.getSelectedRow(), 0));
            consulta.eliminar(carrito);
        }catch (ArrayIndexOutOfBoundsException exception){
            JOptionPane.showMessageDialog(null, "Por favor selecciona un producto para modificar la cantidad");
        }
    }
    
    public void modificar(JTable tabla){
        try{
            Carrito carrito = new Carrito();
            carrito.setId_carrito((int) tabla.getValueAt(tabla.getSelectedRow(), 0));
            carrito.setId_usuario(Sesion.getInstancia().getUsuario().getIdUsuario());
            carrito.setId_producto((int) tabla.getValueAt(tabla.getSelectedRow(), 1));
            carrito.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de productos que desea modificar")));
            consulta.modificar(carrito);
        }catch (ArrayIndexOutOfBoundsException exception){
            JOptionPane.showMessageDialog(null, "Por favor selecciona un producto para modificar la cantidad");
        }catch(NumberFormatException numExcept){
                        JOptionPane.showMessageDialog(null, "Por favor ingresa una cantidad valida","CANTIDAD INVALIDA",WARNING_MESSAGE);
        }
    }

}
