package kuantic.controlador;

import kuantic.modelo.*;
import kuantic.colecciones.*;

public class Controladordelinventario {
    private Inventario inventario;
    private Piladeventas pilaVentas;
    private Colaparadevoluciones colaDevoluciones;
    private Generadordecodigo Generadordecodigo;

    public Controladordelinventario() {
        inventario = new Inventario();
        pilaVentas = new Piladeventas();
        colaDevoluciones = new Colaparadevoluciones();
        Generadordecodigo = new Generadordecodigo();
    }

    public String obtenerNuevoCodigo() {
        return Generadordecodigo.generarCodigo();
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void registrarProducto(Producto p) {
        inventario.agregarproducto(p);
    }

    public void venderProducto(String codigo, int cantidad) {
        inventario.venderProducto(codigo, cantidad);
        Movimientodelinventario mov = new Movimientodelinventario(codigo, cantidad, "VENTA");
        pilaVentas.registrarVenta(mov);
    }

    public void registrarDevolucion(String codigo, int cantidad) {
        Movimientodelinventario mov = new Movimientodelinventario(codigo, cantidad, "DEVOLUCION");
        colaDevoluciones.agregardevolucion(mov);
    }

    public void procesarDevolucion() {
        Movimientodelinventario mov = colaDevoluciones.procesarDevolucion();
        if (mov != null) {
            inventario.sumarStock(mov.getCodigoProducto(), mov.getCantidad());
        }
    }

    public Piladeventas getPilaVentas() {
        return pilaVentas;
    }

    public Colaparadevoluciones getColaDevoluciones() {
        return colaDevoluciones;
    }
}
