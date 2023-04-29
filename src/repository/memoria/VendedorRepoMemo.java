package repository.memoria;

import entidad.Vendedor;

import java.util.List;
import java.util.Optional;

public class VendedorRepoMemo implements IVendedorRepoMemo{

    public final List<Vendedor> vendedores;

    public VendedorRepoMemo(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    @Override
    public Vendedor crear(Vendedor vendedor) {
        vendedor.setId((long) (this.vendedores.size()+1));
        this.vendedores.add(vendedor);
        return vendedor;
    }

    @Override
    public List<Vendedor> verTodos() {
        return this.vendedores;
    }

    @Override
    public Optional<Vendedor> buscarByCodigo(String codigo) {
        Optional<Vendedor> oVende=this.vendedores.stream()
                .filter(vende->vende.getCodigo().equalsIgnoreCase(codigo))
                .findAny();
        return oVende;
    }
}
