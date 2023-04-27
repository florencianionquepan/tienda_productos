package repository.memoria;

import entidad.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoRepoMemo {
    List<Producto> verTodos();
    Producto crear(Producto producto);
    Optional<Producto> buscarByCodigo(String codigo);
    List<Producto> buscarByNombre(String nombre);
    List<Producto> buscarByCategoria(String categoria);
    List<Producto> buscarByRangoPrecio(float desde, float hasta);
}
