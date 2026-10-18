package Clases;

import java.util.ArrayList;

public class Repartidor {
    private String codigo;
    private String nombre;
    private String placaMoto;
    private ArrayList<PedidoDelivery> pedidos;

    public Repartidor(String codigo, String nombre, String placaMoto) {
        setCodigo(codigo);
        setNombre(nombre);
        setPlacaMoto(placaMoto);
        this.pedidos = new ArrayList<PedidoDelivery>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del repartidor no puede estar vacío.");
        }
        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del repartidor no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getPlacaMoto() {
        return placaMoto;
    }

    public void setPlacaMoto(String placaMoto) {
        if (placaMoto == null || placaMoto.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa de moto no puede estar vacía.");
        }
        this.placaMoto = placaMoto.trim();
    }

    public ArrayList<PedidoDelivery> getPedidos() {
        return new ArrayList<PedidoDelivery>(pedidos);
    }

    void agregarPedidoInterno(PedidoDelivery pedido) {
        if (!pedidos.contains(pedido)) {
            pedidos.add(pedido);
        }
    }

    void quitarPedidoInterno(PedidoDelivery pedido) {
        pedidos.remove(pedido);
    }

    public double calcularPago() {
        double pago = 0;
        for (PedidoDelivery pedido : pedidos) {
            if (pedido.getEstado() == EstadoPedido.ENTREGADO) {
                pago = pago + pedido.getDistanciaKilometros();
            }
        }
        return pago;
    }
}