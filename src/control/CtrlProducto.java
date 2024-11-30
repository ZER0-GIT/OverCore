

package control;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.ConsultaCategoria;
import modelo.ConsultaProducto;
import modelo.Producto;

public class CtrlProducto {
    ConsultaProducto consulta = new ConsultaProducto();
    Producto prodc = new Producto();
    
    public void actualizarTabla(JTable tabla){
        List<Object[]> productos = consulta.obtenerProductos();
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : productos) {
        modelo.addRow(fila);
        }
    }
    
    public void registrarControl(){
        prodc.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del Producto"));
        prodc.setIdCategoria(seleccionarCategoria());
        prodc.setMarca(JOptionPane.showInputDialog("Ingrese la marca del Producto"));
        prodc.setModelo(JOptionPane.showInputDialog("Ingrese el modelo del Producto"));
        prodc.setDescripcion(JOptionPane.showInputDialog("Ingrese la descripción del Producto"));
        prodc.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del Producto")));
        prodc.setStock(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Stock del Producto")));
        consulta.registrar(prodc);
    }
    
    public static int seleccionarCategoria() {
        ConsultaCategoria consulta = new ConsultaCategoria();
        List<Categoria> categorias = consulta.listaCategoria();
        String[] nombresCategorias = categorias.stream().map(Categoria::getNombre).toArray(String[]::new);
        String categoriaSeleccionada = (String) JOptionPane.showInputDialog(null,"Seleccione una categoría:","Categorías",JOptionPane.QUESTION_MESSAGE,null,nombresCategorias,nombresCategorias[0]
        );
        if (categoriaSeleccionada != null) {
            Categoria categoria = categorias.stream().filter(cat -> cat.getNombre().equals(categoriaSeleccionada)).findFirst().orElse(null);
            if (categoria != null) {
                return categoria.getIdCategoria();
            }
        }
        return -1;
    }
}
