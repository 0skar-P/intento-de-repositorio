package Clases;

import java.time.LocalDate;

public class PedidoDelivery extends Pedido {
    private String direccionEntrega;
    private double distanciaKilometros;
    private Repartidor repartidor;
    private EstadoPedido estado;

    public PedidoDelivery(String codigo, LocalDate fecha, Cliente cliente,
            String direccionEntrega, double distanciaKilometros) {
        super(codigo, fecha, cliente);
        setDireccionEntrega(direccionEntrega);
        setDistanciaKilometros(distanciaKilometros);
        this.estado = EstadoPedido.PENDIENTE;
        getCliente().agregarPedidoInterno(this);
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        if (direccionEntrega == null || direccionEntrega.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección de entrega no puede estar vacía.");
        }
        this.direccionEntrega = direccionEntrega.trim();
    }

    public double getDistanciaKilometros() {
        return distanciaKilometros;
    }

    public void setDistanciaKilometros(double distanciaKilometros) {
        if (distanciaKilometros < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa.");
        }
        this.distanciaKilometros = distanciaKilometros;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void asignarRepartidor(Repartidor nuevoRepartidor) {
        if (nuevoRepartidor == null) {
            throw new IllegalArgumentException("El repartidor no puede ser nulo.");
        }
        if (estado != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("Solo se puede cambiar el repartidor mientras el pedido está pendiente.");
        }
        if (repartidor == nuevoRepartidor) {
            return;
        }

        if (repartidor != null) {
            repartidor.quitarPedidoInterno(this);
        }
        repartidor = nuevoRepartidor;
        repartidor.agregarPedidoInterno(this);
    }

    public void marcarEnCamino() {
        if (repartidor == null) {
            throw new IllegalStateException("Asigne un repartidor antes de marcar el pedido EN_CAMINO.");
        }
        if (estado != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("Solo un pedido PENDIENTE puede pasar a EN_CAMINO.");
        }
        estado = EstadoPedido.EN_CAMINO;
    }

    public void marcarEntregado() {
        if (estado != EstadoPedido.EN_CAMINO) {
            throw new IllegalStateException("Solo un pedido EN_CAMINO puede marcarse como ENTREGADO.");
        }
        estado = EstadoPedido.ENTREGADO;
    }

    public double calcularCostoEnvio() {
        if (calcularSubtotal() >= 100) {
            return 0;
        }
        return distanciaKilometros * 1.50;
    }

    @Override
    public double calcularTotal() {
        return calcularSubtotal() + calcularCostoEnvio();
    }
}
