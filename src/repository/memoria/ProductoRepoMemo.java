package repository.memoria;

import entidad.Producto;
import entidad.Venta;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductoRepoMemo implements IProductoRepoMemo {

    public final List<Producto> productos;

    public ProductoRepoMemo(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public List<Producto> verTodos() {
        return this.productos;
    }

    @Override
    public Producto crear(Producto producto) {
        producto.setId((long) (productos.size() + 1));
        productos.add(producto);
        return producto;
    }

    @Override
    public Optional<Producto> buscarByCodigo(String codigo) {
        Optional<Producto> oProdu = this.productos.stream()
                .filter(prod -> prod.getCodigo().equalsIgnoreCase(codigo))
                .findAny();
        return oProdu;
    }

    @Override
    public List<Producto> buscarByNombre(String nombre) {
        return this.productos.stream()
                .filter(prod -> prod.getNombre().toLowerCase()
                        .contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> buscarByCategoria(String categoria) {
        return this.productos.stream()
                .filter(prod -> prod.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> buscarByRangoPrecio(float desde, float hasta) {
        return this.productos.stream()
                .filter(prod -> prod.getPrecio() >= desde
                        && prod.getPrecio() <= hasta)
                .collect(Collectors.toList());
    }

    @Override
    public void disminuirCantidad(String codigo) {
        Producto producto = this.productos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
        if (producto != null) {
            producto.setCantidad(producto.getCantidad() - 1);
            int index = this.productos.indexOf(producto);
            this.productos.set(index, producto);
        }

    }
}
