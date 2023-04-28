package servicio.imple;

import entidad.Producto;
import entidad.Vendedor;
import entidad.Venta;
import exceptions.VentaException;
import repository.memoria.IVentaRepoMemo;
import servicio.interfaz.IVentaService;

import java.util.List;

public class VentaService implements IVentaService {

    private final IVentaRepoMemo repo;

    public VentaService(IVentaRepoMemo repo) {
        this.repo = repo;
    }

    @Override
    public Venta crear(Venta nueva) {
        if(nueva.getProductos()==null){
            throw new VentaException("Debe ingresar algun producto");
        }
        if(nueva.getVendedor()==null){
            throw new VentaException("Debe ingresar el vendedor a cargo");
        }
        float comision=this.calcularComision(nueva);
        return this.repo.crear(nueva);
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
}
