package kuantic.colecciones;

import kuantic.modelo.Movimientodelinventario;
import java.util.LinkedList;
import java.util.Queue;

public class Colaparadevoluciones {
    private Queue<Movimientodelinventario> devoluciones;

    public Colaparadevoluciones() {
        devoluciones = new LinkedList<>();
    }

    public void agregardevolucion(Movimientodelinventario m) {
        devoluciones.offer(m);
    }

    public Movimientodelinventario procesarDevolucion() {
        return devoluciones.poll();
    }

    public boolean estaVacia() {
        return devoluciones.isEmpty();
    }
}
