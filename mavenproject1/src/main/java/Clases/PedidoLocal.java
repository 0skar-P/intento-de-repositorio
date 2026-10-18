package Clases;

import java.time.LocalDate;

public class PedidoLocal extends Pedido {
    private int numeroMesa;
    private double porcentajePropina;

    public PedidoLocal(String codigo, LocalDate fecha, Cliente cliente,
            int numeroMesa, double porcentajePropina) {
        super(codigo, fecha, cliente);
        if (numeroMesa <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor que cero.");
        }
        if (porcentajePropina < 0) {
            throw new IllegalArgumentException("El porcentaje de propina no puede ser negativo.");
        }
        this.numeroMesa = numeroMesa;
        this.porcentajePropina = porcentajePropina;
        getCliente().agregarPedidoInterno(this);
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public double getPorcentajePropina() {
        return porcentajePropina;
    }

    public double calcularPropina() {
        return calcularSubtotal() * porcentajePropina / 100;
    }

    @Override
    public double calcularTotal() {
        return calcularSubtotal() + calcularPropina();
    }
}
