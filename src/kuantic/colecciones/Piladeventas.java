package kuantic.colecciones;

import kuantic.modelo.Movimientodelinventario;
import java.util.Stack;

public class Piladeventas {
    private Stack<Movimientodelinventario> ventas;

    public Piladeventas() {
        ventas = new Stack<>();
    }

    public void registrarVenta(Movimientodelinventario m) {
        ventas.push(m);
    }

    public Movimientodelinventario ultimaVenta() {
        if (!ventas.isEmpty()) {
            return ventas.peek();
        } else {
            return null;
        }
    }

    public boolean estaVacia() {
        return ventas.isEmpty();
    }
}
