package Clases;

import java.util.ArrayList;

public class Categoria {
    private String nombre;
    private Categoria categoriaSuperior;
    private ArrayList<Categoria> subcategorias;
    private ArrayList<Producto> productos;

    public Categoria(String nombre) {
        setNombre(nombre);
        this.subcategorias = new ArrayList<Categoria>();
        this.productos = new ArrayList<Producto>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public Categoria getCategoriaSuperior() {
        return categoriaSuperior;
    }

    public ArrayList<Categoria> getSubcategorias() {
        return new ArrayList<Categoria>(subcategorias);
    }

    public ArrayList<Producto> getProductos() {
        return new ArrayList<Producto>(productos);
    }

    public void agregarSubcategoria(Categoria subcategoria) {
        if (subcategoria == null) {
            throw new IllegalArgumentException("La subcategoría no puede ser nula.");
        }

        // Si esta categoría aparece por encima de la candidata, se formaría un ciclo.
        Categoria actual = this;
        while (actual != null) {
            if (actual == subcategoria) {
                throw new IllegalArgumentException("La categoría no puede ser su propia superior ni formar un ciclo.");
            }
            actual = actual.categoriaSuperior;
        }

        if (subcategoria.categoriaSuperior == this) {
            return;
        }

        if (subcategoria.categoriaSuperior != null) {
            subcategoria.categoriaSuperior.quitarSubcategoriaInterna(subcategoria);
        }

        subcategoria.categoriaSuperior = this;
        if (!subcategorias.contains(subcategoria)) {
            subcategorias.add(subcategoria);
        }
    }

    private void quitarSubcategoriaInterna(Categoria subcategoria) {
        subcategorias.remove(subcategoria);
    }

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }

        if (producto.getCategoria() == this) {
            return;
        }

        if (producto.getCategoria() != null) {
            producto.getCategoria().quitarProductoInterno(producto);
        }

        producto.setCategoriaInterna(this);
        if (!productos.contains(producto)) {
            productos.add(producto);
        }
    }

    private void quitarProductoInterno(Producto producto) {
        productos.remove(producto);
    }

    public String obtenerRutaCompleta() {
        if (categoriaSuperior == null) {
            return nombre;
        }
        return categoriaSuperior.obtenerRutaCompleta() + " > " + nombre;
    }

    public int contarProductos() {
        int cantidad = productos.size();
        for (Categoria subcategoria : subcategorias) {
            cantidad = cantidad + subcategoria.contarProductos();
        }
        return cantidad;
    }
}
