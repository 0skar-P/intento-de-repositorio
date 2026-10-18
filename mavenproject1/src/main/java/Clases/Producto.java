package Clases;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Producto(String codigo, String nombre, double precio, Categoria categoria) {
        setCodigo(codigo);
        setNombre(nombre);
        setPrecio(precio);
        this.categoria = null;
        if (categoria != null) {
            categoria.agregarProducto(this);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del producto no puede estar vacío.");
        }
        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    void setCategoriaInterna(Categoria categoria) {
        this.categoria = categoria;
    }
}