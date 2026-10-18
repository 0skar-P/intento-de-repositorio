package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Pedido {
    private String codigo;
    private LocalDate fecha;
    private Cliente cliente;
    private ArrayList<DetallePedido> detalles;

    public Pedido(String codigo, LocalDate fecha, Cliente cliente) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del pedido no puede estar vacío.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha del pedido no puede ser nula.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El pedido debe tener un cliente.");
        }

        this.codigo = codigo.trim();
        this.fecha = fecha;
        this.cliente = cliente;
        this.detalles = new ArrayList<DetallePedido>();

    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return new ArrayList<DetallePedido>(detalles);
    }

    public void agregarDetalle(Producto producto, int cantidad) {
        detalles.add(new DetallePedido(this, producto, cantidad));
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (DetallePedido detalle : detalles) {
            subtotal = subtotal + detalle.calcularSubtotal();
        }
        return subtotal;
    }

    public abstract double calcularTotal();
}
