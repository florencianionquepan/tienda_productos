package repository.memoria;

import entidad.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductoRepoMemo implements IProductoRepoMemo{

    public final List<Producto> productos;

    public ProductoRepoMemo(List<Producto> productos) {
        this.productos=productos;
    }

    @Override
    public List<Producto> verTodos() {
        return this.productos;
    }

    @Override
    public Producto crear(Producto producto) {
        producto.setId((long) (productos.size()+1));
        productos.add(producto);
        return producto;
    }

    @Override
    public Optional<Producto> buscarByCodigo(String codigo) {
        Optional<Producto> oProdu=this.productos.stream()
                .filter(prod->prod.getCodigo().equals(codigo))
                .findAny();
        return oProdu;
    }

    @Override
    public List<Producto> buscarByNombre(String nombre) {
        return this.productos.stream()
                .filter(prod->prod.getNombre().equals(nombre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> buscarByCategoria(String categoria) {
        return this.productos.stream()
                .filter(prod->prod.getCategoria().equals(categoria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> buscarByRangoPrecio(float desde, float hasta) {
        return this.productos.stream()
                .filter(prod->prod.getPrecio()>=desde
                && prod.getPrecio()<=hasta)
                .collect(Collectors.toList());
    }
}
