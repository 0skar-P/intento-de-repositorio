package Ejecucion;

import Clases.Categoria;
import Clases.Cliente;
import Clases.Pedido;
import Clases.PedidoDelivery;
import Clases.PedidoLocal;
import Clases.Producto;
import Clases.Repartidor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        LocalDate fechaPrueba = LocalDate.of(2026, 10, 14);

        // 1. Se crean las categorías y se conectan las subcategorías.
        Categoria comidas = new Categoria("Comidas");
        Categoria bebidas = new Categoria("Bebidas");
        Categoria platosFondo = new Categoria("Platos de fondo");
        Categoria entradas = new Categoria("Entradas");
        Categoria gaseosas = new Categoria("Gaseosas");
        Categoria jugos = new Categoria("Jugos");

        comidas.agregarSubcategoria(platosFondo);
        comidas.agregarSubcategoria(entradas);
        bebidas.agregarSubcategoria(gaseosas);
        bebidas.agregarSubcategoria(jugos);

        // 2. Cada producto se agrega a su categoría.
        Producto ceviche = new Producto("PR001", "Ceviche", 28, platosFondo);
        Producto lomoSaltado = new Producto("PR002", "Lomo saltado", 30, platosFondo);
        Producto papaHuancaina = new Producto("PR003", "Papa a la huancaína", 15, entradas);
        Producto incaKola = new Producto("PR004", "Inca Kola 500 ml", 5, gaseosas);
        Producto jugoMaracuya = new Producto("PR005", "Jugo de maracuyá", 8, jugos);

        // 3. Se crean los clientes y el repartidor.
        Cliente ana = new Cliente("C001", "Ana Torres", "No proporcionado");
        Cliente luis = new Cliente("C002", "Luis Ramos", "No proporcionado");
        Repartidor carlos = new Repartidor("R001", "Carlos Quispe", "1234-AB");

        // 4. Se registra el pedido para consumo en el local.
        PedidoLocal pedido1 = new PedidoLocal("P-001", fechaPrueba, ana, 5, 10);
        pedido1.agregarDetalle(lomoSaltado, 2);
        pedido1.agregarDetalle(incaKola, 2);

        // 5. Se registran los pedidos de delivery, se asignan y se completan.
        PedidoDelivery pedido2 = new PedidoDelivery(
                "P-002", fechaPrueba, luis, "Dirección de Luis", 4);
        pedido2.agregarDetalle(ceviche, 3);
        pedido2.agregarDetalle(papaHuancaina, 1);
        pedido2.agregarDetalle(jugoMaracuya, 3);
        pedido2.asignarRepartidor(carlos);
        pedido2.marcarEnCamino();
        pedido2.marcarEntregado();

        PedidoDelivery pedido3 = new PedidoDelivery(
                "P-003", fechaPrueba, ana, "Dirección de Ana", 6);
        pedido3.agregarDetalle(ceviche, 1);
        pedido3.agregarDetalle(lomoSaltado, 1);
        pedido3.agregarDetalle(incaKola, 1);
        pedido3.asignarRepartidor(carlos);
        pedido3.marcarEnCamino();
        pedido3.marcarEntregado();

        // Se usa el tipo base Pedido para calcular todos los totales.
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);
        pedidos.add(pedido3);

        System.out.println("SISTEMA DE PEDIDOS - SABORES DEL NORTE");
        System.out.println("--------------------------------------");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.getCodigo()
                    + " | " + pedido.getFecha() + " | " + pedido.getCliente().getNombre()
                    + " | Subtotal: " + dinero(pedido.calcularSubtotal())
                    + " | Total: " + dinero(pedido.calcularTotal()));
        }

        System.out.println();
        System.out.println("Historial de " + ana.getNombre() + ":");
        mostrarHistorial(ana);
        System.out.println("Total gastado por Ana Torres: " + dinero(ana.calcularTotalGastado()));

        System.out.println();
        System.out.println("Historial de " + luis.getNombre() + ":");
        mostrarHistorial(luis);
        System.out.println("Total gastado por Luis Ramos: " + dinero(luis.calcularTotalGastado()));

        double totalVentas = 0;
        for (Pedido pedido : pedidos) {
            totalVentas = totalVentas + pedido.calcularTotal();
        }
        System.out.println();
        System.out.println("Total de ventas del restaurante: " + dinero(totalVentas));
        System.out.println("Pago a Carlos Quispe: " + dinero(carlos.calcularPago()));

        System.out.println();
        System.out.println("Productos en Comidas (incluye subcategorías): " + comidas.contarProductos());
        System.out.println("Productos en Bebidas (incluye subcategorías): " + bebidas.contarProductos());
        System.out.println("Ruta de Gaseosas: " + gaseosas.obtenerRutaCompleta());
        System.out.println("Estado final de P-002: " + pedido2.getEstado());
        System.out.println("Estado final de P-003: " + pedido3.getEstado());
    }

    private static void mostrarHistorial(Cliente cliente) {
        for (Pedido pedido : cliente.getPedidos()) {
            System.out.println("  " + pedido.getCodigo() + " - " + dinero(pedido.calcularTotal()));
        }
    }

    private static String dinero(double monto) {
        return String.format(Locale.US, "S/ %.2f", monto);
    }
}