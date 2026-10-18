package Clases;

import java.util.ArrayList;

public class Cliente {
    private String codigo;
    private String nombre;
    private String telefono;
    private ArrayList<Pedido> pedidos;

    public Cliente(String codigo, String nombre, String telefono) {
        setCodigo(codigo);
        setNombre(nombre);
        setTelefono(telefono);
        this.pedidos = new ArrayList<Pedido>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del cliente no puede estar vacío.");
        }
        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono del cliente no puede estar vacío.");
        }
        this.telefono = telefono.trim();
    }

    public ArrayList<Pedido> getPedidos() {
        return new ArrayList<Pedido>(pedidos);
    }

    void agregarPedidoInterno(Pedido pedido) {
        if (!pedidos.contains(pedido)) {
            pedidos.add(pedido);
        }
    }

    public double calcularTotalGastado() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            total = total + pedido.calcularTotal();
        }
        return total;
    }
}
