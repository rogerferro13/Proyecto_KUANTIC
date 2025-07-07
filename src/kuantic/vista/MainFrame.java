package kuantic.vista;

import kuantic.modelo.*;
import kuantic.controlador.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainFrame extends JFrame {

    private Controladordelinventario controlador;
    private JTextArea areaInventario;

    public MainFrame() {
        controlador = new Controladordelinventario();
        setTitle("Kuantic - Gestión de Inventario");
        setSize(1100, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon logo = new ImageIcon(getClass().getResource("/imagen/KUANTIC.png"));

        JLabel labelImagen = new JLabel(logo);

        add(labelImagen, BorderLayout.NORTH);

        JPanel panel = new JPanel(new BorderLayout());
       
        

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar el producto");
        JButton btnVender = new JButton("Vender el producto");
        JButton btnDevolver = new JButton("Registrar devolución");
        JButton btnProcesar = new JButton("Procesar devolución");
        JButton btnMostrar = new JButton("Mostrar Inventario");
        JButton btnSalir = new JButton("Salir");
        JButton btnExportarExcel = new JButton("Exportar a excel");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnVender);
        panelBotones.add(btnDevolver);
        panelBotones.add(btnProcesar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnSalir);
        panelBotones.add(btnExportarExcel);

        areaInventario = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaInventario);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        add(panel);

        btnAgregar.addActionListener(e -> agregarProducto());
        btnVender.addActionListener(e -> venderProducto());
        btnDevolver.addActionListener(e -> registrarDevolucion());
        btnProcesar.addActionListener(e -> procesarDevolucion());
        btnMostrar.addActionListener(e -> mostrarInventario());
        btnSalir.addActionListener(e -> System.exit(0));
        btnExportarExcel.addActionListener(e -> exportarInventarioCSV());

    }

    private void agregarProducto() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
        String[] categorias = {
            "LAPTOP", "AURICULARES", "CARGADOR", "POWER_BANK",
            "MOUSE", "TECLADO", "MONITOR", "PARLANTES",
            "USB", "WEBCAM", "SMARTWATCH", "TABLET", "MICROFONO"
        };
        String catStr = (String) JOptionPane.showInputDialog(this, "Categoría:", "Categoría",
                JOptionPane.PLAIN_MESSAGE, null, categorias, categorias[0]);
        Categoria categoria = Categoria.valueOf(catStr);
        int cantidad = pedirEntero("Cantidad:");
        double precio = pedirDouble("Precio:");

        // Revisar si ya existe un producto con el mismo nombre y categoría
        boolean encontrado = false;
        for (Producto p : controlador.getInventario().listarProductos()) {
            if (p.getNombre().equalsIgnoreCase(nombre) && p.getCategoria() == categoria) {
                p.setCantidad(p.getCantidad() + cantidad);
                JOptionPane.showMessageDialog(this, "el stock del producto a sido actualizado");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            String codigo = controlador.obtenerNuevoCodigo();
            Producto p = new Producto(codigo, nombre, categoria, cantidad, precio);
            controlador.registrarProducto(p);
            JOptionPane.showMessageDialog(this, " El producto a sido agregado con el codigo : " + codigo);
        }
    }

    private void venderProducto() {
        String codigo = JOptionPane.showInputDialog(this, "Código del producto:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Cantidad a vender:"));
        try {
            controlador.venderProducto(codigo, cantidad);
            JOptionPane.showMessageDialog(this, " La venta sido registrada ");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void registrarDevolucion() {
        String codigo = JOptionPane.showInputDialog(this, "Código del producto:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Cantidad devuelta:"));
        controlador.registrarDevolucion(codigo, cantidad);
        JOptionPane.showMessageDialog(this, "Devolución registrada.");
    }

    private void procesarDevolucion() {
        controlador.procesarDevolucion();
        JOptionPane.showMessageDialog(this, "Devolución procesada.");
    }

    private void mostrarInventario() {
        StringBuilder sb = new StringBuilder();
        for (Producto p : controlador.getInventario().listarProductos()) {
            sb.append(String.format("%s - %s - %s - Stock: %d - $%.2f\n",
                    p.getCodigo(), p.getNombre(), p.getCategoria(), p.getCantidad(), p.getPrecio()));
        }
        areaInventario.setText(sb.toString());
    }

    private void exportarInventarioCSV() {
        try (java.io.PrintWriter writer = new java.io.PrintWriter("inventario.csv")) {
            // Escribir encabezado
            writer.println("Codigo ; Nombre ; Categoria ; Cantidad ; Precio");
            // Escribir datos
            for (Producto p : controlador.getInventario().listarProductos()) {
                writer.printf("\"%s\";\"%s\";\"%s\";%d;%.2f%n",
                        "'" + p.getCodigo(),
                        p.getNombre(),
                        p.getCategoria().toString(),
                        p.getCantidad(),
                        p.getPrecio()
                );
            }
            JOptionPane.showMessageDialog(this, " El inventario ha sido exportado correctamente a inventario.csv");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar: " + e.getMessage());
        }
    }

    private int pedirEntero(String mensaje) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, mensaje);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.");
            }
        }
    }

    private double pedirDouble(String mensaje) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, mensaje);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.");
            }
        }
    }

}
