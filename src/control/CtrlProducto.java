

package control;

import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.ConsultaCategoria;
import modelo.ConsultaProducto;
import modelo.Producto;
import modelo.ConsultaCarrito;
import modelo.Carrito;
import modelo.Sesion;

public class CtrlProducto implements Ctrl{
    ConsultaProducto consulta = new ConsultaProducto();
    Producto prodc = new Producto();
    ConsultaCarrito consultaCa = new ConsultaCarrito();
    Carrito carrito = new Carrito();
    
    public void actualizarTabla(JTable tabla){
        List<Object[]> productos = consulta.listar();
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : productos) {
        modelo.addRow(fila);
        }
    }
    
    public void registrar(){
        prodc.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del Producto"));
        prodc.setIdCategoria(seleccionarCategoria());
        prodc.setMarca(JOptionPane.showInputDialog("Ingrese la marca del Producto"));
        prodc.setModelo(JOptionPane.showInputDialog("Ingrese el modelo del Producto"));
        prodc.setDescripcion(JOptionPane.showInputDialog("Ingrese la descripción del Producto"));
        prodc.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del Producto")));
        prodc.setStock(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Stock del Producto")));
        consulta.registrar(prodc);
    }
    
    @Override
    public void modificar(){
        prodc.setIdProducto(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del Producto a modificar")));
        if(consulta.buscar(prodc)){
            prodc.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del Producto"));
            prodc.setIdCategoria(seleccionarCategoria());
            prodc.setMarca(JOptionPane.showInputDialog("Ingrese la marca del Producto"));
            prodc.setModelo(JOptionPane.showInputDialog("Ingrese el modelo del Producto"));
            prodc.setDescripcion(JOptionPane.showInputDialog("Ingrese la descripción del Producto"));
            prodc.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del Producto")));
            prodc.setStock(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Stock del Producto")));
            consulta.modificar(prodc);
        }
    }

    @Override
    public void eliminar(){
        prodc.setIdProducto(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del Producto a ELIMINAR")));
        consulta.eliminar(prodc);
    }
   @Override
    public void buscar(){
        System.out.println("METODO SIN USADO O SIN IMPLIMENTAR");
    }
    
    @Override
    public void buscar(JTable tabla){
        String entrada = JOptionPane.showInputDialog( "Ingrese el ID del Producto a buscar.");
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
                    JOptionPane.showMessageDialog(null, "El producto con ID " + busqueda + " no fue encontrado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingresa un número válido.");
            }
        }
    }
    
    public void carrito(JTable tabla){
        try{
            carrito.setId_usuario(Sesion.getInstancia().getUsuario().getIdUsuario());
            carrito.setId_producto((int) tabla.getValueAt(tabla.getSelectedRow(), 0));
            carrito.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de productos que desea añadir")));
            consultaCa.registrar(carrito);
        }catch (ArrayIndexOutOfBoundsException exception){
            JOptionPane.showMessageDialog(null, "Por favor selecciona un producto para añadir al carrito");
        }catch(NumberFormatException numExcept){
                        JOptionPane.showMessageDialog(null, "Por favor ingresa una cantidad valida","CANTIDAD INVALIDA",WARNING_MESSAGE);
        }
    }
    
    public static int seleccionarCategoria() {
        ConsultaCategoria consulta = new ConsultaCategoria();
        List<Categoria> categorias = consulta.listar();
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
