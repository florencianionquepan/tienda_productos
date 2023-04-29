package entidad;

import java.util.List;

public class Venta {

    private Long id;
    private Vendedor vendedor;
    private List<Producto> productos;
    private float comision;

    public Venta() {
    }

    public Venta(Long id,Vendedor vendedor, List<Producto> productos, float comision) {
        this.id=id;
        this.vendedor = vendedor;
        this.productos = productos;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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
                ", Vendedor=" + vendedor + "\n"+
                ",comision=" + comision*100 + "%\n"+
                ",Productos=" + productos +
                '}';
    }

}
