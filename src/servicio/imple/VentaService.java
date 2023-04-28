package servicio.imple;

import entidad.Producto;
import entidad.Vendedor;
import entidad.Venta;
import exceptions.VentaException;
import repository.memoria.IProductoRepoMemo;
import repository.memoria.IVentaRepoMemo;
import servicio.interfaz.IVentaService;

import java.util.List;
import java.util.Optional;

public class VentaService implements IVentaService {

    private final IVentaRepoMemo repo;
    private final IProductoRepoMemo repoProducto;

    public VentaService(IVentaRepoMemo repo, IProductoRepoMemo repoProducto) {
        this.repo = repo;
        this.repoProducto = repoProducto;
    }

    @Override
    public Venta crear(Venta nueva) {
        if(nueva.getProductos()==null){
            throw new VentaException("Debe ingresar algun producto");
        }
        if(nueva.getVendedor()==null){
            throw new VentaException("Debe ingresar el vendedor a cargo");
        }
        this.modiStockRepo(nueva.getProductos());
        float comision=this.calcularComision(nueva);
        nueva.setComision(comision);
        Venta creada=this.repo.crear(nueva);
        this.repoProducto.addVenta(creada);
        return creada;
    }

    @Override
    public List<Venta> listarByVendedor(Vendedor vendedor) {
        if(vendedor.getCodigo()==null){
            throw new VentaException("El vendedor no contiene el codigo");
        }
        return this.repo.listarByVendedor(vendedor);
    }

    @Override
    public List<Venta> listarByProducto(Producto producto) {
        if(producto.getCodigo()==null){
            throw new VentaException("El producto no contiene el codigo");
        }
        return this.repo.listarByProducto(producto);
    }

    @Override
    public List<Venta> listar() {
        return this.repo.verTodos();
    }

    private float calcularComision(Venta nueva){
        float comision=0.05f;
        if(nueva.getProductos().size()>2){
            comision=0.1f;
        }
        return comision;
    }

    private void modiStockRepo(List<Producto> productos){
        for(Producto produ: productos){
            Optional<Producto> oProdu=this.repoProducto.buscarByCodigo(produ.getCodigo());
            if(oProdu.isEmpty()){
                throw new VentaException(
                        String.format(
                                "El producto de codigo $s no existe por lo que no puede venderse",
                                produ.getCodigo()
                        ));
            }
            if(oProdu.get().getCantidad()==0){
                throw new VentaException(
                        String.format(
                                "No existe stock en el producto $s por lo que no puede venderse",
                                produ.getCodigo()
                        ));
            }
            this.repoProducto.disminuirCantidad(produ.getCodigo());
        }
    }
}
