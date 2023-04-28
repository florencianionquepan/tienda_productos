package entidad;

import java.util.List;
import java.util.stream.Collectors;

public class Producto {
    private Long id;
    private String codigo;
    private String nombre;
    private float precio;
    private int cantidad;
    private String categoria;
    private List<Venta> ventas;

    public Producto() {
    }

    public Producto(Long id, String codigo, String nombre, float precio,
                    String categoria, int cantidad, List<Venta> ventas) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.ventas = ventas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{id=").append(id);
        sb.append(", codigo='").append(codigo).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", precio=").append(precio);
        sb.append(", categoria='").append(categoria).append('\'');
        sb.append(", ventas=");
        if (ventas.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[");
            sb.append(ventas.stream().map(v -> String.valueOf(v.getId()))
                    .collect(Collectors.joining(", ")));
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }
}
