package servicio.imple;

import entidad.Producto;
import exceptions.ProductoException;
import repository.memoria.IProductoRepoMemo;
import servicio.interfaz.IProductoService;

import java.util.List;
import java.util.Optional;

public class ProductoService implements IProductoService {

    private final IProductoRepoMemo repo;

    public ProductoService(IProductoRepoMemo repo) {
        this.repo = repo;
    }

    @Override
    public List<Producto> listar() {
        return this.repo.verTodos();
    }

    @Override
    public Producto crear(Producto nuevo) {
        if(nuevo.getCodigo()==null){
            throw new ProductoException("El producto no puede crearse sin un codigo.");
        }
        Optional<Producto> oProd=this.repo.buscarByCodigo(nuevo.getCodigo());
        if(oProd.isPresent()){
            throw new ProductoException("El producto con el codigo ingresado ya existe");
        }
        return this.repo.crear(nuevo);
    }

    @Override
    public Producto buscarByCodigo(String codigo) {
        Optional<Producto> oProd=this.repo.buscarByCodigo(codigo);
        if(oProd.isEmpty()){
            throw new ProductoException(String.format(
                    "El producto con el codigo %s no existe",codigo));
        }
        return oProd.get();
    }

    @Override
    public List<Producto> buscarByNombre(String nombre) {
        List<Producto> productos=this.repo.buscarByNombre(nombre);
        if(productos.size()==0){
            throw new ProductoException("No existen productos con el nombre ingresado");
        }
        return productos;
    }

    @Override
    public List<Producto> buscarByRangoPrecio(float precioDesde, float precioHasta) {
        List<Producto> productos=this.repo.buscarByRangoPrecio(precioDesde,precioHasta);
        if(productos.size()==0){
            throw new ProductoException("No existen productos con el rango de precio ingresado");
        }
        return productos;
    }

    @Override
    public List<Producto> buscarByCategoria(String cate) {
        List<Producto> productos=this.repo.buscarByCategoria(cate);
        if(productos.size()==0){
            throw new ProductoException("No existen productos con la categoria ingresada");
        }
        return productos;
    }
}
