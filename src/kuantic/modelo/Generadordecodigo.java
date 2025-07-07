package kuantic.modelo;

public class Generadordecodigo {
    private int contador;

    public Generadordecodigo() {
        this.contador = 1;
    }

    public String generarCodigo() {
        return String.format("%04d", contador++);
    }
}
