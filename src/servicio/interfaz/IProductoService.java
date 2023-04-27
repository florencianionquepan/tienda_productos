package servicio.interfaz;

import entidad.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> listar();
    Producto crear(Producto nuevo);
    Producto buscarByCodigo(String codigo);
    List<Producto> buscarByNombre(String nombre);
    List<Producto> buscarByRangoPrecio(float precioDesde, float precioHasta);
    List<Producto> buscarByCategoria(List<Producto> productos);
}
