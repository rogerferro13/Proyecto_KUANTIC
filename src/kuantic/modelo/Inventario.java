 package kuantic.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregarproducto(Producto p) {
        productos.add(p);
    }

    public Optional<Producto> buscarporcodigo(String codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public void venderProducto(String codigo, int cantidad) {
        Optional<Producto> opt = buscarporcodigo(codigo);
        if (opt.isPresent()) {
            Producto p = opt.get();
            if (p.getCantidad() >= cantidad) {
                p.setCantidad(p.getCantidad() - cantidad);
            } else {
                throw new IllegalArgumentException("Stock insuficiente.");
            }
        } else {
            throw new IllegalArgumentException("Producto no encontrado.");
        }
    }

    public void sumarStock(String codigo, int cantidad) {
        Optional<Producto> opt = buscarporcodigo(codigo);
        if (opt.isPresent()) {
            Producto p = opt.get();
            p.setCantidad(p.getCantidad() + cantidad);
        } else {
            throw new IllegalArgumentException("Producto no encontrado.");
        }
    }
}
