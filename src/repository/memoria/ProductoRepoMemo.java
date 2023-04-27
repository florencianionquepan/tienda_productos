package repository.memoria;

import entidad.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductoRepoMemo implements IProductoRepoMemo{

    private List<Producto> productos;

    public ProductoRepoMemo(List<Producto> productos) {
        this.productos = new ArrayList<Producto>(
                Arrays.asList(
                        new Producto(1L, "P001", "Laptop HP", 15000.0f, "Electronica"),
                        new Producto(2L, "P002", "Smartphone Samsung", 8000.0f, "Electronica"),
                        new Producto(3L, "P003", "Televisor LG", 12000.0f, "Electronica"),
                        new Producto(4L, "P004", "Zapatillas Nike", 2000.0f, "Calzado"),
                        new Producto(5L, "P005", "Jeans Levi's", 1500.0f, "Ropa"),
                        new Producto(6L, "P006", "Reloj Casio", 1000.0f, "Ropa"),
                        new Producto(7L, "P007", "Botella de vino tinto", 500.0f, "Bebidas"),
                        new Producto(8L, "P008", "Caja de chocolates", 200.0f, "Dulces")
                )
        );
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
