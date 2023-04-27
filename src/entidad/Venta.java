package entidad;

import java.util.List;

public class Venta {

    private static Long siguienteId = 1L;
    private Long id;
    private Vendedor vendedor;
    private List<Producto> producto;

    public Venta() {
    }

    public Venta(Vendedor vendedor, List<Producto> producto) {
        this.id = siguienteId;
        siguienteId++;
        this.vendedor = vendedor;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", vendedor=" + vendedor +
                ", producto=" + producto +
                '}';
    }
}
