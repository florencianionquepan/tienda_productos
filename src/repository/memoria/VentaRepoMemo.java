package repository.memoria;

import entidad.Producto;
import entidad.Vendedor;
import entidad.Venta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VentaRepoMemo implements IVentaRepoMemo{

    public final List<Venta> ventas;

    public VentaRepoMemo(List<Venta> ventas) {
        this.ventas = ventas;
    }

    @Override
    public Venta crear(Venta nueva) {
        nueva.setId((long) (this.ventas.size()+1));
        List<Producto> productos = new ArrayList<Producto>(nueva.getProductos());
        nueva.setProductos(productos);
        ventas.add(nueva);
        return nueva;
    }

    @Override
    public List<Venta> listarByVendedor(Vendedor vendedor) {
        return this.ventas.stream()
                .filter(venta->venta.getVendedor().getCodigo().equals(vendedor.getCodigo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> listarByProducto(Producto producto) {
        return this.ventas.stream()
                .filter(venta->venta.getProductos().stream()
                        .anyMatch(p->p.getCodigo().equals(producto.getCodigo())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> verTodos() {
        return this.ventas;
    }
}
