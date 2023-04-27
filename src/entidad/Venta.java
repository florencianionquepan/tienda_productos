package entidad;

import java.util.List;

public class Venta {

    private Long id;
    private Vendedor vendedor;
    private List<Producto> producto;
    private float comision;

    public Venta() {
    }

    public Venta(Long id,Vendedor vendedor, List<Producto> producto, float comision) {
        this.id=id;
        this.vendedor = vendedor;
        this.producto = producto;
        this.comision=comision;
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
    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
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
