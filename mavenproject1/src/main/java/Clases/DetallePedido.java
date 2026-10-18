package Clases;

public class DetallePedido {
    private Pedido pedido;
    private Producto producto;
    private int cantidad;

    DetallePedido(Pedido pedido, Producto producto, int cantidad) {
        if (pedido == null) {
            throw new IllegalArgumentException("El detalle debe pertenecer a un pedido.");
        }
        if (producto == null) {
            throw new IllegalArgumentException("El producto del detalle no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}