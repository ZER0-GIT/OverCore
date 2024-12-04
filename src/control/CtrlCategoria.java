

package control;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.ConsultaCategoria;

public class CtrlCategoria implements Ctrl{
    
    ConsultaCategoria consulta = new ConsultaCategoria();
    Categoria cat = new Categoria();
    
    public void actualizarTabla(JTable tabla){
        List<Categoria> categorias = consulta.listar();
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        for (Categoria cat : categorias) {
            Object[] fila = new Object[3];
            fila[0] = cat.getIdCategoria();
            fila[1] = cat.getNombre();
            fila[2] = cat.getDescripcion();
            modelo.addRow(fila);
        }
    }
    
    @Override
    public void registrar(){
        cat.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la  Categoria"));
        cat.setDescripcion(JOptionPane.showInputDialog("Ingrese la descripción del Producto"));
        consulta.registrar(cat);
    }
    
    @Override
    public void modificar(){
        cat.setIdCategoria(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del Producto a modificar")));
        if(consulta.buscar(cat)){
            cat.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del Producto"));
            cat.setDescripcion(JOptionPane.showInputDialog("Ingrese la descripción del Producto"));
            consulta.modificar(cat);
        }
    }

    @Override
    public void eliminar(){
        cat.setIdCategoria(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del Producto a ELIMINAR")));
        consulta.eliminar(cat);
    }
   @Override
    public void buscar(){
        System.out.println("METODO SIN USADO O SIN IMPLIMENTAR");
    }
    
    @Override
    public void buscar(JTable tabla){
        String entrada = JOptionPane.showInputDialog( "Ingrese el ID de la categoria a buscar.");
        if (entrada == null || entrada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa un dato");
        } else {
            try {
                int busqueda = Integer.parseInt(entrada);
                tabla.clearSelection();
                boolean encontrado = false;

                for (int i = 0; i < tabla.getRowCount(); i++) {
                    if (tabla.getValueAt(i, 0).toString().equals(String.valueOf(busqueda))) {
                        tabla.requestFocus();
                        tabla.changeSelection(i, 0, false, false);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    JOptionPane.showMessageDialog(null, "La categoria con ID " + busqueda + " no fue encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingresa un número válido.");
            }
        }
    }

}
