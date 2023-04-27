package repository.memoria;

import entidad.Vendedor;

import java.util.List;
import java.util.Optional;

public interface IVendedorRepoMemo {
    Vendedor crear(Vendedor vendedor);
    List<Vendedor> verTodos();
    Optional<Vendedor> buscarByCodigo(String codigo);
}
