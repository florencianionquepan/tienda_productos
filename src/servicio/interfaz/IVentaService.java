package servicio.interfaz;

import entidad.Producto;
import entidad.Vendedor;
import entidad.Venta;

import java.util.List;

public interface IVentaService {
    Venta crear(Venta nueva);
    List<Venta> listarByVendedor(Vendedor vendedor);
    List<Venta> listarByProducto(Producto producto);
}
