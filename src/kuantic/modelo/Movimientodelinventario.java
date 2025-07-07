/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kuantic.modelo;

import java.time.LocalDateTime;

public class Movimientodelinventario {
    private String codigoproducto;
    private int cantidad;
    private LocalDateTime fecha;
    private String tipo; 

    public Movimientodelinventario(String codigoProducto, int cantidad, String tipo) {
        this.codigoproducto = codigoproducto;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
    }

    public String getCodigoProducto() { return codigoproducto; }
    public int getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }
    public String getTipo() { return tipo; }
}
