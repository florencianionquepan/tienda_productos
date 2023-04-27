package servicio.imple;

import entidad.Producto;
import entidad.Vendedor;
import exceptions.ProductoException;
import exceptions.VendedorException;
import repository.memoria.IVendedorRepoMemo;
import servicio.interfaz.IVendedorService;

import java.util.List;
import java.util.Optional;

public class VendedorService implements IVendedorService {

    private final IVendedorRepoMemo repo;

    public VendedorService(IVendedorRepoMemo repo) {
        this.repo = repo;
    }

    @Override
    public Vendedor crear(Vendedor nuevo) {
        if(nuevo.getCodigo()==null){
            throw new VendedorException("El vendedor debe poseer un codigo para ser creado");
        }
        Optional<Vendedor> oVende=this.repo.buscarByCodigo(nuevo.getCodigo());
        if(oVende.isPresent()) {
            throw new VendedorException(
                    String.format("El vendedor con el codigo %s ya existe", nuevo.getCodigo()));
        }
        return this.repo.crear(nuevo);
    }

    @Override
    public List<Vendedor> listar() {
        return this.repo.verTodos();
    }

    @Override
    public Vendedor buscarByCodigo(String codigo) {
        Optional<Vendedor> oProd=this.repo.buscarByCodigo(codigo);
        if(oProd.isEmpty()){
            throw new VendedorException((String.format(
                    "El vendedor con el codigo %s no existe",codigo));
        }
        return oProd.get();
    }
}
