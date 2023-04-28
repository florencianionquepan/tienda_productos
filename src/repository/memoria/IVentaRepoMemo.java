package repository.memoria;

import entidad.Producto;
import entidad.Vendedor;
import entidad.Venta;

import java.util.List;

public interface IVentaRepoMemo {
    Venta crear(Venta nueva);
    List<Venta> listarByVendedor(Vendedor vendedor);
    List<Venta> listarByProducto(Producto producto);
    List<Venta> verTodos();
}
